<template>
  <a-spin :spinning="confirmLoading">
    <a-form ref="formRef" class="antd-modal-form" :labelCol="labelCol" :wrapperCol="wrapperCol">
      <a-row>
        <a-col :span="24">
          <a-form-item label="Name" v-bind="validateInfos.name">
            <a-input v-model:value="formData.name" placeholder="请输入Name" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="Total" v-bind="validateInfos.total">
	          <a-input-number v-model:value="formData.total" placeholder="请输入Total" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="TokenId Start" v-bind="validateInfos.startIndex">
	          <a-input-number v-model:value="formData.startIndex" placeholder="请输入TokenId Start" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="Next TokenId" v-bind="validateInfos.nextIndex">
	          <a-input-number v-model:value="formData.nextIndex" placeholder="请输入Next TokenId" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="TokenId End" v-bind="validateInfos.endIndex">
	          <a-input-number v-model:value="formData.endIndex" placeholder="请输入TokenId End" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="Inventory" v-bind="validateInfos.inventory">
	          <a-input-number v-model:value="formData.inventory" placeholder="请输入Inventory" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="Delivered Quantity" v-bind="validateInfos.delivered">
	          <a-input-number v-model:value="formData.delivered" placeholder="请输入Delivered Quantity" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="Type" v-bind="validateInfos.type">
            <a-input v-model:value="formData.type" placeholder="请输入Type" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="Contract Address" v-bind="validateInfos.contractAddress">
            <a-input v-model:value="formData.contractAddress" placeholder="请输入Contract Address" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="Image" v-bind="validateInfos.image">
            <a-input v-model:value="formData.image" placeholder="请输入Image" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="Desc" v-bind="validateInfos.description">
	          <a-textarea v-model:value="formData.description" rows="4" placeholder="请输入Desc" :disabled="disabled"/>
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
  </a-spin>
</template>

<script lang="ts" setup>
  import { ref, reactive, defineExpose, nextTick, defineProps, computed } from 'vue';
  import { defHttp } from '/@/utils/http/axios';
  import { useMessage } from '/@/hooks/web/useMessage';
  import moment from 'moment';
  import { getValueType } from '/@/utils';
  import { saveOrUpdate } from '../Nft.api';
  import { Form } from 'ant-design-vue';
  import { duplicateValidate } from '/@/utils/helper/validator'
  
  const props = defineProps({
    disabled: { type: Boolean, default: false },
  });
  const formRef = ref();
  const useForm = Form.useForm;
  const emit = defineEmits(['register', 'ok']);
  const formData = reactive<Record<string, any>>({
    id: '',
    name: '',   
    id: '',
    total: undefined,
    id: '',
    startIndex: undefined,
    id: '',
    nextIndex: undefined,
    id: '',
    endIndex: undefined,
    id: '',
    inventory: undefined,
    id: '',
    delivered: undefined,
    id: '',
    type: '',   
    id: '',
    contractAddress: '',   
    id: '',
    image: '',   
    id: '',
    description: '',   
  });
  const { createMessage } = useMessage();
  const labelCol = ref<any>({ xs: { span: 24 }, sm: { span: 5 } });
  const wrapperCol = ref<any>({ xs: { span: 24 }, sm: { span: 16 } });
  const confirmLoading = ref<boolean>(false);
  //表单验证
  const validatorRules = {
    name: [{ required: false}, { validator: nameDuplicatevalidate }],
    total: [{ required: false}, { pattern: /^-?\d+$/, message: '请输入整数!'},],
    startIndex: [{ required: false}, { pattern: /^-?\d+$/, message: '请输入整数!'},],
    nextIndex: [{ required: false}, { pattern: /^-?\d+$/, message: '请输入整数!'},],
    endIndex: [{ required: false}, { pattern: /^-?\d+$/, message: '请输入整数!'},],
    inventory: [{ required: false}, { pattern: /^-?\d+$/, message: '请输入整数!'},],
    delivered: [{ required: false}, { pattern: /^-?\d+$/, message: '请输入整数!'},],
  };
  const { resetFields, validate, validateInfos } = useForm(formData, validatorRules, { immediate: true });
  
  /**
   * 新增
   */
  function add() {
    edit({});
  }

  /**
   * 编辑
   */
  function edit(record) {
    nextTick(() => {
      resetFields();
      //赋值
      Object.assign(formData, record);
    });
  }

  /**
   * 提交数据
   */
  async function submitForm() {
    // 触发表单验证
    await validate();
    confirmLoading.value = true;
    const isUpdate = ref<boolean>(false);
    //时间格式化
    let model = formData;
    if (model.id) {
      isUpdate.value = true;
    }
    //循环数据
    for (let data in model) {
      //如果该数据是数组并且是字符串类型
      if (model[data] instanceof Array) {
        let valueType = getValueType(formRef.value.getProps, data);
        //如果是字符串类型的需要变成以逗号分割的字符串
        if (valueType === 'string') {
          model[data] = model[data].join(',');
        }
      }
    }
    await saveOrUpdate(model, isUpdate.value)
      .then((res) => {
        if (res.success) {
          createMessage.success(res.message);
          emit('ok');
        } else {
          createMessage.warning(res.message);
        }
      })
      .finally(() => {
        confirmLoading.value = false;
      });
  }


  async function nameDuplicatevalidate(_r, value) {
    return duplicateValidate('nft', 'name', value, formData.id || '')
  }
  defineExpose({
    add,
    edit,
    submitForm,
  });
</script>

<style lang="less" scoped>
  .antd-modal-form {
    height: 500px !important;
    overflow-y: auto;
    padding: 24px 24px 24px 24px;
  }
</style>
