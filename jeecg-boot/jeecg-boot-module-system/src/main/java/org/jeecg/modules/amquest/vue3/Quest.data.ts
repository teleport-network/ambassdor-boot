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
    dataIndex: 'type_dictText'
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
    dataIndex: 'issueDate'
   },
   {
    title: 'queat duration',
    align:"center",
    dataIndex: 'deadline'
   },
   {
    title: 'last sync time',
    align:"center",
    dataIndex: 'syncTime'
   },
   {
    title: 'create time in gleam',
    align:"center",
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
    component: 'JTreeSelect',
    componentProps:{
        dict:"quest_type,name,id",
        pidValue:"0",
    },
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
    title: 'worth',
    align:"center",
    dataIndex: 'worth'
   },
   {
    title: 'points rewards',
    align:"center",
    dataIndex: 'reward'
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
    label: 'worth',
    field: 'worth',
    component: 'InputNumber',
  },
  {
    label: 'points rewards',
    field: 'reward',
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
