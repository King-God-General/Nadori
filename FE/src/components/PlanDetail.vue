<script setup>
import { ref } from "vue";
import contentCard from "@/components/contentCard.vue";
import { VueToggles } from "vue-toggles";

import { useUserPlanStore } from "@/stores/userPlan";
import { storeToRefs } from "pinia";
const userPlanStore = useUserPlanStore() 
const { curPlan, curDayNum } = storeToRefs(userPlanStore)

const dayCnt = ref([1, 2, 3]);
const isRelease = ref(false);
const onMemoEditor = ref(false)
const memoContent = ref('')

const changeCurDayNum = (n) => {
    curDayNum.value = n;
    console.log(curDayNum.value);
}

const addMemo = () => {
    if (memoContent.value!='' || memoContent.value!='\n'){
        curPlan.value[curDayNum.value].plan.push({
        type: 'memo',
        content: memoContent.value
    })
    
    memoContent.value='';
}
}
</script>

<template>

    <div class="page">

        <div class="dayNavContainer">
            <input class="dayButton" type="button" v-for="day in dayCnt" :key="day" :value="'Day ' + day"
                @click="changeCurDayNum(day)" :class="{ 'selectedDayButton': curDayNum === day }">
        </div>

        <div class="sectionContainer">

            <div class="contentsContainer">
                <contentCard v-for="(item, index) in curPlan[curDayNum].plan" :key="curDayNum+'-'+index" :item="item"></contentCard>
            </div>

            <div  class="memoContainer" @keyup.enter="addMemo">
                <button class="btn addMemoButton" @click="onMemoEditor = !onMemoEditor">
                    <svg xmlns="http://www.w3.org/2000/svg" id="Layer_1" data-name="Layer 1" viewBox="0 0 24 24" width="24" height="24">
                        <path d="m20.5,0H3.5C1.57,0,0,1.57,0,3.5v20.5h18.381l5.619-5.664V3.5c0-1.93-1.57-3.5-3.5-3.5ZM3,3.5c0-.275.224-.5.5-.5h17c.276,0,.5.225.5.5v12.5h-5v5H3V3.5Zm7.5,12.5v-2.5h-2.5v-3h2.5v-2.5h3v2.5h2.5v3h-2.5v2.5h-3Z" />
                    </svg>
                </button>
                <textarea v-show="onMemoEditor" class="form-control memoEditor" name="memo" id="memo" cols="auto" rows="5" v-model="memoContent"></textarea>
            </div>
        </div>

        <div class="buttonNavContainer">
            <div class="toggle">
                <div class="toggleTitle">다른 사람들과 공유하시겠어요?</div>
                <VueToggles :v-model="isRelease" :height="30" :width="70" checkedText="On" uncheckedText="Off"
                    fontSize="18" checkedBg="#162F65" uncheckedBg="grey"></VueToggles>
            </div>
            
            <button class="saveButton" @click="onMemoEditor = !onMemoEditor">저장하기</button>
        </div>

    </div>
</template>

<style scoped>

.dayNavContainer {
    margin: 20px 0px 0px 20px;
}

.dayButton {
    font-size: 1.1em;
    color: #3361AC;
    background-color: white;

    border-radius: 10px 10px 0px 0PX;
    padding: 10px;
    font-weight: bolder;
    cursor: pointer;
}

.selectedDayButton {
    background-color: #3361AC;
    color: white;
}

.sectionContainer {
    background-color: #3361AC;
    margin: 0px 20px;
    padding: 40px;
    border-radius: 0px 15px 15px 15px;
    display: flex;
    flex-direction: column;
}

.contentsContainer {
    display: flex;
    flex-direction: column;
    align-items: center;
}

.memoContainer {
    display: flex;
    flex-direction: row;
}

.addMemoButton {
    background-color: #E7E6DD;
    border-radius: 10px;
    width: fit-content;
    height: fit-content;
    fill: #3361AC;
}

.memoEditor {
    background-color: #E7E6DD;
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
    background-color: #3361AC;
    color: rgba(255, 255, 255);
    font-weight: bolder;
    border-radius: 10px;
    padding: 8px;
    margin-top: 5px;
    width: fit-content;
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