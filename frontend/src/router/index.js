import { createRouter, createWebHistory } from "vue-router";

import app from "../App.vue";
import successPayment from "../components/successPayment.vue";

const routes = [
   {
    path: "/",
    name: "app",
    component: app,
    },
    {
    path: "/success",
    name: "successPayment",
    component: successPayment,
    },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});
export default router;