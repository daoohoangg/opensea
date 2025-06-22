import { createRouter, createWebHistory } from 'vue-router';
import Login from '@/pages/Login.vue';
import Dashboard from '@/pages/Dashboard.vue';
import AuthLayout from '@/components/auth-modal/AuthModalComponent.vue'; 

import CreateCollectionAndNft from '@/pages/CreateCollectionAndNft.vue';
import FormCreateCollection from '@/layouts/createform/FormCreateCollection.vue';
import FormCreateNFT from '@/layouts/createform/FormCreateNFT.vue';
import StudioLayout from '@/pages/StudioLayout.vue';
import DropNFTs from '@/layouts/createform/DropNFTs.vue';


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
    path: '/dashboard',
    component: DropNFTs
  },
  {
  path: '/opennft/studio',
  component: StudioLayout,
  children: [
    {
      path: 'create-drop', // full path: /opennft/studio/create-collection
      component: FormCreateCollection
    },
    {
      path: 'create-nft', // full path: /opennft/studio/create-nft
      component: FormCreateNFT
    },
    {
      path: '',
      component: CreateCollectionAndNft,
    }
  ]
}

];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
