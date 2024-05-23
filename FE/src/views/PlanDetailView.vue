<script setup>
import { NaverMap, NaverMarker } from 'vue3-naver-maps'
import { ref, watch, onMounted, defineProps } from 'vue'
import { useRouter } from 'vue-router'
import { useNadoriStore } from '@/stores/nadori'
import { storeToRefs } from 'pinia'
import contentCard from '@/components/contentCard.vue'
import contentAPI from '@/apis/content'
import planAPI from '@/apis/plan'
import Swal from 'sweetalert2'
import PlanFormView from './PlanFormView.vue'

const router = useRouter()
const store = useNadoriStore()
const { member, plan, planDetail, curDayNum } = storeToRefs(store)

const props = defineProps(['planId'])

const mapRef = ref(null)
const morphOption = {
  duration: 300,
  easing: 'easeInCubic'
}
const mapOptions = {
  latitude: 37.51347, // 지도 중앙 위도
  longitude: 127.041722, // 지도 중앙 경도
  zoom: 20
}
const moveMap = () => {
  console.log(curDayAttraction.value)
  if (curDayAttraction.value != {}) {
    const bounds = curDayAttraction.value.reduce((acc, item) => {
      if (!acc) {
        return new window.naver.maps.LatLngBounds(
          new window.naver.maps.LatLng(item.latitude - 0.000001, item.longitude - 0.000001),
          new window.naver.maps.LatLng(item.latitude + 0.000001, item.longitude + 0.000001)
        )
      }
      acc.extend(new window.naver.maps.LatLng(item.latitude, item.longitude))
      return acc
    }, null)
    mapRef.value.panToBounds(bounds, morphOption, { top: 60, left: 60, right: 60, bottom: 60 })
  }
}

const dayCnt = ref([])
const curDayAttraction = ref(null)

const onLoadMap = (map) => {
  mapRef.value = map
}

const makeDayArray = () => {
  const start = new Date(plan.value.startDate)
  const end = new Date(plan.value.endDate)
  const diffTime = Math.abs(end - start)
  const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)) + 1
  return Array.from({ length: diffDays }, (_, i) => i + 1)
}

//일자 변경
const changeCurDayNum = (n) => {
  curDayNum.value = n
}

//api 데이터 전처리
const reverseTransformPlanDetail = (json) => {
  const result = {}
  dayCnt.value.forEach((n) => {
    result[n] = { plan: [] }
  })
  json.forEach((item) => {
    const { dayNum, contentNum, memo, attraction } = item
    if (memo) {
      result[dayNum].plan.push({ type: 'memo', content: memo })
    } else if (attraction) {
      result[dayNum].plan.push({ type: 'attraction', content: attraction })
    }
  })
  return result
}

//플랜 수정
const modifyPlan = () => {
  Swal.fire({
    text: '플랜을 수정하시겠어요?',
    icon: 'question',
    iconColor: '#f7a200',
    showCancelButton: true,
    confirmButtonColor: '#f7a200',
    confirmButtonText: 'O',
    cancelButtonColor: '#f7a200',
    cancelButtonText: 'X'
  }).then((result) => {
    if (result.isConfirmed) {
      router.push(`/plan/form/${plan.value.planId}`)
    }
  })
}

//플랜 삭제
const removePlan = () => {
  Swal.fire({
    text: '한번 삭제한 플랜은 되돌릴 수 없어요!',
    icon: 'warning',
    iconColor: '#d33',
    showCancelButton: true,
    confirmButtonColor: '#f7a200',
    cancelButtonColor: '#f7a200',
    confirmButtonText: '그래도, 삭제할래요!',
    cancelButtonText: '좀 더 생각해볼게요.'
  }).then((result) => {
    if (result.isConfirmed) {
      planAPI.deletePlan(
        props.planId,
        () => {
          Swal.fire({
            icon: 'success',
            confirmButtonColor: '#f7a200',
            text: '성공적으로 삭제되었어요!'
          })
        },
        () => {
          console.log('플랜 삭제 실패')
        }
      )
      router.push(`/home`)
    }
  })
}

const transformPlanDetail = () => {
  const result = []
  Object.keys(planDetail.value).forEach((dayNum) => {
    planDetail.value[dayNum].plan.forEach((item, index) => {
      const transformedItem = {
        contentId: null,
        dayNum: parseInt(dayNum),
        contentNum: index + 1,
        memo: item.type === 'memo' ? item.content : null,
        attraction: item.type === 'attraction' ? item.content : null
      }
      result.push(transformedItem)
    })
  })
  return result
}

//플랜 삭제
const copyPlan = () => {
  Swal.fire({
    text: '플랜을 복사하시겠어요?',
    icon: 'question',
    iconColor: '#f7a200',
    showCancelButton: true,
    confirmButtonColor: '#f7a200',
    cancelButtonColor: '#f7a200',
    confirmButtonText: '복사할게요!',
    cancelButtonText: '좀 더 생각해볼게요.'
  }).then((result) => {
    if (result.isConfirmed) {
      plan.value.writer = member.value

      planAPI.postPlan(
        plan.value,
        (response) => {
          plan.value.planId = response.data
          contentAPI.postContents(
            plan.value.planId,
            transformPlanDetail(),
            () => {
              Swal.fire({
                icon: 'success',
                confirmButtonColor: '#f7a200',
                text: '성공적으로 복사되었어요!'
              })
              router.push(`/plan/form/${plan.value.planId}`)
            },
            () => {
              console.log('현재 플랜에 대한 콘텐츠 복사에 실패했습니다.')
            }
          )
        },
        () => {
          console.log('플랜 저장에 실패했습니다.')
        }
      )
    }
  })
}

