import { createRouter, createWebHistory } from "vue-router";
import routes from "./router.config";


const router = createRouter({
  //用WebHashHistory会污染url，必须用#指向，似乎更安全
  // history: createWebHashHistory(),
  history: createWebHistory(),
  routes: routes
});
export default router;
