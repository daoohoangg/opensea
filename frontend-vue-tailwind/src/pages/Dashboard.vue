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
    <div class="top-0 left-0 w-full z-50">
      <NavBar />
    </div>

    <div class="top-0 left-0 w-full z-50">
      <SearchBar />
    </div>
    
    <div v-if="!onlyMain2">
      <main class="grid grid-cols-15 md:grid-cols-15">
      <div class="col-span-1 md:col-span-1"></div>
      <section class="col-span-14 md:col-span-13">
        <div class="grid grid-cols-1 md:grid-cols-12 flex-1">
          <!-- Phần chính -->
          <div :class="showRight ? 'col-span-1 md:col-span-9' : 'col-span-1 md:col-span-12'">
            <CategoryBar :show-right="showRight" @toggle="toggleRight" />

            <div class="py-5">
              <!-- CollectionBanner -->
              <CollectionBanner />

              <!-- 👉 CollectionsStatsRight hiển thị trên mobile -->
              <div class="block md:hidden max-h-[30vh] py-4 overflow-y-auto">
                <CollectionsStatsRight :show-right="showRight" @toggle="toggleRight" />
              </div>

              <!-- FeaturedCollection -->
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
          </div>
          <transition name="slide-out" mode="out-in" :key="showRight">
            <div
              v-if="showRight"
              class="hidden md:block col-span-1 md:col-span-3 w-full overflow-y-auto max-h-[30vh] md:max-h-none transition-all duration-500 ease-in-out"
            >
              <div class="relative top-0 left-0 w-full z-50">
                <CollectionsStatsRight :show-right="showRight" @toggle="toggleRight" />
              </div>
            </div>
          </transition>
        </div>
      </section>

      <div class="md:col-span-1"></div>
      </main>
    </div>
    <main v-if="onlyMain2">
      <div><Stats/></div>
      
    </main>

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



