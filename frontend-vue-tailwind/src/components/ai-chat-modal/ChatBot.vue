<template>
  <div>
    <!-- Nút bật/tắt chat ở góc dưới bên phải -->
    <button @click="chatStore.toggle()" class="chat-toggle-btn">
      <span v-if="!chatStore.isOpen">💬</span>
      <span v-else>×</span>
    </button>

    <!-- Cửa sổ chat -->
    <div v-if="chatStore.isOpen" class="chat-window">
      <div class="chat-header">
        <span>OpenNFT Chatbot</span>
      </div>

      <div class="chat-messages" ref="chatContainer">
        <div
          v-for="(msg, index) in messages"
          :key="index"
          class="chat-message"
          :class="msg.sender"
        >
          <strong>{{ msg.sender === 'you' ? 'You' : 'ONBot' }}:</strong>
          <span>{{ msg.text }}</span>
        </div>

        <div v-if="isLoading" class="chat-message bot">
          <em>ONBot is typing...</em>
        </div>
      </div>

      <div class="chat-input">
        <input
          v-model="input"
          @keyup.enter="sendMessage"
          placeholder="Ask something..."
        />
        <button @click="sendMessage">Send</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, nextTick } from 'vue'
import { useChatStore } from '@/services/chatStore.js'
import axios from 'axios'

const chatStore = useChatStore()
const input = ref('')
const messages = ref([
  { sender: 'bot', text: 'Hi! I am the OpenNFT chatbot. How can I help you?' }
])
const chatContainer = ref(null)
const isLoading = ref(false)

const API_URL = 'http://localhost:8085/api/v1/ai/chat'

async function sendMessage() {
  if (!input.value.trim()) return

  const userMessage = input.value
  messages.value.push({ sender: 'you', text: userMessage })
  input.value = ''
  isLoading.value = true

  try {
    const response = await axios.post(API_URL, { message: userMessage })
    messages.value.push({ sender: 'ONBot', text: response.data.message })
  } catch (error) {
    messages.value.push({ sender: 'ONBot', text: 'Lỗi khi gửi yêu cầu tới server.' })
    console.error(error)
  } finally {
    isLoading.value = false
  }

  await nextTick()
  if (chatContainer.value) {
    chatContainer.value.scrollTop = chatContainer.value.scrollHeight
  }
}
</script>

<style scoped>
.chat-toggle-btn {
  position: fixed;
  bottom: 24px;
  right: 24px;
  background: #353638;
  color: white;
  border: none;
  border-radius: 50%;
  width: 56px;
  height: 56px;
  font-size: 24px;
  cursor: pointer;
  box-shadow: 0 2px 8px rgba(0,0,0,0.2);
  z-index: 999;
}

.chat-window {
  position: fixed;
  bottom: 90px;
  right: 24px;
  width: 300px;
  height: 90vh;
  max-height: 400px;
  background: white;
  border: 1px solid #ccc;
  border-radius: 12px;
  display: flex;
  flex-direction: column;
  box-shadow: 0 4px 16px rgba(0,0,0,0.2);
  z-index: 998;
}

.chat-header {
  background: #353638;
  color: white;
  padding: 12px;
  font-weight: bold;
  border-top-left-radius: 12px;
  border-top-right-radius: 12px;
  text-align: center;
}

.chat-messages {
  flex: 1;
  overflow-y: auto;
  padding: 12px;
  font-size: 14px;
}

.chat-message {
  margin-bottom: 8px;
}
.chat-message.you {
  text-align: right;
  color: #353638;
}
.chat-message.bot {
  text-align: left;
  color: #333;
}

.chat-input {
  display: flex;
  padding: 8px;
  border-top: 1px solid #eee;
}

.chat-input input {
  flex: 1;
  padding: 6px;
  border: 1px solid #ccc;
  border-radius: 6px;
}

.chat-input button {
  margin-left: 6px;
  padding: 6px 10px;
  background: #353638;
  color: white;
  border: none;
  border-radius: 6px;
}
</style>
