<template>
  <div>
    <el-row :gutter="10">
      <el-col :xs="8" :sm="6" :md="4" :lg="3">
        <el-menu mode="vertical" default-active="1" class="">
          <el-menu-item index="1" v-show="role != 'GUEST'">
            编辑测试方案
            <el-switch
              v-model="editTestPlan"
              on-text="on"
              off-text="off">
            </el-switch>
          </el-menu-item>
          <el-menu-item index="2" @click="addCatalogue" v-show="editTestPlan">
            <i class="el-icon-message"></i>添加目录
          </el-menu-item>
          <el-menu-item-group title="目录">
            <el-tree :data="allCatalogueExceptRoot" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
          </el-menu-item-group>
        </el-menu>
      </el-col>
      <el-col :xs="8" :sm="12" :md="16" :lg="18">
        <el-card class="box-card">
          <!-- Show -->
          <div class="grid-content show" v-show="!editTestPlan">
            <el-row :gutter="20">
              <el-col :xs="6" :sm="6" :md="6" :lg="6">
                <div class="">*测试ID</div>
              </el-col>
              <el-col :xs="12" :sm="12" :md="12" :lg="6">
                <div class="">{{testPlanForm.testPlanContext.testPlanContext.testPlanID}}</div>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :xs="6" :sm="6" :md="6" :lg="6">
                <div class="">*测试项目</div>
              </el-col>
              <el-col :xs="12" :sm="12" :md="12" :lg="6">
                <div class="">{{testPlanForm.testPlanContext.testPlanContext.testProject}}</div>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :xs="6" :sm="6" :md="6" :lg="6">
                <div class="">*测试目的</div>
              </el-col>
              <el-col :xs="12" :sm="12" :md="12" :lg="6">
                <div class="">{{testPlanForm.testPlanContext.testPlanContext.testPlanPurpose}}</div>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :xs="6" :sm="6" :md="6" :lg="6">
                <div class="">*需求ID</div>
              </el-col>
              <el-col :xs="12" :sm="12" :md="12" :lg="6">
                <div class="">{{testPlanForm.testPlanContext.testPlanContext.requirementID}}</div>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :xs="6" :sm="6" :md="6" :lg="6">
                <div class="">*测试拓扑</div>
              </el-col>
              <el-col :xs="12" :sm="12" :md="12" :lg="6">
                <div class="">{{testPlanForm.testPlanContext.testPlanContext.topologicalID}}</div>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :xs="6" :sm="6" :md="6" :lg="6">
                <div class="">*测试内容</div>
              </el-col>
              <el-col :xs="6" :sm="6" :md="6" :lg="6">
                <div class="">*预期结果</div>
              </el-col>
            </el-row>
            <div v-for="o in testPlanForm.testPlanContext.testPlanContext.testContextAndResult">
              <el-row :gutter="20">
                <el-col :xs="6" :sm="6" :md="6" :lg="6">
                  <div class="">{{o.context}}</div>
                </el-col>
                <el-col :xs="12" :sm="12" :md="12" :lg="6">
                  <div class="">{{o.result}}</div>
                </el-col>
              </el-row>
            </div>
            <el-row :gutter="20">
              <el-col :xs="6" :sm="6" :md="6" :lg="6">
                <div class="">*北向接口</div>
              </el-col>
              <el-col :xs="12" :sm="12" :md="12" :lg="6">
                <div class="">{{testPlanForm.testPlanContext.testPlanContext.NorthInterface}}</div>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :xs="6" :sm="6" :md="6" :lg="6">
                <div class="">*测试样例</div>
              </el-col>
              <el-col :xs="12" :sm="12" :md="12" :lg="6">
                <div class="">{{testPlanForm.testPlanContext.testPlanContext.testSample}}</div>
              </el-col>
            </el-row>
          </div>
          <!-- EDIT -->
          <div class="grid-content " v-show="editTestPlan">
            <el-row :gutter="20">
              <el-col :xs="6" :sm="6" :md="6" :lg="6">
                <div class="">目录名称</div>
              </el-col>
              <el-col :xs="12" :sm="12" :md="12" :lg="6">
                <el-input v-model="testPlanForm.catalogueName" placeholder="请输入内容"></el-input>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :xs="6" :sm="6" :md="6" :lg="6">
                <div class="">父级目录</div>
              </el-col>
              <el-col :xs="12" :sm="12" :md="12" :lg="6">
                <el-cascader placeholder="请选择目录" :options="allCatalogue" :props="defaultProps" filterable
                             change-on-select v-model="selectedOptions">
                </el-cascader>
                {{test}}
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :xs="6" :sm="6" :md="6" :lg="6">
                <div class="">兄弟级序列</div>
              </el-col>
              <el-col :xs="12" :sm="12" :md="12" :lg="6">
                <el-input v-model="testPlanForm.sequence" placeholder="请输入兄弟序列"></el-input>
              </el-col>
            </el-row>
            <hr>
            <el-row :gutter="20">
              <el-col :xs="6" :sm="6" :md="6" :lg="6">
                <div class="">*测试ID</div>
              </el-col>
              <el-col :xs="12" :sm="12" :md="12" :lg="6">
                <el-input v-model="testPlanForm.testPlanContext.testPlanContext.testPlanID"
                          placeholder="请输入测试ID"></el-input>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :xs="6" :sm="6" :md="6" :lg="6">
                <div class="">*测试项目</div>
              </el-col>
              <el-col :xs="12" :sm="12" :md="12" :lg="6">
                <div class="">
                  <el-input v-model="testPlanForm.testPlanContext.testPlanContext.testProject"
                            placeholder="请输入测试项目"></el-input>
                </div>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :xs="6" :sm="6" :md="6" :lg="6">
                <div class="">*测试目的</div>
              </el-col>
              <el-col :xs="12" :sm="12" :md="12" :lg="6">
                <div class="">
                  <el-input v-model="testPlanForm.testPlanContext.testPlanContext.testPlanPurpose"
                            placeholder="请输入测试目的"></el-input>
                </div>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :xs="6" :sm="6" :md="6" :lg="6">
                <div class="">*需求ID</div>
              </el-col>
              <el-col :xs="12" :sm="12" :md="12" :lg="6">
                <div class="">
                  <el-input v-model="testPlanForm.testPlanContext.testPlanContext.requirementID"
                            placeholder="请输入需求ID"></el-input>
                </div>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :xs="6" :sm="6" :md="6" :lg="6">
                <div class="">*测试拓扑</div>
              </el-col>
              <el-col :xs="12" :sm="12" :md="12" :lg="6">
                <div class="">
                  <el-input v-model="testPlanForm.testPlanContext.testPlanContext.topologicalID"
                            placeholder="请输入测试拓扑"></el-input>
                </div>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :xs="6" :sm="6" :md="6" :lg="6">
                <div class="">*测试内容</div>
              </el-col>
              <el-col :xs="6" :sm="6" :md="6" :lg="6">
                <div class="">*预期结果</div>
              </el-col>
              <el-col :xs="6" :sm="6" :md="6" :lg="6">
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
              <el-col :xs="6" :sm="6" :md="6" :lg="6">
                <div class="">*北向接口</div>
              </el-col>
              <el-col :xs="12" :sm="12" :md="12" :lg="6">
                <div class="">
                  <el-input v-model="testPlanForm.testPlanContext.testPlanContext.NorthInterface"
                            placeholder="请输入北向接口"></el-input>
                </div>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :xs="6" :sm="6" :md="6" :lg="6">
                <div class="">*测试样例</div>
              </el-col>
              <el-col :xs="12" :sm="12" :md="12" :lg="6">
                <div class="">
                  <el-input v-model="testPlanForm.testPlanContext.testPlanContext.testSample"
                            placeholder="请输入测试样例"></el-input>
                </div>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :xs="12" :sm="6" :md="4" :lg="3">
                <el-button type="primary" @click="updateTestPlanForm">确认提交</el-button>
              </el-col>
              <el-col :xs="12" :sm="6" :md="4" :lg="3">
                <el-button>取消</el-button>
              </el-col>
            </el-row>
          </div>
        </el-card>
      </el-col>

    </el-row>
  </div>
