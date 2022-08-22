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
    title: 'Points for Level-up',
    align:"center",
    dataIndex: 'points'
   },
   {
    title: 'Token Allocation',
    align:"center",
    dataIndex: 'token'
   },
   {
    title: 'Level Index',
    align:"center",
    dataIndex: 'lvlIndex'
   },
   {
    title: 'NFT Allocation',
    align:"center",
    dataIndex: 'nft'
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
                 {...rules.duplicateCheckRule('ambassador_level', 'name',model,schema)[0]},
          ];
     },
  },
  {
    label: 'Points for Level-up',
    field: 'points',
    component: 'InputNumber',
  },
  {
    label: 'Token Allocation',
    field: 'token',
    component: 'InputNumber',
  },
  {
    label: 'Level Index',
    field: 'lvlIndex',
    component: 'InputNumber',
  },
  {
    label: 'NFT Allocation',
    field: 'nft',
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
