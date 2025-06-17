<template>
  <div class="flex min-h-screen">
    <!-- Left section -->
    <div class="w-full md:w-1/2 p-10 bg-white">
      <button @click="goBack" class="mb-6 text-gray-600 hover:text-black">&larr;</button>
      <h1 class="text-4xl font-bold mb-6">Create</h1>

      <!-- Drop -->
      <a href="/opennft/studio/create-collection" class="mb-4 p-6 rounded-lg border hover:shadow cursor-pointer flex justify-between items-center" @click="handleClick('drop')">
        <div>
          <p class="text-lg font-semibold mb-1">Drop</p>
          <p class="text-sm text-gray-600">
            A drop is the release of a new project. This usually happens on a specified date and time. Items will be revealed after they have been purchased.
          </p>
        </div>
        <span class="text-xl">&rarr;</span>
        <router-view />
      </a>

      <!-- Collection or Item -->
      <a href="/opennft/studio/create-nft" class="p-6 rounded-lg border hover:shadow cursor-pointer flex justify-between items-center" @click="handleClick('collection')">
        <div>
          <p class="text-lg font-semibold mb-1">Collection or item</p>
          <p class="text-sm text-gray-600">
            Create a new NFT collection or add an NFT to an existing one. Your items will display immediately. List for sale when you're ready.
          </p>
        </div>
        <span class="text-xl">&rarr;</span>
        <router-view />
      </a>

      <p class="text-sm text-blue-600 mt-4 cursor-pointer">Learn more about each option.</p>
    </div>

    <!-- Right section -->
    <div class="hidden md:block w-1/2 relative">
      <img src="/src/assets/createCollNft/Screenshot 2025-06-17 120511.png" alt="NFT Scene" class="w-full h-full object-cover" />
      <div class="absolute top-2 left-4 text-white text-sm">Featured ZTX Genesis Homes</div>
    </div>
  </div>
</template>

<script setup>
function goBack() {
  window.history.back();
}
function handleClick(type) {
  console.log("User selected:", type);
  // You can use router.push(`/create/${type}`) if using Vue Router
}
</script>
