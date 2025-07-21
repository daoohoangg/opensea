<template>
  <NavBar />
  <SearchBar />
  <CategoryBar />
  <!-- <router-link to="/auth/login">Login</router-link> -->
  <!-- <ChartCoin/> -->
  <!-- <CoinChart/> -->
   <CollectionBanner/>
  <CollectionsStatsRight />
  <Footer />
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
  
