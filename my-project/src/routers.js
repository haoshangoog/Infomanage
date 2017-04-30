'use strict'
const MainContext = resolve => require(['./components/MainContext.vue'], resolve)
const Home = resolve => require(['./components/MainContext/Home.vue'], resolve)
const TestPlanList = resolve => require(['./components/MainContext/TestPlanList.vue'], resolve)
const TestPlanForm = resolve => require(['./components/MainContext/TestPlanForm.vue'], resolve)
const FAQList = resolve => require(['./components/MainContext/FAQList.vue'], resolve)
const FAQForm = resolve => require(['./components/MainContext/FAQForm.vue'], resolve)
const UserManage = resolve => require(['./components/MainContext/UserManage.vue'], resolve)
const Login = resolve => require(['./components/MainContext/Login.vue'], resolve)

export default [
  {
    path: '/',
    component: MainContext,
    children: [
      {
        path: '/Home',
        component: Home
      },
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
        path: '/FAQForm/:FAQID',
        component: FAQForm
      },
      {
        path: '/UserManage',
        component: UserManage
      },
      {
        path: '/Login',
        component: Login
      }
    ]
  }
]

