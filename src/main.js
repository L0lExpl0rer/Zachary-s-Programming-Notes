import Vue from 'vue';
import ElementUI from 'element-ui';
import App from './App.vue';
import 'element-ui/lib/theme-chalk/index.css';
import axios from "axios";

Vue.use(ElementUI);
axios.defaults.baseURL = "http://localhost:8082"

new Vue({
    render: (h) => h(App),
}).$mount('#app');
