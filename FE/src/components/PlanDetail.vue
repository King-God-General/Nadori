<script setup>

/*
onMounted로 완전 초기 세팅을 진행한다.

plan에서 startDate, endDate를 받아와서
그 날짜 차이만큼 포맷를 만들어....
만약 이틀짜리 여행이면
const contents = ref(
    {1: {null}},
    {2: {null}}, ...
)
이런 식으로 생기는 거임ㅇㅇ

그리고 버튼이 있어....
이걸 클릭할 때마다 
const curDate = ref()
얘 값이 바뀌고

그러면 v-for = contents[curDate] 여서 
우리 눈에 보이는 애가 달라지는 거임ㅇㅇ

내가 Map에서 버튼 딸깍하면
contents[curDate].push(item)되는 거ㅇㅇㅇㅇ

*/

import { ref } from "vue";
import contentCard from "@/components/contentCard.vue";
import { VueToggles } from "vue-toggles";

const curDay = ref(1);
const curPlan = ref([
    {
        dayNum: 1,
        contents: [
            { memo: "1일차 테스트 메모" },
            {
                attraction: {
                    "contentId": "126486",
                    "overview": "서울시 강남구 신사동에 자리한 근린공원이다. 1970년 3월 10일 박정희 전 대통령은 도산 안창호 선생이 이 나라 자주와 독립을 위해 바친 위대한 애국정신과 민중의 교화를 위한 교육정신을 국민의 귀감으로 삼게 하고자 도산공원을 조성할 것을 지시한다. 이에 서울시는 1973년 강남구 신사동에 약 1만여평 공원을 조성하고, 망우리 공동묘지에서 선생의 유해를 이장하는 동시에 미국 로스앤젤레스에서 부인 이혜련 여사의 유해를 옮겨와 합장하였다. 그 후, 강남구 청담동에서 논현동에 이르는 노폭 50m 연장 3250m의 신설가로를 도산대로로 명명하였다. 공원 내에는 도산 선생 기념물로 동상 1기, 어록비 1기, 비문해설기 1기 등이 설치되어 있으며, 매년 3월 10일 이곳에서 홍사단 및 도산기념 사업회 주관으로 도산 선생 추모 기념 행사를 거행한다.",
                    "cat1": "A01",
                    "cat2": "A0101",
                    "cat3": "A01010500",
                    "title": "도산공원",
                    "addr1": "서울특별시 강남구 도산대로45길 20",
                    "addr2": "",
                    "zipcode": "6020",
                    "contentTypeId": 12,
                    "firstImage": "http://tong.visitkorea.or.kr/cms/resource/60/2678560_image2_1.jpg",
                    "firstImage2": null,
                    "sidoCode": 1,
                    "gugunCode": 1,
                    "latitude": 37.52146,
                    "longitude": 127.03381
                }
            }
        ]
    },
    {
        dayNum: 2,
        contents: [
            { memo: "2일차 테스트 메모" },
            {
                attraction: {
                    "contentId": "126525",
                    "overview": "선릉에는 조선 제9대 성종과 그 계비 정현왕후 윤씨를 모신 선릉과 제11대 중종을 모신 정릉이 있다. 성종은 세조의 손자이며 덕종(추존)의 둘째 아들로 세조 3년(1457)에 탄생하여 13세(1469)때 즉위하고 재위 25년간 학문을 장려하고 제도를 정비하는 등 많은 치적을 남겼으며 38세(1494)로 승하하였다. 정현왕후는 우상 영원 부원군 윤호의 딸로서, 성종 11년 (1480)에 비로 책봉되고 중종 25년(1530)에 69세로 승하하였다. 중종은 성종의 둘째 아들로, 성종 19년(1488)에 탄생하여 1506년에 즉위하고, 재위 39년(1544) 57세로 승하하였다. 사적인 선정릉은 도시 가운데 있으면서도, 그리 잘 알려져 있지 않아 한적한 편이며, 조용히 산책을 즐길 수 있다. 또한, 능을 둘러싸고 있는 철망 울타리를 철거하여 숲이 있는 구간에는 고풍스러운 담장을 쌓아 돌담길을 만들었다.",
                    "cat1": "A02",
                    "cat2": "A0201",
                    "cat3": "A02010700",
                    "title": "서울 선릉(성종과 정현왕후)과 정릉(중종) [유네스코 세계문화유산]",
                    "addr1": "서울특별시 강남구 선릉로100길 1",
                    "addr2": "",
                    "zipcode": "6153",
                    "contentTypeId": 12,
                    "firstImage": "",
                    "firstImage2": null,
                    "sidoCode": 1,
                    "gugunCode": 1,
                    "latitude": 37.50742,
                    "longitude": 127.04718
                }
            }
        ]
    },
    {
        dayNum: 3,
        contents: []
    }
]

);

