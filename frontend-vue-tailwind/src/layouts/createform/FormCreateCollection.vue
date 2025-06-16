<template>
  <div class="max-w-3xl mx-auto p-6 bg-white rounded-2xl shadow-md space-y-6">
    <h1 class="text-2xl font-bold">Create a Collection</h1>

    <!-- Logo image -->
    <div>
      <label class="block font-medium">Logo image *</label>
      <input type="file" @change="handleFile('logo', $event)" class="mt-1" />
    </div>

    <!-- Banner image -->
    <div>
      <label class="block font-medium">Banner image</label>
      <input type="file" @change="handleFile('banner', $event)" class="mt-1" />
    </div>

    <!-- Name -->
    <div>
      <label class="block font-medium">Name *</label>
      <input v-model="form.name" type="text" class="w-full border p-2 rounded" required />
    </div>
    <!-- Name -->
    <div>
      <label class="block font-medium">Symbols *</label>
      <input v-model="form.symbols" type="text" class="w-full border p-2 rounded" required />
    </div>
    <!-- Description -->
    <div>
      <label class="block font-medium">Description</label>
      <textarea v-model="form.description" class="w-full border p-2 rounded" rows="4"></textarea>
    </div>

    <!-- Category -->
    <div>
      <label class="block font-medium">Category</label>
      <select v-model="form.category" class="w-full border p-2 rounded">
        <option>Art</option>
        <option>Gaming</option>
        <option>Music</option>
        <option>Photography</option>
      </select>
    </div>

    <!-- Blockchain -->
    <div>
      <label class="block font-medium">Blockchain</label>
      <select v-model="form.blockchain" class="w-full border p-2 rounded">
        <option>Ethereum</option>
        <option>Polygon</option>
        <option>BNB Smart Chain</option>
      </select>
    </div>

    <!-- Submit -->
    <div>
      <button @click="submitForm" class="bg-blue-600 text-white px-6 py-2 rounded hover:bg-blue-700">
        Create Collection
      </button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        name: '',
        symbols: '',
        description: '',
        category: '',
        blockchain: 'Ethereum',
      },
      files: {
        logo: null,
        banner: null,
      },
    };
  },
  methods: {
    handleFile(type, event) {
      this.files[type] = event.target.files[0];
    },
    async submitForm() {
      const formData = new FormData();
      formData.append('name', this.form.name);
      formData.append('symbols', this.form.symbols);
      formData.append('description', this.form.description);
      formData.append('category', this.form.category);
      formData.append('blockchain', this.form.blockchain);
      if (this.files.logo) formData.append('logo', this.files.logo);
      if (this.files.banner) formData.append('banner', this.files.banner);

      try {
        const res = await fetch('/api/collections', {
          method: 'POST',
          body: formData,
        });

        const data = await res.json();
        alert('Collection created: ' + data.collectionId);
      } catch (err) {
        console.error(err);
        alert('Failed to create collection');
      }
    },
  },
};
</script>

<style scoped>
/* Optional: style preview or drag-drop */
</style>

