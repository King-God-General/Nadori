<script setup>
import { NaverMap, NaverMarker } from 'vue3-naver-maps'
import { ref, watch, onMounted } from 'vue';
import { useUserPlanStore } from "@/stores/userPlan";
import { storeToRefs } from "pinia";
import { defineProps } from 'vue';

const userPlanStore = useUserPlanStore();
const { curPlan, curDayNum } = storeToRefs(userPlanStore);


const mapOptions = {
    latitude: 37.51347, // 지도 중앙 위도
    longitude: 127.041722, // 지도 중앙 경도
    zoom: 20,
}

// const mapRef = ref(null)
// const morphOption = {
//     duration: 300,
//     easing: 'easeInCubic',
// }

// const attractionList = ref();
// onMounted(() => {
//       if (curPlan.value && curDayNum.value) {
//         console.log(curPlan.value[curDayNum.value].plan);
//         attractionList.value = curPlan.value[curDayNum.value].plan.filter(item => item.type === 'attraction');
//         console.log(curDayNum.value+"의 attraction들: "+attractionList.value);
//     }
// });
// watch(curDayNum, () => {
//     attractionList.value = curPlan.value[curDayNum.value].plan.filter(item => item.type === 'attraction');
//     console.log(curDayNum+"의 attraction들: "+attractionList.value);
// });

// const onLoadMap = (map) => {
//     mapRef.value = map
// }

// const props = defineProps(['selectAttraction']);

// const moveMap = () => {
//     const bounds = attractionList.value.reduce((acc, item) => {
//         if (!acc) {
//             return new window.naver.maps.LatLngBounds(
//                 new window.naver.maps.LatLng(item.latitude, item.longitude),
//                 new window.naver.maps.LatLng(item.latitude, item.longitude),
//             )
//         }
//         acc.extend(new window.naver.maps.LatLng(item.latitude, item.longitude))
//         return acc
//     }, null)

//     mapRef.value.panToBounds(bounds, morphOption, { top: 60, left: 60, right: 60, bottom: 60 })
// }

</script>

<template>

    <div class="page">

        <div class="mapContainer">

            <!-- <naver-map style="width: 1600px; height: 1300px" :map-options="mapOptions" @on-load="onLoadMap($event)">
                <div v-for="item in attractionList" :key="item.attractionId">
                    <naver-marker :latitude="item.latitude" :longitude="item.longitude" />
                </div>
            </naver-map> -->

            <!-- <div class="widgetContainer">
                <div v-if="props.selectAttraction != null" class="selectContainer" :v-model="props.selectAttraction">
                    <button type="button" class="svgCloseButton" @click="props.selectAttraction == null">
                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1"
                            id="Capa_1" x="0px" y="0px" viewBox="0 0 512.021 512.021"
                            style="enable-background:new 0 0 512.021 512.021;" xml:space="preserve" width="20"
                            height="25">
                            <path
                                d="M301.258,256.01L502.645,54.645c12.501-12.501,12.501-32.769,0-45.269c-12.501-12.501-32.769-12.501-45.269,0l0,0   L256.01,210.762L54.645,9.376c-12.501-12.501-32.769-12.501-45.269,0s-12.501,32.769,0,45.269L210.762,256.01L9.376,457.376   c-12.501,12.501-12.501,32.769,0,45.269s32.769,12.501,45.269,0L256.01,301.258l201.365,201.387   c12.501,12.501,32.769,12.501,45.269,0c12.501-12.501,12.501-32.769,0-45.269L301.258,256.01z" />
                        </svg>
                    </button>

                    <img class="selectAttractionImg"
                        :src="props.selectAttraction.firstImage == '' ? 'src/assets/no_image.jpg' : props.selectAttraction.firstImage">

                    <div class="selectAttractionDataContainer">
                        <p class="title">{{ props.selectAttraction.title }}</p>
                        <p class="address">{{ props.selectAttraction.addr1 }}</p>

                    </div>

                    <p class="description">{{ props.selectAttraction.overview }}</p>
                    <input type="checkbox" class="card-content__more-btn">
                </div>
            </div> -->

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
    -webkit-line-clamp: unset
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