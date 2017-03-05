<template>
  <div id="testPlanList">
    <el-row :gutter="10">
      <el-col :xs="8" :sm="6" :md="4" :lg="3">
        <el-button v-on:click="addCatalogue" v-show="editTestPlan">添加目录</el-button>
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
              <div class="">*测试目的</div>
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
              <el-input v-model="testPlanForm.catalogueName" placeholder="请输入内容"></el-input>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6">
              <div class="">父级目录</div>
            </el-col>
            <el-col :span="6">
              <el-select v-model="testPlanForm.parentsId" placeholder="请选择">
                <el-option-group
                  v-for="group in catalogueSelect"
                  :label="group.label">
                  <el-option
                    v-for="item in group.options"
                    :label="item.catalogueName"
                    :value="item.id">
                  </el-option>
                </el-option-group>
              </el-select>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6">
              <div class="">兄弟级序列</div>
            </el-col>
            <el-col :span="6">
              <el-input v-model="testPlanForm.sequence" placeholder="请输入兄弟序列"></el-input>
            </el-col>
          </el-row>
          <hr>
          <el-row :gutter="20">
            <el-col :span="6">
              <div class="">*测试ID</div>
            </el-col>
            <el-col :span="6">
              <el-input v-model="testPlanForm.testPlanContext.testPlanContext.testPlanID"
                        placeholder="请输入测试ID"></el-input>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6">
              <div class="">*测试项目</div>
            </el-col>
            <el-col :span="6">
              <div class="">
                <el-input v-model="testPlanForm.testPlanContext.testPlanContext.testProject"
                          placeholder="请输入测试项目"></el-input>
              </div>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6">
              <div class="">*测试目的</div>
            </el-col>
            <el-col :span="6">
              <div class="">
                <el-input v-model="testPlanForm.testPlanContext.testPlanContext.testPlanPurpose"
                          placeholder="请输入测试目的"></el-input>
              </div>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6">
              <div class="">*需求ID</div>
            </el-col>
            <el-col :span="6">
              <div class="">
                <el-input v-model="testPlanForm.testPlanContext.testPlanContext.requirementID"
                          placeholder="请输入需求ID"></el-input>
              </div>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6">
              <div class="">*测试拓扑</div>
            </el-col>
            <el-col :span="6">
              <div class="">
                <el-input v-model="testPlanForm.testPlanContext.testPlanContext.topologicalID"
                          placeholder="请输入测试拓扑"></el-input>
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
                  <el-input v-model="o.context" placeholder="请输入测试内容"></el-input>
                </div>
              </el-col>
              <el-col :span="6">
                <div class="">
                  <el-input v-model="o.result" placeholder="请输入预期结果"></el-input>
                </div>
              </el-col>
              <el-col :span="6" v-show="index">
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
                <el-input v-model="testPlanForm.testPlanContext.testPlanContext.NorthInterface"
                          placeholder="请输入北向接口"></el-input>
              </div>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6">
              <div class="">*测试样例</div>
            </el-col>
            <el-col :span="6">
              <div class="">
                <el-input v-model="testPlanForm.testPlanContext.testPlanContext.testSample"
                          placeholder="请输入测试样例"></el-input>
              </div>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-button v-on:click="updateTestPlanForm">确认提交</el-button>
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
        textPlanId: 0,
        editTestPlan: false,
        testPlanForm: {
          'id': 0,
          'catalogueName': '',
          'parentsId': 0,
          'sequence': 0,
          'testPlanId': 0,
          'testPlanContextId': 0,
          'deleteFlag': 0,
          'testPlanContext': {
            'testPlanContext': {
              'testPlanID': 0,
              'testProject': 'testProject',
              'testPlanPurpose': 'testPlanPurpose',
              'requirementID': 'requirementID',
              'topologicalID': 'topologicalID',
              'testContextAndResult': [
                {'context': '内容..', 'result': '结果'}
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
        }
      }
    },
    computed: {
      catalogueSelect () {
        // 父级目录可选择的有1. 根目录 2. 当前目录 3. 子目录
        var vm = this
        return vm.jsonTree(vm.allCatalogue, vm.testPlanForm.parentsId)
      }
    },
    mounted () {
      this.testPlanId = this.$route.params.testPlanID
      this.getTestPlanPageCatalogue(this.testPlanId)
    },
    methods: {
      handleNodeClick (data) {
        this.getTestPlanForm(data.id)
      },
      addCatalogue () {
        this.testPlanForm = {
          'id': 0,
          'catalogueName': '',
          'parentsId': 0,
          'sequence': 0,
          'testPlanId': this.testPlanId,
          'testPlanContextId': 0,
          'deleteFlag': 0,
          'testPlanContext': {
            'testPlanContext': {
              'testPlanID': this.testPlanId,
              'testProject': 'testProject',
              'testPlanPurpose': 'testPlanPurpose',
              'requirementID': 'requirementID',
              'topologicalID': 'topologicalID',
              'testContextAndResult': [
                {'context': '内容..', 'result': '结果'}
              ],
              'NorthInterface': 'North-Interface',
              'testSample': 'Test sample'
            }
          }
        }
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
      getTestPlanForm (catalogueID) {
        var vm = this
        var apiUrl = 'http://localhost:8085/testPlanCatalogue/getTestPlanForm'
        var formData = new window.FormData()
        formData.append('catalogueId', catalogueID)
        vm.$http.post(apiUrl, formData).then((response) => {
          return response.json()
        }).then((json) => {
          var testPlanContext = json.testPlanContext.testPlanContext
          if (testPlanContext) {
            json.testPlanContext.testPlanContext = JSON.parse(testPlanContext)
          } else {
            json.testPlanContext.testPlanContext = {
              'testPlanID': '',
              'testProject': '',
              'testPlanPurpose': '',
              'requirementID': '',
              'topologicalID': '',
              'testContextAndResult': [
                {'context': '', 'result': ''}
              ],
              'NorthInterface': '',
              'testSample': ''
            }
          }
          vm.testPlanForm = json
        })
      },
      // 更新 testPlanForm
      updateTestPlanForm () {
        var vm = this
        var apiUrl = 'http://localhost:8085/testPlanCatalogue/updateTestPlanForm'
        var formData = new window.FormData()
        var testPlanContext = vm.testPlanForm.testPlanContext.testPlanContext
        vm.testPlanForm.testPlanContext.testPlanContext =
          (typeof testPlanContext === 'object') === true ? JSON.stringify(testPlanContext) : testPlanContext
        formData.append('testPlanForm', JSON.stringify(vm.testPlanForm))
        vm.$http.post(apiUrl, formData).then((response) => {
          console.log(response)
        })
      },
      // 内容部分 END
      jsonTree (jsonTree, value, root) {
        if (jsonTree.length === 0) {
          return
        }
        var array = []
        if ((typeof jsonTree === 'object') && (jsonTree.constructor === Object.prototype.constructor)) {
          array.push(jsonTree)
        } else {
          array = jsonTree
        }
        if (!root) {
          root = array[0].parentsId
          if (Number(value) === root || Number(value) === 0) {
            var s = []
            this.testPlanForm.parentsId = root
            s.push({label: '根目录', options: [{'catalogueName': '根目录', 'id': root}]})
            s.push({label: '子目录', options: array})
            return s
          }
        }
        var result = []
        for (var i = 0; i < array.length; i++) {
          var jn = array[i]
          if (jn.id === Number(value)) {
            result.push({label: '根目录', options: [{'catalogueName': '根目录', 'id': root}]})
            result.push({label: '当前目录', options: [{'catalogueName': jn.catalogueName, 'id': jn.id}]})
            result.push({label: '子目录', options: jn.children})
            return result
          } else if (jn.children && jn.children.length > 0) {
            result = this.jsonTree(jn.children, value, root)
          }
          if (result.length !== 0) {
            return result
          }
        }
        return result
      }
    }
  }
</script>
