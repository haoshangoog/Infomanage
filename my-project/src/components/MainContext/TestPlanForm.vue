<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
  <div id="testPlanList">
    <el-row :gutter="10">
      <el-col :xs="8" :sm="6" :md="4" :lg="3">
        <div class="grid-content bg-purple-light">
          <el-tree :data="data" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
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
            <el-col :span="6"><div class="">*测试ID</div></el-col>
            <el-col :span="6"><div class="">{{testPlan.testPlanID}}</div></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6"><div class="">*测试项目</div></el-col>
            <el-col :span="6"><div class="">{{testPlan.testProject}}</div></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6"><div class="">*测试项目</div></el-col>
            <el-col :span="6"><div class="">{{testPlan.testPlanPurpose}}</div></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6"><div class="">*需求ID</div></el-col>
            <el-col :span="6"><div class="">{{testPlan.requirementID}}</div></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6"><div class="">*测试拓扑</div></el-col>
            <el-col :span="6"><div class="">{{testPlan.topologicalID}}</div></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6"><div class="">*测试内容</div></el-col>
            <el-col :span="6"><div class="">*预期结果</div></el-col>
          </el-row>
          <div v-for="o in testPlan.testContextAndResult">
            <el-row :gutter="20">
              <el-col :span="6"><div class="">{{o.context}}</div></el-col>
              <el-col :span="6"><div class="">{{o.result}}</div></el-col>
            </el-row>
          </div>
          <el-row :gutter="20">
            <el-col :span="6"><div class="">*北向接口</div></el-col>
            <el-col :span="6"><div class="">{{testPlan.NorthInterface}}</div></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6"><div class="">*测试样例</div></el-col>
            <el-col :span="6"><div class="">{{testPlan.testSample}}</div></el-col>
          </el-row>
        </div>
        <!-- EDIT -->
        <div class="grid-content bg-purple-light" v-show="editTestPlan">
          <el-row :gutter="20">
            <el-col :span="6"><div class="">*测试ID</div></el-col>
            <el-col :span="6"><el-input v-model="testPlan.testPlanID" placeholder="请输入内容"></el-input></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6"><div class="">*测试项目</div></el-col>
            <el-col :span="6"><div class=""><el-input v-model="testPlan.testProject" placeholder="请输入内容"></el-input></div></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6"><div class="">*测试项目</div></el-col>
            <el-col :span="6"><div class=""><el-input v-model="testPlan.testPlanPurpose" placeholder="请输入内容"></el-input></div></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6"><div class="">*需求ID</div></el-col>
            <el-col :span="6"><div class=""><el-input v-model="testPlan.requirementID" placeholder="请输入内容"></el-input></div></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6"><div class="">*测试拓扑</div></el-col>
            <el-col :span="6"><div class=""><el-input v-model="testPlan.topologicalID" placeholder="请输入内容"></el-input></div></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6"><div class="">*测试内容</div></el-col>
            <el-col :span="6"><div class="">*预期结果</div></el-col>
            <el-col :span="6"><el-button type="text" icon="plus" v-on:click="addTestContextAndResult">添加</el-button></el-col>

          </el-row>
          <div v-for="(o, index) in testPlan.testContextAndResult">
            <el-row :gutter="20">
              <el-col :span="6"><div class=""><el-input v-model="o.context" placeholder="请输入内容"></el-input></div></el-col>
              <el-col :span="6"><div class=""><el-input v-model="o.result" placeholder="请输入内容"></el-input></div></el-col>
              <el-col :span="6"><el-button type="text" icon="delete" v-on:click="deleteTestContextAndResult(index)"></el-button></el-col>

            </el-row>
          </div>
          <el-row :gutter="20">
            <el-col :span="6"><div class="">*北向接口</div></el-col>
            <el-col :span="6"><div class=""><el-input v-model="testPlan.NorthInterface" placeholder="请输入内容"></el-input></div></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="6"><div class="">*测试样例</div></el-col>
            <el-col :span="6"><div class=""><el-input v-model="testPlan.testSample" placeholder="请输入内容"></el-input></div></el-col>
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
        testPlan: {
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
        },
        data: [{
          label: '一级 1',
          children: [{
            label: '二级 1-1',
            children: [{
              label: '三级 1-1-1'
            }]
          }]
        }, {
          label: '一级 2',
          children: [{
            label: '二级 2-1'
          }, {
            label: '二级 2-2'
          }]
        }, {
          label: '一级 3',
          children: [{
            label: '二级 3-1'
          }, {
            label: '二级 3-2'
          }]
        }],
        defaultProps: {
          children: 'children',
          label: 'label'
        }
      }
    },
    methods: {
      handleNodeClick (data) {
        console.log(data)
      },
      addTestContextAndResult () {
        console.log('addTestContextAndResult')
        var vm = this
        vm.testPlan.testContextAndResult.push({'context': '', 'result': ''})
      },
      deleteTestContextAndResult (index) {
        console.log('deleteTestContextAndResult')
        var vm = this
        vm.testPlan.testContextAndResult.splice(index, 1)
      }
    }
  }
</script>

<style>
  .bg-purple-light {
    background: #e5e9f2;
  }

  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
</style>
