import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: 'quest key',
    align:"center",
    dataIndex: 'questKey'
   },
   {
    title: 'active',
    align:"center",
    dataIndex: 'active',
    customRender:({text}) => {
       return  render.renderSwitch(text, [{text:'是',value:'Y'},{text:'否',value:'N'}])
     },
   },
   {
    title: 'title',
    align:"center",
    dataIndex: 'title'
   },
   {
    title: 'quest link',
    align:"center",
    dataIndex: 'url'
   },
   {
    title: 'rewards points',
    align:"center",
    dataIndex: 'rewards'
   },
   {
    title: 'type',
    align:"center",
    sorter: true,
    dataIndex: 'type_dictText'
   },
   {
    title: 'assign to',
    align:"center",
    sorter: true,
    dataIndex: 'assignTo_dictText'
   },
   {
    title: 'description',
    align:"center",
    dataIndex: 'description',
    slots: { customRender: 'htmlSlot' },
   },
   {
    title: 'image',
    align:"center",
    dataIndex: 'image',
    customRender:render.renderImage,
   },
   {
    title: 'quest date',
    align:"center",
    sorter: true,
    dataIndex: 'issueDate'
   },
   {
    title: 'queat duration',
    align:"center",
    sorter: true,
    dataIndex: 'deadline'
   },
   {
    title: 'last sync time',
    align:"center",
    sorter: true,
    dataIndex: 'syncTime'
   },
   {
    title: 'create time in gleam',
    align:"center",
    sorter: true,
    dataIndex: 'gleamCreateAt'
   },
   {
    title: 'fraud_type',
    align:"center",
    dataIndex: 'fraudType'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
	{
      label: "type",
      field: "type",
      component: 'JDictSelectTag',
      componentProps:{
          dictCode:"quest_type"
      },
      colProps: {span: 6},
 	},
	{
      label: "assign to",
      field: "assignTo",
      component: 'JDictSelectTag',
      componentProps:{
          dictCode:"user_role"
      },
      colProps: {span: 6},
 	},
];

//表单数据
export const formSchema: FormSchema[] = [
  {
    label: 'quest key',
    field: 'questKey',
    component: 'Input',
    dynamicDisabled:true,
  },
  {
    label: 'active',
    field: 'active',
     component: 'JSwitch',
     componentProps:{
     },
  },
  {
    label: 'title',
    field: 'title',
    component: 'Input',
  },
  {
    label: 'quest link',
    field: 'url',
    component: 'Input',
  },
  {
    label: 'rewards points',
    field: 'rewards',
    component: 'InputNumber',
  },
  {
    label: 'type',
    field: 'type',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"quest_type"
     },
  },
  {
    label: 'assign to',
    field: 'assignTo',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"user_role"
     },
  },
  {
    label: 'description',
    field: 'description',
    component: 'JEditor',
  },
  {
    label: 'image',
    field: 'image',
     component: 'JImageUpload',
     componentProps:{
      },
  },
  {
    label: 'quest date',
    field: 'issueDate',
    component: 'DatePicker',
    componentProps: {
       showTime:true,
       valueFormat: 'YYYY-MM-DD HH:mm:ss'
     },
  },
  {
    label: 'queat duration',
    field: 'deadline',
    component: 'DatePicker',
    componentProps: {
       showTime:true,
       valueFormat: 'YYYY-MM-DD HH:mm:ss'
     },
  },
  {
    label: 'last sync time',
    field: 'syncTime',
    component: 'DatePicker',
    componentProps: {
       showTime:true,
       valueFormat: 'YYYY-MM-DD HH:mm:ss'
     },
  },
  {
    label: 'create time in gleam',
    field: 'gleamCreateAt',
    component: 'DatePicker',
    componentProps: {
       showTime:true,
       valueFormat: 'YYYY-MM-DD HH:mm:ss'
     },
  },
  {
    label: 'fraud_type',
    field: 'fraudType',
    component: 'Input',
  },
	// TODO 主键隐藏字段，目前写死为ID
	{
	  label: '',
	  field: 'id',
	  component: 'Input',
	  show: false
	},
];

