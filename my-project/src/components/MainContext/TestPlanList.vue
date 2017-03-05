<template>
  <div id="testPlanList">
    <el-row :gutter="10">
      <el-col :xs="8" :sm="6" :md="4" :lg="3">
        <div class="grid-content bg-purple-light">
          <el-button type="text" @click="addTestPlan">添加测试方案</el-button>
        </div>
      </el-col>
      <el-col :xs="8" :sm="12" :md="16" :lg="18">
        <div class="grid-content bg-purple-light">
          <div v-for="o in testPlanList">
            <el-card class="box-card">
              <router-link :to="'/testPlanForm/'+ o.id">
                <div class="text item">
                  {{o.planname}}
                </div>
              </router-link>
              <el-button type="text" @click="editTestPlan(o.id)">编辑</el-button>
              <el-button type="text" @click="deleteTestPlan(o.id)">删除</el-button>
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
        apiUrl: 'http://localhost:8085/testPlan/pageingTestPlan',
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
          this.$message({
            type: 'info',
            message: '取消输入'
          })
        })
      },
      addTestPlanReq (planName) {
        var vm = this
        var formData = new window.FormData()
        formData.append('planName', planName)
        vm.$http.post('http://localhost:8085/testPlan/createTestPlan', formData).then((response) => {
          console.log(response.body)
          if (response.body === '0101') {
            this.$message({
              type: 'success',
              message: '创建成功'
            })
          } else {
            this.$message({
              type: 'info',
              message: '创建失败'
            })
          }
        })
      },
      editTestPlan (id) {
        console.log('编辑测试方案的ID是: ' + id)
        this.$prompt('请输入测试方案名:', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
//          inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
          inputErrorMessage: '测试方案名格式不正确'
        }).then(({value}) => {
          this.editTestPlanReq(id, value)
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消输入'
          })
        })
      },
      editTestPlanReq (id, planName) {
        var vm = this
        var formData = new window.FormData()
        formData.append('planNameId', id)
        formData.append('planName', planName)
        vm.$http.post('http://localhost:8085/testPlan/updateTestPlan', formData).then((response) => {
          console.log(response.body)
          if (response.body === '0105') {
            this.$message({
              type: 'success',
              message: '更新成功'
            })
          } else {
            this.$message({
              type: 'info',
              message: '更新失败'
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
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      },
      deleteTestPlanReq (id) {
        var vm = this
        var formData = new window.FormData()
        formData.append('planNameId', id)
        vm.$http.post('http://localhost:8085/testPlan/deleteTestPlan', formData).then((response) => {
          console.log(response.body)
          if (response.body === '0103') {
            this.$message({
              type: 'success',
              message: '删除成功'
            })
          } else {
            this.$message({
              type: 'info',
              message: '删除失败'
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
    font-size: 14px;
  }

  .item {
    padding: 18px 0;
  }

  .box-card {
    width: 480px;
  }

  .bg-purple-light {
    background: #e5e9f2;
  }

  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
</style>
