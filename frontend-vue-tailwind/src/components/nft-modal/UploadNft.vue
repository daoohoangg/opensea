<template>
  <div>
    <input type="file" @change="handleFileUpload" />
    <p v-if="ipfsHash">IPFS Hash: {{ ipfsHash }}</p>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { pinFileToIPFS } from '@/services/pinata.js';

const ipfsHash = ref('');

const handleFileUpload = async (e) => {
  const file = e.target.files[0];
  if (file) {
    try {
      const result = await pinFileToIPFS(file);
      ipfsHash.value = result.IpfsHash;
    } catch (error) {
      console.error('Upload failed:', error);
    }
  }
};
</script>
