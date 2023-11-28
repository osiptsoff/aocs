import {createRouter, createWebHashHistory, RouteRecordRaw} from "vue-router";
import MemoryRequestPrompt from "../components/MemoryRequestPrompt.vue";
import ErrorComponent from "../components/ErrorComponent.vue";

const routes: Array<RouteRecordRaw>  = [
    {
        path: '/',
        name: 'root',
        redirect: { name: 'MemoryRequest' }
    },
    {
        path: '/allocate',
        name: 'MemoryRequest',
        component: MemoryRequestPrompt
    },
    {
        name: 'NotFound',
        path: '/:path(.*)*',
        component: ErrorComponent,
    },
];

const router = createRouter({
    history: createWebHashHistory(),
    routes
});

export { router }