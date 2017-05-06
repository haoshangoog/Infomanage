<template>
  <div>
    <router-view
      class="view"
      keep-alive
      transition
      transition-mode="out-in">
    </router-view>
  </div>
</template>
<script>
  import {mapGetters} from 'vuex'
  export default{
    computed: {
      ...mapGetters(['role'])
    },
    mounted () {
      this.isLoginReq()
    },
    methods: {
      isLoginReq () {
        var vm = this
        vm.$http.post('/user/isLogin').then((response) => {
          if (response.body === '1107') {
            console.log('用户没有进行登录！')
          } else {
            return response.json()
          }
        }).then((json) => {
          if (json === null) {
            this.$store.commit('setRoleGuest')
          } else {
            var identity = json.identity
            if (identity === '1') {
              this.$store.commit('setRoleUser')
            } else if (identity === '2') {
              this.$store.commit('setRoleAdmin')
            } else {
              this.$store.commit('setRoleGuest')
            }
            this.$message({
              type: 'info',
              message: '登录成功'
            })
            this.$router.push('/TestPlanList')
          }
        })
      }
    }
  }
</script>
<style>
  body {
    margin : 0;
    padding : 0;
    background-color: #D3DAD3;
  }
</style>

