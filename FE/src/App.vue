<script setup>
import '@/assets/tailwind.css'
import { RouterLink, RouterView } from 'vue-router'
import { ref, onMounted } from 'vue'

// const packages = [
//   { name: 'italy', price: '$499' },
//   { name: 'england', price: '$1499' },
//   { name: 'france', price: '$1199' },
//   { name: 'india', price: '$799' },
//   { name: 'spain', price: '$999' },
//   { name: 'thailand', price: '$799' }
// ]

// const packagesContent = ref(null)

// onMounted(() => {
//   // packages-content 요소 가져오기
//   packagesContent.value = document.getElementById('packages-content')

//   // 배열 순회하며 각 패키지 정보를 동적으로 생성하여 packages-content에 추가
//   packages.forEach((packageItem) => {
//     // 새로운 패키지 요소 생성
//     const packageElement = document.createElement('div')
//     packageElement.classList.add('col-md-4', 'col-sm-6')
//     packageElement.innerHTML = `
//         <div class="single-package-item">
//           <img src="assets/images/packages/${packageItem.name}.png" alt="package-place" />
//           <div class="single-package-item-txt">
//             <h3>${packageItem.name} <span class="pull-right">${packageItem.price}</span></h3>
//             <div class="packages-para">
//               <p>
//                 <span> <i class="fa fa-angle-right"></i> 5 days 6 nights </span>
//                 <i class="fa fa-angle-right"></i> 5 star accommodation
//               </p>
//               <p>
//                 <span> <i class="fa fa-angle-right"></i> transportation </span>
//                 <i class="fa fa-angle-right"></i> food facilities
//               </p>
//             </div>
//             <div class="packages-review">
//               <p>
//                 <i class="fa fa-star"></i>
//                 <i class="fa fa-star"></i>
//                 <i class="fa fa-star"></i>
//                 <i class="fa fa-star"></i>
//                 <i class="fa fa-star"></i>
//                 <span>2544 review</span>
//               </p>
//             </div>
//             <div class="about-btn">
//               <button class="about-view packages-btn">book now</button>
//             </div>
//           </div>
//         </div>
//       `
//     // 패키지 요소를 packages-content에 추가
//     packagesContent.value.appendChild(packageElement)
//   })
// })

// const blogs = [
//   {
//     date: '15 november 2017',
//     imgSrc: 'assets/images/blog/magazine.jpg',
//     title: "Let's Goooooooooooo",
//     description:
//       'Lorem ipsum dolor sit amet, contur adip elit, sed do mod incid ut labore et dolore magna aliqua. Ut enim ad minim veniam',
//     link: '#'
//   },
//   {
//     date: '15 november 2017',
//     imgSrc: 'assets/images/blog/magazine.jpg',
//     title: 'Discover on beautiful weather, Fantastic foods and historical place in india',
//     description:
//       'Lorem ipsum dolor sit amet, contur adip elit, sed do mod incid ut labore et dolore magna aliqua. Ut enim ad minim veniam',
//     link: '#'
//   },
//   {
//     date: '15 november 2017',
//     imgSrc: 'assets/images/blog/magazine.jpg',
//     title: '10 Most Natural place to Discover',
//     description:
//       'Lorem ipsum dolor sit amet, contur adip elit, sed do mod incid ut labore et dolore magna aliqua. Ut enim ad minim veniam',
//     link: '#'
//   }
// ]

// const blogRows = document.getElementById('blog-rows')

// blogs.forEach((blog) => {
//   const col = document.createElement('div')
//   col.className = 'col-sm-4 col-md-4'

//   col.innerHTML = `
//         <div class="thumbnail">
//           <h2>trending news <span>${blog.date}</span></h2>
//           <div class="thumbnail-img">
//             <img src="${blog.imgSrc}" alt="blog-img" />
//             <div class="thumbnail-img-overlay"></div>
//           </div>
//           <div class="caption">
//             <div class="blog-txt">
//               <h3><a href="${blog.link}">${blog.title}</a></h3>
//               <p>${blog.description}</p>
//               <a href="${blog.link}">Read More</a>
//             </div>
//           </div>
//         </div>
//       `

//   blogRows.appendChild(col)
// })
</script>

<template>
  <div class="head">
    <head>
      <!-- META DATA -->
      <meta charset="utf-8" />
      <meta http-equiv="X-UA-Compatible" content="IE=edge" />
      <meta name="viewport" content="width=device-width, initial-scale=1" />
      <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

      <!--font-family-->
      <link href="https://fonts.googleapis.com/css?family=Rufina:400,700" rel="stylesheet" />

      <link
        href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900"
        rel="stylesheet"
      />

      <!-- TITLE OF SITE -->
      <title>Nadori</title>

      <!-- favicon img -->
      <link rel="shortcut icon" type="image/icon" href="@/assets/nadoriLogo2.png" />

      <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
      <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->

      <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
      <![endif]-->
    </head>
  </div>
  <div class="header top-area">
    <!-- main-menu Start -->

    <div class="header-area" style="height: 92px">
      <div class="container">
        <div class="row">
          <div class="col-sm-2">
            <div class="logo">
              <a href="main.html"><img id="logo" src="@/assets/nadoriLogo2.png" /></a>

              <!-- <a href="main.html"> Na<span>dori</span> </a> -->
            </div>
            <!-- /.logo-->
          </div>
          <!-- /.col-->
          <div class="col-sm-10" style="margin-top: 10px">
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
                  <li class="smooth-menu"><a href="#home">home</a></li>
                  <!-- <li class="smooth-menu"><a href="#gallery">Destination</a></li> -->
                  <li class="smooth-menu"><a href="#pack">Packages </a></li>
                  <!-- <li class="smooth-menu"><a href="#spo">Special Offers</a></li> -->
                  <li class="smooth-menu"><a href="#blog">blog</a></li>
                  <li class="smooth-menu"><a href="#subs">subscription</a></li>
                  <li>
                    <button class="book-btn">book now</button>
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
                    <button class="about-view">explore now</button>
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
      <div class="container">
        <div class="gallary-header text-center">
          <h2 style="font-weight: bold">인기 여행지</h2>
          <p>지금 인기있는 여행지로 떠나보아요.</p>
        </div>
        <div class="packages-content" id="packages-content"></div>
      </div>
    </section>
    <!--packages end-->

    <!--blog start-->
    <section id="blog" class="blog">
      <div class="container">
        <div class="blog-details">
          <div class="gallary-header text-center">
            <h2 style="font-weight: bold">나도리 매거진</h2>
            <p>더 많은 여행들을 확인해보세요.</p>
          </div>
          <!--/.gallery-header-->
          <div class="blog-content">
            <div class="row" id="blog-rows"></div>
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

  <!-- <div>

    <nav class="header">

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
    </nav>

    <router-view></router-view>
  </div> -->
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
