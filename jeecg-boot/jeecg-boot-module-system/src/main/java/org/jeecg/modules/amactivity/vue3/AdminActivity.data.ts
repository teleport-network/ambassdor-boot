import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: 'Title',
    align:"center",
    dataIndex: 'title'
   },
   {
    title: 'Sender',
    align:"center",
    dataIndex: 'sender'
   },
   {
    title: 'Send time',
    align:"center",
    dataIndex: 'sendTime'
   },
   {
    title: 'Type',
    align:"center",
    dataIndex: 'type_dictText'
   },
   {
    title: 'Amount',
    align:"center",
    dataIndex: 'inputAmount'
   },
   {
    title: 'Quest',
    align:"center",
    dataIndex: 'questRef'
   },
   {
    title: 'Action Id',
    align:"center",
    dataIndex: 'actionRef'
   },
   {
    title: 'Status',
    align:"center",
    dataIndex: 'status_dictText'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: 'Title',
    field: 'title',
    component: 'Input',
    dynamicDisabled:true
  },
  {
    label: 'Sender',
    field: 'sender',
    component: 'Input',
    dynamicDisabled:true
  },
  {
    label: 'Send time',
    field: 'sendTime',
    component: 'DatePicker',
    componentProps: {
       showTime: true,
       valueFormat: 'YYYY-MM-DD HH:mm:ss'
     },
    dynamicDisabled:true
  },
  {
    label: 'Type',
    field: 'type',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"activity_type"
     },
    dynamicDisabled:true
  },
  {
    label: 'Amount',
    field: 'inputAmount',
    component: 'InputNumber',
  },
  {
    label: 'Quest',
    field: 'questRef',
    component: 'Input',
    dynamicDisabled:true
  },
  {
    label: 'Action Id',
    field: 'actionRef',
    component: 'Input',
    dynamicDisabled:true
  },
  {
    label: 'Status',
    field: 'status',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"activity_status"
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
