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
            <a-form-model-item label="evm address" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="address">
              <a-input v-model="model.address" placeholder="请输入evm address" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="邮箱" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="email">
              <a-input v-model="model.email" placeholder="请输入邮箱" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="用户名" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="username">
              <a-input v-model="model.username" placeholder="请输入用户名" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="头像" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="avatar">
              <j-image-upload isMultiple  v-model="model.avatar" ></j-image-upload>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="角色" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="role">
              <j-dict-select-tag type="list" v-model="model.role" dictCode="user_role" placeholder="请选择角色" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="国家/地区" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="location">
              <a-input v-model="model.location" placeholder="请输入国家/地区" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="当前token数量" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="token">
              <a-input-number v-model="model.token" placeholder="请输入当前token数量" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="当前points数量" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="point">
              <a-input-number v-model="model.point" placeholder="请输入当前points数量" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="当前未收集的points" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="pointCache">
              <a-input-number v-model="model.pointCache" placeholder="请输入当前未收集的points" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="当前等级" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="level">
              <j-dict-select-tag type="list" v-model="model.level" dictCode="user_level" placeholder="请选择当前等级" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="discord id" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="discordId">
              <a-input v-model="model.discordId" placeholder="请输入discord id" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="twitter id" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="twitterId">
              <a-input v-model="model.twitterId" placeholder="请输入twitter id" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="telegram id" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="telegramId">
              <a-input v-model="model.telegramId" placeholder="请输入telegram id" ></a-input>
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
    name: "AmbassadorUserModal",
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
           address: [
              { required: true, message: '请输入evm address!'},
              { validator: (rule, value, callback) => validateDuplicateValue('ambassador_user', 'address', value, this.model.id, callback)},
           ],
           email: [
              { required: true, message: '请输入邮箱!'},
              { validator: (rule, value, callback) => validateDuplicateValue('ambassador_user', 'email', value, this.model.id, callback)},
           ],
           token: [
              { required: false},
              { pattern: /^(([1-9][0-9]*)|([0]\.\d{0,2}|[1-9][0-9]*\.\d{0,2}))$/, message: '请输入正确的金额!'},
           ],
           point: [
              { required: false},
              { pattern: /^-?\d+$/, message: '请输入整数!'},
           ],
        },
        url: {
          add: "/amuser/ambassadorUser/add",
          edit: "/amuser/ambassadorUser/edit",
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