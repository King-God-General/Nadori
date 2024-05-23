<script setup>
import '@/assets/tailwind.css'
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <div>
    <!-- <nav class="header">

      <div class="logoContainer">
        <router-link to="/">
          <img id="logo" src="@/assets/nadoriLogo2.png">
        </router-link> 
      </div>

       <div v-if="memberId!=null" class="linkContainer">
          <router-link to="/login" class="menu btn font-weight-bold">로그인</router-link>
       </div>

       <div v-else class="linkContainer">
          <router-link to="/plan/form" class="menu btn">새로운 플랜 만들기</router-link>
          <router-link to="/mypage" class="menu btn font-weight-bold">마이페이지</router-link>
        </div>
    </nav> -->

    <router-view></router-view>
  </div>
</template>

<style scoped></style>
