<template>
  <a-spin :spinning="loading">
    <a-form v-bind="formItemLayout">
      <a-row>
        <a-col :span="24">
          <a-form-item label="quest key" v-bind="validateInfos.questKey">
            <a-input v-model:value="formData.questKey" placeholder="请输入quest key" disabled></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="title" v-bind="validateInfos.title">
            <a-input v-model:value="formData.title" placeholder="请输入title" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="quest link" v-bind="validateInfos.url">
            <a-input v-model:value="formData.url" placeholder="请输入quest link" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="rewards points" v-bind="validateInfos.rewards">
	          <a-input-number v-model:value="formData.rewards" placeholder="请输入rewards points" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="type" v-bind="validateInfos.type">
            <a-input v-model:value="formData.type" placeholder="请输入type" :disabled="disabled"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="image" v-bind="validateInfos.image">
	          <j-image-upload  v-model:value="formData.image" :disabled="disabled"></j-image-upload>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="quest date" v-bind="validateInfos.issueDate">
		        <a-date-picker placeholder="请选择quest date"  v-model:value="formData.issueDate" showTime value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="queat duration" v-bind="validateInfos.deadline">
		        <a-date-picker placeholder="请选择queat duration"  v-model:value="formData.deadline" showTime value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="last sync time" v-bind="validateInfos.syncTime">
		        <a-date-picker placeholder="请选择last sync time"  v-model:value="formData.syncTime" showTime value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label="create time in gleam" v-bind="validateInfos.gleamCreateAt">
		        <a-date-picker placeholder="请选择create time in gleam"  v-model:value="formData.gleamCreateAt" showTime value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" :disabled="disabled"/>
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>

		<!-- 子表单区域 -->
    <a-tabs v-model:activeKey="activeKey">
      <a-tab-pane tab="quest def" key="actionDef" :forceRender="true">
        <j-vxe-table
          :keep-source="true"
          ref="actionDefTableRef"
          :loading="actionDefTable.loading"
          :columns="actionDefTable.columns"
          :dataSource="actionDefTable.dataSource"
          :maxHeight="300"
          :disabled="formDisabled"
          :rowNumber="true"
          :rowSelection="true"
          :toolbar="true"/>
      </a-tab-pane>
    </a-tabs>
  </a-spin>
</template>

<script lang="ts">
  import { defineComponent, ref, reactive, computed, toRaw } from 'vue';
  import { useValidateAntFormAndTable } from '/@/hooks/system/useJvxeMethods';
  import { queryActionDefListByMainId, queryDataById, saveOrUpdate } from '../Quest.api';
  import { JVxeTable } from '/@/components/jeecg/JVxeTable';
  import {actionDefColumns} from '../Quest.data';
  import JImageUpload from '/@/components/Form/src/jeecg/components/JImageUpload.vue';
  import { Form } from 'ant-design-vue';
  const useForm = Form.useForm;

  export default defineComponent({
    name: "QuestForm",
    components:{
      JImageUpload,
      JVxeTable,
    },
    props:{
      disabled:{
        type: Boolean,
        default: false
      }
    },
    emits:['success'],
    setup(props, {emit}) {
      const loading = ref(false);
      const actionDefTableRef = ref();
      const actionDefTable = reactive<Record<string, any>>({
        loading: false,
        columns: actionDefColumns,
        dataSource: []
      });
      const activeKey = ref('actionDef');
      const formData = reactive<Record<string, any>>({
        id: '',
        questKey: '',
        title: '',
        url: '',
        rewards: '',
        type: '',
        image: '',
        issueDate: '',
        deadline: '',
        syncTime: '',
        gleamCreateAt: '',
      });

      //表单验证
      const validatorRules = reactive({
      });
      const {resetFields, validate, validateInfos} = useForm(formData, validatorRules, {immediate: true});
      const dbData = {};
      const formItemLayout = {
        labelCol: {xs: {span: 24}, sm: {span: 5}},
        wrapperCol: {xs: {span: 24}, sm: {span: 16}},
      };

      const formDisabled = computed(() => {
        return props.disabled;
      });

      function add() {
        resetFields();
        actionDefTable.dataSource = [];
      }

      async function edit(row) {
        //主表数据
        await queryMainData(row.id);
        //子表数据
        const actionDefDataList = await queryActionDefListByMainId(row['id']);
        actionDefTable.dataSource = [...actionDefDataList];
      }

      async function queryMainData(id) {
        const row = await queryDataById(id);
        Object.keys(row).map(k => {
          formData[k] = row[k];
        });
      }

      const {getSubFormAndTableData, transformData} = useValidateAntFormAndTable(activeKey, {
        'actionDef': actionDefTableRef,
      });

      async function getFormData() {
        await validate();
        return transformData(toRaw(formData))
      }

      async function submitForm() {
        const mainData = await getFormData();
        const subData = await getSubFormAndTableData();
        const values = Object.assign({}, dbData, mainData, subData);
        console.log('表单提交数据', values)
        const isUpdate = values.id ? true : false
        await saveOrUpdate(values, isUpdate);
        //关闭弹窗
        emit('success');
      }
      
      function setFieldsValue(values) {
        if(values){
          Object.keys(values).map(k=>{
            formData[k] = values[k];
          });
        }
      }

      /**
       * 值改变事件触发-树控件回调
       * @param key
       * @param value
       */
      function handleFormChange(key, value) {
        formData[key] = value;
      }


      return {
        actionDefTableRef,
        actionDefTable,
        validatorRules,
        validateInfos,
        activeKey,
        loading,
        formData,
        setFieldsValue,
        handleFormChange,
        formItemLayout,
        formDisabled,
        getFormData,
        submitForm,
        add,
        edit
      }
    }
  });
</script>