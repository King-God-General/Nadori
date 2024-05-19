<script setup>
import Plan from '@/components/Plan.vue'
import Map from '@/components/Map.vue'

</script>

<template>
  <div class="main">
    <Plan class="plan"></Plan>
    <Map class="map"></Map>
  </div>
</template>

<style scoped>
.main {
  display: flex;
  flex-direction: row;
}
.plan {
  flex: 0 0 600px;
}
.map {
  width: auto;
}
</style>