//子表列表数据
export const actionDefColumns: BasicColumn[] = [
   {
    title: 'type',
    align:"center",
    dataIndex: 'type'
   },
   {
    title: 'Categories',
    align:"center",
    dataIndex: 'categories_dictText'
   },
   {
    title: 'points rewards',
    align:"center",
    dataIndex: 'reward'
   },
   {
    title: 'worth',
    align:"center",
    dataIndex: 'worth'
   },
   {
    title: 'mandatory',
    align:"center",
    dataIndex: 'mandatory'
   },
   {
    title: 'config1',
    align:"center",
    dataIndex: 'config1'
   },
   {
    title: 'config2',
    align:"center",
    dataIndex: 'config2'
   },
   {
    title: 'config3',
    align:"center",
    dataIndex: 'config3'
   },
   {
    title: 'config4',
    align:"center",
    dataIndex: 'config4'
   },
   {
    title: 'config5',
    align:"center",
    dataIndex: 'config5'
   },
   {
    title: 'config6',
    align:"center",
    dataIndex: 'config6'
   },
   {
    title: 'config7',
    align:"center",
    dataIndex: 'config7'
   },
   {
    title: 'config8',
    align:"center",
    dataIndex: 'config8'
   },
   {
    title: 'config9',
    align:"center",
    dataIndex: 'config9'
   },
   {
    title: 'original',
    align:"center",
    dataIndex: 'original'
   },
   {
    title: 'image',
    align:"center",
    dataIndex: 'image'
   },
   {
    title: 'paid',
    align:"center",
    dataIndex: 'paid'
   },
   {
    title: 'config_toggle',
    align:"center",
    dataIndex: 'configToggle'
   },
   {
    title: 'actions_required',
    align:"center",
    dataIndex: 'actionsRequired'
   },
];
//子表表单数据
export const actionDefFormSchema: FormSchema[] = [
  // TODO 子表隐藏字段，目前写死为ID
  {
    label: '',
    field: 'id',
    component: 'Input',
    show: false
  },
  {
    label: 'type',
    field: 'type',
    component: 'Input',
  },
  {
    label: 'Categories',
    field: 'categories',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"action_type"
     },
  },
  {
    label: 'points rewards',
    field: 'reward',
    component: 'InputNumber',
  },
  {
    label: 'worth',
    field: 'worth',
    component: 'InputNumber',
  },
  {
    label: 'mandatory',
    field: 'mandatory',
    component: 'Input',
  },
  {
    label: 'config1',
    field: 'config1',
    component: 'Input',
  },
  {
    label: 'config2',
    field: 'config2',
    component: 'Input',
  },
  {
    label: 'config3',
    field: 'config3',
    component: 'Input',
  },
  {
    label: 'config4',
    field: 'config4',
    component: 'Input',
  },
  {
    label: 'config5',
    field: 'config5',
    component: 'Input',
  },
  {
    label: 'config6',
    field: 'config6',
    component: 'Input',
  },
  {
    label: 'config7',
    field: 'config7',
    component: 'Input',
  },
  {
    label: 'config8',
    field: 'config8',
    component: 'Input',
  },
  {
    label: 'config9',
    field: 'config9',
    component: 'Input',
  },
  {
    label: 'original',
    field: 'original',
    component: 'InputTextArea',
  },
  {
    label: 'image',
    field: 'image',
    component: 'Input',
  },
  {
    label: 'paid',
    field: 'paid',
    component: 'Input',
  },
  {
    label: 'config_toggle',
    field: 'configToggle',
    component: 'Input',
  },
  {
    label: 'actions_required',
    field: 'actionsRequired',
    component: 'InputNumber',
  },
];
