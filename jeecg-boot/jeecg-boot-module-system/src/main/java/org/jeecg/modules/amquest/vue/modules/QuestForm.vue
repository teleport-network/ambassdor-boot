<template>
   <a-spin :spinning="confirmLoading">
     <j-form-container :disabled="formDisabled">
       <!-- 主表单区域 -->
       <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
         <a-row>
          <a-col :xs="24" :sm="12">
            <a-form-model-item label="quest key" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="questKey">
              <a-input v-model="model.questKey" placeholder="请输入quest key" disabled></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-model-item label="title" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="title">
              <a-input v-model="model.title" placeholder="请输入title" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-model-item label="quest link" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="url">
              <a-input v-model="model.url" placeholder="请输入quest link" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-model-item label="rewards points" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="rewards">
              <a-input-number v-model="model.rewards" placeholder="请输入rewards points" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-model-item label="type" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="type">
              <a-input v-model="model.type" placeholder="请输入type" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-model-item label="image" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="image">
              <j-image-upload isMultiple  v-model="model.image" ></j-image-upload>
            </a-form-model-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-model-item label="quest date" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="issueDate">
              <j-date placeholder="请选择quest date" v-model="model.issueDate" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-model-item label="queat duration" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="deadline">
              <j-date placeholder="请选择queat duration" v-model="model.deadline" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-model-item label="last sync time" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="syncTime">
              <j-date placeholder="请选择last sync time" v-model="model.syncTime" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-model-item label="create time in gleam" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="gleamCreateAt">
              <j-date placeholder="请选择create time in gleam" v-model="model.gleamCreateAt" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-model-item label="fraud_type" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="fraudType">
              <a-input v-model="model.fraudType" placeholder="请输入fraud_type" ></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
     </j-form-container>
      <!-- 子表单区域 -->
      <a-tabs v-model="activeKey" @change="handleChangeTabs">
        <a-tab-pane tab="quest def" :key="refKeys[0]" :forceRender="true">
          <j-editable-table
            :ref="refKeys[0]"
            :loading="actionDefTable.loading"
            :columns="actionDefTable.columns"
            :dataSource="actionDefTable.dataSource"
            :maxHeight="300"
            :disabled="formDisabled"
            :rowNumber="true"
            :rowSelection="true"
            :actionButton="true"/>
        </a-tab-pane>
      </a-tabs>
    </a-spin>
</template>

<script>

  import { FormTypes,getRefPromise,VALIDATE_NO_PASSED } from '@/utils/JEditableTableUtil'
  import { JEditableTableModelMixin } from '@/mixins/JEditableTableModelMixin'
  import { validateDuplicateValue } from '@/utils/util'

  export default {
    name: 'QuestForm',
    mixins: [JEditableTableModelMixin],
    components: {
    },
    data() {
      return {
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        model:{
        },
        validatorRules: {
        },
        // 新增时子表默认添加几行空数据
        addDefaultRowNum: 1,
        refKeys: ['actionDef', ],
        tableKeys:['actionDef', ],
        activeKey: 'actionDef',
        // quest def
        actionDefTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: 'type',
              key: 'type',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: 'worth',
              key: 'worth',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: 'mandatory',
              key: 'mandatory',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: 'config1',
              key: 'config1',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: 'config2',
              key: 'config2',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: 'config3',
              key: 'config3',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: 'config4',
              key: 'config4',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: 'config5',
              key: 'config5',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: 'config6',
              key: 'config6',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: 'config7',
              key: 'config7',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: 'config8',
              key: 'config8',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: 'config9',
              key: 'config9',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: 'original',
              key: 'original',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: 'image',
              key: 'image',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: 'paid',
              key: 'paid',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: 'config_toggle',
              key: 'configToggle',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: 'actions_required',
              key: 'actionsRequired',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
          ]
        },
        url: {
          add: "/amquest/quest/add",
          edit: "/amquest/quest/edit",
          actionDef: {
            list: '/amquest/quest/queryActionDefByMainId'
          },
        }
      }
    },
    props: {
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    computed: {
      formDisabled(){
        return this.disabled
      },
    },
    created () {
    },
    methods: {
     addBefore(){
            this.actionDefTable.dataSource=[]
      },
      getAllTable() {
        let values = this.tableKeys.map(key => getRefPromise(this, key))
        return Promise.all(values)
      },
      /** 调用完edit()方法之后会自动调用此方法 */
      editAfter() {
        this.$nextTick(() => {
        })
        // 加载子表数据
        if (this.model.id) {
          let params = { id: this.model.id }
          this.requestSubTableData(this.url.actionDef.list, params, this.actionDefTable)
        }
      },
      //校验所有一对一子表表单
    validateSubForm(allValues){
        return new Promise((resolve,reject)=>{
          Promise.all([
          ]).then(() => {
            resolve(allValues)
          }).catch(e => {
            if (e.error === VALIDATE_NO_PASSED) {
              // 如果有未通过表单验证的子表，就自动跳转到它所在的tab
              this.activeKey = e.index == null ? this.activeKey : this.refKeys[e.index]
            } else {
              console.error(e)
            }
          })
        })
    },
      /** 整理成formData */
      classifyIntoFormData(allValues) {
        let main = Object.assign(this.model, allValues.formValue)

        return {
          ...main, // 展开
          actionDefList: allValues.tablesValue[0].values,
        }
      },
      validateError(msg){
        this.$message.error(msg)
      },
     close() {
        this.visible = false
        this.$emit('close')
        this.$refs.form.clearValidate();
      },

    }
  }
</script>

<style scoped>
</style>