// stores/auth.js
import { defineStore } from 'pinia'
import Cookies from 'js-cookie'

export const useAuthStore = defineStore('auth', {
  state: () => ({
    token: Cookies.get('token') || null,
    user: null
  }),
  actions: {
    setToken(token) {
      this.token = token
      Cookies.set('token', token, { expires: 7 }) // lưu 7 ngày
    },
    clearToken() {
      this.token = null
      Cookies.remove('token')
    },
    async fetchUserInfo() {
      if (!this.token) return
      try {
        const res = await fetch('/api/user', {
          headers: {
            'Authorization': `Bearer ${this.token}`
          }
        })
        this.user = await res.json()
      } catch (err) {
        console.error('Lỗi lấy thông tin user', err)
      }
    }
  }
})
