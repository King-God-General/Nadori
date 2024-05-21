<script setup>
import PlanDetail from '@/components/PlanDetail.vue'
import MapDetail from '@/components/MapDetail.vue'
import { defineProps, ref, watch } from 'vue'
import { useNadoriStore } from '@/stores/nadori'
import { storeToRefs } from 'pinia'

const store = useNadoriStore()
const { member, planDetail, curDayNum } = storeToRefs(store)

const props = defineProps(['planId'])
const curDayNumPlan = ref(null)

watch(curDayNumPlan, () => console.log('curDayNumPlan: ' + curDayNumPlan.value))
</script>

<template>
  <div class="main">
    <div class="plan">
      <PlanDetail :planId="planId" @clickDayNum="curDayNumPlan"></PlanDetail>
    </div>
    <div class="map">
      <MapDetail :curDayNumPlan="curDayNumPlan"></MapDetail>
    </div>
  </div>
</template>

<style scoped>
.main {
  display: flex;
  flex-direction: row;
}

.plan {
  flex: 0 0 500px;
}

.map {
  width: auto;
}
</style>
