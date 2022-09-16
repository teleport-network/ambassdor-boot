package org.jeecg.modules.amnft.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: nft
 * @Author: jeecg-boot
 * @Date:   2022-09-16
 * @Version: V1.0
 */
@Data
@TableName("nft")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="nft对象", description="nft")
public class Nft implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**Name*/
	@Excel(name = "Name", width = 15)
    @ApiModelProperty(value = "Name")
    private java.lang.String name;
	/**Total*/
	@Excel(name = "Total", width = 15)
    @ApiModelProperty(value = "Total")
    private java.lang.Integer total;
	/**TokenId Start*/
	@Excel(name = "TokenId Start", width = 15)
    @ApiModelProperty(value = "TokenId Start")
    private java.lang.Integer startIndex;
	/**Next TokenId*/
	@Excel(name = "Next TokenId", width = 15)
    @ApiModelProperty(value = "Next TokenId")
    private java.lang.Integer nextIndex;
	/**TokenId End*/
	@Excel(name = "TokenId End", width = 15)
    @ApiModelProperty(value = "TokenId End")
    private java.lang.Integer endIndex;
	/**Inventory*/
	@Excel(name = "Inventory", width = 15)
    @ApiModelProperty(value = "Inventory")
    private java.lang.Integer inventory;
	/**Delivered Quantity*/
	@Excel(name = "Delivered Quantity", width = 15)
    @ApiModelProperty(value = "Delivered Quantity")
    private java.lang.Integer delivered;
	/**Type*/
	@Excel(name = "Type", width = 15)
    @ApiModelProperty(value = "Type")
    private java.lang.String type;
	/**Ipfs Url*/
	@Excel(name = "Ipfs Url", width = 15)
    @ApiModelProperty(value = "Ipfs Url")
    private java.lang.String ipfs;
	/**Contract Address*/
	@Excel(name = "Contract Address", width = 15)
    @ApiModelProperty(value = "Contract Address")
    private java.lang.String contractAddress;
	/**Image*/
	@Excel(name = "Image", width = 15)
    @ApiModelProperty(value = "Image")
    private java.lang.String image;
	/**Desc*/
	@Excel(name = "Desc", width = 15)
    @ApiModelProperty(value = "Desc")
    private java.lang.String description;
}
