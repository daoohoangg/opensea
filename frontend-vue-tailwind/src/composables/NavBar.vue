<!-- filepath: d:\Ky4\SpringBasic\Microservices\frontend-vue\src\composables\NavBar.vue -->
<template>
  <nav class="bg-[#141415] group fixed h-screen w-13 hover:w-45 transition-all duration-300 bg-black text-white flex flex-col font-medium">
    <div class="flex items-center p-4 space-x-2">
      <a href="/" class="flex">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-7 w-6 h-6">
          <path stroke-linecap="round" stroke-linejoin="round" d="M2.25 18.75a60.07 60.07 0 0 1 15.797 2.101c.727.198 1.453-.342 1.453-1.096V18.75M3.75 4.5v.75A.75.75 0 0 1 3 6h-.75m0 0v-.375c0-.621.504-1.125 1.125-1.125H20.25M2.25 6v9m18-10.5v.75c0 .414.336.75.75.75h.75m-1.5-1.5h.375c.621 0 1.125.504 1.125 1.125v9.75c0 .621-.504 1.125-1.125 1.125h-.375m1.5-1.5H21a.75.75 0 0 0-.75.75v.75m0 0H3.75m0 0h-.375a1.125 1.125 0 0 1-1.125-1.125V15m1.5 1.5v-.75A.75.75 0 0 0 3 15h-.75M15 10.5a3 3 0 1 1-6 0 3 3 0 0 1 6 0Zm3 0h.008v.008H18V10.5Zm-12 0h.008v.008H6V10.5Z" />
        </svg>
        <h4 class="hidden group-hover:block font-bold pl-3">OpenNFT</h4>
      </a>
    </div>
    <ul class="nav-links flex flex-col space-y-1 p-1">
      <li class="flex items-center space-x-3 hover:bg-gray-900 rounded-md p-2 cursor-pointer transition-colors">
         <a href="#discover" class="group flex item-center space-y-3 space-x-2 hover:text-white pt-3 ">
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="size-7 w-6 h-6">
            <path d="M11.47 3.841a.75.75 0 0 1 1.06 0l8.69 8.69a.75.75 0 1 0 1.06-1.061l-8.689-8.69a2.25 2.25 0 0 0-3.182 0l-8.69 8.69a.75.75 0 1 0 1.061 1.06l8.69-8.689Z" />
            <path d="m12 5.432 8.159 8.159c.03.03.06.058.091.086v6.198c0 1.035-.84 1.875-1.875 1.875H15a.75.75 0 0 1-.75-.75v-4.5a.75.75 0 0 0-.75-.75h-3a.75.75 0 0 0-.75.75V21a.75.75 0 0 1-.75.75H5.625a1.875 1.875 0 0 1-1.875-1.875v-6.198a2.29 2.29 0 0 0 .091-.086L12 5.432Z" />
          </svg>
          <span class="icon-text hidden group-hover:inline font-medium">Discover</span>
        </a>
      </li>
      <li class="dropdown flex justify-end flex-col items-center hover:bg-gray-900 rounded-md cursor-pointer transition-colors" @click="toggleDropdown('stats')">
        <a href="#stats" class="mr-auto group flex item-center space-y-3 space-x-2 hover:text-white pt-3 pl-3 hover:bg-gray-900">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-7 w-6 h-6">
            <path stroke-linecap="round" stroke-linejoin="round" d="M3 13.125C3 12.504 3.504 12 4.125 12h2.25c.621 0 1.125.504 1.125 1.125v6.75C7.5 20.496 6.996 21 6.375 21h-2.25A1.125 1.125 0 0 1 3 19.875v-6.75ZM9.75 8.625c0-.621.504-1.125 1.125-1.125h2.25c.621 0 1.125.504 1.125 1.125v11.25c0 .621-.504 1.125-1.125 1.125h-2.25a1.125 1.125 0 0 1-1.125-1.125V8.625ZM16.5 4.125c0-.621.504-1.125 1.125-1.125h2.25C20.496 3 21 3.504 21 4.125v15.75c0 .621-.504 1.125-1.125 1.125h-2.25a1.125 1.125 0 0 1-1.125-1.125V4.125Z" />
          </svg>
          <span class="icon-text hidden group-hover:inline font-medium">Stats</span>
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-7 w-6 h-6 hidden group-hover:inline">
            <path stroke-linecap="round" stroke-linejoin="round" d="m19.5 8.25-7.5 7.5-7.5-7.5" />
          </svg>
        </a>
        <ul class="dropdown-menu space-y-2 font-medium" v-if="dropdowns.stats">
          <li class="hover:bg-gray-900"><a href="#nfts" class="hidden group-hover:inline ">NFTs</a></li>
          <li class="hover:bg-gray-900"><a href="#tokens" class="hidden group-hover:inline">Tokens</a></li>
        </ul>
      </li>
      <li class=" flex items-center space-x-3 hover:bg-gray-900 rounded-md p-2 cursor-pointer transition-colors">
        <a href="#activity" class="group flex item-center space-y-3 space-x-2 hover:text-white pt-3">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-7 w-6 h-6">
          <path stroke-linecap="round" stroke-linejoin="round" d="M3.75 3v11.25A2.25 2.25 0 0 0 6 16.5h2.25M3.75 3h-1.5m1.5 0h16.5m0 0h1.5m-1.5 0v11.25A2.25 2.25 0 0 1 18 16.5h-2.25m-7.5 0h7.5m-7.5 0-1 3m8.5-3 1 3m0 0 .5 1.5m-.5-1.5h-9.5m0 0-.5 1.5m.75-9 3-3 2.148 2.148A12.061 12.061 0 0 1 16.5 7.605" />
        </svg>
        <span class="hidden group-hover:inline">Activity</span> 
        </a>
      </li>
      <li class="flex items-center space-x-3 hover:bg-gray-900 rounded-md p-2 cursor-pointer transition-colors">
        <a href="#rewards" class="group flex item-center space-y-3 space-x-2 hover:text-white pt-3">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-7 w-6 h-6">
          <path stroke-linecap="round" stroke-linejoin="round" d="M21 11.25v8.25a1.5 1.5 0 0 1-1.5 1.5H5.25a1.5 1.5 0 0 1-1.5-1.5v-8.25M12 4.875A2.625 2.625 0 1 0 9.375 7.5H12m0-2.625V7.5m0-2.625A2.625 2.625 0 1 1 14.625 7.5H12m0 0V21m-8.625-9.75h18c.621 0 1.125-.504 1.125-1.125v-1.5c0-.621-.504-1.125-1.125-1.125h-18c-.621 0-1.125.504-1.125 1.125v1.5c0 .621.504 1.125 1.125 1.125Z" />
        </svg>
        <span class="hidden group-hover:inline">Rewards</span>
          </a>
      </li>
      <li class="flex items-center space-x-3 hover:bg-gray-900 rounded-md p-2 cursor-pointer transition-colors">
        <a href="/opennft/studio" class="group flex item-center space-y-3 space-x-2 hover:text-white pt-3">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-7 w-6 h-6">
          <path stroke-linecap="round" stroke-linejoin="round" d="m16.862 4.487 1.687-1.688a1.875 1.875 0 1 1 2.652 2.652L10.582 16.07a4.5 4.5 0 0 1-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 0 1 1.13-1.897l8.932-8.931Zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0 1 15.75 21H5.25A2.25 2.25 0 0 1 3 18.75V8.25A2.25 2.25 0 0 1 5.25 6H10" />
        </svg>
        <span class="hidden group-hover:inline">Studio</span>
          </a>
      </li>
      <li class="dropdown flex flex-end flex-col items-center space-x-3 hover:bg-gray-900 rounded-md p-2 cursor-pointer transition-colors" @click="toggleDropdown('profile')">
        <a href="#profile" class="mr-auto justify-end group flex item-center space-y-3 space-x-2 hover:text-white pt-3">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-7 w-6 h-6">
            <path stroke-linecap="round" stroke-linejoin="round" d="M17.982 18.725A7.488 7.488 0 0 0 12 15.75a7.488 7.488 0 0 0-5.982 2.975m11.963 0a9 9 0 1 0-11.963 0m11.963 0A8.966 8.966 0 0 1 12 21a8.966 8.966 0 0 1-5.982-2.275M15 9.75a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" />
          </svg>
          <span class="hidden group-hover:inline">Profile</span> 
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-7 w-6 h-6 hidden group-hover:inline">
            <path stroke-linecap="round" stroke-linejoin="round" d="m19.5 8.25-7.5 7.5-7.5-7.5" />
          </svg>
        </a>
        <ul class="dropdown-menu pl-8 pb-5 space-y-2 space-y-2 font-medium" v-if="dropdowns.profile">
          <li><a href="#nfts" class="hidden group-hover:inline">NFTs</a></li>
          <li><a href="#tokens" class="hidden group-hover:inline">Tokens</a></li>
          <li><a href="#listings" class="hidden group-hover:inline">Listings</a></li>
          <li><a href="#offers" class="hidden group-hover:inline">Offers</a></li>
          <li><a href="#deals" class="hidden group-hover:inline">Deals</a></li>
          <li><a href="#portfolio" class="hidden group-hover:inline">Portfolio</a></li>
          <li><a href="#created" class="hidden group-hover:inline">Created</a></li>
          <li><a href="#watchlist" class="hidden group-hover:inline">Watchlist</a></li>
          <li><a href="#favorites" class="hidden group-hover:inline">Favorites</a></li>
          <li><a href="#activity" class="hidden group-hover:inline">Activity</a></li>
        </ul>
      </li>
    </ul>

    <ul class="space-y-4"></ul>

    <ul class="nav-links space-y-1 p-1">
      <li class="dropdown flex flex-end flex-col items-center space-x-3 hover:bg-gray-900 rounded-md p-2 cursor-pointer transition-colors" @click="toggleDropdown('resources')">
        <a href="#resources" class="mr-auto justify-end group flex item-center space-y-3 space-x-2 hover:text-white pt-3">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-7 w-6 h-6">
            <path stroke-linecap="round" stroke-linejoin="round" d="M19.5 14.25v-2.625a3.375 3.375 0 0 0-3.375-3.375h-1.5A1.125 1.125 0 0 1 13.5 7.125v-1.5a3.375 3.375 0 0 0-3.375-3.375H8.25m0 12.75h7.5m-7.5 3H12M10.5 2.25H5.625c-.621 0-1.125.504-1.125 1.125v17.25c0 .621.504 1.125 1.125 1.125h12.75c.621 0 1.125-.504 1.125-1.125V11.25a9 9 0 0 0-9-9Z" />
          </svg>
          <span class="hidden group-hover:inline">Resources</span>
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-7 w-6 h-6 hidden group-hover:inline">
            <path stroke-linecap="round" stroke-linejoin="round" d="m19.5 8.25-7.5 7.5-7.5-7.5" />
          </svg>
        </a>
        <ul class="dropdown-menu pl-8 pb-5 space-y-2 space-y-2 font-medium" v-if="dropdowns.resources">
          <li><a href="#learn" class="hidden group-hover:inline">Learn</a></li>
          <li><a href="#helpcenter" class="hidden group-hover:inline">Help Center</a></li>
          <li><a href="#taxresources" class="hidden group-hover:inline">Tax Resources</a></li>
        </ul>
      </li>
      <li class="dropdown flex flex-end flex-col items-center space-x-3 hover:bg-gray-900 rounded-md p-2 cursor-pointer transition-colors" @click="toggleDropdown('settings')">
          <a href="#settings" class="mr-auto group flex item-center space-y-5 space-x-2 hover:text-white pt-3">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-7 w-6 h-6">
            <path stroke-linecap="round" stroke-linejoin="round" d="M10.343 3.94c.09-.542.56-.94 1.11-.94h1.093c.55 0 1.02.398 1.11.94l.149.894c.07.424.384.764.78.93.398.164.855.142 1.205-.108l.737-.527a1.125 1.125 0 0 1 1.45.12l.773.774c.39.389.44 1.002.12 1.45l-.527.737c-.25.35-.272.806-.107 1.204.165.397.505.71.93.78l.893.15c.543.09.94.559.94 1.109v1.094c0 .55-.397 1.02-.94 1.11l-.894.149c-.424.07-.764.383-.929.78-.165.398-.143.854.107 1.204l.527.738c.32.447.269 1.06-.12 1.45l-.774.773a1.125 1.125 0 0 1-1.449.12l-.738-.527c-.35-.25-.806-.272-1.203-.107-.398.165-.71.505-.781.929l-.149.894c-.09.542-.56.94-1.11.94h-1.094c-.55 0-1.019-.398-1.11-.94l-.148-.894c-.071-.424-.384-.764-.781-.93-.398-.164-.854-.142-1.204.108l-.738.527c-.447.32-1.06.269-1.45-.12l-.773-.774a1.125 1.125 0 0 1-.12-1.45l.527-.737c.25-.35.272-.806.108-1.204-.165-.397-.506-.71-.93-.78l-.894-.15c-.542-.09-.94-.56-.94-1.109v-1.094c0-.55.398-1.02.94-1.11l.894-.149c.424-.07.765-.383.93-.78.165-.398.143-.854-.108-1.204l-.526-.738a1.125 1.125 0 0 1 .12-1.45l.773-.773a1.125 1.125 0 0 1 1.45-.12l.737.527c.35.25.807.272 1.204.107.397-.165.71-.505.78-.929l.15-.894Z" />
            <path stroke-linecap="round" stroke-linejoin="round" d="M15 12a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" />
          </svg>
          <span class="hidden group-hover:inline">Settings</span>
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-7 w-6 h-6 hidden group-hover:inline">
            <path stroke-linecap="round" stroke-linejoin="round" d="m19.5 8.25-7.5 7.5-7.5-7.5" />
          </svg>
        </a>
        <ul class="dropdown-menu pl-8 pb-5 space-y-2 space-y-2 font-medium" v-if="dropdowns.settings">
          <li><a href="#profile" class="hidden group-hover:inline">Profile</a></li>
          <li><a href="#emailnotify" class="hidden group-hover:inline">Email Notifications</a></li>
          <li><a href="#developer" class="hidden group-hover:inline">Developer</a></li>
          <li><a href="#verification" class="hidden group-hover:inline">Verification</a></li>
          <li><a href="#language" class="hidden group-hover:inline">Language</a></li>
          <li><a href="#tokens" class="hidden group-hover:inline">Tokens</a></li>
        </ul>
      </li>
      <li class="flex items-center space-x-3 hover:bg-gray-900 rounded-md p-2 cursor-pointer transition-colors"><a href="#support" class="group flex item-center space-y-3 space-x-2 hover:text-white pt-3">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-7 w-6 h-6">
          <path stroke-linecap="round" stroke-linejoin="round" d="M9.879 7.519c1.171-1.025 3.071-1.025 4.242 0 1.172 1.025 1.172 2.687 0 3.712-.203.179-.43.326-.67.442-.745.361-1.45.999-1.45 1.827v.75M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Zm-9 5.25h.008v.008H12v-.008Z" />
        </svg>
        <span class="hidden group-hover:inline">Support</span>
        </a>
      </li>
      <li class="flex items-center space-x-3 hover:bg-gray-900 rounded-md p-2 cursor-pointer transition-colors"><a href="#backto" class="group flex item-center space-y-3 space-x-2 hover:text-white pt-3">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-7 w-6 h-6">
          <path stroke-linecap="round" stroke-linejoin="round" d="M9 15 3 9m0 0 6-6M3 9h12a6 6 0 0 1 0 12h-3" />
        </svg>
        <span class="hidden group-hover:inline">Back to OS1</span>
        </a>
      </li>
    </ul>
  </nav>
</template>

<script>
import { HomeIcon } from '@heroicons/vue/24/solid';
export default {
  name: "NavBar",
  data() {
    return {
      dropdowns: {
        stats: false, // Tracks the visibility of the Stats dropdown
        profile: false, // Tracks the visibility of the Profile dropdown
        resources: false, // Tracks the visibility of the Resources dropdown
        settings: false, // Tracks the visibility of the Settings dropdown
      },
    };
  },
  methods: {
    toggleDropdown(menu) {
      // Toggles the visibility of the specified dropdown
      this.dropdowns[menu] = !this.dropdowns[menu];
    },
  },
};

</script>