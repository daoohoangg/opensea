<template>
  <div class="bg-[#131313] h-18 flex items-center">
    <!-- Ô search -->
    <div
      ref="searchBoxRef"
      class="flex items-center bg-[#131313] border border-gray-600 rounded-md px-5 py-3 w-80 ml-60"
      @click="showPopup = true"
      
    >
      <svg
        xmlns="http://www.w3.org/2000/svg"
        fill="none"
        viewBox="0 0 24 24"
        stroke-width="1.5"
        stroke="currentColor"
        class="w-4 h-4 text-gray-400 mr-2"
      >
        <path
          stroke-linecap="round"
          stroke-linejoin="round"
          d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z"
        />
      </svg>
      <input
        type="text"
        placeholder="Search OpenSea"
        class="bg-transparent text-white placeholder-gray-400 outline-none flex-1"
        readonly
      />
      <div class="text-sm text-gray-400 ml-2 border border-gray-600 rounded px-1 py-0.5">/</div>
    </div>
    <Transition name="fade">
      <div
        v-if="showPopup"
        class="fixed inset-0 backdrop-blur-sm z-40 transition-opacity duration-10"
      ></div>
    </Transition>

    <!-- POP-UP ở giữa màn hình -->
     <Transition
        name="fade-scale"
        enter-active-class="transition duration-250 ease-out"
        enter-from-class="opacity-0 scale-95"
        enter-to-class="opacity-100 scale-100"
        leave-active-class="transition duration-150 ease-in"
        leave-from-class="opacity-100 scale-100"
        leave-to-class="opacity-0 scale-95"
        >
    <div
      v-if="showPopup"
      class="fixed inset-0 flex justify-center items-center z-50"
    >
      <div
        class="bg-[#1a1a1a] w-[600px] max-h-[80vh] overflow-y-auto rounded-lg border border-gray-700 shadow-lg p-6 relative"
        ref="popupRef"
      >
        <!-- Nút đóng -->
        <!-- <button
          @click="showPopup = false"
          class="absolute top-2 right-2 text-gray-400 hover:text-white"
        >
          ✕
        </button> -->

        <!-- Thanh input search bên trong -->
        <div class="flex items-center border border-gray-600 rounded-md px-4 py-2 mb-4">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            stroke-width="1.5"
            stroke="currentColor"
            class="w-4 h-4 text-gray-400 mr-2"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z"
            />
          </svg>
          <input
            type="text"
            placeholder="Search OpenSea"
            class="bg-transparent text-white placeholder-gray-400 outline-none flex-1"
          />
        </div>

        <!-- Trending collections -->
        <div>
          <p class="text-sm text-gray-400 mb-2">TRENDING COLLECTIONS</p>
          <div class="space-y-2">
            <div class="flex justify-between items-center hover:bg-gray-700 p-2 rounded">
              <div>
                <p class="font-semibold text-white">Courtyard.io</p>
                <p class="text-gray-400 text-sm">174.2K items</p>
              </div>
              <p class="text-white">$8.63</p>
            </div>
          </div>
        </div>

        <!-- Top tokens -->
        <div class="mt-6">
          <p class="text-sm text-gray-400 mb-2">TOP TOKENS</p>
          <div class="space-y-2">
            <div class="flex items-center justify-between hover:bg-gray-700 p-2 rounded">
              <div class="flex items-center space-x-2">
                <img class="w-6 h-6 rounded-full" />
                <div>
                  <p class="text-white text-sm font-semibold">Nobody Sausage</p>
                  <p class="text-gray-400 text-xs">NOBODY</p>
                </div>
              </div>
              <div class="text-right">
                <p class="text-white text-sm">$0.02578</p>
                <p class="text-green-400 text-xs">+127.4%</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    </Transition>
    <div class="flex flex-col justify-end items-end  ">
      <h2 class=" text-white text-lg font-semibold">Login</h2>
      <div class="register-container">
        <h2>/Sign Up</h2>
        <button @click="registerWithMetaMask">Kết nối & Đăng ký</button>
        <p v-if="statusMessage">{{ statusMessage }}</p>
      </div>
    </div>
  </div>
  
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue'
import { ethers } from 'ethers'
import axios from 'axios'


const showPopup = ref(false)
const popupRef = ref(null)
const searchBoxRef = ref(null)

function handleClickOutside(event) {
  const popup = popupRef.value
  const searchBox = searchBoxRef.value

  if (
    popup &&
    !popup.contains(event.target) &&
    searchBox &&
    !searchBox.contains(event.target)
  ) {
    showPopup.value = false
  }
}

onMounted(() => {
  document.addEventListener('click', handleClickOutside)
})

onBeforeUnmount(() => {
  document.removeEventListener('click', handleClickOutside)
})


const statusMessage = ref('')

async function registerWithMetaMask() {
  if (!window.ethereum) {
    statusMessage.value = 'Vui lòng cài đặt MetaMask'
    return
  }

  try {
    // 1. Yêu cầu kết nối ví
    const [address] = await window.ethereum.request({ method: 'eth_requestAccounts' })

    // 2. Tạo thông điệp để ký
    const message = `Đăng ký tại DApp lúc ${new Date().toISOString()}`

    // 3. Dùng Ethers.js để ký
    const provider = new ethers.BrowserProvider(window.ethereum)
    const signer = await provider.getSigner()
    const signature = await signer.signMessage(message)

    // 4. Gửi đến backend
    const response = await axios.post('http://localhost:8080/auth/register', {
      address,
      message,
      signature
    })

    statusMessage.value = response.data

  } catch (err) {
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
  border: 1px solid #ccc;
  border-radius: 12px;
  text-align: center;
}
button {
  padding: 0.5rem 1rem;
  background-color: #f6851b;
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
}
</style>
