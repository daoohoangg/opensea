<template>
  <div class="bg-[#131313] h-18 items-center grid grid-cols-15">
    <div class="col-span-1"></div>
    <!-- Ô search -->
    <div
      ref="searchBoxRef"
      class=" items-center flex flex-row  bg-[#131313] border border-gray-600 rounded-md px-5 py-3 w-80 h-10 col-span-9"
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
    <div class="flex flex-row justify-end items-end col-span-4">
      <div @click="showSignup = true"
      class="cursor-pointer text-white text-sm font-semibold flex flex-row">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
          <path stroke-linecap="round" stroke-linejoin="round" d="M21 12a2.25 2.25 0 0 0-2.25-2.25H15a3 3 0 1 1-6 0H5.25A2.25 2.25 0 0 0 3 12m18 0v6a2.25 2.25 0 0 1-2.25 2.25H5.25A2.25 2.25 0 0 1 3 18v-6m18 0V9M3 12V9m18 0a2.25 2.25 0 0 0-2.25-2.25H5.25A2.25 2.25 0 0 0 3 9m18 0V6a2.25 2.25 0 0 0-2.25-2.25H5.25A2.25 2.25 0 0 0 3 6v3" />
        </svg>
        <p class="ml-1">Connect Wallet</p>
      </div>
      <div class="text-white mb-0.5 font-light px-1">|</div>
      <div @click="showSignup = true"
      class="cursor-pointer text-white"
      >  
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
          <path stroke-linecap="round" stroke-linejoin="round" d="M17.982 18.725A7.488 7.488 0 0 0 12 15.75a7.488 7.488 0 0 0-5.982 2.975m11.963 0a9 9 0 1 0-11.963 0m11.963 0A8.966 8.966 0 0 1 12 21a8.966 8.966 0 0 1-5.982-2.275M15 9.75a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" />
        </svg>
      </div>
      <!-- khung dang ky tai khoan -->
      <PopUpAccount v-if="showSignup" @close="showSignup = false"/>
    </div>
  </div>
  
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue'
import PopUpAccount from './loginmethods/PopUpAccount.vue'

//khung search
const showPopup = ref(false)
const popupRef = ref(null)
const searchBoxRef = ref(null)
// khung dang ky tai khoan
const showSignup = ref(false)
const popupRef1 = ref(null)
const searchBoxRef1 = ref(null)
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

</script>

