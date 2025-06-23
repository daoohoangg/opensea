<template>
  <div class="max-w-3xl mx-auto p-6 bg-white rounded-2xl shadow-md space-y-6">
    <button @click="goBack" class="mb-6 text-gray-600 hover:text-black">&larr;</button>
    <h1 class="text-2xl font-bold">First, you’ll need to create a collection for your NFT</h1>
    <h4>You’ll need to deploy an ERC-1155 contract on the blockchain to create a collection for your NFT. <a>What is a contract?</a></h4>


    <button @click="connectWallet">🔌 Kết nối MetaMask</button>
    <p v-if="account">👜 Ví: {{ account }}</p>


    <!-- Logo image -->
    <div>
      <label class="block font-medium">Logo image *</label>
      <input type="file" @change="handleFile('logo', $event)" class="mt-1"  required/>
    </div>

    <!-- Banner image -->
    <!-- <div>
      <label class="block font-medium">Banner image</label>
      <input type="file" @change="handleFile('banner', $event)" class="mt-1" />
    </div> -->

    <!-- Name -->
    <div>
      <label class="block font-medium">Contract Name *</label>
      <input v-model="form.name" type="text" class="w-full border p-2 rounded" required />
    </div>
    <!-- Name -->
    <div>
      <label class="block font-medium">Token Symbols *</label>
      <input v-model="form.symbols" type="text" class="w-full border p-2 rounded" required />
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
      <button @click="() => { deployContract(); }" class="bg-blue-600 text-white px-6 py-2 rounded hover:bg-blue-700">
        Create Collection
      </button>
    </div>
  </div>
</template>
<script setup>
import { reactive } from 'vue'
import { ref } from "vue";
import { ethers } from "ethers";

const form = reactive({
  name: '',
  symbols: '',
  description: '',
  category: '',
  blockchain: 'Polygon',
})

const files = reactive({
  logo: null,
  banner: null,
})

function handleFile(type, event) {
  files[type] = event.target.files[0]
}

const status = ref("");
const account = ref("");
const contractAddress = ref("");
const connectWallet = async () => {
  if (window.ethereum) {
    const provider = new ethers.BrowserProvider(window.ethereum);
    await window.ethereum.request({ method: "eth_requestAccounts" });
    const signer = await provider.getSigner();
    account.value = await signer.getAddress();
  } else {
    alert("Hãy cài MetaMask");
  }
};

import factoryABI from "@/";

const factory = new ethers.Contract(factoryAddress, factoryABI.abi, signer);

const deployContract = async () => {
  if (!account.value) {
    await connectWallet(); // Bảo đảm người dùng đã kết nối ví
  }

  status.value = "Smart contract is deploying...";
  try {
    const provider = new ethers.BrowserProvider(window.ethereum);
    const signer = await provider.getSigner();

    const factory = new ethers.ContractFactory(
      contractArtifact.abi,
      contractArtifact.bytecode,
      signer
    );

    const contract = await factory.deploy();
    await contract.waitForDeployment();

    const deployedAddress = await contract.getAddress();
    contractAddress.value = deployedAddress;
    status.value = "✅ Deploy thành công!";

    // 🔽 Sau khi deploy xong → gửi API
    const formData = new FormData();
    formData.append('name', form.name);
    formData.append('symbols', form.symbols);
    formData.append('blockchain', form.blockchain);
    formData.append('contractAddress', deployedAddress); // << thêm địa chỉ contract
    if (files.logo) formData.append('logo', files.logo);

    const res = await fetch('http://localhost:8083/api/v1/create/collection', {
      method: 'POST',
      body: formData,
    });

    const data = await res.json();
    if (res.ok) {
      console.log("✅ Collection saved to DB:", data);
    } else {
      console.error("❌ Backend error:", data);
    }

  } catch (error) {
    console.error(error);
    status.value = "❌ Deploy thất bại!";
  }
};

function goBack() {
  window.history.back()
}
</script>

<style scoped>
/* Optional: style preview or drag-drop */
</style>

