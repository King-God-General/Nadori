<script setup>
import { useNadoriStore } from '@/stores/nadori'
import { storeToRefs } from 'pinia'
import { onMounted, ref } from 'vue'
import planAPI from '@/apis/plan'
import planCard from '@/components/planCard.vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const nadoriStore = useNadoriStore()
const { member } = storeToRefs(nadoriStore)
const myPlans = ref()

const logout = () => {
  member.value = null
  router.push('/home')
}

const moveToDetailPage = (planId) => {
  router.push(`/plan/detail/${planId}`)
}

onMounted(() => {
  planAPI.getPlansByUsername(
    member.value.username,
    (response) => {
      console.log('유저의 모든 플랜을 불러오는데 성공했습니다.: ' + JSON.stringify(response.data))
      myPlans.value = response.data
    },
    () => {
      console.log('유저의 모든 플랜을 불러오는데 실패했습니다.')
    }
  )
})
</script>

<template>
  <div class="page">
    <div class="row">
      <h3 class="title">{{ member.username }}</h3>
      <div class="buttonContainer">
        <button class="button btn">별명 바꾸기</button>
        <button class="button btn" @click="logout">로그아웃</button>
        <button class="button btn">회원 탈퇴</button>
      </div>
    </div>

    <div class="row">
      <div class="myPlanContainer">
        <h3 class="title">📝 나의 플랜</h3>
        <div class="plans" v-for="(item, index) in myPlans" :key="item.planId">
          <planCard
            :item="myPlans[index]"
            @click="moveToDetailPage(myPlans[index].planId)"
          ></planCard>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.page {
  padding-top: 100px;
  margin: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.row {
  margin: 10px 0px;
  padding: 20px;
  width: 500px;
}

.title {
  font-weight: bolder;
  margin-bottom: 15px;
}

.button {
  background-color: rgb(247, 162, 0);
  color: rgba(255, 255, 255);
  font-size: 0.9em;
  font-weight: bolder;
  border-radius: 10px;
  padding: 8px;
  margin-top: 10px;
  margin-right: 5px;
}
</style>
