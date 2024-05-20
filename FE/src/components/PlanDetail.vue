<script setup>
import { ref, reactive, computed, watch, onMounted, initCustomFormatter } from 'vue'
import '@vuepic/vue-datepicker/dist/main.css'
import contentCard from '@/components/contentCard.vue'
import { useUserPlanStore } from '@/stores/userPlan'
import { storeToRefs } from 'pinia'
import contentAPI from '@/apis/content'

const userPlanStore = useUserPlanStore()
const { curPlan, curDayNum } = storeToRefs(userPlanStore)

const plan = reactive({
  planId: 1,
  memberId: 1,
  title: '제주도',
  description: '자바자바 MT 가보자고',
  startDate: '2024-06-01',
  endDate: '2024-06-04'
})
const isRelease = ref(true)

const init = (id) => {
  contentAPI.getContents(
    plan.planId,
    (response) => {
      console.log('플랜에 대한 콘텐츠를 성공적으로 불러왔습니다. :' + JSON.stringify(response.data))
      curPlan.value = reverseTransformCurPlan(response.data)
      console.log('불러온 데이터 :' + JSON.stringify(curPlan.value))
      curDayNum.value = 1
    },
    () => {
      console.log('플랜에 대한 콘텐츠를 불러오는 데 실패했습니다.')
    }
  )
}

const dayCnt = computed(() => {
  const start = new Date(plan.startDate)
  const end = new Date(plan.endDate)
  const diffTime = Math.abs(end - start)
  const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)) + 1
  return Array.from({ length: diffDays }, (_, i) => i + 1)
})
const changeCurDayNum = (n) => {
  curDayNum.value = n
}

const reverseTransformCurPlan = (transformedPlan) => {
  const result = {}

  transformedPlan.forEach((item) => {
    const { dayNum, memo, attraction } = item
    const content = memo
      ? { type: 'memo', content: memo }
      : { type: 'attraction', content: attraction }

    if (!result[dayNum]) {
      result[dayNum] = { plan: [] }
    }

    result[dayNum].plan.push(content)
  })

  return result
}

init()

watch(dayCnt, (newDayCnt) => {
  const newCurPlan = {}
  newDayCnt.forEach((day) => {
    newCurPlan[day] = { plan: [] }
  })
  curPlan.value = newCurPlan
})
</script>

<template>
  <div class="page">
    <div class="rowContainer">
      <div class="planContainer">
        <p>{{ plan.title }}</p>
        <p>{{ plan.description }}</p>
        <p>{{ plan.startDate }} ~ {{ plan.endDate }}</p>
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
        <div class="contentsContainer overflow-auto">
          <contentCard
            v-for="item in curPlan[curDayNum].plan"
            :key="
              item.type == 'memo' ? item.type + item.content : item.type + item.content.attractionId
            "
            :item="item"
          >
          </contentCard>
        </div>
      </div>

      <div class="buttonNavContainer">
        <button class="saveButton" @click="">수정하기</button>
        <button class="saveButton" @click="">삭제하기</button>
        <button class="saveButton" @click="">복사하기</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.page {
  display: flex;
  flex-direction: column;
  margin: 20px;
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

.buttonNavContainer {
  display: flex;
  flex-direction: row;
}

.saveButton {
  background-color: rgb(247, 162, 0);
  color: rgba(255, 255, 255);
  font-size: 1.1em;
  font-weight: bolder;
  border-radius: 10px;
  padding: 10px;
  margin-top: 8px;
}
</style>
