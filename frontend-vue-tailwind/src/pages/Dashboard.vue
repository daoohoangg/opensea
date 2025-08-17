<script setup>
import { ref, computed } from 'vue'
import { useRoute } from 'vue-router'

const showRight = ref(true)

function toggleRight() {
  showRight.value = !showRight.value
}

const route = useRoute()
const showOnlyMain2 = ['/stats']
const onlyMain2 = computed(() => showOnlyMain2.includes(route.path))

import Footer from '@/composables/Footer.vue';
import NavBar from '@/composables/NavBar.vue';
import SearchBar from '@/composables/SearchBar.vue';
import CollectionsStatsRight from '@/composables/CollectionsStatsRight.vue';
import CategoryBar from '@/composables/CategoryBar.vue';
import CollectionBanner from '@/components/collection-modal/CollectionBanner.vue';
import FeaturedCollection from '@/components/nft-modal/FeatureCollection.vue';
import FeaturedDrops from '@/components/nft-modal/FeatureDrops.vue';
import TopMoverToday from '@/components/nft-modal/TopMoverToday.vue';
import FeaturedTokens from '@/components/nft-modal/FeatureTokens.vue';
import TrendingTokens from '@/components/nft-modal/TrendingTokens.vue';
import TrendingCollections from '@/components/nft-modal/TrendingCollections.vue';
import HighestWeeklySales from '@/components/nft-modal/HighestWeeklySales.vue';
import Nft101 from '@/components/nft-modal/Nft101.vue';

import Stats from '@/components/stats-modal/Stats.vue';

import ChatBot from '@/components/ai-chat-modal/ChatBot.vue'
</script>

<template>
  <div class="min-h-screen flex flex-col bg-black-100">
    <!-- Navbar + SearchBar cố định trên đầu -->
    <div class="fixed top-0 left-0 w-full z-50 bg-black-100">
      <NavBar />
      <SearchBar />

    </div>

    <!-- Spacer đẩy nội dung xuống tránh bị che -->
    <div class="h-[70px]"></div>

    <div v-if="!onlyMain2" class="flex flex-1 overflow-hidden">
      <!-- Nội dung chính scroll -->
      <main
        :class="showRight ? 'flex-1 max-h-[calc(100vh-96px)] overflow-y-auto' : 'flex-1 max-h-[calc(100vh-96px)] overflow-y-auto px-6'"
        class="px-4">
        <div :class="[
          'fixed z-10 left-0 right-0 transition-all duration-300 ease-in-out',
          showRight ? '4xl:w-7/8 2xl:w-3/4 w-full' : 'w-full'
        ]">
          <CategoryBar :show-right="showRight" @toggle="toggleRight" />
        </div>
        <div class="h-[70px]"></div>
        <div class="ml-10">
          <CollectionBanner />

          <!-- CollectionsStatsRight trên mobile -->
          <div class="block md:hidden max-h-[30vh] py-2 overflow-y-auto" :class="showRight ? 'w-1/6' : 'w-full'">
            <CollectionsStatsRight :show-right="showRight" @toggle="toggleRight" />
          </div>

          <!-- Nội dung chính scroll được -->
          <div class="pl-5 md:pl-0 space-y-2">
            <FeaturedCollection />
            <FeaturedDrops />
            <TrendingTokens />
            <TopMoverToday />
            <FeaturedTokens />
            <TrendingCollections />
            <HighestWeeklySales />
            <Nft101 />
          </div>
        </div>

      </main>

      <!-- Sidebar phải CollectionsStatsRight cố định sticky -->
      <transition name="slide-out" mode="out-in" :key="showRight">
        <div v-if="showRight"
          class="hidden md:block sticky top-[96px] h-[calc(100vh-96px)] w-90 overflow-auto transition-all duration-500 ease-in-out mr-20 z-10">
          <CollectionsStatsRight :show-right="showRight" @toggle="toggleRight" />
        </div>
      </transition>
    </div>

    <ChatBot />
    <Footer />
  </div>
</template>

<style scoped>
.slide-out-enter-active,
.slide-out-leave-active {
  transition: transform 0.5s ease;
}

.slide-out-enter-from {
  transform: translateX(100%);
}

.slide-out-enter-to {
  transform: translateX(0%);
}

.slide-out-leave-from {
  transform: translateX(0%);
}

.slide-out-leave-to {
  transform: translateX(100%);
}
</style>
