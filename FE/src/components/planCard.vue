<script setup>
import { defineProps, onMounted } from 'vue'
const data = defineProps(['item'])
const item = data.item;
</script>

<template>
    <div class="planCard">
        <div class="headerContainer">
            <p class="title header">{{ item.title }}</p>
            <p class="description header">: {{ item.description }}</p>
            <p class="date header">{{ item.startDate }} ~ {{ item.endDate }}</p>
        </div>
    </div>
</template>

<style scoped>
p {
    margin-bottom: 0px;
}

.planCard {
    padding: 20px;
    border-radius: 10px;
    background-color: rgb(247, 162, 0);
    margin-bottom: 10px;
}

.title {
    font-size: 1.3em;
    font-weight: bold;
}
.description {
    font-size: 1.2em;
    margin-bottom: 10px;
}
.date {

}
</style>
