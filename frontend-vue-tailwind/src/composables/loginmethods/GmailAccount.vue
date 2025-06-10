<template>
    <div class="text-black rounded transition" id="googleSignInDiv">
        
    </div>
</template>
<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import { loadGoogleIdentityScript } from '../../services/useGoogleScript.js'


const statusMessage = ref('')

onMounted(async () => {
    await loadGoogleIdentityScript()
    window.google.accounts.id.initialize({
    client_id: '10942482793-kuln1t6m8band0acdojiudnelr00h0ta.apps.googleusercontent.com',
    callback: handleCredentialResponse,
  })

  window.google.accounts.id.renderButton(
    document.getElementById('googleSignInDiv'),
    { theme: 'outline', size: 'large' }
  )
})

const handleCredentialResponse = async (response) => {
  try {
    const idToken = response.credential
    console.log(response.credential)
    const res = await axios.post('http://localhost:8080/auth/registrationbygmail', { idToken })
    statusMessage.value = 'Đăng nhập thành công: ' + res.data.email
  } catch (err) {
    console.error(err)
    statusMessage.value = 'Lỗi đăng nhập: ' + err.message
  }
}

</script>
