import {defHttp} from '/@/utils/http/axios';
import { useMessage } from "/@/hooks/web/useMessage";

const { createConfirm } = useMessage();

enum Api {
  list = '/amuser/ambassadorUser/list',
  save='/amuser/ambassadorUser/add',
  edit='/amuser/ambassadorUser/edit',
  deleteOne = '/amuser/ambassadorUser/delete',
  deleteBatch = '/amuser/ambassadorUser/deleteBatch',
  importExcel = '/amuser/ambassadorUser/importExcel',
  exportXls = '/amuser/ambassadorUser/exportXls',
  amUserPointList = '/amuser/ambassadorUser/listAmUserPointByMainId',
  amUserPointSave='/amuser/ambassadorUser/addAmUserPoint',
  amUserPointEdit='/amuser/ambassadorUser/editAmUserPoint',
  amUserPointDelete = '/amuser/ambassadorUser/deleteAmUserPoint',
  amUserPointDeleteBatch = '/amuser/ambassadorUser/deleteBatchAmUserPoint',
  amUserTokenList = '/amuser/ambassadorUser/listAmUserTokenByMainId',
  amUserTokenSave='/amuser/ambassadorUser/addAmUserToken',
  amUserTokenEdit='/amuser/ambassadorUser/editAmUserToken',
  amUserTokenDelete = '/amuser/ambassadorUser/deleteAmUserToken',
  amUserTokenDeleteBatch = '/amuser/ambassadorUser/deleteBatchAmUserToken',
  amUserNftList = '/amuser/ambassadorUser/listAmUserNftByMainId',
  amUserNftSave='/amuser/ambassadorUser/addAmUserNft',
  amUserNftEdit='/amuser/ambassadorUser/editAmUserNft',
  amUserNftDelete = '/amuser/ambassadorUser/deleteAmUserNft',
  amUserNftDeleteBatch = '/amuser/ambassadorUser/deleteBatchAmUserNft',
  amUserQuestList = '/amuser/ambassadorUser/listAmUserQuestByMainId',
  amUserQuestSave='/amuser/ambassadorUser/addAmUserQuest',
  amUserQuestEdit='/amuser/ambassadorUser/editAmUserQuest',
  amUserQuestDelete = '/amuser/ambassadorUser/deleteAmUserQuest',
  amUserQuestDeleteBatch = '/amuser/ambassadorUser/deleteBatchAmUserQuest',
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
export const amUserPointList = (params) => {
  if(params['ambassadorUserId']){
    return defHttp.get({url: Api.amUserPointList, params});
  }
  return Promise.resolve({});
}


/**
 * 删除单个
 */
export const amUserPointDelete = (params,handleSuccess) => {
  return defHttp.delete({url: Api.amUserPointDelete, params}, {joinParamsToUrl: true}).then(() => {
    handleSuccess();
  });
}
/**
 * 批量删除
 * @param params
 */
export const amUserPointDeleteBatch = (params, handleSuccess) => {
  createConfirm({
    iconType: 'warning',
    title: '确认删除',
    content: '是否删除选中数据',
    okText: '确认',
    cancelText: '取消',
    onOk: () => {
      return defHttp.delete({url: Api.amUserPointDeleteBatch, data: params}, {joinParamsToUrl: true}).then(() => {
        handleSuccess();
      });
    }
  });
}
/**
 * 保存或者更新
 * @param params
 */
export const  amUserPointSaveOrUpdate = (params, isUpdate) => {
  let url = isUpdate ? Api.amUserPointEdit : Api.amUserPointSave;
  return defHttp.post({url: url, params});
}
/**
 * 导入
 */
export const amUserPointImportUrl = '/amuser/ambassadorUser/importAmUserPoint'

/**
 * 导出
 */
export const amUserPointExportXlsUrl = '/amuser/ambassadorUser/exportAmUserPoint'
/**
 * 列表接口
 * @param params
 */
export const amUserTokenList = (params) => {
  if(params['ambassadorUserId']){
    return defHttp.get({url: Api.amUserTokenList, params});
  }
  return Promise.resolve({});
}


/**
 * 删除单个
 */
export const amUserTokenDelete = (params,handleSuccess) => {
  return defHttp.delete({url: Api.amUserTokenDelete, params}, {joinParamsToUrl: true}).then(() => {
    handleSuccess();
  });
}
/**
 * 批量删除
 * @param params
 */
export const amUserTokenDeleteBatch = (params, handleSuccess) => {
  createConfirm({
    iconType: 'warning',
    title: '确认删除',
    content: '是否删除选中数据',
    okText: '确认',
    cancelText: '取消',
    onOk: () => {
      return defHttp.delete({url: Api.amUserTokenDeleteBatch, data: params}, {joinParamsToUrl: true}).then(() => {
        handleSuccess();
      });
    }
  });
}
/**
 * 保存或者更新
 * @param params
 */
export const  amUserTokenSaveOrUpdate = (params, isUpdate) => {
  let url = isUpdate ? Api.amUserTokenEdit : Api.amUserTokenSave;
  return defHttp.post({url: url, params});
}
/**
 * 导入
 */
export const amUserTokenImportUrl = '/amuser/ambassadorUser/importAmUserToken'

/**
 * 导出
 */
export const amUserTokenExportXlsUrl = '/amuser/ambassadorUser/exportAmUserToken'
/**
 * 列表接口
 * @param params
 */
export const amUserNftList = (params) => {
  if(params['ambassadorUserId']){
    return defHttp.get({url: Api.amUserNftList, params});
  }
  return Promise.resolve({});
}


/**
 * 删除单个
 */
export const amUserNftDelete = (params,handleSuccess) => {
  return defHttp.delete({url: Api.amUserNftDelete, params}, {joinParamsToUrl: true}).then(() => {
    handleSuccess();
  });
}
/**
 * 批量删除
 * @param params
 */
export const amUserNftDeleteBatch = (params, handleSuccess) => {
  createConfirm({
    iconType: 'warning',
    title: '确认删除',
    content: '是否删除选中数据',
    okText: '确认',
    cancelText: '取消',
    onOk: () => {
      return defHttp.delete({url: Api.amUserNftDeleteBatch, data: params}, {joinParamsToUrl: true}).then(() => {
        handleSuccess();
      });
    }
  });
}
/**
 * 保存或者更新
 * @param params
 */
export const  amUserNftSaveOrUpdate = (params, isUpdate) => {
  let url = isUpdate ? Api.amUserNftEdit : Api.amUserNftSave;
  return defHttp.post({url: url, params});
}
/**
 * 导入
 */
export const amUserNftImportUrl = '/amuser/ambassadorUser/importAmUserNft'

/**
 * 导出
 */
export const amUserNftExportXlsUrl = '/amuser/ambassadorUser/exportAmUserNft'
/**
 * 列表接口
 * @param params
 */
export const amUserQuestList = (params) => {
  if(params['ambassadorUserId']){
    return defHttp.get({url: Api.amUserQuestList, params});
  }
  return Promise.resolve({});
}


/**
 * 删除单个
 */
export const amUserQuestDelete = (params,handleSuccess) => {
  return defHttp.delete({url: Api.amUserQuestDelete, params}, {joinParamsToUrl: true}).then(() => {
    handleSuccess();
  });
}
/**
 * 批量删除
 * @param params
 */
export const amUserQuestDeleteBatch = (params, handleSuccess) => {
  createConfirm({
    iconType: 'warning',
    title: '确认删除',
    content: '是否删除选中数据',
    okText: '确认',
    cancelText: '取消',
    onOk: () => {
      return defHttp.delete({url: Api.amUserQuestDeleteBatch, data: params}, {joinParamsToUrl: true}).then(() => {
        handleSuccess();
      });
    }
  });
}
/**
 * 保存或者更新
 * @param params
 */
export const  amUserQuestSaveOrUpdate = (params, isUpdate) => {
  let url = isUpdate ? Api.amUserQuestEdit : Api.amUserQuestSave;
  return defHttp.post({url: url, params});
}
/**
 * 导入
 */
export const amUserQuestImportUrl = '/amuser/ambassadorUser/importAmUserQuest'

/**
 * 导出
 */
export const amUserQuestExportXlsUrl = '/amuser/ambassadorUser/exportAmUserQuest'
