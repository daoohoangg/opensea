<template>
  <div class="max-w-3xl mx-auto p-6 bg-white shadow rounded-xl space-y-6">
    <h2 class="text-2xl font-bold">Create New NFT</h2>

    <form @submit.prevent="submitForm" enctype="multipart/form-data">
      <!-- Image Upload -->
      <div>
        <label class="block font-medium mb-2">Image</label>
        <input type="file" @change="onImageChange" required />
      </div>

      <!-- Name -->
      <div>
        <label class="block font-medium mb-2">Name</label>
        <input v-model="form.name" class="w-full border rounded p-2" required />
      </div>

      <!-- Description -->
      <div>
        <label class="block font-medium mb-2">Description</label>
        <textarea v-model="form.description" class="w-full border rounded p-2" rows="3" required></textarea>
      </div>

      <!-- Select Collection -->
      <div>
        <label class="block font-medium mb-2">Collection</label>
        <select v-model="form.collectionId" class="w-full border rounded p-2">
          <option disabled value="">-- Choose a collection --</option>
          <option v-for="col in collections" :key="col.id" :value="col.id">
            {{ col.name }}
          </option>
        </select>
      </div>

      <!-- Attributes -->
      <div>
        <label class="block font-medium mb-2">Attributes</label>
        <div v-for="(attr, index) in form.attributes" :key="index" class="flex gap-2 mb-2">
          <input v-model="attr.trait_type" placeholder="Trait type" class="border p-1 rounded w-1/2" />
          <input v-model="attr.value" placeholder="Value" class="border p-1 rounded w-1/2" />
          <button type="button" @click="removeAttribute(index)" class="text-red-500">✕</button>
        </div>
        <button type="button" @click="addAttribute" class="text-blue-600">+ Add Attribute</button>
      </div>

      <!-- Submit -->
      <button type="submit" class="bg-blue-600 text-white px-4 py-2 rounded mt-4">Create NFT</button>
    </form>

    <!-- Result -->
    <div v-if="result" class="bg-green-100 p-4 mt-6 rounded">
      ✅ Token URI: {{ result.tokenUri }}
    </div>
  </div>
</template>
<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const form = ref({
  name: '',
  description: '',
  image: null,
  collectionId: '',
  attributes: []
})

const collections = ref([])
const result = ref(null)

onMounted(async () => {
  const res = await axios.get('http://localhost:8080/api/collections')
  collections.value = res.data
})

function onImageChange(e) {
  form.value.image = e.target.files[0]
}

function addAttribute() {
  form.value.attributes.push({ trait_type: '', value: '' })
}

function removeAttribute(index) {
  form.value.attributes.splice(index, 1)
}

async function submitForm() {
  const formData = new FormData()
  formData.append('image', form.value.image)
  formData.append('name', form.value.name)
  formData.append('description', form.value.description)
  formData.append('collectionId', form.value.collectionId)
  formData.append('attributes', JSON.stringify(form.value.attributes))

  const res = await axios.post('http://localhost:8080/api/nft/upload', formData, {
    headers: { 'Content-Type': 'multipart/form-data' }
  })

  result.value = res.data
}
</script>
