
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

// /* 导入vuex开始 start */
// import Vuex from 'vuex'
// import vuexstore from './store'
// Vue.use(Vuex)
// const vuexStore = new VueRouter({vuexstore})
// /* 导入vuex 结束 */

/* 导入Ajax/Date Start */
var VueResource = require('vue-resource')
Vue.use(VueResource)
/* 导入Ajax/Date END */
Vue.http.interceptors.push(function (request, next) {
  // modify request
  request.credentials = true
  request['url'] = 'http://localhost:8083/infomanage' + request.url
  // request.headers.set('X-CSRF-TOKEN', 'TOKEN')

  // continue to next interceptor
  next(function (response) {
    // modify response
  })
})

import VueClip from 'vue-clip'
Vue.use(VueClip)

import App from './App'
import store from './store'

/* eslint-disable no-new */
new Vue({
  router,
  el: '#app',
  store,
  render: (h) => h(App)
})
