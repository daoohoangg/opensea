<template>
  <div class="bg-black rounded-xl overflow-hidden border border-gray-700 hover:scale-105 transition-transform">
    <img :src="image" alt="collection image" class="w-full h-40 object-cover" />

    <div class="p-4">
      <h3 class="text-white font-semibold text-lg">
        {{ title }}
        <span v-if="author" class="text-sm font-normal text-gray-400">by {{ author }}</span>
        <span class="text-blue-400 ml-1">✔️</span>
      </h3>
      <p class="text-sm text-gray-400">
        Floor price: ${{ floorPrice.toFixed(2) }}
        <span
          v-if="change !== null"
          :class="change > 0 ? 'text-green-500' : change < 0 ? 'text-red-500' : 'text-white'"
          class="ml-2"
        >
          {{ change > 0 ? '+' : '' }}{{ change.toFixed(1) }}%
        </span>
      </p>
    </div>
  </div>
</template>

<script setup>
defineProps({
  image: String,
  title: String,
  floorPrice: Number,
  change: Number,
  author: String
})
</script>
