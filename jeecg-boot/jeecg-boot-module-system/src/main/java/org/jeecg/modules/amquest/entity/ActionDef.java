package org.jeecg.modules.amquest.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.jeecg.common.aspect.annotation.Dict;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.UnsupportedEncodingException;

/**
 * @Description: quest def
 * @Author: jeecg-boot
 * @Date:   2022-08-10
 * @Version: V1.0
 */
@Data
@TableName("action_def")
@ApiModel(value="action_def对象", description="quest def")
public class ActionDef implements Serializable {
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
	/**type*/
	@Excel(name = "type", width = 15)
    @ApiModelProperty(value = "type")
    private java.lang.String type;
	/**worth*/
	@Excel(name = "worth", width = 15)
    @ApiModelProperty(value = "worth")
    private java.lang.Integer worth;
	/**reward*/
	@Excel(name = "reward", width = 15)
    @ApiModelProperty(value = "reward")
    private java.lang.Integer reward;
	/**mandatory*/
	@Excel(name = "mandatory", width = 15)
    @ApiModelProperty(value = "mandatory")
    private java.lang.String mandatory;
	/**config1*/
	@Excel(name = "config1", width = 15)
    @ApiModelProperty(value = "config1")
    private java.lang.String config1;
	/**config2*/
	@Excel(name = "config2", width = 15)
    @ApiModelProperty(value = "config2")
    private java.lang.String config2;
	/**config3*/
	@Excel(name = "config3", width = 15)
    @ApiModelProperty(value = "config3")
    private java.lang.String config3;
	/**config4*/
	@Excel(name = "config4", width = 15)
    @ApiModelProperty(value = "config4")
    private java.lang.String config4;
	/**config5*/
	@Excel(name = "config5", width = 15)
    @ApiModelProperty(value = "config5")
    private java.lang.String config5;
	/**config6*/
	@Excel(name = "config6", width = 15)
    @ApiModelProperty(value = "config6")
    private java.lang.String config6;
	/**config7*/
	@Excel(name = "config7", width = 15)
    @ApiModelProperty(value = "config7")
    private java.lang.String config7;
	/**config8*/
	@Excel(name = "config8", width = 15)
    @ApiModelProperty(value = "config8")
    private java.lang.String config8;
	/**config9*/
	@Excel(name = "config9", width = 15)
    @ApiModelProperty(value = "config9")
    private java.lang.String config9;
	/**original*/
	@Excel(name = "original", width = 15)
    @ApiModelProperty(value = "original")
    private java.lang.String original;
	/**image*/
	@Excel(name = "image", width = 15)
    @ApiModelProperty(value = "image")
    private java.lang.String image;
	/**paid*/
	@Excel(name = "paid", width = 15)
    @ApiModelProperty(value = "paid")
    private java.lang.String paid;
	/**config_toggle*/
	@Excel(name = "config_toggle", width = 15)
    @ApiModelProperty(value = "config_toggle")
    private java.lang.String configToggle;
	/**actions_required*/
	@Excel(name = "actions_required", width = 15)
    @ApiModelProperty(value = "actions_required")
    private java.lang.Integer actionsRequired;
	/**quest fk*/
    @ApiModelProperty(value = "quest fk")
    private java.lang.String questFk;
}
