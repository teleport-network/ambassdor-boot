<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="quest key" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="questKey">
              <a-input v-model="model.questKey" placeholder="请输入quest key"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="quest url" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="landingUrl">
              <a-input v-model="model.landingUrl" placeholder="请输入quest url"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="name" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="name">
              <a-input v-model="model.name" placeholder="请输入name"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="email" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="email">
              <a-input v-model="model.email" placeholder="请输入email"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="status" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="status">
              <a-input v-model="model.status" placeholder="请输入status"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="action id" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="actionId">
              <a-input-number v-model="model.actionId" placeholder="请输入action id" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="action type" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="actionType">
              <a-input v-model="model.actionType" placeholder="请输入action type"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="value" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="value">
              <a-input v-model="model.value" placeholder="请输入value"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="gallery item" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="galleryItem">
              <a-textarea v-model="model.galleryItem" rows="4" placeholder="请输入gallery item" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="action config" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="actionConfig">
              <a-textarea v-model="model.actionConfig" rows="4" placeholder="请输入action config" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="last sync time" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="syncTime">
              <j-date placeholder="请选择last sync time"  v-model="model.syncTime" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
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
    name: 'QuestActionForm',
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
          add: "/amqaction/questAction/add",
          edit: "/amqaction/questAction/edit",
          queryById: "/amqaction/questAction/queryById"
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