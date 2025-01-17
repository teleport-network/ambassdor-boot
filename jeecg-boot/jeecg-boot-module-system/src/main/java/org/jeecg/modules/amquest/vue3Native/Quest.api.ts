import {defHttp} from '/@/utils/http/axios';
import { useMessage } from "/@/hooks/web/useMessage";

const { createConfirm } = useMessage();

enum Api {
  list = '/amquest/quest/list',
  save='/amquest/quest/add',
  edit='/amquest/quest/edit',
  deleteOne = '/amquest/quest/delete',
  deleteBatch = '/amquest/quest/deleteBatch',
  importExcel = '/amquest/quest/importExcel',
  exportXls = '/amquest/quest/exportXls',
  queryDataById = '/amquest/quest/queryById',
  actionDefList = '/amquest/quest/queryActionDefByMainId',
}
/**
 * 导出api
 * @param params
 */
export const getExportUrl = Api.exportXls;

/**
 * 导入api
 */
export const getImportUrl = Api.importExcel;

/**
 * 查询子表数据
 * @param params
 */
export const queryActionDefListByMainId = (id) => defHttp.get({url: Api.actionDefList, params:{ id }});

/**
 * 列表接口
 * @param params
 */
export const list = (params) =>
  defHttp.get({url: Api.list, params});

/**
 * 删除单个
 */
export const deleteOne = (params,handleSuccess) => {
  return defHttp.delete({url: Api.deleteOne, params}, {joinParamsToUrl: true}).then(() => {
    handleSuccess();
  });
}
/**
 * 批量删除
 * @param params
 */
export const batchDelete = (params, handleSuccess) => {
  createConfirm({
    iconType: 'warning',
    title: '确认删除',
    content: '是否删除选中数据',
    okText: '确认',
    cancelText: '取消',
    onOk: () => {
      return defHttp.delete({url: Api.deleteBatch, data: params}, {joinParamsToUrl: true}).then(() => {
        handleSuccess();
      });
    }
  });
}
/**
 * 保存或者更新
 * @param params
 */
export const saveOrUpdate = (params, isUpdate) => {
  let url = isUpdate ? Api.edit : Api.save;
  return defHttp.post({url: url, params});
}

/**
* 根据id查询数据
* @param params
*/
export const queryDataById = (id) => defHttp.get({url: Api.queryDataById, params:{ id }});

