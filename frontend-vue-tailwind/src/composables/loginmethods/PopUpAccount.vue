<template>
  <div class="min-h-screen flex items-center justify-center bg-gray-50 px-4 ">
    <div class="w-full max-w-md bg-white p-6 rounded-lg shadow">
      <h2 class="text-2xl font-bold text-center mb-6">Welcome back</h2>

      <form @submit.prevent="handleLogin">
        <div class="mb-4">
          <label class="block text-sm font-medium text-gray-700 mb-1">
            Email <span class="text-red-500">*</span>
          </label>
          <input
            v-model="email"
            type="email"
            placeholder="name@email.com"
            class="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring focus:border-blue-500"
            required
          />
        </div>

        <div class="mb-4">
          <label class="block text-sm font-medium text-gray-700 mb-1">
            Password <span class="text-red-500">*</span>
          </label>
          <div class="relative">
            <input
              :type="showPassword ? 'text' : 'password'"
              v-model="password"
              placeholder="Enter your password"
              class="w-full border border-gray-300 rounded px-3 py-2 pr-10 focus:outline-none focus:ring focus:border-blue-500"
              required
            />
            <button type="button" class="absolute right-2 top-2 text-gray-500" @click="toggleShowPassword">
              👁️
            </button>
          </div>
        </div>

        <div class="text-right mb-4">
          <a href="#" class="text-sm text-blue-600 hover:underline">Forgot password?</a>
        </div>

        <button type="submit" class="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700 transition">
          Login
        </button>
      </form>

      <div class="my-6 flex items-center justify-between">
        <hr class="w-1/3 border-gray-300" />
        <span class="text-sm text-gray-500">or</span>
        <hr class="w-1/3 border-gray-300" />
      </div>

      <div class="space-y-3">
        <GmailAccount />
        <!-- dang nhap bang vi moblie tich hop quet maxQR -->
        <button class="w-full border border-gray-300 flex items-center justify-center py-2 rounded hover:bg-gray-100">
          <img src="https://www.svgrepo.com/show/475647/facebook-color.svg" alt="Facebook" class="w-5 h-5 mr-2" />
          Continue with QRCode
        </button>

        <button class="w-full border border-gray-300 flex items-center justify-center py-2 rounded hover:bg-gray-100">
          <img src="https://www.svgrepo.com/show/303128/apple-logo.svg" alt="Apple" class="w-5 h-5 mr-2" />
          Continue with <MetamaskAccount />
        </button>
      </div>

      <p class="text-center text-sm mt-6">
        New to OPENNFT? <a href="#" class="text-blue-600 hover:underline">Sign up</a>
      </p>

      <p class="text-center text-sm mt-2">
        <a href="#" class="text-blue-600 hover:underline">Log in with your organization</a>
      </p>

      <p class="text-xs text-gray-400 text-center mt-4">
        This site is protected by reCAPTCHA Enterprise and the Google
        <a href="#" class="underline">Privacy Policy</a> and
        <a href="#" class="underline">Terms of Service</a> apply.
      </p>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import GmailAccount from './GmailAccount.vue';
import MetamaskAccount from './MetamaskAccount.vue';

const email = ref('');
const password = ref('');
const showPassword = ref(false);

const toggleShowPassword = () => {
  showPassword.value = !showPassword.value;
};

const handleLogin = () => {
  alert(`Email: ${email.value}, Password: ${password.value}`);
};

</script>
