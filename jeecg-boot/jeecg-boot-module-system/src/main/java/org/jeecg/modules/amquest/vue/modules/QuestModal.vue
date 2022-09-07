<template>
  <j-modal
    :title="title"
    :width="width"
    :visible="visible"
    :confirmLoading="confirmLoading"
    switchFullscreen
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    <a-spin :spinning="confirmLoading">
      <a-form-model ref="form" :model="model" :rules="validatorRules">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="quest key" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="questKey">
              <a-input v-model="model.questKey" placeholder="请输入quest key" disabled></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="active" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="active">
              <j-switch v-model="model.active"  ></j-switch>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="title" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="title">
              <a-input v-model="model.title" placeholder="请输入title" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="quest link" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="url">
              <a-input v-model="model.url" placeholder="请输入quest link" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="rewards points" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="rewards">
              <a-input-number v-model="model.rewards" placeholder="请输入rewards points" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="type" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="type">
              <j-dict-select-tag type="list" v-model="model.type" dictCode="quest_type" placeholder="请选择type" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="assign to" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="assignTo">
              <j-dict-select-tag type="list" v-model="model.assignTo" dictCode="user_role" placeholder="请选择assign to" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="description" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="description">
              <j-editor v-model="model.description" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="image" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="image">
              <j-image-upload isMultiple  v-model="model.image" ></j-image-upload>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="quest date" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="issueDate">
              <j-date placeholder="请选择quest date" v-model="model.issueDate" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="queat duration" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="deadline">
              <j-date placeholder="请选择queat duration" v-model="model.deadline" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="last sync time" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="syncTime">
              <j-date placeholder="请选择last sync time" v-model="model.syncTime" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="create time in gleam" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="gleamCreateAt">
              <j-date placeholder="请选择create time in gleam" v-model="model.gleamCreateAt" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="fraud_type" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="fraudType">
              <a-input v-model="model.fraudType" placeholder="请输入fraud_type" ></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
    </a-spin>
  </j-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import { validateDuplicateValue } from '@/utils/util'

  export default {
    name: "QuestModal",
    components: { 
    },
    data () {
      return {
        title:"操作",
        width:800,
        visible: false,
        model:{
        },
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },

        confirmLoading: false,
        validatorRules: {
        },
        url: {
          add: "/amquest/quest/add",
          edit: "/amquest/quest/edit",
        }
     
      }
    },
    created () {
    //备份model原始值
      this.modelDefault = JSON.parse(JSON.stringify(this.model));
    },
    methods: {
      add () {
        this.edit(this.modelDefault);
      },
      edit (record) {
        this.model = Object.assign({}, record);
        this.visible = true;
      },
      close () {
        this.$emit('close');
        this.visible = false;
        this.$refs.form.clearValidate();
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.$refs.form.validate(valid => {
          if (valid) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            httpAction(httpurl,this.model,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })
          }else{
             return false
          }
        })
      },
      handleCancel () {
        this.close()
      },

      
    }
  }
</script>