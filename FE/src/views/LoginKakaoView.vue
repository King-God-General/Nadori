<script setup>
import MemberAPI from '@/apis/member'
import { onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { useNadoriStore } from '@/stores/nadori'
import { storeToRefs } from 'pinia'

const router = useRouter()
const route = useRoute()
const nadoriStore = useNadoriStore()
const { member } = storeToRefs(nadoriStore)

onMounted(() => {
  MemberAPI.loginOrSignUp(
    route.query.code,
    (response) => {
      console.log('인가 코드를 서버로 전달하는데 성공했습니다. :'+response.data)
      
      member.memberId=response.data.memberId;
      member.nickname=response.data.nickname;
      member.username=response.data.username;
      
      console.log(member);

      router.push(`/mypage`);
    },
    () => {
      console.log('인가 코드를 서버로 전달하는데 실패했습니다.')
    }
  )
})
</script>

<template>
  <div></div>
</template>

<style scoped></style>
