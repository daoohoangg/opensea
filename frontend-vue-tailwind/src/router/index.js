import { createRouter, createWebHistory } from 'vue-router';
import Login from '@/pages/Login.vue';
import Dashboard from '@/pages/Dashboard.vue';
import AuthLayout from '@/components/auth-modal/AuthModalComponent.vue'; 
import FormCreateCollection from '@/layouts/createform/FormCreateCollection.vue';
import FormCreateNFT from '@/layouts/createform/FormCreateNFT.vue';

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
  },
  {
    path: '/create',
    // component: AuthLayout,
    children: [
      {
        path: 'collection', // => /create/collection
        component: FormCreateCollection
      },
      {
        path: 'nft', // => /create/nft
        component: FormCreateNFT
      }
    ]
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
