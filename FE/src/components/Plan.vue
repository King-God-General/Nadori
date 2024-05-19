<script setup>
import { ref, reactive, computed, watch } from "vue";
import '@vuepic/vue-datepicker/dist/main.css'
import VueDatePicker from '@vuepic/vue-datepicker';
import contentCard from "@/components/contentCard.vue";
import { VueToggles } from "vue-toggles";

import { useUserPlanStore } from "@/stores/userPlan";
import { storeToRefs } from "pinia";

import planAPI from "@/apis/plan";

const userPlanStore = useUserPlanStore()
const { curPlan, curDayNum } = storeToRefs(userPlanStore)

const step = ref([true, false, false])
const isRelease = ref(false);
const onMemoEditor = ref(false)

//로그인 기능 만든 다음에 수정해야 함!
const plan= reactive({planId: null, memberId:2, title:'', description:'', startDate:'', endDate:''})
const memoContent = ref('')

const dayCnt = computed(() => {
  const start = new Date(plan.startDate);
    const end = new Date(plan.endDate);
    const diffTime = Math.abs(end - start);
    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)) + 1;
    return Array.from({ length: diffDays }, (_, i) => i + 1);
});

const formatDate = (date) => {
  const year = date.getFullYear();
  const month = date.getMonth() + 1;
  const day = date.getDate();

  if (month || day < 10) {
    const zeroDay = ('00' + day).slice(-2);
    const zeroMonth = ('00' + month).slice(-2);
    return `${year}-${zeroMonth}-${zeroDay}`;
  } else {
    return `${year}-${month}-${day}`;
  }
}

const changeCurDayNum = (n) => {
  curDayNum.value = n;
}

const addMemo = () => {
  if (memoContent.value != '' || memoContent.value != '\n') {
    curPlan.value[curDayNum.value].plan.push({
      type: 'memo',
      content: memoContent.value
    })
    memoContent.value = '';
  }
}

const savePlan = () => {
  transformCurPlan();
  
}

const transformCurPlan = () => {
  const result = [];
  Object.keys(curPlan.value).forEach(dayNum => {
    curPlan.value[dayNum].plan.forEach((item, index) => {
      const transformedItem = {
        contentId: null,
        dayNum: parseInt(dayNum),
        contentNum: index + 1,
        memo: item.type === 'memo' ? item.content : null,
        attraction: item.type === 'attraction' ? item.content : null
      };
      result.push(transformedItem);
    });
  });
}

const checkDateInput = () => {
  if (plan.startDate && plan.endDate){
    plan.startDate=formatDate(plan.startDate);
    plan.endDate=formatDate(plan.endDate);
    step.value[0]=false;
    step.value[1]=true;
  }
}
const checkTextInput = () => {
  if (plan.title && plan.description) {
    planAPI.postPlan(
      plan,
      (response)=>{
        console.log("플랜 저장을 성공했습니다.: "+response.data);
        plan.planId=response.data;
        step.value[1]=false;
        step.value[2]=true;
      },
      ()=>{
        console.log("플랜 저장을 실패했습니다.");
      }
    )
  }
}

watch(dayCnt, (newDayCnt) => {
  const newCurPlan = {};
  newDayCnt.forEach((day) => {
    newCurPlan[day] = { plan: [] };
  });
  curPlan.value = newCurPlan;
});

</script>

