<template >
  <div id="testPlanList">
    <el-row :gutter="10">
      <el-col :xs="8" :sm="6" :md="4" :lg="3">
        <div class="grid-content bg-purple-light">
          <el-tree :data="allCatalogue" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
          <label>编辑测试方案</label>
          <el-switch
            v-model="editTestPlan"
            on-text="on"
            off-text="off">
          </el-switch>
        </div>
      </el-col>
      <el-col :xs="8" :sm="12" :md="16" :lg="18">
        <!-- Show -->
        <div class="grid-content bg-purple-light" v-show="!editTestPlan">
          <el-row :gutter="20">
            <el-col :span="6">
              <div class="">*测试ID</div>
            </el-col>
            <el-col :span="6">
              <div class="">{{testPlanForm.testPlanContext.testPlanContext.testPlanID}}</div>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6">
              <div class="">*测试项目</div>
            </el-col>
            <el-col :span="6">
              <div class="">{{testPlanForm.testPlanContext.testPlanContext.testProject}}</div>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6">
              <div class="">*测试项目</div>
            </el-col>
            <el-col :span="6">
              <div class="">{{testPlanForm.testPlanContext.testPlanContext.testPlanPurpose}}</div>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6">
              <div class="">*需求ID</div>
            </el-col>
            <el-col :span="6">
              <div class="">{{testPlanForm.testPlanContext.testPlanContext.requirementID}}</div>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6">
              <div class="">*测试拓扑</div>
            </el-col>
            <el-col :span="6">
              <div class="">{{testPlanForm.testPlanContext.testPlanContext.topologicalID}}</div>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6">
              <div class="">*测试内容</div>
            </el-col>
            <el-col :span="6">
              <div class="">*预期结果</div>
            </el-col>
          </el-row>
          <div v-for="o in testPlanForm.testPlanContext.testPlanContext.testContextAndResult">
            <el-row :gutter="20">
              <el-col :span="6">
                <div class="">{{o.context}}</div>
              </el-col>
              <el-col :span="6">
                <div class="">{{o.result}}</div>
              </el-col>
            </el-row>
          </div>
          <el-row :gutter="20">
            <el-col :span="6">
              <div class="">*北向接口</div>
            </el-col>
            <el-col :span="6">
              <div class="">{{testPlanForm.testPlanContext.testPlanContext.NorthInterface}}</div>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6">
              <div class="">*测试样例</div>
            </el-col>
            <el-col :span="6">
              <div class="">{{testPlanForm.testPlanContext.testPlanContext.testSample}}</div>
            </el-col>
          </el-row>
        </div>
        <!-- EDIT -->
        <div class="grid-content bg-purple-light" v-show="editTestPlan">
          <el-row :gutter="20">
            <el-col :span="6">
              <div class="">目录名称</div>
            </el-col>
            <el-col :span="6">
              <el-input v-model="testPlanForm.testPlanContext.testPlanContext.testPlanID" placeholder="请输入内容"></el-input>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6">
              <div class="">父目录</div>
            </el-col>
            <el-col :span="6">
              <el-select v-model="value" placeholder="请选择">
                <el-option
                  v-for="item in options"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6">
              <div class="">兄弟级序列</div>
            </el-col>
            <el-col :span="6">
              <el-input v-model="testPlanForm.testPlanContext.testPlanContext.testPlanID" placeholder="请输入内容"></el-input>
            </el-col>
          </el-row>
          <hr>
          <el-row :gutter="20">
            <el-col :span="6">
              <div class="">*测试ID</div>
            </el-col>
            <el-col :span="6">
              <el-input v-model="testPlanForm.testPlanContext.testPlanContext.testPlanID" placeholder="请输入内容"></el-input>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6">
              <div class="">*测试项目</div>
            </el-col>
            <el-col :span="6">
              <div class="">
                <el-input v-model="testPlanForm.testPlanContext.testPlanContext.testProject" placeholder="请输入内容"></el-input>
              </div>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6">
              <div class="">*测试项目</div>
            </el-col>
            <el-col :span="6">
              <div class="">
                <el-input v-model="testPlanForm.testPlanContext.testPlanContext.testPlanPurpose" placeholder="请输入内容"></el-input>
              </div>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6">
              <div class="">*需求ID</div>
            </el-col>
            <el-col :span="6">
              <div class="">
                <el-input v-model="testPlanForm.testPlanContext.testPlanContext.requirementID" placeholder="请输入内容"></el-input>
              </div>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6">
              <div class="">*测试拓扑</div>
            </el-col>
            <el-col :span="6">
              <div class="">
                <el-input v-model="testPlanForm.testPlanContext.testPlanContext.topologicalID" placeholder="请输入内容"></el-input>
              </div>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6">
              <div class="">*测试内容</div>
            </el-col>
            <el-col :span="6">
              <div class="">*预期结果</div>
            </el-col>
            <el-col :span="6">
              <el-button type="text" icon="plus" v-on:click="addTestContextAndResult">添加</el-button>
            </el-col>
          </el-row>
          <div v-for="(o, index) in testPlanForm.testPlanContext.testPlanContext.testContextAndResult">
            <el-row :gutter="20">
              <el-col :span="6">
                <div class="">
                  <el-input v-model="o.context" placeholder="请输入内容"></el-input>
                </div>
              </el-col>
              <el-col :span="6">
                <div class="">
                  <el-input v-model="o.result" placeholder="请输入内容"></el-input>
                </div>
              </el-col>
              <el-col :span="6">
                <el-button type="text" icon="delete" v-on:click="deleteTestContextAndResult(index)"></el-button>
              </el-col>
            </el-row>
          </div>
          <el-row :gutter="20">
            <el-col :span="6">
              <div class="">*北向接口</div>
            </el-col>
            <el-col :span="6">
              <div class="">
                <el-input v-model="testPlanForm.testPlanContext.testPlanContext.NorthInterface" placeholder="请输入内容"></el-input>
              </div>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6">
              <div class="">*测试样例</div>
            </el-col>
            <el-col :span="6">
              <div class="">
                <el-input v-model="testPlanForm.testPlanContext.testPlanContext.testSample" placeholder="请输入内容"></el-input>
              </div>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-button v-on:click="updateTestPlanForm">确认提交</el-button>
            <el-button v-on:click="getTestPlanForm">获取</el-button>
          </el-row>
        </div>
      </el-col>

    </el-row>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        editTestPlan: false,
        testPlanForm: {
          'id': 4,
          'catalogueName': '目录5',
          'parentsId': 1,
          'sequence': 2,
          'testPlanId': 1,
          'testPlanContextId': 3,
          'deleteFlag': 0,
          'testPlanContext': {
            'testPlanContext': {
              'testPlanID': '1',
              'testProject': 'testProject',
              'testPlanPurpose': 'testPlanPurpose',
              'requirementID': 'requirementID',
              'topologicalID': 'topologicalID',
              'testContextAndResult': [
                {'context': 'AAA', 'result': 'aaa'},
                {'context': 'AAA', 'result': 'aaa'},
                {'context': 'AAA', 'result': 'aaa'},
                {'context': 'AAA', 'result': 'aaa'}
              ],
              'NorthInterface': 'North-Interface',
              'testSample': 'Test sample'
            }
          }
        },
        allCatalogue: [],
        defaultProps: {
          children: 'children',
          label: 'catalogueName'
        },
        catalogueName: ''
      }
    },
    mounted () {
      this.getTestPlanPageCatalogue(1)
    },
    methods: {
      handleNodeClick (data) {
        console.log(data)
      },
      // 目录部分 Start
      getTestPlanPageCatalogue (testPlanId) {
        var vm = this
        var apiUrl = 'http://localhost:8085/testPlanCatalogue/getAllCatalogue'
        var formData = new window.FormData()
        formData.append('testPlanId', testPlanId)
        vm.$http.post(apiUrl, formData).then((response) => {
          return response.json()
        }).then((json) => {
          vm.allCatalogue = json
          console.log(vm.allCatalogue)
        })
      },
      // 目录部分 END
      // 内容部分 Start
      addTestContextAndResult () {
        console.log('addTestContextAndResult')
        var vm = this
        vm.testPlanForm.testPlanContext.testPlanContext.testContextAndResult.push({'context': '', 'result': ''})
      },
      deleteTestContextAndResult (index) {
        console.log('deleteTestContextAndResult')
        var vm = this
        vm.testPlanForm.testPlanContext.testPlanContext.testContextAndResult.splice(index, 1)
      },
      // 获取 testPlanForm
      getTestPlanForm () {
        var vm = this
        var apiUrl = 'http://localhost:8085/testPlanCatalogue/getTestPlanForm'
        var formData = new window.FormData()
        formData.append('catalogueId', '4')
        vm.$http.post(apiUrl, formData).then((response) => {
          return response.json()
        }).then((json) => {
          json.testPlanContext.testPlanContext = JSON.parse(json.testPlanContext.testPlanContext)
          console.log(json)
          vm.testPlanForm = json
          console.log(vm.testPlanForm)
        })
      },
      // 更新 testPlanForm
      updateTestPlanForm () {
        var vm = this
        var apiUrl = 'http://localhost:8085/testPlanCatalogue/updateTestPlanForm'
        var formData = new window.FormData()
        vm.testPlanForm.testPlanContext.testPlanContext = JSON.stringify(vm.testPlanForm.testPlanContext.testPlanContext)
        formData.append('testPlanForm', JSON.stringify(vm.testPlanForm))
        vm.$http.post(apiUrl, formData).then((response) => {
          console.log(response)
        })
      }
      // 内容部分 END
    }
  }
</script>
