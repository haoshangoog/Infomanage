'use strict'
const MainContext = resolve => require(['./components/MainContext.vue'], resolve)
const TestPlanList = resolve => require(['./components/MainContext/TestPlanList.vue'], resolve)
const TestPlanForm = resolve => require(['./components/MainContext/TestPlanForm.vue'], resolve)
const FAQList = resolve => require(['./components/MainContext/FAQList.vue'], resolve)
const Login = resolve => require(['./components/MainContext/Login.vue'], resolve)

export default [
  {
    path: '/',
    component: MainContext,
    children: [
      {
        path: '/TestPlanList',
        component: TestPlanList
      },
      {
        path: '/TestPlanForm/:testPlanID',
        component: TestPlanForm
      },
      {
        path: '/FAQList',
        component: FAQList
      },
      {
        path: '/Login',
        component: Login
      }
    ]
  }
]

