
<template>
<CollectionCard
  title="Featured Drops"
  subtitle="This week's curated live and upcoming drops"
>
  <div
    class="flex gap-4 overflow-x-auto scroll-smooth snap-x snap-mandatory px-1 py-2 md:hidden"
  >
    <div
      v-for="(item, index) in collections"
      :key="index"
      class="min-w-full snap-start bg-zinc-900 rounded-xl shadow hover:shadow-lg 
         transition-transform duration-300 ease-in-out transform hover:scale-105"
    >
      <img
        :src="item.image"
        alt="collection image"
        class="w-full h-40 object-cover rounded-t-xl"
      />
      <div class="p-4 w-full">
        <h3 class="text-white font-semibold flex items-center gap-1">
          {{ item.name }}
          <span v-if="item.verified" class="text-blue-400">✔️</span>
        </h3>
        <p class="text-sm text-gray-300">
          Floor price:
          <span class="text-white font-semibold">${{ item.floorPrice }}</span>
          <span :class="item.change >= 0 ? 'text-green-400' : 'text-red-400'">
            {{ item.change >= 0 ? '+' : '' }}{{ item.change }}%
          </span>
        </p>
      </div>
    </div>
  </div>

  <div 
    v-for="(item, index) in collections"
    :key="index"
    class="hidden md:grid ">
    <div
      class="bg-zinc-900 rounded-xl shadow hover:shadow-lg transition duration-300 w-full
      ease-in-out transform hover:scale-105"
    >
      <img
        :src="item.image"
        alt="collection image"
        class="w-full h-40 object-cover rounded-t-xl"
      />
      <div class="p-4 w-full">
        <h3 class="text-white font-semibold flex items-center gap-1">
          {{ item.name }}
          <span v-if="item.verified" class="text-blue-400 text-sm">✔️</span>
        </h3>
        <p class="text-sm text-gray-300">
          <span class="text-green-400 font-semibold border-1 rounded-md">MINTING NOW</span>
          <span class="text-white font-semibold">
            ${{ Number(item.floorPrice).toLocaleString(undefined, { minimumFractionDigits: 2, maximumFractionDigits: 2 }) }}
          </span>
          <!-- <span
            :class="item.change >= 0 ? 'text-green-400' : 'text-red-400'"
            class="font-semibold"
          >
            {{ item.change >= 0 ? '+' : '' }}{{ item.change }}%
          </span> -->
        </p>
      </div>
    </div>

  </div>
</CollectionCard>


</template>

<script setup>
import CollectionCard from '@/composables/CollectionCard.vue';

const collections = [
  {
    name: 'Moonbirds',
    image: 'https://example.com/moonbirds.jpg',
    floorPrice: '6,259.23',
    change: 5.8,
    verified: true
  },
  {
    name: 'Off The Grid',
    image: 'https://example.com/offthegrid.jpg',
    floorPrice: '0.34',
    change: -79.6,
    verified: true
  },
  {
    name: 'On-Chain All-Stars',
    image: 'https://example.com/allstars.jpg',
    floorPrice: '14.50',
    change: 21.7,
    verified: true
  },
  {
    name: 'Overture by Mitchell F. Chan',
    image: 'https://example.com/overture.jpg',
    floorPrice: '1,198.66',
    change: 0,
    verified: false
  }
]
</script>
