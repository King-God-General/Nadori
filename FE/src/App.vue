<script setup>
import '@/assets/tailwind.css'
import { RouterLink, RouterView } from 'vue-router'
import { useNadoriStore } from '@/stores/nadori'
import { storeToRefs } from 'pinia'
import Swal from 'sweetalert2'

const { VITE_KAKAO_API_KEY, VITE_KAKAO_REDIRECT_URL } = import.meta.env

const nadoriStore = useNadoriStore()
const { member } = storeToRefs(nadoriStore)

const doKakaoLogin = () => {
  const url =
    'https://kauth.kakao.com/oauth/authorize?client_id=' +
    VITE_KAKAO_API_KEY +
    '&redirect_uri=' +
    VITE_KAKAO_REDIRECT_URL +
    '&response_type=code&scope=profile_nickname'

  window.location.href = url
}

const loginForm = () => {
  Swal.fire({
    showCancelButton: true,
    showDenyButton: true,
    html: `
    <label for="email" value="이메일">
    <input type="email" id="email" class="form-control" placeholder="이메일">
    <label for="email" value="비밀번호">
    <input type="password" id="password" class="form-control" placeholder="비밀번호">
  `,
    focusConfirm: false,
    confirmButtonText: '로그인',
    denyButtonText: '카카오 로그인',
    showLoaderOnConfirm: true,
    preConfirm: async () => {
      const email = Swal.getPopup().querySelector('#email').value
      const password = Swal.getPopup().querySelector('#password').value

      if (!email || !password) {
        Swal.showValidationMessage('이메일과 비밀번호를 입력해주세요.')
        return
      }

      try {
        const response = await fetch('/your-login-api-endpoint', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({ email, password })
        })
        if (!response.ok) {
          const error = await response.json()
          Swal.showValidationMessage(`Error: ${JSON.stringify(error)}`)
          return
        }
        return response.json()
      } catch (error) {
        Swal.showValidationMessage(`Request failed: ${error}`)
      }
    },
    allowOutsideClick: () => !Swal.isLoading()
  }).then((result) => {
    if (result.isConfirmed) {
      Swal.fire({
        title: '로그인 성공!',
        text: `${result.value.email}님, 환영합니다!`,
        icon: 'success'
      })
    }
  })

  document.addEventListener('click', function (event) {
    if (event.target.id === 'signup') {
      Swal.fire({
        title: '회원가입 페이지로 이동',
        text: '회원가입 페이지로 이동합니다.',
        icon: 'info'
      })
    }

    if (event.target.id === 'oauth') {
      Swal.fire({
        title: '연동 로그인',
        text: '연동 로그인을 시도합니다.',
        icon: 'info'
      })
    }
  })
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

      <div v-if="member.id == 0" class="linkContainer">
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
