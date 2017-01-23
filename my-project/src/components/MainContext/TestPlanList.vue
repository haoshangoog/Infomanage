<template xmlns:v-on="http://www.w3.org/1999/xhtml">
  <div id="testPlanList">
    <el-row :gutter="10">
      <el-col :xs="8" :sm="6" :md="4" :lg="3">
        <div class="grid-content bg-purple-light"></div>
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
