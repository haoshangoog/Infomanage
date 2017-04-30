<template>
  <div>
    <el-row :gutter="10">
      <el-col :xs="8" :sm="6" :md="4" :lg="3">
        <el-menu mode="vertical" default-active="1" class="" @select="handleSelect">
          <el-menu-item-group title="基本功能">
            <el-menu-item index="1"><i class="el-icon-message"></i>修改信息</el-menu-item>
            <el-menu-item index="2"><i class="el-icon-message"></i>修改密码</el-menu-item>
          </el-menu-item-group>
        </el-menu>
      </el-col>

      <el-col :xs="16" :sm="12" :md="16" :lg="18">
        <div class="grid-content bg-purple-light">
          <!-- 修改个人信息 -->
          <div v-show="menuKey == 1 ">
            <el-form label-width="80px">
              <el-form-item label="用户名">
                <el-input v-model="currentUserInfo.accountName" :disabled="true"></el-input>
              </el-form-item>
              <el-form-item label="真实姓名">
                <el-input v-model="currentUserInfo.realName"></el-input>
              </el-form-item>
              <el-form-item label="权限">
                <el-select v-model="currentUserInfo.identity" placeholder="权限" :disabled="true">
                  <el-option label="普通用户" value=Number(1)></el-option>
                  <el-option label="管理员" value=Number(2)></el-option>
                </el-select>
              </el-form-item>
            </el-form>
            <el-button type="primary" @click="handleUpdateUserInfo()">确认修改</el-button>
          </div>
          <!-- 修改密码 -->
          <div v-show="menuKey == 2 ">
            <el-form label-width="80px">
              <el-form-item label="原密码">
                <el-input v-model="password.old"></el-input>
              </el-form-item>
              <el-form-item label="新密码">
                <el-input v-model="password.new"></el-input>
              </el-form-item>
              <el-form-item label="重新输入新密码">
                <el-input v-model="password.confirm"></el-input>
              </el-form-item>
            </el-form>
            <el-button type="primary" @click="">确认修改密码</el-button>
          </div>

        </div>
      </el-col>
      <el-col :xs="8" :sm="6" :md="4" :lg="3">
      </el-col>
    </el-row>
  </div>
</template>
<script>
  import {mapGetters} from 'vuex'
  export default{
    data () {
      return {
        currentUserInfo: {},
        menuKey: 1,
        password: {
          old: '',
          new: '',
          confirm: ''
        }
      }
    },
    computed: {
      ...mapGetters(['role'])
    },
    mounted () {
      this.getUserInfo()
    },
    methods: {
      handleSelect (key, keyPath) {
        this.menuKey = key
      },

      handleUpdateUserInfo () {
        this.$confirm('您确定更新您的个人信息吗？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.resetPasswordReq()
        }).catch(() => {
          this.$notify.info({
            title: '消息',
            message: '取消更新个人信息'
          })
        })
      },
      UpdateUserInfoReq () {
        var vm = this
        var formData = new window.FormData()
        formData.append('id', vm.currentUserInfo.id)
        formData.append('realName', vm.currentUserInfo.realName)
        vm.$http.post('/user/updateUser', formData).then((response) => {
          if (response.body === '0105') {
            this.$notify({
              title: '成功',
              message: '更新个人信息成功!',
              type: 'success'
            })
          } else {
            this.$notify({
              title: '失败',
              message: '更新个人信息失败!',
              type: 'warning'
            })
          }
        })
      },

      handleUpdatePassword () {
        if (this.password.new !== this.password.confirm && this.password.new !== '') {
          this.$confirm('您输入的两次新密码不一致', '提示', {
            confirmButtonText: '确定',
            type: 'warning'
          }).then(() => {
            this.password.new = this.password.confirm = ''
            return
          })
        }
        this.$confirm('您确定更新您的个人信息吗？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.resetPasswordReq()
        }).catch(() => {
          this.$notify.info({
            title: '消息',
            message: '取消更新个人信息'
          })
        })
      },
      UpdatePasswordReq () {
        var vm = this
        var formData = new window.FormData()
        formData.append('id', vm.currentUserInfo.id)
        formData.append('password', this.password.new)
        vm.$http.post('/user/updateUser', formData).then((response) => {
          if (response.body === '0105') {
            this.$notify({
              title: '成功',
              message: '更新密码成功!',
              type: 'success'
            })
          } else {
            this.$notify({
              title: '失败',
              message: '更新密码失败!',
              type: 'warning'
            })
          }
        })
      },

      getUserInfo () {
        var vm = this
        vm.$http.post('/user/isLogin').then((response) => {
          return response.json()
        }).then((json) => {
          vm.currentUserInfo = {
            'id': 2,
            'accountName': 'admin',
            'realName': '杨杨毅',
            'password': 'admin',
            'identity': '2',
            'deleteFlag': '0'
          }
        })
      }
    }
  }
</script>

<style>
  .text {
    font-size: 20px;
  }

  .item {
    padding: 10px 0;
  }

  .bg-purple-light {
    background: #e5e9f2;
  }

  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
</style>

