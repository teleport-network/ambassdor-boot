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
            <a-form-model-item label="delivered time" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="deliveredTime">
              <j-date placeholder="请选择delivered time" v-model="model.deliveredTime" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="from" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="fromAddress">
              <a-input v-model="model.fromAddress"placeholder="请输入from" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="to" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="toAddress">
              <a-input v-model="model.toAddress"placeholder="请输入to" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="tx hash" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="txHash">
              <a-input v-model="model.txHash"placeholder="请输入tx hash" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="url" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="scanUrl">
              <a-input v-model="model.scanUrl"placeholder="请输入url" ></a-input>
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
    name: "DeliveredHistoryModal",
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
          add: "/amnft/nft/addDeliveredHistory",
          edit: "/amnft/nft/editDeliveredHistory",
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
            this.model['nftId'] = this.mainId
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
