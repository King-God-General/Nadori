<script setup>
import { ref, reactive, computed, watch, onMounted } from 'vue'
import '@vuepic/vue-datepicker/dist/main.css'
import contentCard from '@/components/contentCard.vue'
import { useUserPlanStore } from '@/stores/userPlan'
import { storeToRefs } from 'pinia'
import contentAPI from '@/apis/content'
import { defineEmits } from 'vue';

const emit = defineEmits(['clickAttractionCard']);

const userPlanStore = useUserPlanStore()
const { curPlan, curDayNum } = storeToRefs(userPlanStore)

onMounted()

const plan = reactive({
  planId: 1,
  memberId: 1,
  title: '제주도',
  description: '자바자바 MT 가보자고',
  startDate: '2024-06-01',
  endDate: '2024-06-04'
})

const curMemberId = 1;

const init = () => {
  contentAPI.getContents(
    plan.planId,
    (response) => {
      console.log('플랜에 대한 콘텐츠를 성공적으로 불러왔습니다. :' + JSON.stringify(response.data))
      curPlan.value = reverseTransformCurPlan(response.data)
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

watch(dayCnt, (newDayCnt) => {
  const newCurPlan = {}
  newDayCnt.forEach((day) => {
    newCurPlan[day] = { plan: [] }
  })
  curPlan.value = newCurPlan
})


const clickAttractionCard = (item) => {
    if (item.type=='attraction'){
        emit('clickAttractionCard', item.content);
    }
}

</script>

<template>
  <div class="page">
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
        <div class="contentsContainer overflow-auto">
          <contentCard
            v-for="item in curPlan[curDayNum].plan"
            :key="item.type == 'memo' ? item.type + item.content : item.type + item.content.attractionId"
            :item="item"
            @click="clickAttractionCard(item)"
          >
          </contentCard>
        </div>
      </div>

      <div class="buttonNavContainer">
        <button v-if="curMemberId==plan.memberId" class="Button" >수정하기</button>
        <button v-if="curMemberId==plan.memberId" class="Button" >삭제하기</button>
        <button v-if="curMemberId!=plan.memberId" class="Button" >복사하기</button>
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

.headerContainer {
  padding: 30px;
  border-radius: 0px 25px;
  background: linear-gradient(to top left, rgb(248, 223, 142), rgb(252, 166, 7));
  color: white;
}

.header{
  margin: 0;
}

.title {
  font-size: 1.6em;
  font-weight: bolder;
}

.description {
  font-size: 1.3em;
  font-weight: bolder;
  margin-bottom: 30px; 
}

.date {
  font-size: 1.1em;
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

.Button {
  background-color: rgb(247, 162, 0);
  color: rgba(255, 255, 255);
  font-size: 1.1em;
  font-weight: bolder;
  border-radius: 10px;
  padding: 10px;
  margin-top: 20px;
  margin-right: 5px;
}
</style>
