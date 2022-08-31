import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: 'Title',
    align: "center",
    dataIndex: 'title'
  },
  {
    title: 'Sender',
    align: "center",
    dataIndex: 'sender'
  },
  {
    title: 'Send time',
    align: "center",
    dataIndex: 'sendTime'
  },
  {
    title: 'Type',
    align: "center",
    dataIndex: 'type_dictText'
  },
  {
    title: 'Value',
    align: "center",
    dataIndex: 'value'
  },
  {
    title: 'Amount',
    align: "center",
    dataIndex: 'inputAmount'
  },
  {
    title: 'Quest Name',
    align: "center",
    dataIndex: 'questName'
  },
  {
    title: 'Action Name',
    align: "center",
    dataIndex: 'actionName'
  },
  {
    title: 'Status',
    align: "center",
    dataIndex: 'status_dictText'
  },
];

//查询数据
export const searchFormSchema: FormSchema[] = [
  {
    label: "Sender",
    field: 'sender',
    component: 'Input',
    colProps: {span: 6},
  },
  {
    label: "Quest Id",
    field: 'questRef',
    component: 'Input',
    colProps: {span: 6},
  },
];

//表单数据
export const formSchema: FormSchema[] = [
  {
    label: 'Title',
    field: 'title',
    component: 'Input',
  },
  {
    label: 'Sender',
    field: 'sender',
    component: 'Input',
  },
  {
    label: 'Send time',
    field: 'sendTime',
    component: 'DatePicker',
    componentProps: {
      showTime: true,
      valueFormat: 'YYYY-MM-DD HH:mm:ss'
    },
  },
  {
    label: 'Type',
    field: 'type',
    component: 'JDictSelectTag',
    componentProps:{
      dictCode: "activity_type"
    },
  },
  {
    label: 'Value',
    field: 'value',
    component: 'InputTextArea',
  },
  {
    label: 'Amount',
    field: 'inputAmount',
    component: 'InputNumber',
  },
  {
    label: 'Quest Id',
    field: 'questRef',
    component: 'Input',
  },
  {
    label: 'Quest Name',
    field: 'questName',
    component: 'Input',
  },
  {
    label: 'Action Id',
    field: 'actionRef',
    component: 'Input',
  },
  {
    label: 'Action Name',
    field: 'actionName',
    component: 'Input',
  },
  {
    label: 'Status',
    field: 'status',
    component: 'JDictSelectTag',
    componentProps:{
      dictCode: "activity_status"
    },
    dynamicDisabled: true
  },
	// TODO 主键隐藏字段，目前写死为ID
  {
    label: '',
    field: 'id',
    component: 'Input',
    show: false,
  },
];
