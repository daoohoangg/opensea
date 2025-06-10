import { createApp } from 'vue'
import App from './App.vue'
import router from './router';
import './style.css'
import { Buffer } from 'buffer'


const app = createApp(App);
app.use(router);
app.mount('#app');
window.Buffer = Buffer