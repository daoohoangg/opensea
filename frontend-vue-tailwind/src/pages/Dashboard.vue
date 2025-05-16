<template>
  <NavBar />
  <SearchBar />
  <!-- <router-link to="/auth/login">Login</router-link> -->
  <CollectionsStatsRight />
  <Footer />
</template>
<script setup>
import Footer from '@/composables/Footer.vue';
import NavBar from '@/composables/NavBar.vue';
import SearchBar from '@/composables/SearchBar.vue';
import CollectionsStatsRight from '@/composables/CollectionsStatsRight.vue';
</script>
  
