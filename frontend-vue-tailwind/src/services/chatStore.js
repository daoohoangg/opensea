
import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useChatStore = defineStore('chat', () => {
  const isOpen = ref(false)
  function toggle() {
    isOpen.value = !isOpen.value
  }

  return { isOpen, toggle }
})
