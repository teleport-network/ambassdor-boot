package org.jeecg.modules.amnft.vo;

import java.util.List;
import org.jeecg.modules.amnft.entity.Nft;
import org.jeecg.modules.amnft.entity.MintHistory;
import org.jeecg.modules.amnft.entity.DeliveredHistory;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: nft
 * @Author: jeecg-boot
 * @Date:   2022-08-01
 * @Version: V1.0
 */
@Data
@ApiModel(value="nftPage对象", description="nft")
public class NftPage {

	/**主键*/
	@ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
	@ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
	@ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**image*/
	@Excel(name = "image", width = 15)
	@ApiModelProperty(value = "image")
    private java.lang.String image;
	/**name*/
	@Excel(name = "name", width = 15)
	@ApiModelProperty(value = "name")
    private java.lang.String name;
	/**type*/
	@Excel(name = "type", width = 15)
	@ApiModelProperty(value = "type")
    private java.lang.String type;
	/**desc*/
	@Excel(name = "desc", width = 15)
	@ApiModelProperty(value = "desc")
    private java.lang.String description;
	/**address*/
	@Excel(name = "address", width = 15)
	@ApiModelProperty(value = "address")
    private java.lang.String address;
	/**total*/
	@Excel(name = "total", width = 15)
	@ApiModelProperty(value = "total")
    private java.lang.Integer total;
	/**inventory*/
	@Excel(name = "inventory", width = 15)
	@ApiModelProperty(value = "inventory")
    private java.lang.Integer inventory;
	/**delivered quantity*/
	@Excel(name = "delivered quantity", width = 15)
	@ApiModelProperty(value = "delivered quantity")
    private java.lang.Integer delivered;
	/**reuest quantity*/
	@Excel(name = "reuest quantity", width = 15)
	@ApiModelProperty(value = "reuest quantity")
    private java.lang.Integer txRequestNum;
	/**mint*/
	@Excel(name = "mint", width = 15)
	@ApiModelProperty(value = "mint")
    private java.lang.String mintHistoryString;

	@ExcelCollection(name="mint history")
	@ApiModelProperty(value = "mint history")
	private List<MintHistory> mintHistoryList;
	@ExcelCollection(name="delivered history")
	@ApiModelProperty(value = "delivered history")
	private List<DeliveredHistory> deliveredHistoryList;

}
