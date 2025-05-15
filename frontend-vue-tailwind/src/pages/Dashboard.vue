<template>
  <NavBar />
  <nav>
    <router-link to="/auth/login">Login</router-link>
  </nav>
  <Footer />
</template>
<script setup>
import Footer from '@/composables/Footer.vue';
import NavBar from '@/composables/NavBar.vue';
</script>
  
