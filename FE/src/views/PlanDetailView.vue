<script setup>
import PlanDetail from '@/components/PlanDetail.vue'
import MapDetail from '@/components/MapDetail.vue'
import {ref} from 'vue'
</script>

<template>
    <div class="main">
        <PlanDetail class="plan"></PlanDetail>
        <MapDetail class="map"></MapDetail>

        
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