<template>

  <div class="page">

    <div class="rowContainer">
      <div class="svg">
        <svg xmlns="http://www.w3.org/2000/svg" id="Layer_1" data-name="Layer 1" viewBox="0 0 24 24" width="80"
          height="80">
          <path
            d="m24,18c0,2.206-1.794,4-4,4h-5c-.553,0-1-.448-1-1s.447-1,1-1h5c1.103,0,2-.897,2-2s-.897-2-2-2h-7c-2.206,0-4-1.794-4-4s1.794-4,4-4h2c.553,0,1,.448,1,1s-.447,1-1,1h-2c-1.103,0-2,.897-2,2s.897,2,2,2h7c2.206,0,4,1.794,4,4Zm-12,2.5c0,.828-.672,1.5-1.5,1.5h-.525c.012.082.025.164.025.25,0,.966-.784,1.75-1.75,1.75s-1.75-.784-1.75-1.75c0-.086.013-.168.025-.25h-2.05c.012.082.025.164.025.25,0,.966-.784,1.75-1.75,1.75s-1.75-.784-1.75-1.75c0-.114.013-.224.033-.331-.599-.197-1.033-.754-1.033-1.419v-2.5c0-1.654,1.346-3,3-3h1.895c1.34,0,2.584.666,3.328,1.781l1.479,2.219h.798c.828,0,1.5.672,1.5,1.5Zm-10-1.5h5.298l-.74-1.109c-.372-.558-.994-.891-1.664-.891h-1.895c-.551,0-1,.449-1,1v1Zm17-9c.553,0,1-.448,1-1v-3.75l3.565-1.885c.581-.399.581-1.329,0-1.729l-2.813-1.259c-.556-.249-1.151-.377-1.751-.377-.553,0-1,.448-1,1v8c0,.552.447,1,1,1Z" />
        </svg>
      </div>
    </div>

    <div class="rowContainer" v-if="step[0]">

      <div class="contentContainer" id="forDate">

        <div class="qContainer">
          <p class="question">먼저, 여행을 떠나는 날짜를 알려주세요.</p>
        </div>

        <div class="inputContainer">
          <div class="singleDate">
            <VueDatePicker v-model="plan.startDate" locale="ko" :format="formatDate" :enable-time-picker="false"
              placeholder="여행을 떠나는 날" auto-apply />
          </div>
          <div class="singleDate">
            <VueDatePicker v-model="plan.endDate" locale="ko" :format="formatDate" :enable-time-picker="false"
              placeholder="집으로 돌아오는 날" auto-apply />
          </div>
        </div>

        <button class="stepButton btn" @click="checkDateInput">
          <svg xmlns="http://www.w3.org/2000/svg" id="Bold" viewBox="0 0 24 24" width="30" height="30">
            <path
              d="M17.061,9.525,13.475,5.939a1.5,1.5,0,0,0-2.121,2.122L13.793,10.5H5a1.5,1.5,0,0,0,0,3h8.793l-2.439,2.439a1.5,1.5,0,0,0,2.121,2.122l3.586-3.586A3.505,3.505,0,0,0,17.061,9.525Z" />
          </svg>
        </button>
      </div>

    </div>

    <div class="rowContainer" v-if="step[1]">

      <div class="contentContainer" id="forText">

        <div class="qContainer">
          <p class="question">다음으로, 제목과 설명을 입력해주세요.</p>
        </div>

        <div class="inputContainer">
          <div class="singleText">
            <input type="text" class="form-control" id="title" name="title" v-model="plan.title">
          </div>
          <div class="singleText">
            <textarea class="form-control editor" cols="5" v-model="plan.description"></textarea>
          </div>
        </div>

        <button class="stepButton btn" @click="checkTextInput">
          <svg xmlns="http://www.w3.org/2000/svg" id="Bold" viewBox="0 0 24 24" width="30" height="30">
            <path
              d="M17.061,9.525,13.475,5.939a1.5,1.5,0,0,0-2.121,2.122L13.793,10.5H5a1.5,1.5,0,0,0,0,3h8.793l-2.439,2.439a1.5,1.5,0,0,0,2.121,2.122l3.586-3.586A3.505,3.505,0,0,0,17.061,9.525Z" />
          </svg>
        </button>

      </div>

    </div>

    <div v-if="step[2]" class="rowContainer">

      <div class="dayNavContainer">
        <input class="dayButton" type="button" v-for="day in dayCnt" :key="day" :value="'Day ' + day"
          @click="changeCurDayNum(day)" :class="{ 'selectedDayButton': curDayNum === day }">
      </div>

      <div class="contentContainer">

        <div class="contentsContainer overflow-auto">
          <contentCard v-for="(item, index) in curPlan[curDayNum].plan" :key="curDayNum + '-' + index" :item="item">
          </contentCard>
        </div>

        <div class="memoContainer" @keyup.enter="addMemo">
          <button class="btn addMemoButton" @click="onMemoEditor = !onMemoEditor">
            <svg xmlns="http://www.w3.org/2000/svg" id="Layer_1" data-name="Layer 1" viewBox="0 0 24 24" width="24"
              height="24">
              <path
                d="m20.5,0H3.5C1.57,0,0,1.57,0,3.5v20.5h18.381l5.619-5.664V3.5c0-1.93-1.57-3.5-3.5-3.5ZM3,3.5c0-.275.224-.5.5-.5h17c.276,0,.5.225.5.5v12.5h-5v5H3V3.5Zm7.5,12.5v-2.5h-2.5v-3h2.5v-2.5h3v2.5h2.5v3h-2.5v2.5h-3Z" />
            </svg>
          </button>
          <textarea v-show="onMemoEditor" class="form-control editor" name="memo" id="memo" cols="auto" rows="5"
            v-model="memoContent"></textarea>
        </div>
      </div>

      <div class="buttonNavContainer">
        <div class="toggle">
          <div class="toggleTitle">다른 사람들과 공유하시겠어요?</div>
          <VueToggles :v-model="isRelease" :height="30" :width="70" checkedText="On" uncheckedText="Off" fontSize="18"
            checkedBg="#f05053" uncheckedBg="grey"></VueToggles>
        </div>
        <button class="saveButton" @click="savePlan">저장하기</button>
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
  background: linear-gradient(50deg, #e1eec3, #f05053);
}

.svg {
  fill: #f05053;
  margin-left: 15px;
}

.question {
  font-size: 1.5em;
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
  fill: #f05053;
  padding: 4px;
  border-radius: 10px;
  margin-top: 3px;
}

.dayNavContainer {
  margin: 20px 0px 10px 20px;
}

.dayButton {
  font-size: 1.1em;
  color: #f3d4d5;
  padding: 10px;
  font-weight: bolder;
}

.selectedDayButton {
  color: #f05053;
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
  align-items: center;

  height: 300px;
}

.memoContainer {
  display: flex;
  flex-direction: row;
}

.addMemoButton {
  background-color: white;
  border-radius: 10px;
  height: fit-content;
  fill: #f05053;
}

.editor {
  border: none;
  background-color: #ffffff;
  height: auto;
}

.buttonNavContainer {
  display: flex;
  flex-direction: column;
  margin: 20px;
  align-items: flex-end;
  float: right;
}

.saveButton {
  background-color: #f05053;
  color: rgba(255, 255, 255);
  font-size: 1.1em;
  font-weight: bolder;
  border-radius: 10px;
  padding: 10px;
  margin-top: 8px;
}

.toggleTitle {
  margin-top: 2px;
  margin-right: 10px;
}

.toggle {
  display: flex;
  margin-top: 5px;
  margin-bottom: 5px;
}
</style>
