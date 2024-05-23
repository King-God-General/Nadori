<script setup>
import { ref, computed, watch, onMounted } from 'vue'
import '@vuepic/vue-datepicker/dist/main.css'
import VueDatePicker from '@vuepic/vue-datepicker'
import contentCard from '@/components/contentCard.vue'
import { useNadoriStore } from '@/stores/nadori'
import { storeToRefs } from 'pinia'
import planAPI from '@/apis/plan'
import contentAPI from '@/apis/content'
import { useRouter } from 'vue-router'

const nadoriStore = useNadoriStore()
const { plan, planDetail, curDayNum, member } = storeToRefs(nadoriStore)

const step = ref([true, false, false])
const onMemoEditor = ref(false)
const router = useRouter()

const memoContent = ref('')

const dayCnt = computed(() => {
  const start = new Date(plan.value.startDate)
  const end = new Date(plan.value.endDate)
  const diffTime = Math.abs(end - start)
  const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)) + 1
  return Array.from({ length: diffDays }, (_, i) => i + 1)
})

const formatDate = (date) => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  if (month < 10 || day < 10) {
    const zeroDay = ('00' + day).slice(-2)
    const zeroMonth = ('00' + month).slice(-2)
    return `${year}-${zeroMonth}-${zeroDay}`
  } else {
    return `${year}-${month}-${day}`
  }
}

const changeCurDayNum = (n) => {
  curDayNum.value = n
}

const addMemo = () => {
  if (memoContent.value.trim() !== '') {
    planDetail.value[curDayNum.value].plan.push({
      type: 'memo',
      content: memoContent.value
    })
    memoContent.value = ''
  }
}

const transformPlanDetail = () => {
  const result = []
  Object.keys(planDetail.value).forEach((dayNum) => {
    planDetail.value[dayNum].plan.forEach((item, index) => {
      const transformedItem = {
        contentId: null,
        dayNum: parseInt(dayNum),
        contentNum: index + 1,
        memo: item.type === 'memo' ? item.content : null,
        attraction: item.type === 'attraction' ? item.content : null
      }
      result.push(transformedItem)
    })
  })
  return result
}

const checkDateInput = () => {
  if (plan.value.startDate && plan.value.endDate) {
    plan.value.startDate = formatDate(plan.value.startDate)
    plan.value.endDate = formatDate(plan.value.endDate)
  }
}

const checkTextInput = () => {
  if (plan.title && plan.description) {
    console.log(plan.title + ' : ' + plan.description)
    planAPI.postPlan(
      plan,
      (response) => {
        console.log('플랜 저장에 성공했습니다.: ' + response.data)
        plan.planId = response.data
        step.value[1] = false
        step.value[2] = true
      },
      () => {
        console.log('플랜 저장에 실패했습니다.')
      }
    )
  }
}

const modifyPlan = () => {
  console.log('JSON.stringify(plan.value)')
  console.log(JSON.stringify(plan.value))

  planAPI.putPlan(
    plan.value,
    () => {
      console.log('플랜 수정 성공')
    },
    () => {
      console.log('플랜 등록 실패')
    }
  )

  contentAPI.deleteContents(
    plan.value.planId,
    () => {
      console.log('이전 플랜 콘텐츠 삭제 성공')
    },
    () => {
      console.log('이전 플랜 콘텐츠 삭제 실패')
    }
  )

  contentAPI.postContents(
    plan.value.planId,
    transformPlanDetail(),
    () => {
      console.log('현재 플랜에 대한 콘텐츠 저장에 성공했습니다.: ' + transformPlanDetail())
      router.push(`/plan/detail/${plan.value.planId}`)
    },
    () => {
      console.log('현재 플랜에 대한 콘텐츠 저장에 실패했습니다.')
    }
  )
}

watch(dayCnt, (newDayCnt) => {
  const newPlanDetail = {}
  newDayCnt.forEach((day) => {
    newPlanDetail[day] = { plan: [] }
  })
  planDetail.value = newPlanDetail
})

function handleClose(item) {
  console.log(planDetail.value[curDayNum.value].plan)
  planDetail.value[curDayNum.value].plan = planDetail.value[curDayNum.value].plan.filter(
    (c) => c !== item
  )
  console.log(planDetail.value[curDayNum.value].plan)
}

// onMounted(() => {
//   console.log('pinia: plan의 값은')
//   console.log(JSON.stringify(plan.value))
//   console.log('pinia: planDetail의 값은')
//   console.log(JSON.stringify(planDetail.value))
// })
</script>

