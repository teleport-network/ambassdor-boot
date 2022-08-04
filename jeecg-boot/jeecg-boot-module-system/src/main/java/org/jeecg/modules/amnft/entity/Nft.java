package org.jeecg.modules.amnft.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.jeecgframework.poi.excel.annotation.Excel;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
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
@TableName("nft")
@ApiModel(value="nft对象", description="nft")
public class Nft implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
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
    private transient java.lang.String mintHistoryString;

    private byte[] mintHistory;

    public byte[] getMintHistory(){
        if(mintHistoryString==null){
            return null;
        }
        try {
            return mintHistoryString.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getMintHistoryString(){
        if(mintHistory==null || mintHistory.length==0){
            return "";
        }
        try {
            return new String(mintHistory,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
