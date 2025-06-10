<template>  
    <button @click="loginWithWallet" class="max-w-[400px] mx-auto p-2 border border-[#dcdcdc] rounded-[2px] text-center w-100 border-r-2">Continue with QRCode</button>
</template>
<script setup>
import { connectWallet } from "@/services/useMobileWalletConnect.js";
import axios from "axios";

async function loginWithWallet() {
  const { address, signature, message } = await connectWallet();

  const res = await axios.post("http://localhost:8080/auth/registrationbywallet", {
    address,
    signature,
    message,
  });

  console.log("JWT token:", res.data.token);
}
</script>
<style>
button {
  cursor: pointer;
}
</style>