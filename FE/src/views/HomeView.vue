<script setup>
import '@/assets/tailwind.css'

const courses = [
  { id: 1, name: 'Beach Paradise', description: 'Relax and enjoy the sun on beautiful beaches.' },
  {
    id: 2,
    name: 'Mountain Adventure',
    description: 'Experience thrilling adventures in the mountains.'
  },
  {
    id: 3,
    name: 'City Exploration',
    description: 'Discover the vibrant culture of bustling cities.'
  }
]
</script>

<template>
  <div class="container mx-auto mt-8">
    <h1 class="text-3xl font-semibold mb-4">Welcome to Travel App</h1>
    <p class="text-lg">Check out our amazing travel courses!</p>

    <!-- 여행 코스 리스트 -->
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4 mt-8">
      <div v-for="(course, index) in courses" :key="index" class="border rounded p-4">
        <h2 class="text-xl font-semibold">{{ course.name }}</h2>
        <p>{{ course.description }}</p>
        <router-link :to="'/courses/' + course.id" class="text-blue-500 mt-2 block"
          >View Details</router-link
        >
      </div>
    </div>
  </div>
</template>
<style scoped></style>
