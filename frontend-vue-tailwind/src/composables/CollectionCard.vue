<template>
  <div class="bg-gray-900 text-white p-6 rounded-xl">
    <h2 class="text-2xl font-bold mb-1">Featured Collections</h2>
    <p class="text-gray-400 mb-4">This week's curated collections</p>

    <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-5 gap-4">
      <CollectionCard
        v-for="(item, index) in collections"
        :key="index"
        :image="item.image"
        :title="item.title"
        :floorPrice="item.floorPrice"
        :change="item.change"
        :author="item.author"
      />
    </div>
  </div>
</template>

<script setup>
import CollectionCard from './CollectionCard.vue'

const collections = [
  {
    image: '/moonbirds.jpg',
    title: 'Moonbirds: Mythics',
    floorPrice: 955.45,
    change: 17.8,
    author: null
  },
  {
    image: '/abstractio.jpg',
    title: 'Abstractio',
    floorPrice: 133.39,
    change: 75.4,
    author: null
  },
  {
    image: '/lumens.jpg',
    title: 'Lumens',
    floorPrice: 291.39,
    change: null,
    author: null
  },
  {
    image: '/glitch.jpg',
    title: 'Glitch Skulls',
    floorPrice: 8.66,
    change: -7.7,
    author: '0xsh'
  },
  {
    image: '/ghost.jpg',
    title: 'GHOST MACHINE',
    floorPrice: 3.18,
    change: 9.3,
    author: null
  }
]
</script>
