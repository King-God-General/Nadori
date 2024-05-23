<script setup>
import PlanForm from '@/components/PlanForm.vue'
import Map from '@/components/Map.vue'
</script>

<template>
  <div class="main">
    <PlanForm class="plan"></PlanForm>
    <Map class="map"></Map>
  </div>
</template>
<style scoped>
.main {
  padding-top: 100px;
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
