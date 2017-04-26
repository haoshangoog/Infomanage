<template>
  <div>
    <el-row :gutter="10">
      <el-col :xs="0" :sm="3" :md="6" :lg="8">
        <span>.</span>
      </el-col>
      <el-col :xs="24" :sm="18" :md="12" :lg="6">
        <el-card class="box-card" >
          <div slot="header" class="clearfix">
            <span>登录</span>
          </div>
          <el-input v-model="userName" placeholder="请输入用户名"></el-input>
          <el-input v-model="passWord" placeholder="请输入密码"></el-input>
          <el-button type="button" @click="loginReq">登陆</el-button>
        </el-card>
      </el-col>
      <el-col :xs="0" :sm="3" :md="6" :lg="9"></el-col>
    </el-row>
  </div>
</template>
<script>
  import {mapGetters} from 'vuex'
  export default{
    data () {
      return {
        userName: '',
        passWord: ''
      }
    },
    computed: mapGetters([
      'role'
    ]),
    methods: {
      loginReq () {
        var vm = this
        var formData = new window.FormData()
        formData.append('accountName', vm.userName)
        formData.append('password', vm.passWord)
        vm.$http.post('/user/login', formData).then((response) => {
          if (response.body === '1104') {
            this.$message({
              type: 'info',
              message: '登陆失败'
            })
            return null
          } else {
            return response.json()
          }
        }).then((json) => {
          if (json === null) {
            this.$store.commit('setRoleGuest')
          } else {
            var identity = json.identity
            if (identity === 1) {
              this.$store.commit('setRoleUser')
            } else if (identity === 2) {
              this.$store.commit('setRoleAdmin')
            } else {
              this.$store.commit('setRoleGuest')
            }
          }
          this.$message({
            type: 'info',
            message: '登录成功'
          })
          this.$router.push('/TestPlanList')
        })
      }
    }
  }
</script>

<style>
  .el-card {
    background-color: #fff;
    overflow: hidden;
    margin-top: 15px;
  }

  .el-input {
    margin-bottom: 10px;
  }
</style>
