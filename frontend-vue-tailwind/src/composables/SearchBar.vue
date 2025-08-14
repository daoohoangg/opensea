<template>
  <div class="bg-[#131313] h-18 items-center grid grid-cols-15">
    <div class="col-span-1"></div>
    <!-- Ô search -->
    <div ref="searchBoxRef"
      class=" items-center flex flex-row  bg-[#131313] border border-gray-600 rounded-md px-5 py-3 w-80 h-10 col-span-9"
      @click="showPopup = true">
      <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor"
        class="w-4 h-4 text-gray-400 mr-2">
        <path stroke-linecap="round" stroke-linejoin="round"
          d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z" />
      </svg>
      <input type="text" placeholder="Search OpenSea"
        class="bg-transparent text-white placeholder-gray-400 outline-none flex-1" readonly />
      <div class="text-sm text-gray-400 ml-2 border border-gray-600 rounded px-1 py-0.5">/</div>
    </div>
    <Transition name="fade">
      <div v-if="showPopup" class="fixed inset-0 backdrop-blur-sm z-40 transition-opacity duration-10"></div>
    </Transition>

    <!-- POP-UP ở giữa màn hình -->
    <Transition name="fade-scale" enter-active-class="transition duration-250 ease-out"
      enter-from-class="opacity-0 scale-95" enter-to-class="opacity-100 scale-100"
      leave-active-class="transition duration-150 ease-in" leave-from-class="opacity-100 scale-100"
      leave-to-class="opacity-0 scale-95">
      <div v-if="showPopup" class="fixed inset-0 flex justify-center items-center z-50">
        <div
          class="bg-[#1a1a1a] w-[600px] max-h-[80vh] overflow-y-auto rounded-lg border border-gray-700 shadow-lg p-6 relative"
          ref="popupRef">
          <!-- Nút đóng -->
          <!-- <button
          @click="showPopup = false"
          class="absolute top-2 right-2 text-gray-400 hover:text-white"
        >
          ✕
        </button> -->

          <!-- Thanh input search bên trong -->
          <div class="flex items-center border border-gray-600 rounded-md px-4 py-2 mb-4">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
              stroke="currentColor" class="w-4 h-4 text-gray-400 mr-2">
              <path stroke-linecap="round" stroke-linejoin="round"
                d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z" />
            </svg>
            <input type="text" placeholder="Search OpenSea"
              class="bg-transparent text-white placeholder-gray-400 outline-none flex-1" />
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
    <div class="col-span-4">
      <div v-if="!isLoggedIn" class="flex flex-row justify-end items-end">
        <div @click="showSignup = true" class="cursor-pointer text-white text-sm font-semibold flex flex-row">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
            stroke="currentColor" class="size-6">
            <path stroke-linecap="round" stroke-linejoin="round"
              d="M21 12a2.25 2.25 0 0 0-2.25-2.25H15a3 3 0 1 1-6 0H5.25A2.25 2.25 0 0 0 3 12m18 0v6a2.25 2.25 0 0 1-2.25 2.25H5.25A2.25 2.25 0 0 1 3 18v-6m18 0V9M3 12V9m18 0a2.25 2.25 0 0 0-2.25-2.25H5.25A2.25 2.25 0 0 0 3 9m18 0V6a2.25 2.25 0 0 0-2.25-2.25H5.25A2.25 2.25 0 0 0 3 6v3" />
          </svg>
          <p class="ml-1">Connect Wallet</p>
        </div>

        <div class="text-white mb-0.5 font-light px-1">|</div>
        <div @click="showSignup = true" class="cursor-pointer text-white">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
            stroke="currentColor" class="size-6">
            <path stroke-linecap="round" stroke-linejoin="round"
              d="M17.982 18.725A7.488 7.488 0 0 0 12 15.75a7.488 7.488 0 0 0-5.982 2.975m11.963 0a9 9 0 1 0-11.963 0m11.963 0A8.966 8.966 0 0 1 12 21a8.966 8.966 0 0 1-5.982-2.275M15 9.75a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" />
          </svg>
        </div>
      </div>
      <div v-else class="relative flex flex-row justify-end items-end">
        <!-- Hiển thị địa chỉ ví + icon -->
        <div class="text-white mb-0.5 font-bold">
          <span>$0.00</span>
        </div>
        <div class="text-gray-300 mb-0.5 font-light px-4">|</div>
        <div class="text-white cursor-pointer">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
            stroke="currentColor" class="size-6">
            <path stroke-linecap="round" stroke-linejoin="round"
              d="M14.857 17.082a23.848 23.848 0 0 0 5.454-1.31A8.967 8.967 0 0 1 18 9.75V9A6 6 0 0 0 6 9v.75a8.967 8.967 0 0 1-2.312 6.022c1.733.64 3.56 1.085 5.455 1.31m5.714 0a24.255 24.255 0 0 1-5.714 0m5.714 0a3 3 0 1 1-5.714 0" />
          </svg>
        </div>
        <div class="text-gray-300 mb-0.5 font-light px-4">|</div>
        <div class="flex items-center cursor-pointer text-white " @mouseenter="dropdownOpen = true"
          @mouseleave="dropdownOpen = false">
          <span>{{ addressWallet?.slice(0, 4) }}...{{ addressWallet?.slice(-2) }}</span>
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
            stroke="currentColor" class="w-5 h-5 ml-1">
            <path stroke-linecap="round" stroke-linejoin="round" d="m19.5 8.25-7.5 7.5-7.5-7.5" />
          </svg>
        </div>

        <transition name="fade">
          <div v-if="dropdownOpen" 
                @mouseenter="dropdownOpen = true" 
                @mouseleave="dropdownOpen = false"
                class="absolute left-60 mt-2 top-full w-48 bg-white rounded-lg shadow-lg overflow-hidden z-50">
            <ul class="text-sm font-bold text-gray-700">
              <li @click.stop="goTo('account')" class="px-4 py-2 hover:bg-gray-100 cursor-pointer flex">
                <span class="mr-2">
                  <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                    stroke="currentColor" class="size-6">
                    <path stroke-linecap="round" stroke-linejoin="round"
                      d="M8.625 12a.375.375 0 1 1-.75 0 .375.375 0 0 1 .75 0Zm0 0H8.25m4.125 0a.375.375 0 1 1-.75 0 .375.375 0 0 1 .75 0Zm0 0H12m4.125 0a.375.375 0 1 1-.75 0 .375.375 0 0 1 .75 0Zm0 0h-.375M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z" />
                  </svg>
                </span>
                <span v-if="!addressWallet">
                  Account
                </span>
                <span v-else>
                  {{ addressWallet?.slice(0, 5) }}...{{ addressWallet?.slice(-5) }}
                </span>
              </li>
              <li @click="showSignup = true"  class="px-4 py-2 hover:bg-gray-100 cursor-pointer flex">
                <span class="mr-2">
                  <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                    stroke="currentColor" class="size-6">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M12 4.5v15m7.5-7.5h-15" />
                  </svg>
                </span>
                <span>Connect Wallet</span>
              </li>
              <li @click.stop="goTo('profile')" class="px-4 py-2 hover:bg-gray-100 cursor-pointer flex">
                <span class="mr-2">
                  <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                    stroke="currentColor" class="size-6">
                    <path stroke-linecap="round" stroke-linejoin="round"
                      d="M17.982 18.725A7.488 7.488 0 0 0 12 15.75a7.488 7.488 0 0 0-5.982 2.975m11.963 0a9 9 0 1 0-11.963 0m11.963 0A8.966 8.966 0 0 1 12 21a8.966 8.966 0 0 1-5.982-2.275M15 9.75a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" />
                  </svg>
                </span>
                <span>Profile</span>
              </li>
              <li @click.stop="handleLogout"  class="px-4 py-2 hover:bg-gray-100 cursor-pointer text-red-500 flex">
                <span class="mr-2">
                  <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                    stroke="currentColor" class="size-6">
                    <path stroke-linecap="round" stroke-linejoin="round"
                      d="M8.25 9V5.25A2.25 2.25 0 0 1 10.5 3h6a2.25 2.25 0 0 1 2.25 2.25v13.5A2.25 2.25 0 0 1 16.5 21h-6a2.25 2.25 0 0 1-2.25-2.25V15m-3 0-3-3m0 0 3-3m-3 3H15" />
                  </svg>
                </span>
                <span>
                  Logout
                </span>
              </li>
            </ul>
          </div>
        </transition>
      </div>
      <!-- khung dang ky tai khoan -->
      <PopUpAccount v-if="showSignup" @close="showSignup = false" />
    </div>
  </div>

