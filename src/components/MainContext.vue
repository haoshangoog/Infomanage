<template>
  <div>
    <el-menu theme="dark" default-active="1" class="el-menu-demo" mode="horizontal" @select="handleSelect">
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
    </el-menu>
    <router-view></router-view>
  </div>
</template>
<script>
  export default {
    methods: {
      handleSelect (key, keyPath) {
        console.log(key, keyPath)
      }
    }
  }
</script>
