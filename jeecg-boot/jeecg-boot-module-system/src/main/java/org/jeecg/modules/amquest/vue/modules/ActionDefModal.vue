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
            <a-form-model-item label="type" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="type">
              <a-input v-model="model.type"placeholder="请输入type" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="worth" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="worth">
              <a-input-number v-model="model.worth"placeholder="请输入worth" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="points rewards" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="reward">
              <a-input-number v-model="model.reward"placeholder="请输入points rewards" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="mandatory" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="mandatory">
              <a-input v-model="model.mandatory"placeholder="请输入mandatory" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="config1" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="config1">
              <a-input v-model="model.config1"placeholder="请输入config1" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="config2" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="config2">
              <a-input v-model="model.config2"placeholder="请输入config2" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="config3" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="config3">
              <a-input v-model="model.config3"placeholder="请输入config3" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="config4" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="config4">
              <a-input v-model="model.config4"placeholder="请输入config4" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="config5" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="config5">
              <a-input v-model="model.config5"placeholder="请输入config5" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="config6" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="config6">
              <a-input v-model="model.config6"placeholder="请输入config6" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="config7" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="config7">
              <a-input v-model="model.config7"placeholder="请输入config7" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="config8" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="config8">
              <a-input v-model="model.config8"placeholder="请输入config8" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="config9" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="config9">
              <a-input v-model="model.config9"placeholder="请输入config9" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="original" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="original">
              <a-textarea v-model="model.original" rows="4" placeholder="请输入original" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="image" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="image">
              <a-input v-model="model.image"placeholder="请输入image" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="paid" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="paid">
              <a-input v-model="model.paid"placeholder="请输入paid" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="config_toggle" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="configToggle">
              <a-input v-model="model.configToggle"placeholder="请输入config_toggle" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="actions_required" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="actionsRequired">
              <a-input-number v-model="model.actionsRequired"placeholder="请输入actions_required" style="width: 100%" />
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
    name: "ActionDefModal",
    components: {
    },
    props:{
      mainId:{
        type:String,
        required:false,
        default:''
      }
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
          add: "/amquest/quest/addActionDef",
          edit: "/amquest/quest/editActionDef",
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
            this.model['questFk'] = this.mainId
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
