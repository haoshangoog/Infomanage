<template>
  <div>
    <div>
      Role:{{role}}
    </div>
    <el-menu theme="dark" default-active="1" class="el-menu-demo" mode="horizontal">
      <router-link :to="'/testPlanList'">
        <el-menu-item index="1">
          测试方案
        </el-menu-item>
      </router-link>
      <router-link :to="'/FAQList'">
        <el-menu-item index="2">
          FAQ
        </el-menu-item>
      </router-link>
      <router-link :to="'/Login'">
        <el-menu-item index="3">
          登陆
        </el-menu-item>
      </router-link>
    </el-menu>
    <router-view></router-view>
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
  body{
    color: #48576a;
  }
</style>
