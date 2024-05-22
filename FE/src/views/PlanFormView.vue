<script setup>
import { defineProps } from 'vue'
import PlanForm from '@/components/PlanForm.vue'
import Map from '@/components/Map.vue'
const props = defineProps(['planId'])
</script>

<template>
  <div class="main">
    <PlanForm class="plan" :planId="props.planId"></PlanForm>
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
