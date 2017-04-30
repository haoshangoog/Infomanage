<template>
  <div>
    <el-row :gutter="10">
      <el-col :xs="8" :sm="6" :md="4" :lg="3">
        .
      </el-col>

      <el-col :xs="8" :sm="12" :md="16" :lg="18">
        <el-card class="box-card">
          <div class="grid-content">
            <label> 问题：</label>
            <el-input
              type="textarea"
              :autosize="{ minRows: 5, maxRows: 10}"
              placeholder="请输入内容"
              :readonly = "questionReadonly"
              v-model="FAQForm.question">
            </el-input>
            <label> 答案：</label>
            <el-input
              type="textarea"
              :autosize="{ minRows:5, maxRows: 10}"
              placeholder="请输入内容"
              :readonly = "answerReadonly"
              v-model="FAQForm.answer">
            </el-input>
            <el-button type="primary" v-show="role != 'GUEST' && answerReadonly" @click="answerReadonly = false">编辑答案</el-button>
            <el-button type="primary" v-show="role != 'GUEST' && !answerReadonly" @click="updateFAQForm">提交</el-button>
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
        FAQID: 0,
        editFAQ: false,
        questionReadonly: true,
        answerReadonly: true,
        FAQForm: {'id': 0, 'question': '', 'answer': '', 'deleteFlag': 0}
      }
    },
    computed: {
      ...mapGetters(['role'])

    },

    mounted () {
      this.FAQID = this.$route.params.FAQID
      console.log(this.FAQID)
      this.getTestPlanForm(this.FAQID)
    },
    methods: {
      getTestPlanForm (FAQID) {
        var vm = this
        var apiUrl = '/FAQ/selectFAQById'
        var formData = new window.FormData()
        formData.append('FAQID', FAQID)
        vm.$http.post(apiUrl, formData).then((response) => {
          return response.json()
        }).then((json) => {
          console.log(json)
          vm.FAQForm = json
        })
      },
      // 更新 FAQForm
      updateFAQForm () {
        var vm = this
        vm.answerReadonly = true
        var apiUrl = '/FAQ/updateFAQ'
        var formData = new window.FormData()
        formData.append('faqId', vm.FAQForm.id)
        formData.append('answer', vm.FAQForm.answer)
        vm.$http.post(apiUrl, formData).then((response) => {
          if (response.body === '0105') {
            this.$notify({
              title: '成功',
              message: '更新成功!',
              type: 'success'
            })
          } else {
            this.$notify({
              title: '失败',
              message: '更新失败!',
              type: 'waring'
            })
          }
        })
      }
    }
  }
</script>

<style>

</style>
