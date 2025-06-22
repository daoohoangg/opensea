<template>
  <div class="max-w-4xl mx-auto py-10 px-4">
    <button @click="goBack" class="mb-6 text-gray-600 hover:text-black">&larr;</button>
    <h1 class="text-3xl font-bold mb-6">Create a smart contract for your drop</h1>

    <div class="bg-white p-6 rounded-xl shadow space-y-6">

      <!-- Logo Upload -->
      <div>
        <label class="block text-sm font-medium mb-2">Logo image</label>
        <input type="file" @change="handleFileUpload" />
        <div v-if="logoPreview" class="mt-2">
          <img :src="logoPreview" class="w-24 h-24 object-cover rounded" />
        </div>
      </div>

      <!-- Contract Name -->
      <div>
        <label class="block text-sm font-medium mb-1">Contract name</label>
        <input v-model="form.contractName" class="w-full border rounded p-2" placeholder="My Collection Name" />
      </div>

      <!-- Token Symbol -->
      <div>
        <label class="block text-sm font-medium mb-1">Token symbol</label>
        <input v-model="form.tokenSymbol" class="w-full border rounded p-2" placeholder="MCN" />
      </div>

      <!-- Blockchain Selection -->
      <div>
        <label class="block text-sm font-medium mb-1">Blockchain</label>
        <div class="flex space-x-4">
          <button
            v-for="chain in blockchains"
            :key="chain.id"
            @click="form.blockchain = chain.id"
            :class="[
              'p-3 border rounded-xl w-40',
              form.blockchain === chain.id ? 'border-blue-500 bg-blue-50' : 'border-gray-300'
            ]"
          >
            <div class="font-medium">{{ chain.name }}</div>
            <div class="text-xs text-gray-500">{{ chain.desc }}</div>
          </button>
        </div>
      </div>

      <!-- Continue Button -->
      <div class="text-right">
        <button
          :disabled="!form.contractName || !form.tokenSymbol"
          class="px-6 py-2 bg-blue-600 text-white rounded disabled:opacity-50"
        >
          Continue
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const form = ref({
  contractName: '',
  tokenSymbol: '',
  blockchain: 'ethereum'
})

const logoPreview = ref(null)

function handleFileUpload(event) {
  const file = event.target.files[0]
  if (file) {
    logoPreview.value = URL.createObjectURL(file)
  }
}

const blockchains = [
  { id: 'ethereum', name: 'Ethereum', desc: 'Most popular' },
  { id: 'base', name: 'Base', desc: 'Cheaper' },
  { id: 'polygon', name: 'Polygon', desc: 'Low gas fees' }
]
function goBack() {
  window.history.back()
}
</script>

<style scoped>
input[type="file"] {
  border: 1px solid #ccc;
  padding: 6px;
  border-radius: 6px;
}
</style>
