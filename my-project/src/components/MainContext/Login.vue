<template>
  <div>
    <el-input v-model="userName" placeholder="请输入用户名"></el-input>
    <el-input v-model="passWord" placeholder="请输入密码"></el-input>
    <el-button type="button" @click="loginReq">登陆</el-button>

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
        vm.$http.post('http://localhost:8085/user/login', formData).then((response) => {
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
        })
      }
    }
  }
</script>