</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue';
import PopUpAccount from './loginmethods/PopUpAccount.vue';
import axios from 'axios';

//khung search
const showPopup = ref(false)
const popupRef = ref(null)
const searchBoxRef = ref(null)
// khung dang ky tai khoan
const showSignup = ref(false)
const popupRef1 = ref(null)
const searchBoxRef1 = ref(null)
//
const accessToken = ref(null)

onMounted(() => {
  document.addEventListener('click', handleClickOutside)
})

onBeforeUnmount(() => {
  document.removeEventListener('click', handleClickOutside)
})

function handleClickOutside(event) {
  const popup = popupRef.value
  const searchBox = searchBoxRef.value

  const popup1 = popupRef1.value?.popupEl
  const searchBox1 = searchBoxRef1.value

  if (
    popup &&
    !popup.contains(event.target) &&
    searchBox &&
    !searchBox.contains(event.target)
  ) {
    showPopup.value = false
  }

  if (
    popup1 &&
    !popup1.contains(event.target) &&
    searchBox1 &&
    !searchBox1.contains(event.target)
  ) {
    showSignup.value = false
  }
}

const isLoggedIn = ref(false) // trạng thái đăng nhập
const addressWallet = ref(null)
onMounted(() => {
  // Kiểm tra localStorage
  const user = localStorage.getItem('access_token') // hoặc token / walletAddress
  const address = localStorage.getItem('wallet_address')
  if (user) {
    isLoggedIn.value = true
    addressWallet.value = address
  }
})
// khung dropdown cho 
const dropdownOpen = ref(false)

function goTo(page) {
  console.log("Đi tới trang:", page)
  dropdownOpen.value = false
}

const emit = defineEmits(['logout-success'])

//logout
  const handleLogout = async () => {
    try {
      const accessToken = localStorage.getItem('access_token');
      console.log(accessToken);

      await axios.post(
        `${import.meta.env.VITE_BASE_URL}/v1/auth/logout`,
        {},
        {
          withCredentials: true,
          headers: {
            Authorization: `Bearer ${accessToken}`
          }
        }
      );


      if (accessToken) {
        localStorage.removeItem('access_token');
        localStorage.removeItem('wallet_address');
        localStorage.removeItem('refresh_token');
      }
      emit('logout-success');
    }
    catch (err) {
      console.error(err);
    }
  }
</script>

<style>
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.2s;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>