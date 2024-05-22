<script setup>
import { useNadoriStore } from '@/stores/nadori'
import { storeToRefs } from 'pinia'
import { onMounted } from 'vue'
import '@/assets/tailwind.css'
import { useRouter } from 'vue-router'
const nadoriStore = useNadoriStore()
const { plan, planDetail, curDayNum } = storeToRefs(nadoriStore)
const router = useRouter()
const moveToDetail = () => {
  router.push(`/plan/detail/3`)
}
onMounted(()=>{
  plan.value=null
  planDetail.value=null
  curDayNum.value=1
})
</script>

<template>
  <div class="container mx-auto mt-8">
    <h1 class="text-3xl font-semibold mb-4">Welcome to Travel App</h1>
    <p class="text-lg">Check out our amazing travel courses!</p>

    <button @click="moveToDetail">상세페이지</button>
  </div>
</template>
<style scoped></style>
