<template>
  <div class="flex justify-end w-full">
    <div class="relative bg-[#181818] p-4 rounded-md w-full max-w-md text-white">
      <!-- Header -->
      <div class="flex items-start justify-between mb-4">
        <div class="flex space-x-2">
          <button class="bg-[#2A2A2A] text-white text-xs px-2 py-1 rounded font-medium">Top</button>
          <button class="text-gray-400 text-xs px-2 py-1 rounded hover:bg-[#2A2A2A]">Trending</button>
        </div>
        <div class="relative">
          <button @click="toggleDropdown('filter')" class="text-xs text-gray-300 bg-[#2A2A2A] px-2 py-1 rounded cursor-pointer">
            1d ▼
          </button>
          <ul
            v-if="dropdowns.filter"
            class="absolute right-0 mt-1 bg-[#2A2A2A] text-xs text-gray-300 rounded shadow-md z-10"
          >
            <li class="px-3 py-1 hover:bg-[#333] cursor-pointer">All</li>
            <li class="px-3 py-1 hover:bg-[#333] cursor-pointer">30d</li>
            <li class="px-3 py-1 hover:bg-[#333] cursor-pointer">7d</li>
            <li class="px-3 py-1 hover:bg-[#333] cursor-pointer">1d</li>
            <li class="px-3 py-1 hover:bg-[#333] cursor-pointer">1h</li>
            <li class="px-3 py-1 hover:bg-[#333] cursor-pointer">1m</li>
          </ul>
        </div>
      </div>

      <!-- Table header -->
      <div class="grid grid-cols-3 text-gray-400 text-xs mb-2 px-2">
        <div>Collection</div>
        <div class="text-center">Floor</div>
        <div class="text-right">Change</div>
      </div>

      <!-- List items -->
      <div
        v-for="item in collections"
        :key="item.name"
        class="grid grid-cols-3 items-center px-2 py-2 hover:bg-[#222] rounded"
      >
        <div class="flex items-center space-x-2 truncate">
          <img :src="item.image" alt="logo" class="w-5 h-5 rounded-full" />
          <span class="truncate text-sm">{{ truncateText(item.name, 18) }}</span>
          <svg
            v-if="item.verified"
            xmlns="http://www.w3.org/2000/svg"
            class="w-4 h-4 text-blue-400"
            viewBox="0 0 24 24"
            fill="currentColor"
          >
            <path d="M12 2L15 8H21L16 13L18 20L12 16L6 20L8 13L3 8H9L12 2Z" />
          </svg>
        </div>
        <div class="text-center text-sm">{{ item.floor }}</div>
        <div
          class="text-right text-sm font-medium"
          :class="{
            'text-green-400': item.change.startsWith('+'),
            'text-red-500': item.change.startsWith('-'),
            'text-gray-400': item.change === '0%'
          }"
        >
          {{ item.change }}
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const collections = [
  {
    name: 'Pudgy Penguins',
    image: 'https://example.com/pudgy.png',
    floor: '$25K',
    change: '-5%',
    verified: false,
  },
  {
    name: 'Bored Ape Yacht Club',
    image: 'https://example.com/bayc.png',
    floor: '$32K',
    change: '+2.8%',
    verified: true,
  },
  {
    name: 'Courtyard.io',
    image: 'https://example.com/courtyard.png',
    floor: '$8.68',
    change: '+2.9%',
    verified: true,
  },
  {
    name: 'CryptoPunks',
    image: 'https://example.com/cryptopunks.png',
    floor: '$119.8K',
    change: '-1.3%',
    verified: true,
  },
]

function truncateText(text, length) {
  return text.length > length ? text.slice(0, length) + '…' : text
}

const dropdowns = ref({
  filter: false,
})

function toggleDropdown(menu) {
  dropdowns.value[menu] = !dropdowns.value[menu]
}
</script>
