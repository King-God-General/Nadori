<script setup>
import '@/assets/tailwind.css'
import { RouterLink, RouterView } from 'vue-router'
import { useNadoriStore } from '@/stores/nadori'
import { storeToRefs } from 'pinia'

const nadoriStore = useNadoriStore()
const { member } = storeToRefs(nadoriStore)
</script>

<template>
  <div>
    <nav class="header">
      <div class="logoContainer">
        <router-link to="/home">
          <img id="logo" src="@/assets/nadoriLogo2.png" />
        </router-link>
      </div>

      <div v-if="member == null" class="linkContainer">
        <router-link to="/login" class="menu btn font-weight-bold">로그인</router-link>
      </div>

      <div v-else class="linkContainer">
        <router-link to="/plan/form" class="menu btn">새로운 플랜 만들기</router-link>
        <router-link to="/mypage" class="menu btn font-weight-bold">마이페이지</router-link>
      </div>
    </nav>

    <router-view></router-view>
  </div>
</template>

<style scoped>
.header {
  background-color: rgb(255, 210, 57);
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 10px 50px;
}

#logo {
  width: 100px;
  height: 100px;
}

.linkContainer {
  display: flex;
  flex-direction: row;
}

.menu {
  text-decoration-line: none;
  font-size: 1.1em;
  font-weight: bolder;
  margin-right: 10px;
  color: rgb(24, 42, 57);
  padding: 5px;
  border-radius: 10px;
}
</style>
