<script setup>
import Plan from '@/components/Plan.vue'
import Map from '@/components/Map.vue'
import PlanDetail from '@/components/PlanDetail.vue'

</script>

<template>
  <div class="main">
    <!-- <Plan class="plan"></Plan> -->
    <PlanDetail class="planDetail"></PlanDetail>
    <Map class="map"></Map>
  </div>
</template>

<style scoped>
.main {
  display: flex;
  flex-direction: row;
}
.plan {
  width: 40%;
}
.planDetail {
  width: 40%;
}
.map {
  /* background-color: rgb(140, 127, 255); */
  width: 60%;
}
</style>
