import {createRouter, createWebHashHistory, RouteRecordRaw} from "vue-router";
import MemoryRequestPrompt from "../components/MemoryRequestPrompt.vue";
import ErrorComponent from "../components/ErrorComponent.vue";
import ProcessorPage from "../components/ProcessorPage.vue";

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
        path: '/processor',
        name: 'Processor',
        component: ProcessorPage
    },
    {
        path: '/:path(.*)*',
        name: 'Error',
        component: ErrorComponent,
    },
];

const router = createRouter({
    history: createWebHashHistory(),
    routes
});

export { router }