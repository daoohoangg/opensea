<template>
  <div class="group relative bg-white rounded-xl shadow-lg hover:shadow-xl transition-all duration-300 overflow-hidden">
    <!-- Product Image -->
    <div class="aspect-square overflow-hidden bg-gray-100">
      <img 
        :src="product.image" 
        :alt="product.name"
        class="w-full h-full object-cover group-hover:scale-105 transition-transform duration-300"
      />
      <!-- Badge -->
      <div v-if="product.badge" class="absolute top-3 left-3">
        <span class="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-red-100 text-red-800">
          {{ product.badge }}
        </span>
      </div>
      <!-- Favorite Button -->
      <button 
        @click="toggleFavorite"
        class="absolute top-3 right-3 p-2 rounded-full bg-white/80 backdrop-blur-sm hover:bg-white transition-colors duration-200"
      >
        <svg 
          :class="isFavorite ? 'text-red-500 fill-current' : 'text-gray-400 hover:text-red-500'"
          class="w-5 h-5 transition-colors duration-200" 
          fill="none" 
          stroke="currentColor" 
          viewBox="0 0 24 24"
        >
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z" />
        </svg>
      </button>
    </div>

    <!-- Product Info -->
    <div class="p-4">
      <!-- Category -->
      <div class="text-sm text-gray-500 mb-1">{{ product.category }}</div>
      
      <!-- Product Name -->
      <h3 class="text-lg font-semibold text-gray-900 mb-2 line-clamp-2 group-hover:text-blue-600 transition-colors duration-200">
        {{ product.name }}
      </h3>
      
      <!-- Rating -->
      <div class="flex items-center mb-3">
        <div class="flex items-center">
          <svg 
            v-for="star in 5" 
            :key="star"
            :class="star <= product.rating ? 'text-yellow-400' : 'text-gray-300'"
            class="w-4 h-4 fill-current"
            viewBox="0 0 20 20"
          >
            <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
          </svg>
        </div>
        <span class="text-sm text-gray-600 ml-1">({{ product.reviewCount }})</span>
      </div>
      
      <!-- Price -->
      <div class="flex items-center justify-between mb-4">
        <div class="flex items-center space-x-2">
          <span class="text-xl font-bold text-gray-900">${{ product.price }}</span>
          <span v-if="product.originalPrice" class="text-sm text-gray-500 line-through">${{ product.originalPrice }}</span>
        </div>
        <span v-if="product.discount" class="text-sm font-medium text-green-600">{{ product.discount }}% OFF</span>
      </div>
      
      <!-- Action Buttons -->
      <div class="flex space-x-2">
        <button 
          @click="addToCart"
          class="flex-1 bg-blue-600 text-white py-2 px-4 rounded-lg font-medium hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2 transition-colors duration-200"
        >
          Add to Cart
        </button>
        <button 
          @click="quickView"
          class="p-2 text-gray-600 hover:text-blue-600 hover:bg-blue-50 rounded-lg transition-colors duration-200"
        >
          <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z" />
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z" />
          </svg>
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

// Props
const props = defineProps({
  product: {
    type: Object,
    required: true,
    default: () => ({
      id: 1,
      name: 'Sample Product',
      category: 'Electronics',
      price: 99.99,
      originalPrice: null,
      discount: null,
      badge: null,
      image: 'https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=400&h=400&fit=crop',
      rating: 4,
      reviewCount: 128,
      description: 'This is a sample product description.'
    })
  }
})

// Emits
const emit = defineEmits(['add-to-cart', 'quick-view', 'toggle-favorite'])

// Reactive state
const isFavorite = ref(false)

// Methods
const addToCart = () => {
  emit('add-to-cart', props.product)
}

const quickView = () => {
  emit('quick-view', props.product)
}

const toggleFavorite = () => {
  isFavorite.value = !isFavorite.value
  emit('toggle-favorite', { product: props.product, isFavorite: isFavorite.value })
}
</script>

<style scoped>
.line-clamp-2 {
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
</style> 