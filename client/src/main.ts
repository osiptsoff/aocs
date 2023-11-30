
import { createApp } from 'vue';
import { createPinia } from "pinia";

import { router } from "./router";
import { vuetify } from "./vuetify";

import App from './App.vue';


const pinia = createPinia();
const app = createApp(App);

app
    .use(router)
    .use(pinia)
    .use(vuetify);

app.mount('#app')
