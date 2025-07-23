<template>
  <div class="relative w-full overflow-hidden bg-[#121212]">
    <!-- Main Banner -->
    <div class="w-full h-[400px] relative bg-[#121212]">
      <img
        src="@/assets/collectionBanner/10bbca634cc926484c84b1f476e7d845.jpg"
        alt="Azuki Elementals Banner"
        class="w-full h-full object-cover"
      />
      <div class="absolute bottom-0 left-0 right-0 bg-gradient-to-t from-black/80 to-transparent p-6 text-white">
        <h1 class="text-3xl font-bold">Azuki Elementals <span class="text-blue-400">✔</span></h1>
        <p class="text-sm text-gray-300">By TeamAzuki</p>
        <div class="flex gap-8 mt-4 text-sm">
          <div><span class="font-bold">$830.02</span><br />FLOOR PRICE</div>
          <div><span class="font-bold">17,655</span><br />ITEMS</div>
          <div><span class="font-bold">$187.5M</span><br />TOTAL VOLUME</div>
          <div><span class="font-bold">4.2%</span><br />LISTED</div>
        </div>
      </div>
    </div>

    <!-- Avatars Carousel -->
    <div class="absolute bottom-4 right-4 flex gap-3">
      <div v-for="(avatar, index) in avatars" :key="index"
        class="w-12 h-12 rounded-md border-2 border-white overflow-hidden shadow-lg">
        <img :src="avatar" alt="avatar" class="w-full h-full object-cover" />
      </div>
    </div>
  </div>
</template>

<script setup>
const avatars = [
  new URL('@/assets/collectionBanner/10bbca634cc926484c84b1f476e7d845.jpg', import.meta.url).href,
  new URL('@/assets/collectionBanner/45b6a656f168af37c2cebfd29ed7339d.jpg', import.meta.url).href,
  new URL('@/assets/collectionBanner/95296ebc51c3e09446262e18db8ef8e5.jpg', import.meta.url).href,
  new URL('@/assets/collectionBanner/d27615cabc0377b986be541a8f8bf60f.jpg', import.meta.url).href
]
</script>

<style scoped>
/* Optional custom styles */
</style>
