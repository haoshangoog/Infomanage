<template>
  <div>
    <div>
      Role:{{role}}
    </div>
    <el-menu theme="dark" default-active="1" class="el-menu-demo" mode="horizontal">
      <router-link :to="'/Home'">
        <el-menu-item index="1">
          首页
        </el-menu-item>
      </router-link>
      <router-link :to="'/testPlanList'">
        <el-menu-item index="2">
          测试方案
        </el-menu-item>
      </router-link>
      <router-link :to="'/FAQList'">
        <el-menu-item index="3">
          FAQ
        </el-menu-item>
      </router-link>
      <router-link :to="'/Login'">
        <el-menu-item index="4">
          文档资料
        </el-menu-item>
      </router-link>
      <router-link :to="'/UserManage'">
        <el-menu-item index="5">
          用户管理
        </el-menu-item>
      </router-link>
      <router-link style="float: right" :to="'/Login'">
        <el-menu-item index="10">
          登陆
        </el-menu-item>
      </router-link>
    </el-menu>
    <router-view>
      <div>
        11
      </div>
    </router-view>
    <div style="position: fixed;background-color: #323232;width: 100%;height: 100%;
					text-align: center;z-index: 999;top: 88%;opacity: 0.8;">
      <div style="color: white;padding-top: 20px;">
        瑞斯康达科技发展股份有限公司
        <p>Copyright © 2016 Raisecom Technology Co., Ltd. </p></div>
    </div>
  </div>
</template>
<script>
  import {mapGetters} from 'vuex'
  export default {
    computed: mapGetters([
      'role'
    ])
  }

</script>
<style>
  body {
    color: #48576a;
  }
  a {
    text-decoration:none
  }
</style>
