<template>  
  <div class="text-center">
    <button @click="connectWallet" class="max-w-[400px] mx-auto p-2 border border-[#dcdcdc] rounded-[2px] text-center w-100 border-r-2">
      Continue with QRCode
    </button>

    <div v-if="uri">
      <qrcode-vue :value="uri" :size="200" />
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'
import QrcodeVue from 'qrcode.vue'

// Rename imported function to avoid name conflict
import { connectWallet as connectWalletV2 } from "@/types/useWalletConnectV2.ts"

const uri = ref(null)

async function connectWallet() {
  try {
    // Call your actual connectWallet logic from WalletConnect
    const { pairingUri, session, address, signature, message } = await connectWalletV2()

    uri.value = pairingUri

    // Send login/registration data to backend
    const res = await axios.post("http://localhost:8080/auth/registrationbywallet", {
      address,
      signature,
      message,
    });

    console.log("JWT Token:", res.data.token)
  } catch (error) {
    console.error("Wallet connect failed:", error)
  }
}
</script>

<style>
button {
  cursor: pointer;
}
</style>
