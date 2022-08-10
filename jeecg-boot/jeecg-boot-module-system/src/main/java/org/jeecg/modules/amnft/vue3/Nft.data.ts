import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: 'image',
    align:"center",
    dataIndex: 'image'
   },
   {
    title: 'name',
    align:"center",
    dataIndex: 'name'
   },
   {
    title: 'type',
    align:"center",
    dataIndex: 'type'
   },
   {
    title: 'desc',
    align:"center",
    dataIndex: 'description'
   },
   {
    title: 'address',
    align:"center",
    dataIndex: 'address'
   },
   {
    title: 'total',
    align:"center",
    dataIndex: 'total'
   },
   {
    title: 'inventory',
    align:"center",
    dataIndex: 'inventory'
   },
   {
    title: 'delivered quantity',
    align:"center",
    dataIndex: 'delivered'
   },
   {
    title: 'reuest quantity',
    align:"center",
    dataIndex: 'txRequestNum'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];

//表单数据
export const formSchema: FormSchema[] = [
  {
    label: 'image',
    field: 'image',
    component: 'Input',
  },
  {
    label: 'name',
    field: 'name',
    component: 'Input',
  },
  {
    label: 'type',
    field: 'type',
    component: 'Input',
  },
  {
    label: 'desc',
    field: 'description',
    component: 'Input',
  },
  {
    label: 'address',
    field: 'address',
    component: 'Input',
  },
  {
    label: 'total',
    field: 'total',
    component: 'InputNumber',
  },
  {
    label: 'inventory',
    field: 'inventory',
    component: 'InputNumber',
  },
  {
    label: 'delivered quantity',
    field: 'delivered',
    component: 'InputNumber',
  },
  {
    label: 'reuest quantity',
    field: 'txRequestNum',
    component: 'InputNumber',
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
export const mintHistoryColumns: BasicColumn[] = [
   {
    title: 'contract address',
    align:"center",
    dataIndex: 'contractAddress'
   },
   {
    title: 'mint account',
    align:"center",
    dataIndex: 'mintAccount'
   },
   {
    title: 'contract_in_binary',
    align:"center",
    dataIndex: 'contractInBinary'
   },
   {
    title: 'tx hash',
    align:"center",
    dataIndex: 'txHash'
   },
];
//子表表单数据
export const mintHistoryFormSchema: FormSchema[] = [
  // TODO 子表隐藏字段，目前写死为ID
  {
    label: '',
    field: 'id',
    component: 'Input',
    show: false
  },
  {
    label: 'contract address',
    field: 'contractAddress',
    component: 'Input',
  },
  {
    label: 'mint account',
    field: 'mintAccount',
    component: 'Input',
  },
  {
    label: 'contract_in_binary',
    field: 'contractInBinary',
    component: 'InputTextArea',
  },
  {
    label: 'tx hash',
    field: 'txHash',
    component: 'Input',
  },
];
//子表列表数据
export const deliveredHistoryColumns: BasicColumn[] = [
   {
    title: 'delivered time',
    align:"center",
    dataIndex: 'deliveredTime'
   },
   {
    title: 'from',
    align:"center",
    dataIndex: 'fromAddress'
   },
   {
    title: 'to',
    align:"center",
    dataIndex: 'toAddress'
   },
   {
    title: 'tx hash',
    align:"center",
    dataIndex: 'txHash'
   },
   {
    title: 'url',
    align:"center",
    dataIndex: 'scanUrl'
   },
];
//子表表单数据
export const deliveredHistoryFormSchema: FormSchema[] = [
  // TODO 子表隐藏字段，目前写死为ID
  {
    label: '',
    field: 'id',
    component: 'Input',
    show: false
  },
  {
    label: 'delivered time',
    field: 'deliveredTime',
    component: 'DatePicker',
    componentProps: {
       showTime:true,
       valueFormat: 'YYYY-MM-DD HH:mm:ss'
     },
  },
  {
    label: 'from',
    field: 'fromAddress',
    component: 'Input',
  },
  {
    label: 'to',
    field: 'toAddress',
    component: 'Input',
  },
  {
    label: 'tx hash',
    field: 'txHash',
    component: 'Input',
  },
  {
    label: 'url',
    field: 'scanUrl',
    component: 'Input',
  },
];
