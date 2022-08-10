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
            <a-form-model-item label="contract address" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="contractAddress">
              <a-input v-model="model.contractAddress"placeholder="请输入contract address" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="mint account" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="mintAccount">
              <a-input v-model="model.mintAccount"placeholder="请输入mint account" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="contract_in_binary" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="contractInBinary">
              <a-textarea v-model="model.contractInBinary" rows="4" placeholder="请输入contract_in_binary" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="tx hash" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="txHash">
              <a-input v-model="model.txHash"placeholder="请输入tx hash" ></a-input>
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
    name: "MintHistoryModal",
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
          add: "/amnft/nft/addMintHistory",
          edit: "/amnft/nft/editMintHistory",
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
