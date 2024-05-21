<script setup>
import { ref, reactive, computed, watch, onMounted } from 'vue'
import '@vuepic/vue-datepicker/dist/main.css'
import contentCard from '@/components/contentCard.vue'
import { useNadoriStore } from '@/stores/nadori'
import { storeToRefs } from 'pinia'
import contentAPI from '@/apis/content'
import planAPI from '@/apis/plan'
import { defineEmits, defineProps } from 'vue'
import Swal from 'sweetalert2'
import { useRouter } from 'vue-router'

const emit = defineEmits(['clickDayNum'])
const props = defineProps(['planId'])
const router = useRouter()

const store = useNadoriStore()
const { member, planDetail, curDayNum } = storeToRefs(store)
const plan = ref(null)
const dayCnt = ref([])
const curDayNumPlan = ref(null)

const makeDayArray = () => {
  if (!plan.value) return []
  const start = new Date(plan.value.startDate)
  const end = new Date(plan.value.endDate)
  const diffTime = Math.abs(end - start)
  const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)) + 1
  return Array.from({ length: diffDays }, (_, i) => i + 1)
}
const changeCurDayNum = (n) => {
  curDayNum.value = n
  curDayNumPlan.value = planDetail.value[n].plan.filter((item) => item.type === 'attraction')
  console.log('curDayNum이 변경되었습니다.: ' + JSON.stringify(curDayNumPlan.value))
  emit('clickDayNum', curDayNumPlan.value)
}

const reverseTransformPlanDetail = (json) => {
  const result = {}
  dayCnt.value.forEach((n) => {
    result[n] = { plan: [] }
  })

  json.forEach((item) => {
    const { dayNum, contentNum, memo, attraction } = item
    if (memo) {
      result[dayNum].plan.push({ type: 'memo', content: memo })
    } else if (attraction) {
      result[dayNum].plan.push({ type: 'attraction', content: attraction })
    }
  })

  return result
}

const removePlan = () => {
  Swal.fire({
    text: '한번 삭제한 플랜은 되돌릴 수 없어요!',
    icon: 'warning',
    iconColor: '#d33',
    showCancelButton: true,
    confirmButtonColor: '#f7a200',
    cancelButtonColor: '#f7a200',
    confirmButtonText: '그래도, 삭제할래요!',
    cancelButtonText: '좀 더 생각해볼게요.'
  }).then((result) => {
    if (result.isConfirmed) {
      planAPI.deletePlan(
        props.planId,
        () => {
          Swal.fire({
            icon: 'success',
            confirmButtonColor: '#f7a200',
            text: '성공적으로 삭제되었어요!'
          })
        },
        () => {
          console.log('플랜 삭제 실패')
        }
      )
      router.push(`/home`)
    }
  })
}

onMounted(() => {
  planAPI.getPlan(
    props.planId,
    (response) => {
      plan.value = response.data
      dayCnt.value = makeDayArray()
      console.log('plan의 값: ' + JSON.stringify(plan.value))
      console.log('dayCnt의 값: ' + dayCnt.value)
    },
    () => {
      console.log('플랜 데이터를 불러오는 데 실패했습니다.')
    }
  )

  contentAPI.getContents(
    props.planId,
    (response) => {
      console.log('전처리 전: ' + JSON.stringify(response.data))
      planDetail.value = reverseTransformPlanDetail(response.data)
      curDayNum.value = 1
      curDayNumPlan.value = planDetail.value[1].plan.filter((item) => item.type === 'attraction')
      console.log('planDetail의 값: ' + JSON.stringify(planDetail.value))
      console.log('curDayNum의 값: ' + curDayNum.value)
      console.log('curDayNum이 변경되었습니다.: ' + JSON.stringify(curDayNumPlan.value))
      emit('clickDayNum', curDayNumPlan)
    },
    () => {
      console.log('플랜에 대한 콘텐츠를 불러오는 데 실패했습니다.')
    }
  )
})
</script>

<template>
  <div></div>

  <div v-if="plan != null" class="page">
    <div class="rowContainer">
      <div class="headerContainer">
        <p class="title header">{{ plan.title }}</p>
        <p class="description header">: {{ plan.description }}</p>
        <p class="date header">{{ plan.startDate }} ~ {{ plan.endDate }}</p>
      </div>

      <div class="dayNavContainer">
        <input
          class="dayButton"
          type="button"
          v-for="day in dayCnt"
          :key="day"
          :value="'Day ' + day"
          @click="changeCurDayNum(day)"
          :class="{ selectedDayButton: curDayNum.toString() === day.toString() }"
        />
      </div>

      <div class="contentContainer">
        <div class="contentsContainer overflow-auto">
          <contentCard
            v-for="item in planDetail[curDayNum].plan"
            :key="
              item.type == 'memo' ? item.type + item.content : item.type + item.content.attractionId
            "
            :item="item"
            @click="clickAttractionCard(item)"
          >
          </contentCard>
        </div>
      </div>

      <div class="buttonNavContainer">
        <button v-if="member.memberId == plan.memberId" class="Button">수정하기</button>
        <button v-if="member.memberId == plan.memberId" class="Button" @click="removePlan">
          삭제하기
        </button>

        <button v-if="member.memberId != plan.memberId" class="Button">복사하기</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.page {
  display: flex;
  flex-direction: column;
  margin: 10px;
}

.headerContainer {
  padding: 20px;
  border-radius: 0px 25px;
  background: linear-gradient(to top left, rgb(248, 223, 142), rgb(252, 166, 7));
  color: white;
}

.header {
  margin: 0;
}

.title {
  font-size: 1.2em;
  font-weight: bolder;
}

.description {
  font-size: 1em;
  font-weight: bolder;
  margin-bottom: 30px;
}

.date {
  font-size: 0.9em;
}

.rowContainer {
  margin-bottom: 10px;
}

.contentContainer {
  padding: 30px;
  border-radius: 25px 0px;
  height: auto;
  background: linear-gradient(50deg, rgb(248, 224, 145), rgb(247, 162, 0));
}

.dayNavContainer {
  margin: 20px 0px 10px 20px;
}

.dayButton {
  font-size: 0.9em;
  color: rgb(248, 228, 192);
  padding: 0px 10px;
  font-weight: bolder;
}

.selectedDayButton {
  color: rgb(247, 162, 0);
}

.contentsContainer {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
}

.buttonNavContainer {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  margin-top: 5px;
}

.Button {
  background-color: rgb(247, 162, 0);
  color: rgba(255, 255, 255);
  font-size: 0.9em;
  font-weight: bolder;
  border-radius: 10px;
  padding: 8px;
  margin-top: 10px;
  margin-right: 5px;
}
</style>
