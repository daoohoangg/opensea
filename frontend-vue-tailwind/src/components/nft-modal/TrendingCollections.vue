<template>
  <section class="w-full overflow-x-auto select-none">
    <h2 class="text-2xl font-semibold mb-1">Trending Collections</h2>
    <p class="text-lg text-gray-400 mb-4 ">Highest sale in the past hour</p>

    <div
    class="grid grid-flow-col grid-rows-2 gap-4 snap-x snap-mandatory scroll-smooth px-2 cursor-grab active:cursor-grabbing"
    ref="trendingScrollRef"
    >
      <TrendingItems
        v-for="(item, index) in trendingData"
        :key="index"
        :item="item"
        class="snap-start w-[240px] hover:shadow-lg 
         transition-transform duration-300 ease-in-out transform hover:scale-105"
      />
    </div>
  </section>
</template>

<script setup>
import TrendingItems from '@/composables/TrendingItems.vue';
import { ref, onMounted, onBeforeUnmount } from 'vue'
import { useDragScroll } from '@/types/mouseScroll.ts'

const trendingScrollRef = ref(null)

let cleanUp
onMounted(() => {
  if (trendingScrollRef.value) {
    cleanUp = useDragScroll(trendingScrollRef.value)
  }
})

onBeforeUnmount(() => {
  if (cleanUp) cleanUp()
})
const trendingData = [
  {
    image: '/images/doge.png',
    name: 'DOGE',
    symbol: 'DJI6930',
    priceDisplay: '$0.06',
    change: 74.4,
    sparkline: '/images/spark-up.svg',
  },
  {
    image: '/images/mdt.png',
    name: 'Measurable Data',
    symbol: 'MDT',
    priceDisplay: '$0.04',
    change: 53.7,
    sparkline: '/images/spark-up.svg',
  },
  {
    image: '/images/startup.png',
    name: 'Startup',
    symbol: 'STARTUP',
    priceDisplay: '$0.03',
    change: 45.5,
    sparkline: '/images/spark-down.svg',
  },
  
  {
    image: '/images/startup.png',
    name: 'Startup',
    symbol: 'STARTUP',
    priceDisplay: '$0.03',
    change: 45.5,
    sparkline: '/images/spark-down.svg',
  },
  {
    image: '/images/startup.png',
    name: 'Startup',
    symbol: 'STARTUP',
    priceDisplay: '$0.03',
    change: 45.5,
    sparkline: '/images/spark-down.svg',
  },
  {
    image: '/images/startup.png',
    name: 'Startup',
    symbol: 'STARTUP',
    priceDisplay: '$0.03',
    change: 45.5,
    sparkline: '/images/spark-down.svg',
  },
  {
    image: '/images/startup.png',
    name: 'Startup',
    symbol: 'STARTUP',
    priceDisplay: '$0.03',
    change: 45.5,
    sparkline: '/images/spark-down.svg',
  },
  {
    image: '/images/startup.png',
    name: 'Startup',
    symbol: 'STARTUP',
    priceDisplay: '$0.03',
    change: 45.5,
    sparkline: '/images/spark-down.svg',
  },
  {
    image: '/images/startup.png',
    name: 'Startup',
    symbol: 'STARTUP',
    priceDisplay: '$0.03',
    change: 45.5,
    sparkline: '/images/spark-down.svg',
  },
  {
    image: '/images/startup.png',
    name: 'Startup',
    symbol: 'STARTUP',
    priceDisplay: '$0.03',
    change: 45.5,
    sparkline: '/images/spark-down.svg',
  },
];
</script>
