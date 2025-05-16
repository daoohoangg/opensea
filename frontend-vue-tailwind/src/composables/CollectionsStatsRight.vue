<template>
  <div class="flex justify-end h-screen">
    <div class="relative bg-[#131313] p-4 w-full max-w-md text-white">
      <!-- Header -->
      <div class="flex items-start justify-between mb-4">
        <div class="flex space-x-2">
          <button class="bg-[#2A2A2A] text-white text-s px-2 py-1 rounded font-medium">Top</button>
          <button class="text-gray-400 text-s px-2 py-1 rounded hover:bg-[#2A2A2A]">Trending</button>
        </div>
        
        <div class="relative">
          <label @click="toggleDropdown('filter')" class="text-xs text-gray-300 bg-[#2A2A2A] px-2 py-1 rounded cursor-pointer">
            {{ selectedTimeframe || 'All' }} ▼
          </label>
          <select
            v-if="dropdowns.filter"
            v-model="selectedTimeframe"
            @change="dropdowns.filter = false"
            class="absolute right-0 mt-1 bg-[#2A2A2A] text-xs text-gray-300 rounded shadow-md z-10"
          >
            <option class="px-3 py-1 hover:bg-[#333] cursor-pointer" value="All">All</option>
            <option class="px-3 py-1 hover:bg-[#333] cursor-pointer" value="30d">30d</option>
            <option class="px-3 py-1 hover:bg-[#333] cursor-pointer" value="7d">7d</option>
            <option class="px-3 py-1 hover:bg-[#333] cursor-pointer" value="1d">1d</option>
            <option class="px-3 py-1 hover:bg-[#333] cursor-pointer" value="1h">1h</option>
            <option class="px-3 py-1 hover:bg-[#333] cursor-pointer" value="1m">1m</option>
          </select>
        </div>
        <div class="relative group hover:bg-[#2A2A2A]" >
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6" >
            <path stroke-linecap="round" stroke-linejoin="round" d="M3.375 19.5h17.25m-17.25 0a1.125 1.125 0 0 1-1.125-1.125M3.375 19.5h7.5c.621 0 1.125-.504 1.125-1.125m-9.75 0V5.625m0 12.75v-1.5c0-.621.504-1.125 1.125-1.125m18.375 2.625V5.625m0 12.75c0 .621-.504 1.125-1.125 1.125m1.125-1.125v-1.5c0-.621-.504-1.125-1.125-1.125m0 3.75h-7.5A1.125 1.125 0 0 1 12 18.375m9.75-12.75c0-.621-.504-1.125-1.125-1.125H3.375c-.621 0-1.125.504-1.125 1.125m19.5 0v1.5c0 .621-.504 1.125-1.125 1.125M2.25 5.625v1.5c0 .621.504 1.125 1.125 1.125m0 0h17.25m-17.25 0h7.5c.621 0 1.125.504 1.125 1.125M3.375 8.25c-.621 0-1.125.504-1.125 1.125v1.5c0 .621.504 1.125 1.125 1.125m17.25-3.75h-7.5c-.621 0-1.125.504-1.125 1.125m8.625-1.125c.621 0 1.125.504 1.125 1.125v1.5c0 .621-.504 1.125-1.125 1.125m-17.25 0h7.5m-7.5 0c-.621 0-1.125.504-1.125 1.125v1.5c0 .621.504 1.125 1.125 1.125M12 10.875v-1.5m0 1.5c0 .621-.504 1.125-1.125 1.125M12 10.875c0 .621.504 1.125 1.125 1.125m-2.25 0c.621 0 1.125.504 1.125 1.125M13.125 12h7.5m-7.5 0c-.621 0-1.125.504-1.125 1.125M20.625 12c.621 0 1.125.504 1.125 1.125v1.5c0 .621-.504 1.125-1.125 1.125m-17.25 0h7.5M12 14.625v-1.5m0 1.5c0 .621-.504 1.125-1.125 1.125M12 14.625c0 .621.504 1.125 1.125 1.125m-2.25 0c.621 0 1.125.504 1.125 1.125m0 1.5v-1.5m0 0c0-.621.504-1.125 1.125-1.125m0 0h7.5" />
          </svg>
          <div
            class="absolute bottom-full left-1/2 -translate-x-1/2 mb-1 bg-black  text-white text-xs px-2 py-1 rounded opacity-0 group-hover:opacity-100 transition pointer-events-none whitespace-nowrap z-50"
          >
            Table
          </div>
        </div>
        <div class="hover:bg-[#2A2A2A]">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6" title="Table">
            <path stroke-linecap="round" stroke-linejoin="round" d="M3.75 12h16.5m-16.5 3.75h16.5M3.75 19.5h16.5M5.625 4.5h12.75a1.875 1.875 0 0 1 0 3.75H5.625a1.875 1.875 0 0 1 0-3.75Z" />
          </svg>
        </div>
        <div class="hover:bg-[#2A2A2A]">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6" title="Table">
            <path stroke-linecap="round" stroke-linejoin="round" d="m5.25 4.5 7.5 7.5-7.5 7.5m6-15 7.5 7.5-7.5 7.5" />
          </svg>
        </div>
      </div>

      <!-- Table header -->
      <div class="grid grid-cols-3 text-gray-400 text-xs mb-2 px-2">
        <div>COLLECTION</div>
        <div class="text-center">FLOOR</div>
        <div class="text-right">CHANGE</div>
      </div>

      <!-- List items -->
      <div
        v-for="item in filteredCollections"
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
import { ref, computed } from 'vue';

const selectedTimeframe = ref('All')

const dropdowns = ref({
  filter: false,
})
function toggleDropdown(menu) {
  dropdowns.value[menu] = !dropdowns.value[menu]
}

const collections = ref([
  {
    name: 'Pudgy Penguins',
    image: 'https://example.com/pudgy.png',
    floor: '$25K',
    change: '-5%',
    verified: false,
    createdAt: '2025-05-16T13:00:00Z',
  },
  {
    name: 'Bored Ape Yacht Club',
    image: 'https://example.com/bayc.png',
    floor: '$32K',
    change: '+2.8%',
    verified: true,
    createdAt: '2025-05-16T04:00:00Z',
  },
  {
    name: 'Courtyard.io',
    image: 'https://example.com/courtyard.png',
    floor: '$8.68',
    change: '+2.9%',
    verified: true,
    createdAt: '2025-05-16T14:20:00Z',
  },
  {
    name: 'CryptoPunks',
    image: 'https://example.com/cryptopunks.png',
    floor: '$119.8K',
    change: '-1.3%',
    verified: true,
    createdAt: '2025-05-15T14:00:00Z',
  },
])

function truncateText(text, length) {
  return text.length > length ? text.slice(0, length) + '…' : text
}

function getDuration(timeframe) {
  const multipliers = {
    m: 60 * 1000,
    h: 60 * 60 * 1000,
    d: 24 * 60 * 60 * 1000,
  }

  const match = timeframe.match(/^(\d+)([mhd])$/)
  if (!match) return null

  const value = parseInt(match[1], 10)
  const unit = match[2]
  return value * multipliers[unit]
}

const filteredCollections = computed(() => {
  if (selectedTimeframe.value === 'All') return collections.value

  const duration = getDuration(selectedTimeframe.value)
  if (!duration) return collections.value

  const now = Date.now()
  const cutoff = now - duration

  return collections.value.filter(item => new Date(item.createdAt).getTime() >= cutoff)
});

</script>