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
    title: 'quest url',
    align:"center",
    dataIndex: 'landingUrl'
   },
   {
    title: 'name',
    align:"center",
    dataIndex: 'name'
   },
   {
    title: 'email',
    align:"center",
    dataIndex: 'email'
   },
   {
    title: 'status',
    align:"center",
    dataIndex: 'status'
   },
   {
    title: 'action id',
    align:"center",
    dataIndex: 'actionId'
   },
   {
    title: 'action type',
    align:"center",
    dataIndex: 'actionType'
   },
   {
    title: 'action config',
    align:"center",
    dataIndex: 'actionConfig'
   },
   {
    title: 'last sync time',
    align:"center",
    dataIndex: 'syncTime'
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
  },
  {
    label: 'quest url',
    field: 'landingUrl',
    component: 'Input',
  },
  {
    label: 'name',
    field: 'name',
    component: 'Input',
  },
  {
    label: 'email',
    field: 'email',
    component: 'Input',
  },
  {
    label: 'status',
    field: 'status',
    component: 'Input',
  },
  {
    label: 'action id',
    field: 'actionId',
    component: 'InputNumber',
  },
  {
    label: 'action type',
    field: 'actionType',
    component: 'Input',
  },
  {
    label: 'action config',
    field: 'actionConfig',
    component: 'InputTextArea',
  },
  {
    label: 'last sync time',
    field: 'syncTime',
    component: 'DatePicker',
    componentProps: {
       showTime: true,
       valueFormat: 'YYYY-MM-DD HH:mm:ss'
     },
  },
	// TODO 主键隐藏字段，目前写死为ID
	{
	  label: '',
	  field: 'id',
	  component: 'Input',
	  show: false
	},
];
