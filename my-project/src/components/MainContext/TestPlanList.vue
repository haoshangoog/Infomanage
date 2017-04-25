<template>
  <div id="testPlanList">
    <el-row :gutter="10">
      <el-col :xs="8" :sm="6" :md="4" :lg="3">
        <el-menu mode="vertical" default-active="1" class="">
          <el-menu-item-group title="基本功能">
            <el-menu-item index="1" @click="addTestPlan"><i class="el-icon-message"></i>添加测试方案</el-menu-item>
            <el-menu-item index="2"><i class="el-icon-message"></i>其他</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="扩展功能">
            <el-menu-item index="3"><i class="el-icon-message"></i>其他</el-menu-item>
            <el-menu-item index="4"><i class="el-icon-message"></i>其他</el-menu-item>
          </el-menu-item-group>
        </el-menu>
      </el-col>

      <el-col :xs="8" :sm="12" :md="16" :lg="18">
        <div class="grid-content bg-purple-light">
          <div v-for="o in testPlanList">
            <el-card class="">
              <router-link :to="'/testPlanForm/'+ o.id">
                <div class="text item">
                  {{o.planname}}
                </div>
              </router-link>
              <div style="float: right">
                <el-button type="text" @click="editTestPlan(o.id, o.planname)">修改测试方案名称</el-button>
                <el-button type="text" @click="deleteTestPlan(o.id)">删除测试方案</el-button>
              </div>
            </el-card>
          </div>
          <div class="block">
            <el-pagination
              @current-change="handleCurrentChange"
              :current-page="page.currentPage"
              :page-size="page.pageSize"
              layout="prev, pager, next"
              :total="page.total">
            </el-pagination>
          </div>
        </div>
      </el-col>

      <el-col :xs="8" :sm="6" :md="4" :lg="3">
        <div class="grid-content bg-purple-light"></div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  export default{
    data () {
      return {
        apiUrl: '/testPlan/pageingTestPlan',
        testPlanList: {},
        page: {
          'currentPage': 1,
          'pageSize': 5,
          'total': 1
        }
      }
    },
    mounted () {
      this.getTestPlanPageList(1)
    },
    methods: {
      addTestPlan () {
        this.$prompt('请输入测试方案名:', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
//          inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
          inputErrorMessage: '测试方案名格式不正确'
        }).then(({value}) => {
          this.addTestPlanReq(value)
        }).catch(() => {
          this.$notify({
            title: '成功',
            message: '删除成功!',
            type: 'info'
          })
        })
      },
      addTestPlanReq (planName) {
        var vm = this
        var formData = new window.FormData()
        formData.append('planName', planName)
        vm.$http.post('/testPlan/createTestPlan', formData).then((response) => {
          console.log(response.body)
          if (response.body === '0101') {
            this.$notify({
              title: '成功',
              message: '创建成功!',
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

      editTestPlan (id, planName) {
        console.log('编辑测试方案的ID是: ' + id + ' / ' + planName)
        this.$prompt('请修改测试方案名:', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          inputValue: planName,
//          inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
          inputErrorMessage: '测试方案名格式不正确'
        }).then(({value}) => {
          this.editTestPlanReq(id, value)
        }).catch(() => {
          this.$notify.info({
            title: '消息',
            message: '取消输入'
          })
        })
      },

      editTestPlanReq (id, planName) {
        var vm = this
        var formData = new window.FormData()
        formData.append('planNameId', id)
        formData.append('planName', planName)
        vm.$http.post('/testPlan/updateTestPlan', formData).then((response) => {
          console.log(response.body)
          if (response.body === '0105') {
            this.$notify({
              title: '成功',
              message: '修改成功!',
              type: 'success'
            })
          } else {
            this.$notify({
              title: '失败',
              message: '修改失败!',
              type: 'warning'
            })
          }
        })
      },

      deleteTestPlan (id) {
        this.$confirm('此操作将删除该测试方案, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.deleteTestPlanReq(id)
        }).catch(() => {
          this.$notify.info({
            title: '消息',
            message: '已取消删除'
          })
        })
      },
      deleteTestPlanReq (id) {
        var vm = this
        var formData = new window.FormData()
        formData.append('planNameId', id)
        vm.$http.post('/testPlan/deleteTestPlan', formData).then((response) => {
          console.log(response.body)
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
      handleCurrentChange (gotoPage) {
        this.getTestPlanPageList(gotoPage)
      },
      getTestPlanPageList (gotoPage) {
        var vm = this
        var formData = new window.FormData()
        formData.append('pageNo', gotoPage)
        formData.append('pageSize', vm.page.pageSize)
        formData.append('deleteFlag', '0')
        vm.$http.post(vm.apiUrl, formData).then((response) => {
          return response.json()
        }).then((json) => {
          vm.setTestPlanPageListData(json)
          console.log(vm.testPlanList)
        })
      },
      setTestPlanPageListData (json) {
        var vm = this
        vm.testPlanList = json.result
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
