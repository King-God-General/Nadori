<script setup>
import MemberAPI from '@/apis/member'
import { onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'
const router = useRouter()
const route = useRoute()

onMounted(() => {
  MemberAPI.login(
    route.query.code,
    () => {
      console.log('인가 코드를 서버로 전달하는데 성공했습니다.')
    },
    () => {
      console.log('인가 코드를 서버로 전달하는데 실패했습니다.')
    }
  )
  router.push(`/home`)
})
</script>

<template>
  <div></div>
</template>

<style scoped></style>
