import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: 'name',
    align:"center",
    dataIndex: 'name'
   },
   {
    title: 'points for level-up',
    align:"center",
    dataIndex: 'points'
   },
   {
    title: 'token allocation',
    align:"center",
    dataIndex: 'token'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: 'name',
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
    label: 'points for level-up',
    field: 'points',
    component: 'InputNumber',
  },
  {
    label: 'token allocation',
    field: 'token',
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
