<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="Title" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="title">
              <a-input v-model="model.title" placeholder="请输入Title"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="Sender" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="sender">
              <a-input v-model="model.sender" placeholder="请输入Sender"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="Send time" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="sendTime">
              <j-date placeholder="请选择Send time"  v-model="model.sendTime" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="Type" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="type">
              <j-dict-select-tag type="list" v-model="model.type" dictCode="activity_type" placeholder="请选择Type" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="Amount" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="inputAmount">
              <a-input-number v-model="model.inputAmount" placeholder="请输入Amount" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="Quest" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="questRef">
              <a-input v-model="model.questRef" placeholder="请输入Quest"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="Action Id" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="actionRef">
              <a-input v-model="model.actionRef" placeholder="请输入Action Id"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="Status" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="status">
              <j-dict-select-tag type="list" v-model="model.status" dictCode="activity_status" placeholder="请选择Status" disabled/>
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
    </j-form-container>
  </a-spin>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import { validateDuplicateValue } from '@/utils/util'

  export default {
    name: 'AdminActivityForm',
    components: {
    },
    props: {
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    data () {
      return {
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
          add: "/amactivity/adminActivity/add",
          edit: "/amactivity/adminActivity/edit",
          queryById: "/amactivity/adminActivity/queryById"
        }
      }
    },
    computed: {
      formDisabled(){
        return this.disabled
      },
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
      submitForm () {
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
            })
          }
         
        })
      },
    }
  }
</script>