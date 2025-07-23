<template>
  <div class="min-h-screen flex flex-col bg-gray-100">
    <SearchBar />
    <CategoryBar />
    <NavBar />
    
    <div class="grid grid-cols-15 flex-1 pt-1">
      <div class="col-span-1">
        
      </div>
      <div class="col-span-10">
        <CollectionBanner/>
      </div>
      <div class="col-span-4">
        <CollectionsStatsRight />
      </div>
    </div>
    <Footer />
  </div>  
</template>
<script setup>
import Footer from '@/composables/Footer.vue';
import NavBar from '@/composables/NavBar.vue';
import SearchBar from '@/composables/SearchBar.vue';
import CollectionsStatsRight from '@/composables/CollectionsStatsRight.vue';
import CategoryBar from '@/composables/CategoryBar.vue';
import CoinChart from '@/components/nft-modal/NFTChart.vue';
import CollectionBanner from '@/components/collection-modal/CollectionBanner.vue';
// import ChartCoin from '@/composables/ChartCoin.vue';
</script>
  
