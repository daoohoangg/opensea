<!-- components/CollectionSection.vue -->
<template>
  <div> 
  </div>
  <section class="mb-6 mt-[3vh]">
    <h2 class="text-2xl font-semibold mb-1">{{ title }}</h2>
    <p class="text-lg text-gray-400 mb-4">{{ subtitle }}</p>

    <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
      <slot />
    </div>
  </section>
</template>

<script setup>
defineProps({
  title: String,
  subtitle: String
})
</script>