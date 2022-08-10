import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import {JVxeTypes,JVxeColumn} from '/@/components/jeecg/JVxeTable/types'
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
    dataIndex: 'type'
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
    dynamicDisabled:true
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
    component: 'Input',
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
//子表单数据
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
//子表表格配置
export const actionDefJVxeColumns: JVxeColumn[] = [
    {
      title: 'type',
      key: 'type',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: 'worth',
      key: 'worth',
      type: JVxeTypes.inputNumber,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: 'mandatory',
      key: 'mandatory',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: 'config1',
      key: 'config1',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: 'config2',
      key: 'config2',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: 'config3',
      key: 'config3',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: 'config4',
      key: 'config4',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: 'config5',
      key: 'config5',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: 'config6',
      key: 'config6',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: 'config7',
      key: 'config7',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: 'config8',
      key: 'config8',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: 'config9',
      key: 'config9',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: 'original',
      key: 'original',
      type: JVxeTypes.textarea,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: 'image',
      key: 'image',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: 'paid',
      key: 'paid',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: 'config_toggle',
      key: 'configToggle',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: 'actions_required',
      key: 'actionsRequired',
      type: JVxeTypes.inputNumber,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
  ]
