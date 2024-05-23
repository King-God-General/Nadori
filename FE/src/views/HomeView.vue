<script setup>
import { useNadoriStore } from '@/stores/nadori'
import { storeToRefs } from 'pinia'
import '@/assets/tailwind.css'

import { useRouter, RouterLink, RouterView } from 'vue-router'
import { ref, onMounted } from 'vue'
import planAPI from '@/apis/plan'
const { getPlans } = planAPI

const packages = [
  { name: '포항스틸아트페스티벌', price: '12,900원', review: '4.9' },
  { name: '북한산국립공원(서울)', price: '45,900원', review: '4.7' },
  { name: '덕동계곡', price: '32,000원', review: '4.2' }
]

const nadoriStore = useNadoriStore()
const { plan, planDetail, curDayNum } = storeToRefs(nadoriStore)
const router = useRouter()
const moveToDetail = () => {
  router.push(`/plan/detail/3`)
}
onMounted(() => {
  plan.value = null
  planDetail.value = null
  curDayNum.value = 1
})

// 블로그 데이터를 저장할 상태 변수
const blogs = ref([])

// API 호출 및 데이터 처리
onMounted(() => {
  planAPI.getPlans(
    (response) => {
      blogs.value = response.data
      console.log(blogs.value)
    },
    (error) => {
      console.error('API 호출 실패:', error)
    }
  )
})
</script>

