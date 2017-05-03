<template>
  <div>
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
      <router-link :to="'/UserManage'" v-show="role == 'ADMIN'">
        <el-menu-item index="5">
          用户管理
        </el-menu-item>
      </router-link>
      <router-link style="float: right" :to="'/Login'" v-show="role == 'GUEST'">
        <el-menu-item index="10">
          登陆
        </el-menu-item>
      </router-link>
      <el-submenu style="float: right" index="11" v-show="role != 'GUEST'">
        <template slot="title">{{role}}</template>
        <router-link :to="'/PersonCenter'" >
          <el-menu-item index="11-1" >个人中心</el-menu-item>
        </router-link>
        <el-menu-item index="11-2" @click="logout()">退出</el-menu-item>
      </el-submenu>
    </el-menu>
    <router-view class="outer-box">
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
    ]),
    methods: {
      logout () {
        this.$confirm('您确认退出吗？, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.logoutReq()
        }).catch(() => {
          this.$notify.info({
            title: '消息',
            message: '取消退出!'
          })
        })
      },
      logoutReq () {
        var vm = this
        vm.$http.post('/user/logout').then((response) => {
          console.log(response)
          if (response.body === '1105') {
            this.$notify({
              title: '成功',
              message: '退出成功!',
              type: 'success'
            })
            this.$store.commit('setRoleGuest')
          } else {
            this.$notify({
              title: '失败',
              message: '退出失败!',
              type: 'warning'
            })
          }
        })
      }
    }
  }

</script>
<style>
  body {
    color: #48576a;
  }

  a {
    text-decoration: none
  }

  .outer-box {
    padding-bottom: 12%;
  }
</style>
