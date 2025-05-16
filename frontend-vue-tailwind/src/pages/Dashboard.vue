<template>
  <NavBar />
  <SearchBar />
  <nav>
    <router-link to="/auth/login">Login</router-link>
  </nav>
  <CollectionsStatsRight />
  <Footer />
</template>
<script setup>
import Footer from '@/composables/Footer.vue';
import NavBar from '@/composables/NavBar.vue';
import SearchBar from '@/composables/SearchBar.vue';
import CollectionsStatsRight from '@/composables/CollectionsStatsRight.vue';
</script>
  
