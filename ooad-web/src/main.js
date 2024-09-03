import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css'
import './assets/global.css';
import axios from "axios";
import VueRouter from "vue-router";
import router  from "./router";
import store from './store'
import VueI18n from 'vue-i18n';

Vue.filter('timefilter',function(originVal){

    var  aa=new Date(originVal)

    let bb=aa.toLocaleString()

    return bb
})
/*
// axios.interceptors.request.use(
//     config => {
//       if (localStorage.getItem("CurUser")) { //判断token是否存在
//         config.headers.Authorization = "Bearer"+" "+JSON.parse(localStorage.getItem("CurUser")).token;  //将token设置成请求头
//       }
//       return config;
//     },
//     err => {
//       return Promise.reject(err);
//     }
// );

*/
Vue.prototype.$axios=axios;
Vue.prototype.$httpUrl='http://8.134.23.156:8090'
// Vue.prototype.$httpUrl='http://localhost:8090'
Vue.config.productionTip = false
Vue.use(ElementUI,{size:'small'});
Vue.use(VueRouter);
Vue.use(VueI18n);

const i18n = new VueI18n({
  locale: 'zh', // 默认语言
  fallbackLocale: 'zh',
  messages: {
    en: require('./locales/en.json'),
    zh: require('./locales/zh.json'),
    // 添加其他语言
  },
});
new Vue({
  router,
  store,
  i18n,
  render: h => h(App),
}).$mount('#app')


