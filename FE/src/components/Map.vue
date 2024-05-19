<script setup>
import { NaverMap, NaverMarker } from 'vue3-naver-maps'
import { ref, onMounted } from 'vue';
import attractionAPI from "@/apis/attraction"

const mapRef = ref(null)
const morphOption = {
  duration: 300,
  easing: 'easeInCubic',
}
const mapOptions = {
  latitude: 37.51347, // 지도 중앙 위도
  longitude: 127.041722, // 지도 중앙 경도
  zoom: 20,
}

const sidoList = [
  { name: '서울', value: 1 },
  { name: '인천', value: 2 },
  { name: '대전', value: 3 },
  { name: '대구', value: 4 },
  { name: '광주', value: 5 },
  { name: '부산', value: 6 },
  { name: '울산', value: 7 },
  { name: '세종', value: 8 },
  { name: '경기', value: 31 },
  { name: '강원', value: 32 },
  { name: '충북', value: 33 },
  { name: '충남', value: 34 },
  { name: '경북', value: 35 },
  { name: '경남', value: 36 },
  { name: '전북', value: 37 },
  { name: '전남', value: 38 },
  { name: '제주', value: 39 }
];

const gugunList = {
  1: {
    "강남구": "1",
    "강동구": "2",
    "강북구": "3",
    "강서구": "4",
    "관악구": "5",
    "광진구": "6",
    "구로구": "7",
    "금천구": "8",
    "노원구": "9",
    "도봉구": "10",
    "동대문구": "11",
    "동작구": "12",
    "마포구": "13",
    "서대문구": "14",
    "서초구": "15",
    "성동구": "16",
    "성북구": "17",
    "송파구": "18",
    "양천구": "19",
    "영등포구": "20",
    "용산구": "21",
    "은평구": "22",
    "종로구": "23",
    "중구": "24",
    "중랑구": "25"
  },
  2: {
    "강화군": "1",
    "계양구": "2",
    "미추홀구": "3",
    "남동구": "4",
    "동구": "5",
    "부평구": "6",
    "서구": "7",
    "연수구": "8",
    "옹진군": "9",
    "중구": "10"
  },
  3: {
    "대덕구": "1",
    "동구": "2",
    "서구": "3",
    "유성구": "4",
    "중구": "5"
  },
  4: {
    "남구": "1",
    "달서구": "2",
    "달성군": "3",
    "동구": "4",
    "북구": "5",
    "서구": "6",
    "수성구": "7",
    "중구": "8",
    "군위군": "9"
  },
5: {
      "광산구": "1",
      "남구": "2",
      "동구": "3",
      "북구": "4",
      "서구": "5"
    },
6:{
      "강서구": "1",
      "금정구": "2",
      "기장군": "3",
      "남구": "4",
      "동구": "5",
      "동래구": "6",
      "부산진구": "7",
      "북구": "8",
      "사상구": "9",
      "사하구": "10",
      "서구": "11",
      "수영구": "12",
      "연제구": "13",
      "영도구": "14",
      "중구": "15",
      "해운대구": "16"
    },
7:{
      "중구": "1",
      "남구": "2",
      "동구": "3",
      "북구": "4",
      "울주군": "5"
    },
8: {
      "세종특별자치시": "1"
    },
31:{
      "가평군": "1",
      "고양시": "2",
      "과천시": "3",
      "광명시": "4",
      "광주시": "5",
      "구리시": "6",
      "군포시": "7",
      "김포시": "8",
      "남양주시": "9",
      "동두천시": "10",
      "부천시": "11",
      "성남시": "12",
      "수원시": "13",
      "시흥시": "14",
      "안산시": "15",
      "안성시": "16",
      "안양시": "17",
      "양주시": "18",
      "양평군": "19",
      "여주시": "20",
      "연천군": "21",
      "오산시": "22",
      "용인시": "23",
      "의왕시": "24",
      "의정부시": "25",
      "이천시": "26",
      "파주시": "27",
      "평택시": "28",
      "포천시": "29",
      "하남시": "30",
      "화성시": "31"
    },
32: {
      "강릉시": "1",
      "고성군": "2",
      "동해시": "3",
      "삼척시": "4",
      "속초시": "5",
      "양구군": "6",
      "양양군": "7",
      "영월군": "8",
      "원주시": "9",
      "인제군": "10",
      "정선군": "11",
      "철원군": "12",
      "춘천시": "13",
      "태백시": "14",
      "평창군": "15",
      "홍천군": "16",
      "화천군": "17",
      "횡성군": "18"
    },
33:
{
      "괴산군": "1",
      "단양군": "2",
      "보은군": "3",
      "영동군": "4",
      "옥천군": "5",
      "음성군": "6",
      "제천시": "7",
      "진천군": "8",
      "청원군": "9",
      "청주시": "10",
      "충주시": "11",
      "증평군": "12"
    },
34:{
      "공주시": "1",
      "금산군": "2",
      "논산시": "3",
      "당진시": "4",
      "보령시": "5",
      "부여군": "6",
      "서산시": "7",
      "서천군": "8",
      "아산시": "9",
      "예산군": "10",
      "천안시": "11",
      "청양군": "12",
      "태안군": "13",
      "홍성군": "14",
      "계룡시": "15"
    },
35: {
      "경산시": "1",
      "경주시": "2",
      "고령군": "3",
      "구미시": "4",
      "김천시": "5",
      "문경시": "6",
      "봉화군": "7",
      "상주시": "8",
      "성주군": "9",
      "안동시": "10",
      "영덕군": "11",
      "영양군": "12",
      "영주시": "13",
      "영천시": "14",
      "예천군": "15",
      "울릉군": "16",
      "울진군": "17",
      "의성군": "18",
      "청도군": "19",
      "청송군": "20",
      "칠곡군": "21",
      "포항시": "22"
    },
36:{
      "거제시": "1",
      "거창군": "2",
      "고성군": "3",
      "김해시": "4",
      "남해군": "5",
      "마산시": "6",
      "밀양시": "7",
      "사천시": "8",
      "산청군": "9",
      "양산시": "10",
      "의령군": "11",
      "진주시": "12",
      "진해시": "13",
      "창녕군": "14",
      "창원시": "15",
      "통영시": "16",
      "하동군": "17",
      "함안군": "18",
      "함양군": "19",
      "합천군": "20"
    },
37:{
      "고창군": "1",
      "군산시": "2",
      "김제시": "3",
      "남원시": "4",
      "무주군": "5",
      "부안군": "6",
      "순창군": "7",
      "완주군": "8",
      "익산시": "9",
      "임실군": "10",
      "장수군": "11",
      "전주시": "12",
      "정읍시": "13",
      "진안군": "14"
    },
38:{
      "강진군": "1",
      "고흥군": "2",
      "곡성군": "3",
      "광양시": "4",
      "구례군": "5",
      "나주시": "6",
      "담양군": "7",
      "목포시": "8",
      "무안군": "9",
      "보성군": "10",
      "순천시": "11",
      "신안군": "12",
      "여수시": "13",
      "영광군": "14",
      "영암군": "15",
      "완도군": "16",
      "장성군": "17",
      "장흥군": "18",
      "진도군": "19",
      "함평군": "20",
      "해남군": "21",
      "화순군": "22"
    },
39:{
      "남제주군": "1",
      "북제주군": "2",
      "서귀포시": "3",
      "제주시": "4"
    }
};

