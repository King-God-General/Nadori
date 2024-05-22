<script setup>
import '@/assets/tailwind.css'
import { RouterLink, RouterView } from 'vue-router'
import { useNadoriStore } from '@/stores/nadori'
import { storeToRefs } from 'pinia'
import MemberAPI from '@/apis/member'
import { useRouter } from 'vue-router'

const { VITE_KAKAO_API_KEY, VITE_KAKAO_REDIRECT_URL } = import.meta.env
const router = useRouter()
const nadoriStore = useNadoriStore()
const { member } = storeToRefs(nadoriStore)

const doKakaoLogin = () => {
  // const url =
  //   'https://kauth.kakao.com/oauth/authorize?client_id=' +
  //   VITE_KAKAO_API_KEY +
  //   '&redirect_uri=' +
  //   VITE_KAKAO_REDIRECT_URL +
  //   '&response_type=code&scope=profile_nickname'
  // window.location.href = url

  member.value={
    memberId: "1",
    nickname: "King",
    username: "KingUser"
  }
  console.log(member.value)

  router.push("/mypage")
}
const doKakaoLogout = () => {
  // MemberAPI.logout(member.memberId,
  //   ()=>{
  //     console.log('로그아웃에 성공했습니다.')
  //   },
  //   ()=>{
  //     console.log('로그아웃에 실패했습니다.')
  //   }
  // )

}

</script>

<template>
  <div>
    <nav class="header">
      <div class="logoContainer">
        <router-link to="/home">
          <img id="logo" src="@/assets/nadoriLogo2.png" />
        </router-link>
      </div>

      <div v-if="member===null" class="linkContainer">
        <button class="menu" @click="doKakaoLogin">로그인</button>
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
