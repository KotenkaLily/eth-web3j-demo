import {createApp} from 'vue'
import App from './App';
import store from './store'
import router from './router'
import Antd from 'ant-design-vue';
//overwrite default theme by {vue.config.js}'s css configuration
import 'ant-design-vue/dist/antd.less'
//permission
// import './permission'

const app = createApp(App);
app.use(store)
app.use(router)
app.use(Antd)
app.mount('#app')