<template>
  <div class="section">
    <!--about-us start -->
    <section id="home" class="about-us">
      <div class="container">
        <div class="about-us-content">
          <div class="row">
            <div class="col-sm-12">
              <div class="single-about-us">
                <!-- <div class="about-us-txt"> -->
                <div>
                  <h2 class="animated fadeInUp" style="opacity: 1">
                    Explore the Beauty of the Beautiful World
                  </h2>
                  <div class="about-btn">
                    <router-link to="/plan/form">
                      <button class="about-view">explore now</button>
                    </router-link>
                  </div>
                  <!--/.about-btn-->
                </div>
                <!--/.about-us-txt-->
              </div>
              <!--/.single-about-us-->
            </div>
            <!--/.col-->
            <div class="col-sm-0">
              <div class="single-about-us"></div>
              <!--/.single-about-us-->
            </div>
            <!--/.col-->
          </div>
          <!--/.row-->
        </div>
        <!--/.about-us-content-->
      </div>
      <!--/.container-->
    </section>
    <!--/.about-us-->
    <!--about-us end -->

    <!--packages start-->
    <section id="pack" class="packages">
      <div
        class="container"
        style="
          display: flex;
          flex-wrap: wrap;
          place-content: stretch center;
          align-content: center;
          flex-direction: column;
        "
      >
        <div class="gallary-header text-center">
          <h2 style="font-weight: bold">추천 여행지</h2>
          <p>리뷰기반 추천 여행지로 떠나보아요.</p>
        </div>
        <div class="packages-content" id="packages-content">
          <div
            v-for="packageItem in packages"
            :key="packageItem.name"
            class="single-package-item col-md-4 col-sm-6"
            style="width: 400px; margin: 0px 10px"
          >
            <img
              :src="`src/assets/images/packages/${packageItem.name}.jpg`"
              :alt="`${packageItem.name}-image`"
            />
            <div class="single-package-item-txt">
              <h3>
                {{ packageItem.name }}
                <span class="pull-right">Rating {{ packageItem.review }}</span>
              </h3>

              <div class="about-btn">
                <button class="about-view packages-btn">book now</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!--packages end-->

    <!--blog start-->
    <section id="blog" class="blog">
      <div
        class="container"
        style="display: flex; flex-wrap: wrap; align-content: stretch; justify-content: center"
      >
        <div class="blog-details">
          <div class="gallary-header text-center">
            <h2 style="font-weight: bold">나도리 매거진</h2>
            <p>더 많은 여행들을 확인해보세요.</p>
          </div>
          <!--/.gallery-header-->
          <div class="blog-content">
            <div class="row" id="blog-rows" style="display: flex; justify-content: center">
              <div
                v-for="blog in blogs"
                :key="blog.title"
                class="col-sm-4 col-md-4"
                style="width: 400px"
              >
                <div class="thumbnail">
                  <h2>
                    trending news <span>{{ blog.date }}</span>
                  </h2>
                  <div class="thumbnail-img">
                    <img :src="`src/assets/images/blog/${blog.title}.jpg`" alt="blog-img" />
                    <div class="thumbnail-img-overlay"></div>
                  </div>
                  <div class="caption">
                    <div class="blog-txt">
                      <h3>
                        <a :href="blog.link">{{ blog.title }}</a>
                      </h3>
                      <p>{{ blog.description }}</p>
                      <a :href="blog.link">Read More</a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!--/.row-->
          </div>
          <!--/.blog-content-->
        </div>
        <!--/.blog-details-->
      </div>
      <!--/.container-->
    </section>
    <!--/.blog-->
    <!--blog end-->

    <!--subscribe start-->
    <section id="subs" class="subscribe">
      <div class="container">
        <div class="subscribe-title text-center">
          <h2>Join our Subscribers List to Get Regular Update</h2>
          <p>Subscribe Now. We will send you Best offer for your Trip</p>
        </div>
        <form>
          <div class="row">
            <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
              <div class="custom-input-group">
                <input type="email" class="form-control" placeholder="Enter your Email Here" />
                <button class="appsLand-btn subscribe-btn">Subscribe</button>
                <div class="clearfix"></div>
                <i class="fa fa-envelope"></i>
              </div>
            </div>
          </div>
        </form>
      </div>
    </section>
    <!--subscribe end-->
  </div>

  <div class="footer">
    <!-- footer-copyright start -->
    <footer class="footer-copyright">
      <div class="container">
        <div class="footer-content">
          <div class="row">
            <div class="col-sm-3">
              <div class="single-footer-item">
                <div class="footer-logo">
                  <a href="index.html"> Na<span>dori</span> </a>
                  <p>best travel agency</p>
                </div>
              </div>
              <!--/.single-footer-item-->
            </div>
            <!--/.col-->

            <div class="col-sm-3">
              <div class="single-footer-item">
                <h2>link</h2>
                <div class="single-footer-txt">
                  <p><a href="#home">home</a></p>
                  <!-- <p><a href="#">destination</a></p> -->
                  <p><a href="#pack">spacial packages</a></p>
                  <!-- <p><a href="#">special offers</a></p> -->
                  <p><a href="#blog">blog</a></p>
                  <p><a href="#subs">contacts</a></p>
                </div>
                <!--/.single-footer-txt-->
              </div>
              <!--/.single-footer-item-->
            </div>
            <!--/.col-->

            <div class="col-sm-3">
              <div class="single-footer-item">
                <h2>popular destination</h2>
                <div class="single-footer-txt">
                  <p><a href="#">china</a></p>
                  <p><a href="#">venezuela</a></p>
                  <p><a href="#">brazil</a></p>
                  <p><a href="#">australia</a></p>
                  <p><a href="#">london</a></p>
                </div>
                <!--/.single-footer-txt-->
              </div>
              <!--/.single-footer-item-->
            </div>
            <!--/.col-->

            <div class="col-sm-3">
              <div class="single-footer-item text-center">
                <h2 class="text-left">contacts</h2>
                <div class="single-footer-txt text-left">
                  <p>+1 (300) 1234 6543</p>
                  <p class="foot-email"><a href="#">info@tnest.com</a></p>
                  <p>North Warnner Park 336/A</p>
                  <p>Newyork, USA</p>
                </div>
                <!--/.single-footer-txt-->
              </div>
              <!--/.single-footer-item-->
            </div>
            <!--/.col-->
          </div>
          <!--/.row-->
        </div>
        <!--/.footer-content-->
        <hr />
        <div class="foot-icons">
          <ul class="footer-social-links list-inline list-unstyled">
            <li>
              <a href="#" target="_blank" class="foot-icon-bg-1"><i class="fa fa-facebook"></i></a>
            </li>
            <li>
              <a href="#" target="_blank" class="foot-icon-bg-2"><i class="fa fa-twitter"></i></a>
            </li>
            <li>
              <a href="#" target="_blank" class="foot-icon-bg-3"><i class="fa fa-instagram"></i></a>
            </li>
          </ul>
          <p>&copy; 2017 <a href="https://www.themesine.com">ThemeSINE</a>. All Right Reserved</p>
        </div>
        <!--/.foot-icons-->
        <div id="scroll-Top">
          <i
            class="fa fa-angle-double-up return-to-top"
            id="scroll-top"
            data-toggle="tooltip"
            data-placement="top"
            title=""
            data-original-title="Back to Top"
            aria-hidden="true"
          ></i>
        </div>
        <!--/.scroll-Top-->
      </div>
      <!-- /.container-->
    </footer>
    <!-- /.footer-copyright-->
    <!-- footer-copyright end -->
  </div>
  <div class="container mx-auto mt-8">
    <h1 class="text-3xl font-semibold mb-4">Welcome to Travel App</h1>
    <p class="text-lg">Check out our amazing travel courses!</p>

    <button @click="moveToDetail">상세페이지</button>
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