<template>
  <div class="page">
    <div class="rowContainer">
      <div class="contentContainer" id="forDate">
        <div class="qContainer">
          <p class="question">먼저, 여행을 떠나는 날짜를 알려주세요.</p>
        </div>

        <div class="inputContainer">
          <div class="singleDate">
            <VueDatePicker
              v-model="plan.startDate"
              locale="ko"
              :format="formatDate"
              :enable-time-picker="false"
              placeholder="여행을 떠나는 날"
              auto-apply
            />
          </div>
          <div class="singleDate">
            <VueDatePicker
              v-model="plan.endDate"
              locale="ko"
              :format="formatDate"
              :enable-time-picker="false"
              placeholder="집으로 돌아오는 날"
              auto-apply
            />
          </div>
        </div>

        <button class="stepButton btn" @click="checkDateInput">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            id="Bold"
            viewBox="0 0 24 24"
            width="30"
            height="30"
          >
            <path
              d="M17.061,9.525,13.475,5.939a1.5,1.5,0,0,0-2.121,2.122L13.793,10.5H5a1.5,1.5,0,0,0,0,3h8.793l-2.439,2.439a1.5,1.5,0,0,0,2.121,2.122l3.586-3.586A3.505,3.505,0,0,0,17.061,9.525Z"
            />
          </svg>
        </button>
      </div>
    </div>

    <div class="rowContainer">
      <div class="contentContainer" id="forText">
        <div class="qContainer">
          <p class="question">다음으로, 제목과 설명을 입력해주세요.</p>
        </div>

        <div class="inputContainer">
          <div class="singleText">
            <input type="text" class="form-control" id="title" name="title" v-model="plan.title" />
          </div>
          <div class="singleText">
            <textarea class="form-control editor" cols="5" v-model="plan.description"></textarea>
          </div>
        </div>

        <button class="stepButton button" @click="checkTextInput">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            id="Bold"
            viewBox="0 0 24 24"
            width="30"
            height="30"
          >
            <path
              d="M17.061,9.525,13.475,5.939a1.5,1.5,0,0,0-2.121,2.122L13.793,10.5H5a1.5,1.5,0,0,0,0,3h8.793l-2.439,2.439a1.5,1.5,0,0,0,2.121,2.122l3.586-3.586A3.505,3.505,0,0,0,17.061,9.525Z"
            />
          </svg>
        </button>
      </div>
    </div>

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
          :class="{ selectedDayButton: curDayNum === day }"
        />
      </div>

      <div class="contentContainer">
        <div v-if="planDetail[curDayNum].plan != {}" class="contentsContainer overflow-auto">
          <contentCard
            v-for="item in planDetail[curDayNum].plan"
            :key="
              item.type == 'memo' ? item.type + item.content : item.type + item.content.attractionId
            "
            :item="item"
            @close="handleClose(item)"
          >
          </contentCard>
        </div>

        <div class="memoContainer" @keyup.enter="addMemo">
          <button class="btn addMemoButton" @click="onMemoEditor = !onMemoEditor">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              id="Layer_1"
              data-name="Layer 1"
              viewBox="0 0 24 24"
              width="24"
              height="24"
            >
              <path
                d="m20.5,0H3.5C1.57,0,0,1.57,0,3.5v20.5h18.381l5.619-5.664V3.5c0-1.93-1.57-3.5-3.5-3.5ZM3,3.5c0-.275.224-.5.5-.5h17c.276,0,.5.225.5.5v12.5h-5v5H3V3.5Zm7.5,12.5v-2.5h-2.5v-3h2.5v-2.5h3v2.5h2.5v3h-2.5v2.5h-3Z"
              />
            </svg>
          </button>
          <textarea
            v-show="onMemoEditor"
            class="form-control editor"
            name="memo"
            id="memo"
            cols="auto"
            rows="5"
            v-model="memoContent"
          ></textarea>
        </div>
      </div>

      <div class="buttonNavContainer">
        <button class="saveButton" @click="modifyPlan">저장하기</button>
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

.svg {
  fill: rgb(247, 162, 0);
  margin-left: 15px;
}

.question {
  font-size: 1.1em;
  font-weight: bolder;
  color: white;
}

.singleDate {
  margin-bottom: 5px;
}

.singleText {
  margin-bottom: 5px;
}

.stepButton {
  background-color: white;
  fill: rgb(247, 162, 0);
  padding: 4px;
  border-radius: 10px;
  margin-top: 8px;
}

.dayNavContainer {
  margin: 20px 0px 10px 20px;
}

.dayButton {
  font-size: 1.1em;
  color: rgb(248, 228, 192);
  padding: 10px;
  font-weight: bolder;
}

.selectedDayButton {
  color: rgb(247, 162, 0);
}

.sectionContainer {
  background: linear-gradient(50deg, #e1eec3, #f05053);
  padding: 40px;
  border-radius: 0px 15px 15px 15px;
  display: flex;
  flex-direction: column;
}

.contentsContainer {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
}

.memoContainer {
  display: flex;
  flex-direction: row;
}

.addMemoButton {
  background-color: white;
  border-radius: 10px;
  height: fit-content;
  fill: rgb(247, 162, 0);
}

.editor {
  border: none;
  background-color: #ffffff;
  height: auto;
}

.buttonNavContainer {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  margin-top: 5px;
}

.saveButton {
  background-color: rgb(247, 162, 0);
  color: rgba(255, 255, 255);
  font-size: 0.9em;
  font-weight: bolder;
  border-radius: 10px;
  padding: 8px;
  margin-top: 10px;
  margin-right: 5px;
}

.toggleTitle {
  font-size: 0.9em;
  margin-top: 2px;
  margin-right: 10px;
}

.toggle {
  display: flex;
  margin-top: 5px;
  margin-bottom: 5px;
}
</style>
