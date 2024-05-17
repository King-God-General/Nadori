<script setup>
import { ref } from "vue";
import VueDatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css'


const formatDate = (date) => {
  const year = date.getFullYear();
  const month = date.getMonth() + 1;
  const day = date.getDate();

  // 날짜 앞에 0을 붙여야 하는 경우
  if (month || day < 10) {
    const zeroDay = ('00' + day).slice(-2);
    const zeroMonth = ('00' + month).slice(-2);

    return `${year}.${zeroMonth}.${zeroDay}`;
  } else {

    return `${year}.${month}.${day}`;
  }
}

const startDate = ref();
const endDate = ref();
</script>

<template>

  <div class="page">

    <div class="content">
      <div class="qContainer">
      <p class="emoji">🧳</p>
      <p class="question">여행을 떠나는 날짜를 알려주세요.</p>
    </div>

    <div class="dateContainer">
      <div class="singleDate">
        <VueDatePicker
        v-model="startDate"
        locale="ko"
        :format="formatDate"
        :enable-time-picker="false"
        placeholder="여행을 떠나는 날"
        auto-apply/>
      </div>
      <div class="singleDate">
        <VueDatePicker
          v-model="endDate"
          locale="ko"
          :format="formatDate"
          :enable-time-picker="false"
          placeholder="집으로 돌아오는 날"
          auto-apply/>
      </div>
    </div>

    <button class="btn" @click="">이제 여행 계획을 세우러 가봅시다!</button>
    </div>
    

  </div>
</template>

<style scoped>

.page {
  padding: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.content {
  padding: 50px;
  border-radius: 25px 0px;
  background-color: #162F65;
}

.qContainer {
  border-radius: 25px;
  margin-bottom: 30px;
}
p {
  margin-bottom: 0;
}
.emoji {
  font-size: 4em;
  margin-bottom: 10px;
}
.question{
  font-size: 1.5em;
  font-weight: bolder;
  color: white;
}

.singleDate {
  margin-bottom: 5px;
}

.dateContainer {
  margin-bottom: 30px;
}

button {
  float: right;
  background-color: #E8AF30;
  color: rgba(255, 255, 255);
  font-weight: bolder;
  margin-top: 5px;
  margin-right: 5px;
  font-family: 'jua';
}

</style>