const filterAttraction = (n) => {
  if (planDetail.value[n].plan == {}) {
    curDayAttraction.value = []
  } else {
    curDayAttraction.value = planDetail.value[n].plan
      .filter((item) => item.type === 'attraction')
      .map((item) => item.content)
  }

  console.log(curDayAttraction.value)
}

//감시할 값들에 대한 watch
watch(planDetail, () => {
  console.log('planDetail에 대한 watch 실행: ')
  filterAttraction(1)
})
watch(curDayNum, () => {
  console.log('일자 변경에 대한 watch 실행')
  filterAttraction(curDayNum.value)
})
watch(curDayAttraction, () => {
  if (curDayAttraction.value.length != 0) moveMap()
})

//데이터 초기화
onMounted(() => {
  console.log(props.planId)

  planAPI.getPlan(
    props.planId,
    (response) => {
      plan.value = response.data
      dayCnt.value = makeDayArray()
      console.log('plan 데이터 초기화 성공: ' + JSON.stringify(plan.value))
    },
    () => {
      console.log('플랜 데이터를 불러오는 데 실패했습니다.')
    }
  )

  contentAPI.getContents(
    props.planId,
    (response) => {
      planDetail.value = reverseTransformPlanDetail(response.data)
      console.log('planDetail 데이터 초기화 성공')
    },
    () => {
      console.log('플랜에 대한 콘텐츠를 불러오는 데 실패했습니다.')
    }
  )
})
</script>

<template>
  <div class="main">
    <div class="plan">
      <div v-if="plan != null" class="page">
        <div class="rowContainer">
          <div class="headerContainer">
            <p class="title header">{{ plan.title }}</p>
            <p class="description header">: {{ plan.description }}</p>
            <p class="date header">{{ plan.startDate }} ~ {{ plan.endDate }}</p>
          </div>

          <div class="dayNavContainer">
            <input
              class="dayButton"
              type="button"
              v-for="day in dayCnt"
              :key="day"
              :value="'Day ' + day"
              @click="changeCurDayNum(day)"
              :class="{ selectedDayButton: curDayNum === day }"
            />
          </div>

          <div class="contentContainer">
            <div class="contentsContainer overflow-auto">
              <contentCard
                v-for="item in planDetail[curDayNum].plan"
                :key="
                  item.type == 'memo'
                    ? item.type + item.content
                    : item.type + item.content.attractionId
                "
                :item="item"
                @click="clickAttractionCard(item)"
              >
              </contentCard>
            </div>
          </div>

          <div class="buttonNavContainer">
            <div v-if="member != null && member.memberId == plan.writer.memberId">
              <button class="button" @click="modifyPlan">수정하기</button>
              <button class="button" @click="removePlan">삭제하기</button>
            </div>

            <button
              v-else-if="member != null && member.memberId != plan.writer.memberId"
              class="button"
              @click="copyPlan"
            >
              복사하기
            </button>
          </div>
        </div>
      </div>
    </div>

    <div class="map">
      <div class="page">
        <div class="mapContainer">
          <naver-map
            style="width: 1600px; height: 1300px"
            :map-options="mapOptions"
            @on-load="onLoadMap($event)"
          >
            <div v-for="item in curDayAttraction" :key="item.attractionId">
              <naver-marker :latitude="item.latitude" :longitude="item.longitude" />
            </div>
          </naver-map>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.main {
  display: flex;
  flex-direction: row;
}

.plan {
  flex: 0 0 450px;
  margin: 10px;
}

.map {
  width: auto;
}

.mapContainer {
  position: relative;
  width: 100%;
  height: 1000px;
}

.widgetContainer {
  background-color: transparent;
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

.buttonContainer {
  display: flex;
  flex-direction: row;
  margin: 10px 0px 15px 0px;
}

.headerContainer {
  padding: 20px;
  border-radius: 25px 0px;
  background: linear-gradient(to bottom left, rgb(247, 209, 86), rgb(247, 162, 0));
  color: white;
}

.title {
  font-size: 1.2em;
  font-weight: bolder;
  margin-bottom: 20px;
}

.description {
  font-size: 1em;
  font-weight: bolder;
}

.date {
  font-size: 0.9em;
  margin-bottom: 5px;
}

.address {
  font-size: 1.2em;
  font-weight: 900;
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

.rowContainer {
  margin-bottom: 10px;
}

.contentContainer {
  padding: 30px;
  border-radius: 0px 25px;
  height: auto;
  background: linear-gradient(to bottom left, rgb(247, 209, 86), rgb(247, 162, 0));
}

.dayNavContainer {
  margin: 8px 0px 8px 20px;
}

.dayButton {
  font-size: 0.9em;
  color: rgb(248, 228, 192);
  padding: 0px 10px;
  font-weight: bolder;
}

.selectedDayButton {
  color: rgb(247, 162, 0);
}

.contentsContainer {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
}

.buttonNavContainer {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  margin-top: 5px;
}
</style>
