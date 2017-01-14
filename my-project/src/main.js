
import Vue from 'vue'

/* 导入ElementUI Start */
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-default/index.css'
Vue.use(ElementUI)
/* 导入ElementUI End */

/* 导入路由开始 start */
import VueRouter from 'vue-router'
import routes from './routers'
Vue.use(VueRouter)
const router = new VueRouter({routes})
/* 导入路由开始 结束 */

/* 导入Ajax/Date Start */
var VueResource = require('vue-resource')
Vue.use(VueResource)
/* 导入Ajax/Date END */

import App from './App'

/* eslint-disable no-new */
new Vue({
  router,
  el: '#app',
  render: (h) => h(App)
})
