<script setup>
import '@/assets/tailwind.css'
import { RouterLink, RouterView } from 'vue-router'

import { useNadoriStore } from '@/stores/nadori'
import { storeToRefs } from 'pinia'
import { useRouter } from 'vue-router'

const router = useRouter()
const nadoriStore = useNadoriStore()
const { member } = storeToRefs(nadoriStore)

const login = () => {
  member.value = {
    memberId: '3',
    nickname: 'General',
    username: 'GeneralUser'
  }
  router.push('/mypage')
}
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
    <div class="header top-area">
      <!-- main-menu Start -->

      <div class="header-area">
        <div class="container">
          <div class="row">
            <div class="col-sm-2">
              <div class="logo">
                <!-- <router-link to="/">
                <img id="logo" src="@/assets/nadoriLogo2.png" />
              </router-link> -->
                <a href="/">
                  <img id="logo" src="@/assets/nadoriLogo2.png" />
                </a>
              </div>
              <!-- /.logo-->
            </div>
            <!-- /.col-->
            <div class="col-sm-10">
              <div class="main-menu">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                  <button
                    type="button"
                    class="navbar-toggle"
                    data-toggle="collapse"
                    data-target=".navbar-collapse"
                  >
                    <i class="fa fa-bars"></i></button
                  ><!-- / button-->
                </div>
                <!-- /.navbar-header-->
                <div class="navbar-collapse">
                  <ul class="nav navbar-nav navbar-right" style="flex-direction: row">
                    <li class="smooth-menu" style="top: 10px"><a href="#home">home</a></li>
                    <!-- <li class="smooth-menu"><a href="#gallery">Destination</a></li> -->
                    <li class="smooth-menu" style="top: 10px"><a href="#pack">Packages </a></li>
                    <!-- <li class="smooth-menu"><a href="#spo">Special Offers</a></li> -->
                    <li class="smooth-menu" style="top: 10px"><a href="#blog">blog</a></li>
                    <li class="smooth-menu" style="top: 10px">
                      <router-link to="/mypage">My Page</router-link>
                    </li>
                    <li>
                      <button class="book-btn" @click="login()" style="margin-top: 10px">
                        Login
                      </button>
                    </li>
                    <!--/.project-btn-->
                  </ul>
                </div>
                <!-- /.navbar-collapse -->
              </div>
              <!-- /.main-menu-->
            </div>
            <!-- /.col-->
          </div>
          <!-- /.row -->
          <div class="home-border"></div>
          <!-- /.home-border-->
        </div>
        <!-- /.container-->
      </div>
      <!-- /.header-area -->

      <!-- /.top-area-->
      <!-- main-menu End -->
    </div>
    <router-view></router-view>
  </div>
</template>

<style scoped>
@import '@/assets/css/font-awesome.min.css';
@import '@/assets/css/animate.css';
@import '@/assets/css/hover-min.css';
@import '@/assets/css/datepicker.css';
@import '@/assets/css/owl.carousel.min.css';
@import '@/assets/css/owl.theme.default.min.css';
@import '@/assets/css/jquery-ui.min.css';
@import '@/assets/css/bootstrap.min.css';
@import '@/assets/css/bootsnav.css';
@import '@/assets/css/style.css';
@import '@/assets/css/responsive.css';
@import '@/assets/tailwind.css';
</style>

<style scoped>
/* .header {
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
} */
</style>
