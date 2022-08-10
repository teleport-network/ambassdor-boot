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
            <a-form-model-item label="image" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="image">
              <a-input v-model="model.image" placeholder="请输入image" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="name" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="name">
              <a-input v-model="model.name" placeholder="请输入name" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="type" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="type">
              <a-input v-model="model.type" placeholder="请输入type" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="desc" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="description">
              <a-input v-model="model.description" placeholder="请输入desc" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="address" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="address">
              <a-input v-model="model.address" placeholder="请输入address" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="total" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="total">
              <a-input-number v-model="model.total" placeholder="请输入total" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="inventory" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="inventory">
              <a-input-number v-model="model.inventory" placeholder="请输入inventory" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="delivered quantity" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="delivered">
              <a-input-number v-model="model.delivered" placeholder="请输入delivered quantity" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="reuest quantity" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="txRequestNum">
              <a-input-number v-model="model.txRequestNum" placeholder="请输入reuest quantity" style="width: 100%" />
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
    name: "NftModal",
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
          add: "/amnft/nft/add",
          edit: "/amnft/nft/edit",
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