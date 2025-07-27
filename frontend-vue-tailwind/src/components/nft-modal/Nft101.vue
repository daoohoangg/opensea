
<template>
<CollectionCard
  title="NFT 101"
  subtitle="Learn about NFTs, Web3, and more."
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
      <!-- <div class="p-4 w-full">
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
      </div> -->
      <div>{{ item.description }}</div>
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
      <!-- <div class="p-4 w-full">
        <h3 class="text-white font-semibold flex items-center gap-1">
          {{ item.name }}
          <span v-if="item.verified" class="text-blue-400 text-sm">✔️</span>
        </h3>
        <p class="text-sm text-gray-300">
          Floor price:
          <span class="text-white font-semibold">
            ${{ Number(item.floorPrice).toLocaleString(undefined, { minimumFractionDigits: 2, maximumFractionDigits: 2 }) }}
          </span>
          <span
            :class="item.change >= 0 ? 'text-green-400' : 'text-red-400'"
            class="font-semibold"
          >
            {{ item.change >= 0 ? '+' : '' }}{{ item.change }}%
          </span>
        </p>
      </div> -->
    </div>
    <div>{{ item.description }}</div>
  </div>
  
</CollectionCard>


</template>

<script setup>
import CollectionCard from '@/composables/CollectionCard.vue';

const collections = [
  {
    name: 'Moonbirds',
    image: 'https://example.com/moonbirds.jpg',
    description: 'A collection of 10,000 unique Moonbirds NFTs.',
  },
    {
        name: 'Bored Ape Yacht Club',
        image: 'https://example.com/bored-apes.jpg',
        description: 'A collection of 10,000 unique Bored Ape NFTs.',
    },
    {
        name: 'CryptoPunks',
        image: 'https://example.com/cryptopunks.jpg',
        description: 'A collection of 10,000 unique CryptoPunks NFTs.',
    },
    {
        name: 'Azuki',
        image: 'https://example.com/azuki.jpg',
        description: 'A collection of 10,000 unique Azuki NFTs.',
    },
]
</script>
