<script setup>
import { defineProps, defineEmits } from 'vue'

const item = defineProps(['item'])
const emit = defineEmits(['close'])

const type = item.item.type
const content = item.item.content

function handleClose() {
  console.log(item.item)
  emit('close', item.item)
}
</script>

<template>
  <div v-if="type == 'memo'" class="planCard memo">
    <div class="buttonAndDataContainer">
      <button type="button" class="svgCloseButton" @click="handleClose">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          xmlns:xlink="http://www.w3.org/1999/xlink"
          version="1.1"
          id="Capa_1"
          x="0px"
          y="0px"
          viewBox="0 0 512.021 512.021"
          style="enable-background: new 0 0 512.021 512.021"
          xml:space="preserve"
          width="15"
          height="20"
        >
          <path
            d="M301.258,256.01L502.645,54.645c12.501-12.501,12.501-32.769,0-45.269c-12.501-12.501-32.769-12.501-45.269,0l0,0   L256.01,210.762L54.645,9.376c-12.501-12.501-32.769-12.501-45.269,0s-12.501,32.769,0,45.269L210.762,256.01L9.376,457.376   c-12.501,12.501-12.501,32.769,0,45.269s32.769,12.501,45.269,0L256.01,301.258l201.365,201.387   c12.501,12.501,32.769,12.501,45.269,0c12.501-12.501,12.501-32.769,0-45.269L301.258,256.01z"
          />
        </svg>
      </button>
      <textarea
        class="form-control memoCardEditor"
        name="memo"
        id="memo"
        cols="55"
        :value="content"
        readonly="readonly"
        style="max-height: 200px; overflow: hidden"
      ></textarea>
    </div>
  </div>

  <div v-else-if="type == 'attraction'" class="attraction planCard">
    <img :src="content.firstImage == '' ? 'src/assets/no_image.jpg' : content.firstImage" />

    <div class="buttonAndDataContainer">
      <button type="button" class="svgCloseButton" @click="handleClose">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          xmlns:xlink="http://www.w3.org/1999/xlink"
          version="1.1"
          id="Capa_1"
          x="0px"
          y="0px"
          viewBox="0 0 512.021 512.021"
          style="enable-background: new 0 0 512.021 512.021"
          xml:space="preserve"
          width="15"
          height="20"
        >
          <path
            d="M301.258,256.01L502.645,54.645c12.501-12.501,12.501-32.769,0-45.269c-12.501-12.501-32.769-12.501-45.269,0l0,0   L256.01,210.762L54.645,9.376c-12.501-12.501-32.769-12.501-45.269,0s-12.501,32.769,0,45.269L210.762,256.01L9.376,457.376   c-12.501,12.501-12.501,32.769,0,45.269s32.769,12.501,45.269,0L256.01,301.258l201.365,201.387   c12.501,12.501,32.769,12.501,45.269,0c12.501-12.501,12.501-32.769,0-45.269L301.258,256.01z"
          />
        </svg>
      </button>
      <div class="dataContainer">
        <p class="title">{{ content.title }}</p>
        <p class="address">{{ content.addr1 }}</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
p {
  margin-bottom: 0px;
}

.planCard {
  margin: 2px 4px;
  border-radius: 10px;
  display: flex;
  flex-direction: row;
}

.memo {
  background-color: white;
  padding-left: 10px;
}

.attraction {
  background-color: white;
}

img {
  width: 180px;
  border-radius: 10px 0px 0px 10px;
}

.title {
  font-size: 1em;
  font-weight: bold;
}
.address {
  font-size: 0.8em;
}

.svgCloseButton {
  margin: 8px 8px auto auto;
  fill: rgb(196, 194, 194);
}

.buttonAndDataContainer {
  display: flex;
  flex-direction: column;
}

.dataContainer {
  margin: 0px 20px;
  margin-bottom: 20px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.memoCardEditor {
  all: unset;
  padding: 20px;
}
</style>