const contentTypeList = [
{ name: '관광지', value: 12 },
  { name: '문화시설', value: 14 },
  { name: '행사/공연/축제', value: 15 },
  { name: '여행코스', value: 25 },
  { name: '레포츠', value: 28 },
  { name: '숙박', value: 32 },
  { name: '쇼핑', value: 38 },
  { name: '음식점', value: 39 }
]

const selectedGugunList = ref({});
const searchResult = ref({})

const selectedSidoCode = ref(0);
const selectedGugunCode = ref(0);
const selectedContentTypeId = ref(0);

const selectAttraction = ref(null);

onMounted(() => {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
          position => {
            mapOptions.latitude=position.coords.latitude;
            mapOptions.longitude=position.coords.longitude;
          },
          error => {
            console.log(error);
          }
        );
      } 
});

const updateSelectedGugunList = ()=>{
  selectedGugunList.value=gugunList[selectedSidoCode.value];
}

const search = ()=>{
  attractionAPI.getConditionalData(
  selectedSidoCode.value,selectedGugunCode.value,selectedContentTypeId.value,
(response)=>{
  searchResult.value=response.data
  console.log("관광지 조회에 성공했습니다.")
  moveMap()
}, 
()=>console.log("관광지 조회에 실패했습니다."))
}

const onLoadMap = (map) => {
  mapRef.value = map
}

const moveMap = () => {
  const bounds = searchResult.value.reduce((acc, item) => {
    if (!acc) {
      return new window.naver.maps.LatLngBounds(
        new window.naver.maps.LatLng(item.latitude, item.longitude),
        new window.naver.maps.LatLng(item.latitude, item.longitude),
      )
    }
    acc.extend(new window.naver.maps.LatLng(item.latitude, item.longitude))
    return acc
  }, null)

  mapRef.value.panToBounds(bounds, morphOption, { top: 60, left: 60, right: 60, bottom: 60 })    
}

const selectMarker = (item) => {
  selectAttraction.value=item;
};

import { useUserPlanStore } from "@/stores/userPlan";
import { storeToRefs } from "pinia";
const userPlanStore = useUserPlanStore() 
const { curPlan, curDayNum } = storeToRefs(userPlanStore)

const addAttraction = () => {
  console.log("관광지 추가 시도: "+ selectAttraction.value)
    curPlan.value[curDayNum.value].plan.push({
      type: 'attraction',
      content: selectAttraction.value
    })
}

</script>

