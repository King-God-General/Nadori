<script setup>
import { defineProps  } from 'vue';
const item = defineProps(['item'])
const type = item.item.type;
const content = item.item.content;
</script>

<template>
    <div v-if="type=='memo'" class="memo planCard">
        <textarea class="form-control" name="memo" id="memo" cols="auto" rows="auto" :value="content" read></textarea>
    </div>
    <div v-else-if="type=='attraction'" class="attraction planCard">
        <img :src="content.firstImage=='' ? 'src/assets/no_image.jpg' : content.firstImage">
        <div class="dataContainer">
            <p class="title">{{ content.title }}</p>
            <p class="address">{{ content.addr1 }}</p>
        </div>
    </div>

</template>

<style scoped>
.planCard {
    min-width: 441px;
    width: 100%;
    padding: 20px;
    margin-bottom: 10px;
    border-radius: 5px;

    display: flex;
    flex-direction: row;
    word-wrap: break-word;
}

.memo {
    background-color: white;
    text-align: center;
    
    overflow: auto;
}

.attraction {
    background-color: white;
}

img {
    width:250px;
    border-radius: 10px;
}

.title {
    font-size: 1.3em;
    font-weight: bold;
}

.dataContainer {
    margin-left: 20px;
    display: flex;
    flex-direction: column;
    justify-content: center;
}
</style>