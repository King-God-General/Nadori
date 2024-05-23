<script setup>
import { defineProps } from 'vue'
import PlanModifyForm from '@/components/PlanModifyForm.vue'
import Map from '@/components/Map.vue'
const props = defineProps(['planId'])
</script>

<template>
  <div class="main">
    <PlanModifyForm class="plan" :planId="props.planId"></PlanModifyForm>
    <Map class="map"></Map>
  </div>
</template>

<style scoped>
.main {
  display: flex;
  flex-direction: row;
}
.plan {
  flex: 0 0 450px;
}
.map {
  width: auto;
}
</style>
