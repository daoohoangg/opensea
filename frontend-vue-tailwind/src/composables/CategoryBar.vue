<template>
  <div class="flex flex-col md:flex-row md:items-center md:justify-between bg-[#121212] p-4 text-white gap-4">
    <!-- Category Filters -->
    <div class="flex gap-2 flex-wrap md:ml-56 justify-center">
      <button
        v-for="category in categories"
        :key="category"
        @click="selectCategory(category)"
        :class="[
          'px-4 py-1 rounded-md border border-gray-600 text-sm',
          selectedCategory === category
            ? 'bg-gray-100 text-black font-bold'
            : 'bg-transparent hover:bg-gray-800'
        ]"
      >
        {{ category }}
      </button>
    </div>

    <!-- Icon Filters -->
    <div class="flex gap-2 items-center justify-center">
      <button
        v-for="(icon, index) in iconFilters"
        :key="index"
        class="w-5 h-5 rounded-full"
        :style="{ backgroundColor: icon }"
        @click="selectIcon(icon)"
      />
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const categories = [
  'All',
  'Gaming',
  'Art',
  'PFPs',
  'Memberships',
  'Music',
  'Photography'
]

const iconFilters = [
  '#4ADE80', // green
  '#60A5FA', // blue
  '#FBBF24', // yellow
  '#3B82F6', // darker blue
  '#A855F7', // purple
]

const selectedCategory = ref('All')
const selectedIcon = ref(null)

function selectCategory(category) {
  selectedCategory.value = category
}

function selectIcon(icon) {
  selectedIcon.value = icon
}
</script>
