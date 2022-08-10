import {defHttp} from '/@/utils/http/axios';
import { useMessage } from "/@/hooks/web/useMessage";

const { createConfirm } = useMessage();

enum Api {
  list = '/amnft/nft/list',
  save='/amnft/nft/add',
  edit='/amnft/nft/edit',
  deleteOne = '/amnft/nft/delete',
  deleteBatch = '/amnft/nft/deleteBatch',
  importExcel = '/amnft/nft/importExcel',
  exportXls = '/amnft/nft/exportXls',
  mintHistoryList = '/amnft/nft/listMintHistoryByMainId',
  mintHistorySave='/amnft/nft/addMintHistory',
  mintHistoryEdit='/amnft/nft/editMintHistory',
  mintHistoryDelete = '/amnft/nft/deleteMintHistory',
  mintHistoryDeleteBatch = '/amnft/nft/deleteBatchMintHistory',
  deliveredHistoryList = '/amnft/nft/listDeliveredHistoryByMainId',
  deliveredHistorySave='/amnft/nft/addDeliveredHistory',
  deliveredHistoryEdit='/amnft/nft/editDeliveredHistory',
  deliveredHistoryDelete = '/amnft/nft/deleteDeliveredHistory',
  deliveredHistoryDeleteBatch = '/amnft/nft/deleteBatchDeliveredHistory',
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
 * 列表接口
 * @param params
 */
export const mintHistoryList = (params) => {
  if(params['nftId']){
    return defHttp.get({url: Api.mintHistoryList, params});
  }
  return Promise.resolve({});
}


/**
 * 删除单个
 */
export const mintHistoryDelete = (params,handleSuccess) => {
  return defHttp.delete({url: Api.mintHistoryDelete, params}, {joinParamsToUrl: true}).then(() => {
    handleSuccess();
  });
}
/**
 * 批量删除
 * @param params
 */
export const mintHistoryDeleteBatch = (params, handleSuccess) => {
  createConfirm({
    iconType: 'warning',
    title: '确认删除',
    content: '是否删除选中数据',
    okText: '确认',
    cancelText: '取消',
    onOk: () => {
      return defHttp.delete({url: Api.mintHistoryDeleteBatch, data: params}, {joinParamsToUrl: true}).then(() => {
        handleSuccess();
      });
    }
  });
}
/**
 * 保存或者更新
 * @param params
 */
export const  mintHistorySaveOrUpdate = (params, isUpdate) => {
  let url = isUpdate ? Api.mintHistoryEdit : Api.mintHistorySave;
  return defHttp.post({url: url, params});
}
/**
 * 导入
 */
export const mintHistoryImportUrl = '/amnft/nft/importMintHistory'

/**
 * 导出
 */
export const mintHistoryExportXlsUrl = '/amnft/nft/exportMintHistory'
/**
 * 列表接口
 * @param params
 */
export const deliveredHistoryList = (params) => {
  if(params['nftId']){
    return defHttp.get({url: Api.deliveredHistoryList, params});
  }
  return Promise.resolve({});
}


/**
 * 删除单个
 */
export const deliveredHistoryDelete = (params,handleSuccess) => {
  return defHttp.delete({url: Api.deliveredHistoryDelete, params}, {joinParamsToUrl: true}).then(() => {
    handleSuccess();
  });
}
/**
 * 批量删除
 * @param params
 */
export const deliveredHistoryDeleteBatch = (params, handleSuccess) => {
  createConfirm({
    iconType: 'warning',
    title: '确认删除',
    content: '是否删除选中数据',
    okText: '确认',
    cancelText: '取消',
    onOk: () => {
      return defHttp.delete({url: Api.deliveredHistoryDeleteBatch, data: params}, {joinParamsToUrl: true}).then(() => {
        handleSuccess();
      });
    }
  });
}
/**
 * 保存或者更新
 * @param params
 */
export const  deliveredHistorySaveOrUpdate = (params, isUpdate) => {
  let url = isUpdate ? Api.deliveredHistoryEdit : Api.deliveredHistorySave;
  return defHttp.post({url: url, params});
}
/**
 * 导入
 */
export const deliveredHistoryImportUrl = '/amnft/nft/importDeliveredHistory'

/**
 * 导出
 */
export const deliveredHistoryExportXlsUrl = '/amnft/nft/exportDeliveredHistory'