</template>
<script>
  import {mapGetters} from 'vuex'
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
        allCatalogueOld: [],
        allCatalogue: [],
        defaultProps: {
          children: 'children',
          label: 'catalogueName',
          value: 'id'
        },
        selectedOptions: ['9', '47'],
        testArry: []
      }
    },
    computed: {
      ...mapGetters(['role']),
      allCatalogueExceptRoot () {
        if (this.allCatalogue.length === 0) {
          return []
        } else {
          return this.allCatalogue[0]['children']
        }
      },
      test () {
        var t = this.testPlanForm
        console.log('-----------------' + t)
        console.log(this.allCatalogueOld)
        this.getSelectedOptions(this.allCatalogueOld, this.testPlanForm.id)
        console.log('结果………………………………………………')
        console.log(this.testArry)
        this.selectedOptions = this.testArry
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
          'id': -1,
          'catalogueName': '',
          'parentsId': this.selectedOptions[this.selectedOptions.length - 1],
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
        var apiUrl = '/testPlanCatalogue/getAllCatalogue'
        var formData = new window.FormData()
        formData.append('testPlanId', testPlanId)
        vm.$http.post(apiUrl, formData).then((response) => {
          return response.json()
        }).then((json) => {
          if (json.length === 0) {
            console.log('请求目录信息出错')
          }
          vm.allCatalogueOld = json
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
        var apiUrl = '/testPlanCatalogue/getTestPlanForm'
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
        var apiUrl = '/testPlanCatalogue/updateTestPlanForm'
        var formData = new window.FormData()
        var testPlanContext = vm.testPlanForm.testPlanContext.testPlanContext
        vm.testPlanForm.testPlanContext.testPlanContext =
          (typeof testPlanContext === 'object') === true ? JSON.stringify(testPlanContext) : testPlanContext
        formData.append('testPlanForm', JSON.stringify(vm.testPlanForm))
        vm.$http.post(apiUrl, formData).then((response) => {
          console.log(response)
        })
      },
      getSelectedOptions (jsonTree, id) {
        this.testArry = []
        this.testArry = this.testArry.concat([id])
        for (var i = 0; i < 10000; i++) {
          var old = this.testArry
          this.searchCatalogue(jsonTree, this.testArry[this.testArry.length - 1])
          if (old.length === this.testArry.length) {
            this.testArry = this.testArry.reverse()
            break
          }
        }
      },
      searchCatalogue (jsonTree, id) {
        var vm = this
        for (var i = 0; i < jsonTree.length; i++) {
          var value = jsonTree[i]
          if (value.id === id) {
            if (value.parentsId !== '0') {
              var valueId = [value.parentsId]
              vm.testArry = vm.testArry.concat(valueId)
            }
          } else {
            if (value.children.length !== 0) {
              vm.searchCatalogue(value.children, id)
            }
          }
        }
      }
    }
  }
</script>

<style>
  .el-tree {
    cursor: none;
    background: rgba(255, 255, 255, 0);
    border: none;
  }

  .el-row {
    padding: 2px;
  }

  .show {
    font-size: 16px;
  }

  .show .el-row {
    padding: 10px;
    border-bottom: 1px solid rgba(50, 50, 50, 0.26);
  }
</style>
