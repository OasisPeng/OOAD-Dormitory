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

Vue.prototype.$axios=axios;
Vue.prototype.$httpUrl='http://localhost:8090'
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


