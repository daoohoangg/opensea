<template>
  <div class="max-w-3xl mx-auto p-6 bg-white shadow rounded-xl space-y-6">
    <button @click="goBack" class="mb-6 text-gray-600 hover:text-black">&larr;</button>
    <h2 class="text-2xl font-bold">Create an NFT</h2>
    <p>Once your item is minted you will not be able to change any of its information.</p>

    <form @submit.prevent="submitForm" enctype="multipart/form-data">
      <!-- Image Upload -->
      <div>
        <label class="block font-medium mb-2">Image*</label>
        <input type="file" @change="onImageChange" required />
      </div>

      <!-- Select Collection -->
      <div v-if="collectionsName.length === 0">
      <div
        class="flex items-center gap-4 bg-gray-100 p-4 rounded-lg cursor-pointer hover:bg-gray-200"
        @click="createCollection"
      >
        <div
          class="flex items-center justify-center w-10 h-10 rounded-md bg-gray-200 text-xl font-bold"
        >
          +
        </div>
        <div class="font-semibold text-black">Create a new collection</div>
      </div>

      <p class="text-sm text-gray-500 mt-2">
        Not all collections are eligible.
        <a href="#" class="text-blue-600 hover:underline">Learn more</a>
      </p>
    </div>

    <!-- Có collection -->
    <div v-else-if="collectionsName.length > 0" class="mb-4">
      <select v-model="form.id" class="w-full border rounded p-2">
        <option disabled value="">-- Choose a collection --</option>
        <option v-for="col in collectionsName" :key="col.id" :value="col.id">
          {{ col.name }}
        </option>
      </select>
    </div>

      <!-- Name -->
      <div>
        <label class="block font-medium mb-2">Name*</label>
        <input v-model="form.name" class="w-full border rounded p-2" required />
      </div>

      <!-- Supply -->
      <div>
        <label class="block font-medium mb-2">Supply*</label>
        <input v-model="form.name" class="w-full border rounded p-2" required />
      </div>

      <!-- Description -->
      <div>
        <label class="block font-medium mb-2">Description</label>
        <textarea v-model="form.description" class="w-full border rounded p-2" rows="3" required placeholder="Enter a description"></textarea>
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
/*
  submit form to create a new NFT variables
*/
const collections = ref([])
const result = ref(null)

function onImageChange(e) {
  form.value.image = e.target.files[0]
}

function addAttribute() {
  form.value.attributes.push({ trait_type: '', value: '' })
}

function removeAttribute(index) {
  form.value.attributes.splice(index, 1)
}
// create new collection
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
/*
  get all name of collections variables
*/
const collectionsName = ref([])
const selectedCollection = ref('')
const loading = ref(true)

onMounted(async () => {
  try {
    const res = await axios.get('http://localhost:8083/api/v1/get-collections')
    collectionsName.value = res.data.result 
  } catch (err) {
    console.error('Error fetching collections:', err)
  } finally {
    loading.value = false
  }
})
function goBack() {
  window.history.back();
}
</script>
