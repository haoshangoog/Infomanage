<template>
  <div>
    <el-row :gutter="10">
      <el-col :xs="8" :sm="6" :md="4" :lg="3">
        <el-menu mode="vertical" default-active="1" class="">
          <el-menu-item-group title="基本功能">
            <el-menu-item index="1" @click="addFAQ" v-show="role == 'ADMIN'"><i class="el-icon-message"></i>添加问题</el-menu-item>
          </el-menu-item-group>
        </el-menu>
      </el-col>

      <el-col :xs="16" :sm="12" :md="16" :lg="18">
        <div class="grid-content bg-purple-light">
          <div v-for="o in FAQList">
            <el-card class="">
              <!--<router-link :to="'/testPlanForm/'+ o.id">-->
                <div class="text item">
                  {{o.question}}
                </div>
              <!--</router-link>-->
              <div style="float: right">
                <el-button type="text" @click="editFAQ(o.id, o.question)" v-show="role != 'GUEST'">修改问题</el-button>
                <el-button type="text" @click="deleteFAQ(o.id)" v-show="role != 'GUEST'">删除问题</el-button>
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
      </el-col>
    </el-row>
  </div>
</template>
<script>
  import {mapGetters} from 'vuex'
  export default{
    data () {
      return {
        FAQList: {},
        apiUrl: '/FAQ/pageingFAQ',
        List: {},
        page: {
          'currentPage': 1,
          'pageSize': 5,
          'total': 1
        }
      }
    },
    computed: mapGetters([
      'role'
    ]),
    mounted () {
      this.getFAQPageList(1)
    },
    methods: {
      addFAQ () {
        this.$prompt('请输入问题:', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
//          inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
          inputErrorMessage: '问题格式不正确'
        }).then(({value}) => {
          this.addFAQReq(value)
        }).catch(() => {
          this.$notify.info({
            title: '消息',
            message: '取消编辑!'
          })
        })
      },
      addFAQReq (FAQquestion) {
        var vm = this
        var formData = new window.FormData()
        formData.append('question', FAQquestion)
        vm.$http.post('/FAQ/createFAQ', formData).then((response) => {
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

      editFAQ (id, FAQquestion) {
        console.log('重新编辑问题: ' + id + ' / ' + FAQquestion)
        this.$prompt('请修改问题:', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          inputValue: FAQquestion,
//          inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
          inputErrorMessage: '问题格式不正确'
        }).then(({value}) => {
          this.editFAQReq(id, value)
        }).catch(() => {
          this.$notify.info({
            title: '消息',
            message: '取消输入'
          })
        })
      },

      editFAQReq (id, FAQquestion, FAQanswer) {
        var vm = this
        var formData = new window.FormData()
        formData.append('faqId', id)
        formData.append('question', FAQquestion)
        formData.append('answer', FAQanswer)
        vm.$http.post('/FAQ/updateFAQ', formData).then((response) => {
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

      deleteFAQ (faqId) {
        this.$confirm('此操作将删除该FAQ, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.deleteFAQReq(faqId)
        }).catch(() => {
          this.$notify.info({
            title: '消息',
            message: '已取消删除'
          })
        })
      },
      deleteFAQReq (faqId) {
        var vm = this
        var formData = new window.FormData()
        formData.append('faqId', faqId)
        vm.$http.post('/FAQ/deleteFAQ', formData).then((response) => {
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
        this.getFAQPageList(gotoPage)
      },
      getFAQPageList (gotoPage) {
        var vm = this
        var formData = new window.FormData()
        formData.append('pageNo', gotoPage)
        formData.append('pageSize', vm.page.pageSize)
        formData.append('deleteFlag', '0')
        vm.$http.post(vm.apiUrl, formData).then((response) => {
          return response.json()
        }).then((json) => {
          vm.setTestPlanPageListData(json)
          console.log(vm.FAQList)
        })
      },
      setTestPlanPageListData (json) {
        var vm = this
        vm.FAQList = json.result
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

