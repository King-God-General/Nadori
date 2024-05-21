<script setup>
import { reactive } from 'vue'
import { useNadoriStore } from '@/stores/nadori'
import { storeToRefs } from 'pinia'

const nadoriStore = useNadoriStore()
const { member } = storeToRefs(nadoriStore)

const input = reactive({
  id: '',
  pw: ''
})

const loginService = () => {
  //api 붙이기
  member.id = input.id
  member.username = input.pw
}
</script>

<template>
  <div>
    <div class="formContainer">
      <label for="email">이메일</label>
      <input id="email" name="email" type="text" class="form-control" v-model="input.id" />
      <label for="pw">비밀번호</label>
      <input id="pw" name="pw" type="password" class="form-control" v-model="input.pw" />
      <div class="buttonContainer">
        <button class="btn" @click="loginService">로그인</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.formContainer {
  width: 350px;
}
.buttonContainer {
  display: flex;
  justify-content: flex-end;
  margin-top: 20px;
}
.btn {
  background: linear-gradient(50deg, rgb(248, 224, 145), rgb(247, 162, 0));
  border: none;
  color: rgb(24, 42, 57);
  font-weight: bolder;
  width: 100px;
}
</style>
