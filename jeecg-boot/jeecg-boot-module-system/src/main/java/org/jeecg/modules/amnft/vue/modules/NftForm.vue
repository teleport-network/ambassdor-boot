<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="Name" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="name">
              <a-input v-model="model.name" placeholder="请输入Name"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="Total" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="total">
              <a-input-number v-model="model.total" placeholder="请输入Total" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="TokenId Start" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="startIndex">
              <a-input-number v-model="model.startIndex" placeholder="请输入TokenId Start" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="Next TokenId" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="nextIndex">
              <a-input-number v-model="model.nextIndex" placeholder="请输入Next TokenId" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="TokenId End" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="endIndex">
              <a-input-number v-model="model.endIndex" placeholder="请输入TokenId End" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="Inventory" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="inventory">
              <a-input-number v-model="model.inventory" placeholder="请输入Inventory" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="Delivered Quantity" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="delivered">
              <a-input-number v-model="model.delivered" placeholder="请输入Delivered Quantity" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="Type" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="type">
              <a-input v-model="model.type" placeholder="请输入Type"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="Ipfs Url" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="ipfs">
              <a-input v-model="model.ipfs" placeholder="请输入Ipfs Url"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="Contract Address" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="contractAddress">
              <a-input v-model="model.contractAddress" placeholder="请输入Contract Address"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="Image" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="image">
              <a-input v-model="model.image" placeholder="请输入Image"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="Desc" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="description">
              <a-textarea v-model="model.description" rows="4" placeholder="请输入Desc" />
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
    name: 'NftForm',
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
           name: [
              { required: false},
              { validator: (rule, value, callback) => validateDuplicateValue('nft', 'name', value, this.model.id, callback)},
           ],
           total: [
              { required: false},
              { pattern: /^-?\d+$/, message: '请输入整数!'},
           ],
           startIndex: [
              { required: false},
              { pattern: /^-?\d+$/, message: '请输入整数!'},
           ],
           nextIndex: [
              { required: false},
              { pattern: /^-?\d+$/, message: '请输入整数!'},
           ],
           endIndex: [
              { required: false},
              { pattern: /^-?\d+$/, message: '请输入整数!'},
           ],
           inventory: [
              { required: false},
              { pattern: /^-?\d+$/, message: '请输入整数!'},
           ],
           delivered: [
              { required: false},
              { pattern: /^-?\d+$/, message: '请输入整数!'},
           ],
        },
        url: {
          add: "/amnft/nft/add",
          edit: "/amnft/nft/edit",
          queryById: "/amnft/nft/queryById"
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