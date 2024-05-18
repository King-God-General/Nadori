<script setup>
import { defineProps  } from 'vue';
const item = defineProps(['item'])
const type = item.item.type;
const content = item.item.content;
</script>

<template>
    <div v-if="type=='memo'" class="planCard">
        <textarea class="form-control memo" name="memo" id="memo" cols="auto" rows="auto" :value="content" read></textarea>
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
    margin: 5px;
    border-radius: 5px;
    display: flex;
    flex-direction: row;
    word-wrap: break-word;
}

.memo {
    border: none;
    resize: none;
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