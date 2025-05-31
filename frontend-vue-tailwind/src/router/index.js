import { createRouter, createWebHistory } from 'vue-router';
import Login from '@/pages/Login.vue';
import Dashboard from '@/pages/Dashboard.vue';
import AuthLayout from '@/components/auth-modal/AuthModalComponent.vue'; 


const routes = [
  {
    path: '/',
    component: Dashboard
  },
  {
    path: '/auth',
    component: AuthLayout,
    children: [
      {
        path: '/login', // => /auth/login
        component: Login
      }
    ]
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
