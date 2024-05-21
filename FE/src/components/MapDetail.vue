<script setup>
import { NaverMap, NaverMarker } from 'vue3-naver-maps'
import { ref, watch, onMounted, defineProps } from 'vue'

const props = defineProps(['curDayNumPlan'])
const attractionList = ref(null)
const mapOptions = {
  latitude: 37.51347, // 지도 중앙 위도
  longitude: 127.041722, // 지도 중앙 경도
  zoom: 20
}

onMounted(() => {
  console.log('curDayNumPlan: ' + props.curDayNumPlan.value)
  attractionList.value = curDayNumPlan.value.filter((item) => item.type === 'attraction')
  console.log('mapDetail: ' + JSON.stringify(attractionList.value))
})

const mapRef = ref(null)
const morphOption = {
  duration: 300,
  easing: 'easeInCubic'
}

const onLoadMap = (map) => {
  mapRef.value = map
}
</script>

<template>
  <div class="page">
    <div class="mapContainer">
      <naver-map
        style="width: 1600px; height: 1300px"
        :map-options="mapOptions"
        @on-load="onLoadMap($event)"
      >
        <div v-for="item in attractionList" :key="item.attractionId">
          <naver-marker :latitude="item.latitude" :longitude="item.longitude" />
        </div>
      </naver-map>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.page {
  display: flex;
  width: 100%;
  height: 100%;
}

.mapContainer {
  position: relative;
  width: 100%;
  height: 1000px;
}

.widgetContainer {
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

.selectAttractionDataContainer {
  background: linear-gradient(to bottom left, rgb(248, 224, 145), rgb(247, 162, 0));
  padding: 20px;
  border-radius: 0px 0px 25px 25px;
  color: rgb(24, 42, 57);
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
  border: 1.5px solid rgb(24, 42, 57);
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
  -webkit-line-clamp: unset;
}

.btn {
  background: linear-gradient(50deg, rgb(248, 224, 145), rgb(247, 162, 0));
  border: none;
  color: rgb(24, 42, 57);
  font-weight: bolder;
  width: 300px;
}

.svgCloseButton {
  fill: rgb(24, 42, 57);
  margin-bottom: 7px;
  margin-left: 7px;
}

.svgButton {
  fill: rgb(24, 42, 57);
  margin-top: 4px;
  float: right;
}
</style>
