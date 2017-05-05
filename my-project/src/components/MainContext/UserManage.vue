<template>
  <div>
    <el-row :gutter="10">
      <el-col :xs="8" :sm="6" :md="4" :lg="3">
        <el-menu mode="vertical" default-active="1" class="">
          <el-menu-item-group title="基本功能">
            <el-menu-item index="1" @click="addUser" v-show="role == 'ADMIN'"><i class="el-icon-message"></i>添加用户
            </el-menu-item>
          </el-menu-item-group>
        </el-menu>
      </el-col>

      <el-col :xs="16" :sm="12" :md="16" :lg="18">
        <div class="grid-content bg-purple-light">

          <el-table
            :data="userList"
            border
            style="width: 100%">
            <el-table-column
              label="用户名"
              width="180">
              <template scope="scope">
                <span style="margin-left: 10px">{{ scope.row.accountName }}</span>
              </template>
            </el-table-column>

            <el-table-column
              label="真实姓名"
              width="180">
              <template scope="scope">
                <span style="margin-left: 10px">{{ scope.row.realName }}</span>
              </template>
            </el-table-column>

            <el-table-column
              label="权限"
              width="180">
              <template scope="scope">
                <el-tag
                  :type="scope.row.identity == '1' ? 'primary' : 'success'"
                  close-transition>{{ scope.row.identity == "1" ? "普通用户" : "管理员" }}
                </el-tag>
              </template>
            </el-table-column>

            <el-table-column
              label="状态"
              width="180">
              <template scope="scope">
                <el-tag
                  :type="scope.row.deleteFlag == '0' ? 'success' : 'waring'"
                  close-transition>{{ scope.row.deleteFlag == "0" ? "使用中" : "禁用" }}
                </el-tag>
              </template>
            </el-table-column>

            <el-table-column label="操作">
              <template scope="scope">
                <el-button
                  size="small"
                  @click="handleEditIdentity(scope.$index, scope.row)">修改权限
                </el-button>
                <el-button
                  size="small"
                  @click="handleResetPassword(scope.row)">重置密码
                </el-button>
                <el-button
                  size="small"
                  type="danger"
                  v-show="scope.row.deleteFlag == '0'"
                  @click="handleDisabledUser(scope.$index, scope.row)">禁用
                </el-button>
                <el-button
                  size="small"
                  type="info"
                  v-show="scope.row.deleteFlag == '1'"
                  @click="handleActivateUser(scope.row)">启用
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <div class="block">
            <el-pagination
              @current-change="handleCurrentChange"
              :current-page="getPage.currentPage"
              :page-size="getPage.pageSize"
              :total="getPage.total"
              layout="prev, pager, next">
            </el-pagination>
          </div>
        </div>
        <el-dialog title="修改用户权限" v-model="dialogEditIdentityFormVisible">
          <el-form :model="currentUserInfo">
            <el-form-item label="当前用户：">
              {{currentUserInfo.accountName}}
            </el-form-item>
            <el-form-item label="权限">
              <el-select v-model="currentUserInfo.identity" placeholder="请选择权限">
                <el-option label="普通用户" value="1"></el-option>
                <el-option label="管理员" value="2"></el-option>
              </el-select>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogEditIdentityFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="handleEditIdentityReq(currentUserInfo.id, currentUserInfo.identity)">确 定</el-button>
          </div>
        </el-dialog>
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
        userList: [],
        dialogEditIdentityFormVisible: false,
        currentUserInfo: [],
        page: {
          'currentPage': 1,
          'pageSize': 5,
          'total': 1
        }
      }
    },
    computed: {
      ...mapGetters(['role']),
      getPage () {
        return {
          'currentPage': Number(this.page.currentPage),
          'pageSize': Number(this.page.pageSize),
          'total': Number(this.page.total)
        }
      }
    },
    mounted () {
      this.getUserPageList(1)
    },
    methods: {
      addUser () {
        this.$prompt('请输入用户名:', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
//          inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
          inputErrorMessage: '用户名格式不正确'
        }).then(({value}) => {
          this.addUserReq(value)
        }).catch(() => {
          this.$notify.info({
            title: '消息',
            message: '取消编辑!'
          })
        })
      },
      addUserReq (accountName) {
        var vm = this
        var formData = new window.FormData()
        formData.append('accountName', accountName)
        formData.append('password', '111111')
        formData.append('identity', '1')
        vm.$http.post('/user/createUser', formData).then((response) => {
          if (response.body === '0101') {
            this.$notify({
              title: '成功',
              message: '创建用户成功!',
              type: 'success'
            })
          } else {
            this.$notify({
              title: '失败',
              message: '创建失败!',
              type: 'waring'
            })
          }
        })
      },

      handleEditIdentity (index, row) {
        this.currentUserInfo = row
        this.dialogEditIdentityFormVisible = true
      },

      handleEditIdentityReq (id, identity) {
        var vm = this
        var formData = new window.FormData()
        formData.append('id', id)
        formData.append('identity', identity)
        this.dialogEditIdentityFormVisible = false
        vm.$http.post('/user/updateUser', formData).then((response) => {
          if (response.body === '0105') {
            this.$notify({
              title: '成功',
              message: '更新用户权限成功!',
              type: 'success'
            })
          } else {
            this.$notify({
              title: '失败',
              message: '更新用户权限失败!',
              type: 'warning'
            })
          }
        })
      },

      handleResetPassword (row) {
        this.$confirm('此操作会重置该用户密码为：111111 ', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.resetPasswordReq(row.id)
        }).catch(() => {
          this.$notify.info({
            title: '消息',
            message: '取消重置密码'
          })
        })
      },
      resetPasswordReq (id) {
        var vm = this
        var formData = new window.FormData()
        formData.append('id', id)
        formData.append('password', '111111')
        vm.$http.post('/user/updateUser', formData).then((response) => {
          if (response.body === '0105') {
            this.$notify({
              title: '成功',
              message: '重置密码成功!',
              type: 'success'
            })
          } else {
            this.$notify({
              title: '失败',
              message: '重置密码失败!',
              type: 'warning'
            })
          }
        })
      },

      handleDisabledUser (index, row) {
        this.$confirm('此操作将删除该用户, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.disabledUserReq(row.id)
        }).catch(() => {
          this.$notify.info({
            title: '消息',
            message: '已取消删除'
          })
        })
      },
      disabledUserReq (userId) {
        var vm = this
        var formData = new window.FormData()
        formData.append('id', userId)
        vm.$http.post('/user/deleteUser', formData).then((response) => {
          if (response.body === '0103') {
            this.$notify({
              title: '成功',
              message: '删除成功!',
              type: 'success'
            })
          } else {
            this.$notify({
              title: '失败',
              message: '删除失败!',
              type: 'waring'
            })
          }
        })
      },

      handleActivateUser (row) {
        this.$confirm('此操作会激活用户 ', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.activateUserReq(row.id)
        }).catch(() => {
          this.$notify.info({
            title: '消息',
            message: '激活用户成功'
          })
        })
      },
      activateUserReq (id) {
        var vm = this
        var formData = new window.FormData()
        formData.append('id', id)
        formData.append('deleteFlag', '0')
        vm.$http.post('/user/updateUser', formData).then((response) => {
          if (response.body === '0105') {
            this.$notify({
              title: '成功',
              message: '激活用户成功!',
              type: 'success'
            })
          } else {
            this.$notify({
              title: '失败',
              message: '激活用户失败!',
              type: 'warning'
            })
          }
        })
      },

      handleCurrentChange (gotoPage) {
        this.getUserPageList(gotoPage)
      },
      getUserPageList (gotoPage) {
        var vm = this
        var formData = new window.FormData()
        formData.append('pageNo', gotoPage)
        formData.append('pageSize', vm.page.pageSize)
        vm.$http.post('/user/pageingUser', formData).then((response) => {
          return response.json()
        }).then((json) => {
          vm.setUserPageListData(json)
        })
      },
      setUserPageListData (json) {
        var vm = this
        vm.userList = json.result
        vm.page.currentPage = json.pageNo
        vm.page.pageSize = json.pageSize
        vm.page.total = json.rowCount
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

