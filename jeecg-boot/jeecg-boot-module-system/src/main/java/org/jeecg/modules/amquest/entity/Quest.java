package org.jeecg.modules.amquest.entity;

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
 * @Description: quest
 * @Author: jeecg-boot
 * @Date:   2022-08-03
 * @Version: V1.0
 */
@Data
@TableName("quest")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
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
	@Excel(name = "type", width = 15)
    @ApiModelProperty(value = "type")
    private java.lang.String type;
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
}
