// useGoogleScript.js
export const loadGoogleIdentityScript = () => {
  return new Promise((resolve, reject) => {
    if (window.google && window.google.accounts) {
      resolve()
      return
    }

    const script = document.createElement('script')
    script.src = 'https://accounts.google.com/gsi/client'
    script.async = true
    script.defer = true
    script.onload = resolve
    script.onerror = reject
    document.head.appendChild(script)
  })
}
