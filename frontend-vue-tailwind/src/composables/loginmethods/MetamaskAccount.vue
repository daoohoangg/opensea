<template>
  <button @click="registerWithMetaMask" class="w-100">Countinue with Metamask</button>
  <p v-if="statusMessage">{{ statusMessage }}</p>
</template>
<script setup>
import { ethers } from 'ethers'
import axios from 'axios'   
import { ref } from 'vue'


const statusMessage = ref('')

async function registerWithMetaMask() {
  // window.ethereum.providers?.find(p => p.isMetaMask)?.request({ method: 'eth_requestAccounts' })

  if (!window.ethereum) {
    statusMessage.value = 'Vui lòng cài đặt MetaMask'
    return
  }

  try {
    // 1. Yêu cầu kết nối ví
    const accounts = await window.ethereum.request({ method: 'eth_requestAccounts' })
    const address = accounts[0]

    // 2. Tạo thông điệp để ký
    const message = `Đăng ký tại DApp lúc ${new Date().toISOString()}`

    // 3. Dùng Ethers.js để ký
    const provider = new ethers.BrowserProvider(window.ethereum)
    const signer = await provider.getSigner()
    const signature = await signer.signMessage(message)

    // 4. Gửi đến backend
    const response = await axios.post('http://localhost:8080/auth/registrationbymetamask', {
      address,
      message,
      signature
    })

    statusMessage.value = response.data

  } catch (err) {
    if (err.code === 4001) {
      statusMessage.value = 'Bạn đã từ chối kết nối ví'
    } else if (err.code === -32002) {
      statusMessage.value = 'Vui lòng mở MetaMask để kết nối'
    } else if (err.code === 4100) {
      statusMessage.value = 'Bạn không có quyền truy cập vào ví này'
    } else if (err.code === -32603) {
      statusMessage.value = 'Lỗi máy chủ, vui lòng thử lại sau'
    } else {
      statusMessage.value = 'Lỗi không xác định: ' + err.message
    }
    console.error(err)

    statusMessage.value = 'Đăng ký thất bại: ' + (err?.response?.data || err.message)
  }
}
</script>

<style scoped>
.register-container {
  max-width: 400px;
  margin: auto;
  padding: 1rem;
  border: 0.2px solid #dcdcdc;
  border-radius: 2px;
  text-align: center;
}
button {
  padding: 0.5rem 1rem;
  background-color: #661800;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
</style>