<template>

  <div class="page">

    <div class="mapContainer">
      <naver-map style="width: 1600px; height: 1300px" :map-options="mapOptions" @on-load="onLoadMap($event)">
        <div v-for="item in searchResult" :key="item.attractionId">
          <naver-marker @click="selectMarker(item)" :latitude="item.latitude" :longitude="item.longitude"/>
        </div>
      </naver-map>


      <div class="widgetContainer">
      <div class="searchContainer">
        <select class="form-select" v-model="selectedSidoCode" @click="updateSelectedGugunList">
          <option v-for="sido in sidoList" :key="sido.name" :value="sido.value">{{ sido.name }}</option>
        </select>
        <select class="form-select" v-model="selectedGugunCode">
          <option v-for="(value, name) in selectedGugunList" :key="name" :value="value">{{ name }}</option>
        </select>
        <select class="form-select" v-model="selectedContentTypeId">
          <option v-for="contentType in contentTypeList" :key="contentType.name" :value="contentType.value">{{ contentType.name }}</option>
        </select>
        <button class="btn" @click="search">검색</button>
      </div>
      <div v-if="selectAttraction!=null" class="selectContainer" :v-model="selectAttraction">
        <button type="button" class="svgCloseButton" @click="selectAttraction=null">
          <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" id="Capa_1" x="0px" y="0px" viewBox="0 0 512.021 512.021" style="enable-background:new 0 0 512.021 512.021;" xml:space="preserve" width="20" height="25">
            <path d="M301.258,256.01L502.645,54.645c12.501-12.501,12.501-32.769,0-45.269c-12.501-12.501-32.769-12.501-45.269,0l0,0   L256.01,210.762L54.645,9.376c-12.501-12.501-32.769-12.501-45.269,0s-12.501,32.769,0,45.269L210.762,256.01L9.376,457.376   c-12.501,12.501-12.501,32.769,0,45.269s32.769,12.501,45.269,0L256.01,301.258l201.365,201.387   c12.501,12.501,32.769,12.501,45.269,0c12.501-12.501,12.501-32.769,0-45.269L301.258,256.01z"/>
          </svg>
        </button>
        
        <img class="selectAttractionImg" :src="selectAttraction.firstImage=='' ? 'src/assets/no_image.jpg' : selectAttraction.firstImage">
        
        <div class="selectAttractionDataContainer">
          <p class="title">{{ selectAttraction.title }}</p>
          <p class="address">{{ selectAttraction.addr1}}</p>

          <div class="buttonContainer">
              <button type="button" class="svgButton" @click="addAttraction">
            <svg xmlns="http://www.w3.org/2000/svg" id="Layer_1" data-name="Layer 1" viewBox="0 0 24 24" width="25" height="25">
              <path d="M10.5,16.5h-2.5v-3h2.5v-2.5h3v2.5h2.5v3h-2.5v2.5h-3v-2.5ZM24,5.5V24H0V5.5c0-1.93,1.57-3.5,3.5-3.5h2.5V0h3V2h6V0h3V2h2.5c1.93,0,3.5,1.57,3.5,3.5Zm-3,3.5H3v12H21V9Z"/>
            </svg>
          </button>

          </div>
          
          <p class="description">{{ selectAttraction.overview }}</p>
          <input type="checkbox" class="card-content__more-btn">
        </div>
      </div>
    </div>

    </div>

  </div>

</template>

<style lang="scss" scoped>

.page {
  display: flex;
  width:100%;
  height:100%;
}

.mapContainer {
  position: relative;
  width: 100%; 
  height: 1000px;
}

.widgetContainer{
  background-color: transparent;
}
.searchContainer {
  position: absolute;
  width: 300px;
  top: 0px;
  left: 0px;
  margin: 20px;
}
.form-select {
  margin-bottom: 5px;
}
.selectContainer {
  margin: 20px;
  width: 300px;
  position: absolute;
  bottom: 0px;
  left: 0px;
}
.selectAttractionImg {
  border-radius: 25px 25px 0px 0px;
}
.buttonContainer {
  display: flex;
  flex-direction: row;
  margin: 10px 0px 15px 0px;
}
.selectAttractionDataContainer{
  background: linear-gradient(to bottom left, #e1eec3, #f05053);
  padding: 20px;
  border-radius: 0px 0px 25px 25px;
}
.selectAttractionTitle {
  color: #272343;
}

.title {
  font-size: 1.4em;
  font-weight: 600;
}
.address {
  font-size: 1.2em;
  font-weight: 900;
}
.description {
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical; 
  overflow: hidden;
  margin-bottom: 15px;
}

.card-content__more-btn {
  appearance: none;
  border: 1.5px solid black;
  padding: 5px;
  border-radius: 5px;
  cursor: pointer;
}
.card-content__more-btn::before {
  content: '더보기';
}
.card-content__more-btn:checked::before {
  content: '닫기';
}
.description:has(+ .card-content__more-btn:checked) {
  -webkit-line-clamp:unset
}

.btn {
  background: linear-gradient(to bottom left, #e1eec3, #f05053);
  border:none;
  color: rgba(255, 255, 255);
  font-weight: bolder;
  width: 300px;
}

.svgCloseButton {
  margin-bottom: 7px;
  margin-left: 7px; 
}

.svgButton {
  margin-top: 4px;
  float:right;
}

</style>