const dayCnt = ref([1, 2, 3]);
const curDayNum = ref(1);
const isRelease = ref(false)

const changeCurDayNum = (n) => {
    curDayNum.value = n;
    console.log(curDayNum.value);
}


</script>

<template>

    <div class="page">

        

        <div class="dayNavContainer">
            <input class="dayButton" type="button" v-for="day in dayCnt" :key="day" :value="'Day ' + day"
                @click="changeCurDayNum(day)" :class="{ 'selectedDayButton': curDayNum === day }">
        </div>

        <div class="contentsContainer">

            <contentCard></contentCard>

            <button class="btn addMemoButton" @click="">
                <svg xmlns="http://www.w3.org/2000/svg" id="Layer_1" data-name="Layer 1" viewBox="0 0 24 24" width="24"
                    height="24">
                    <path
                        d="m20.5,0H3.5C1.57,0,0,1.57,0,3.5v20.5h18.381l5.619-5.664V3.5c0-1.93-1.57-3.5-3.5-3.5ZM3,3.5c0-.275.224-.5.5-.5h17c.276,0,.5.225.5.5v12.5h-5v5H3V3.5Zm7.5,12.5v-2.5h-2.5v-3h2.5v-2.5h3v2.5h2.5v3h-2.5v2.5h-3Z" />
                </svg>
            </button>
        </div>

        <div class="memoContainer">
            <label for="memo">메모</label>
            <textarea class="form-control" name="memo" id="memo" cols="10" rows="5"></textarea>
        </div>

        <div class="buttonNavContainer">
            <button class="btn" @click="">저장하기</button>


            <div class="toggle">
                <div class="toggleTitle">다른 사람들과 공유하시겠어요?</div>
                <VueToggles :v-model="isRelease" :height="30" :width="70" checkedText="On" uncheckedText="Off"
                    fontSize="18" checkedBg="#162F65" uncheckedBg="grey"></VueToggles>
            </div>
        </div>



    </div>
</template>

<style scoped>
.page {}

.dayNavContainer {
    margin: 20px 0px 0px 20px;
}

.addMemoButton {
    background-color: #E7E6DD;
    border-radius: 10px;
    width: fit-content;
    fill: #3361AC;
}

.contentsContainer {
    background-color: #3361AC;
    margin: 0px 20px;
    padding: 20px;
    border-radius: 0px 15px 15px 15px;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.memoContainer {
    margin: 20px;
    padding: 20px;
    border-radius: 0px 15px 15px 15px;
    background-color: #E7E6DD;
}

.dayButton {
    font-size: 1.1em;
    color: #3361AC;
    background-color: white;

    border-radius: 10px 10px 0px 0PX;
    padding: 10px;
    font-weight: bolder;
    cursor: pointer;
}

button {
    background-color: #3361AC;
    color: rgba(255, 255, 255);
    font-weight: bolder;
    margin-top: 5px;
    margin-right: 5px;
}

.toggleTitle {
    margin-top: 2px;
    margin-right: 15px;
}

.selectedDayButton {
    background-color: #3361AC;
    color: white;
}

.buttonNavContainer {
    display: flex;
    align-items: center;
    margin: 20px;
}

.city-buttons {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
}

.city-buttons li {
    cursor: pointer;
}

.isSelected {
    background: #333;
    color: #fff
}

.toggle {
    display: flex;
    margin-left: 20px;
    margin-top: 5px;
}
</style>