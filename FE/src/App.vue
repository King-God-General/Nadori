<script setup>
import '@/assets/tailwind.css'
import { RouterLink, RouterView } from 'vue-router'



</script>

<template>
  <div>

    <badge type="default">Default</badge>

    <!-- 네비게이션 바 -->
    <nav class="bg-gray-800 p-4">
      <div class="container mx-auto flex justify-between items-center">
        <router-link to="/" class="text-white text-2xl font-semibold">Nadori</router-link>
        <ul class="flex">
          <li class="mr-4">
            <router-link to="/" class="text-white">Home</router-link>
          </li>
          <li class="mr-4">
            <router-link to="/about" class="text-white">About</router-link>
          </li>
          <li class="mr-4">
            <router-link to="/plan" class="text-white">Plan</router-link>
          </li>
        </ul>
      </div>
    </nav>

    <!-- 여행 코스 페이지 -->
    <router-view></router-view>
  </div>
</template>

<style scoped></style>
