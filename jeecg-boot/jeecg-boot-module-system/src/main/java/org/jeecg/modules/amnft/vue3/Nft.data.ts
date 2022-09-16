import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: 'Name',
    align:"center",
    dataIndex: 'name'
   },
   {
    title: 'Total',
    align:"center",
    dataIndex: 'total'
   },
   {
    title: 'TokenId Start',
    align:"center",
    dataIndex: 'startIndex'
   },
   {
    title: 'Next TokenId',
    align:"center",
    dataIndex: 'nextIndex'
   },
   {
    title: 'TokenId End',
    align:"center",
    dataIndex: 'endIndex'
   },
   {
    title: 'Inventory',
    align:"center",
    dataIndex: 'inventory'
   },
   {
    title: 'Delivered Quantity',
    align:"center",
    dataIndex: 'delivered'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: 'Name',
    field: 'name',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: false},
                 {...rules.duplicateCheckRule('nft', 'name',model,schema)[0]},
          ];
     },
  },
  {
    label: 'Total',
    field: 'total',
    component: 'InputNumber',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: false},
                 { pattern: /^-?\d+$/, message: '请输入整数!'},
          ];
     },
  },
  {
    label: 'TokenId Start',
    field: 'startIndex',
    component: 'InputNumber',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: false},
                 { pattern: /^-?\d+$/, message: '请输入整数!'},
          ];
     },
  },
  {
    label: 'Next TokenId',
    field: 'nextIndex',
    component: 'InputNumber',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: false},
                 { pattern: /^-?\d+$/, message: '请输入整数!'},
          ];
     },
  },
  {
    label: 'TokenId End',
    field: 'endIndex',
    component: 'InputNumber',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: false},
                 { pattern: /^-?\d+$/, message: '请输入整数!'},
          ];
     },
  },
  {
    label: 'Inventory',
    field: 'inventory',
    component: 'InputNumber',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: false},
                 { pattern: /^-?\d+$/, message: '请输入整数!'},
          ];
     },
  },
  {
    label: 'Delivered Quantity',
    field: 'delivered',
    component: 'InputNumber',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: false},
                 { pattern: /^-?\d+$/, message: '请输入整数!'},
          ];
     },
  },
  {
    label: 'Type',
    field: 'type',
    component: 'Input',
  },
  {
    label: 'Ipfs Url',
    field: 'ipfs',
    component: 'Input',
  },
  {
    label: 'Contract Address',
    field: 'contractAddress',
    component: 'Input',
  },
  {
    label: 'Image',
    field: 'image',
    component: 'Input',
  },
  {
    label: 'Desc',
    field: 'description',
    component: 'InputTextArea',
  },
	// TODO 主键隐藏字段，目前写死为ID
	{
	  label: '',
	  field: 'id',
	  component: 'Input',
	  show: false
	},
];
