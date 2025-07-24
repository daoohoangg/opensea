<script setup>
import { ref } from 'vue'

import Footer from '@/composables/Footer.vue';
import NavBar from '@/composables/NavBar.vue';
import SearchBar from '@/composables/SearchBar.vue';
import CollectionsStatsRight from '@/composables/CollectionsStatsRight.vue';
import CategoryBar from '@/composables/CategoryBar.vue';
import CollectionBanner from '@/components/collection-modal/CollectionBanner.vue';

const showRight = ref(true)

function toggleRight() {
  showRight.value = !showRight.value
}
</script>

<template>
  <div class="min-h-screen flex flex-col bg-gray-100">
    <SearchBar />
    <NavBar />

    <main class="flex-1 pt-1 grid grid-cols-15">
      <div class="col-span-1"></div>
      <section class="col-span-14">
        <div class="grid grid-cols-12 flex-1">
          <div :class="showRight ? 'col-span-8' : 'col-span-12'">
            <CategoryBar :show-right="showRight" @toggle="toggleRight" />
            <div class="py-5">
              <CollectionBanner />
            </div>
          </div>
          <transition name="slide-out" mode="out-in" :key="showRight" >
            <div v-if="showRight" class="col-span-4 transition-all duration-500 ease-in-out">
              <CollectionsStatsRight :show-right="showRight" @toggle="toggleRight" />
            </div>
          </transition>
          
        </div>
      </section>
    </main>

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



