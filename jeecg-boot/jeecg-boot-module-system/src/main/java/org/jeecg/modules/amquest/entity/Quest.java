package org.jeecg.modules.amquest.entity;

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
 * @Description: quest
 * @Author: jeecg-boot
 * @Date:   2022-09-01
 * @Version: V1.0
 */
@Data
@TableName("quest")
@ApiModel(value="quest对象", description="quest")
public class Quest implements Serializable {
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
	/**quest key*/
    @Excel(name = "quest key", width = 15)
    @ApiModelProperty(value = "quest key")
    private java.lang.String questKey;
	/**active*/
    @Excel(name = "active", width = 15)
    @ApiModelProperty(value = "active")
    private java.lang.String active;
	/**title*/
    @Excel(name = "title", width = 15)
    @ApiModelProperty(value = "title")
    private java.lang.String title;
	/**quest link*/
    @Excel(name = "quest link", width = 15)
    @ApiModelProperty(value = "quest link")
    private java.lang.String url;
	/**rewards points*/
    @Excel(name = "rewards points", width = 15)
    @ApiModelProperty(value = "rewards points")
    private java.lang.Integer rewards;
	/**type*/
    @Excel(name = "type", width = 15, dicCode = "quest_type")
    @Dict(dicCode = "quest_type")
    @ApiModelProperty(value = "type")
    private java.lang.String type;
	/**assign to*/
    @Excel(name = "assign to", width = 15, dicCode = "user_role")
    @Dict(dicCode = "user_role")
    @ApiModelProperty(value = "assign to")
    private java.lang.String assignTo;
	/**description*/
    @Excel(name = "description", width = 15)
    @ApiModelProperty(value = "description")
    private java.lang.String description;
	/**image*/
    @Excel(name = "image", width = 15)
    @ApiModelProperty(value = "image")
    private java.lang.String image;
	/**quest date*/
    @Excel(name = "quest date", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "quest date")
    private java.util.Date issueDate;
	/**queat duration*/
    @Excel(name = "queat duration", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "queat duration")
    private java.util.Date deadline;
	/**last sync time*/
    @Excel(name = "last sync time", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "last sync time")
    private java.util.Date syncTime;
	/**create time in gleam*/
    @Excel(name = "create time in gleam", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "create time in gleam")
    private java.util.Date gleamCreateAt;
	/**fraud_type*/
    @Excel(name = "fraud_type", width = 15)
    @ApiModelProperty(value = "fraud_type")
    private java.lang.String fraudType;
}
