<template>
    <div class="py-3">
        <h2 class="text-2xl font-semibold mb-1">Highest Weekly Sales</h2>
    </div>
    
  <section class="bg-[#0f172a] text-white p-6 rounded-xl shadow-md">
    <div class="flex flex-col md:flex-row gap-6">
      <!-- Left: Collection Info -->
      <div class=" p-4 rounded-lg flex-1 max-w-sm">
        <img src="" alt="DX Terminal Logo" class="w-16 h-16 mb-2" />
        <h3 class="text-xl font-semibold flex items-center gap-2">
          DX Terminal
          <span class="text-blue-400">✔️</span>
        </h3>
        <p class="text-sm mt-1 text-gray-300">7d sales: 175,001 <span class="text-green-400">+0.5%</span></p>
        <p class="mt-4 text-gray-400 text-sm">
          Welcome to Terminal City!<br />
          This collection is where each of your AI Trader NFTs will appear. Every trader is completely unique, and comes with its own PFP...
        </p>
      </div>

      <!-- Right: NFT List -->
      <div class="hidden md:grid flex-1 grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-4">
        <div v-for="item in nftItems" :key="item.name" class="bg-black/20 rounded-lg overflow-hidden
        hover:shadow-lg 
         transition-transform duration-300 ease-in-out transform hover:scale-105">
          <img :src="item.image" :alt="item.name" class="w-full h-48 object-cover" />
          <div class="p-2">
            <p class="font-semibold">{{ item.name }}</p>
            <p class="text-gray-400 text-sm">${{ item.price }}</p>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup>
const nftItems = [
  {
    name: "GearGuru70",
    price: "15.73",
    image: "/nft1.png", // thay bằng URL thật
  },
  {
    name: "Rote_Rose",
    price: "15.73",
    image: "/nft2.png",
  },
  {
    name: "MemeMaus",
    price: "15.73",
    image: "/nft3.png",
  },
];
</script>
