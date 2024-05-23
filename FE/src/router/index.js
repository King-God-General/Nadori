import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import MyPageView from '@/views/MyPageView.vue'
import PlanDetailView from '@/views/PlanDetailView.vue'
import PlanFormView from '@/views/PlanFormView.vue'
import PlanModifyView from '@/views/PlanModifyView.vue'
import LoginKakaoView from '@/views/LoginKakaoView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'origin',
      component: HomeView
    },
    {
      path: '/home',
      name: 'home',
      component: HomeView
    },
    {
      path: '/mypage',
      name: 'mypage',
      component: MyPageView
    },
    {
      path: '/plan/detail/:planId',
      name: 'planDetail',
      component: PlanDetailView,
      props: true
    },
    {
      path: '/plan/form',
      name: 'planForm',
      component: PlanFormView,
    },
    {
      path: '/plan/form/:planId',
      name: 'planModify',
      component: PlanModifyView,
      props: true
    },
    {
      path: '/login/kakao',
      name: 'loginKakao',
      component: LoginKakaoView
    }
  ]
})

export default router
