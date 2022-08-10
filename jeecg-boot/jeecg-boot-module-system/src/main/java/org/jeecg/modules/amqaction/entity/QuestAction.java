package org.jeecg.modules.amqaction.entity;

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
 * @Description: user action in quest
 * @Author: jeecg-boot
 * @Date:   2022-08-08
 * @Version: V1.0
 */
@Data
@TableName("quest_action")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="quest_action对象", description="user action in quest")
public class QuestAction implements Serializable {
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
	/**quest url*/
	@Excel(name = "quest url", width = 15)
    @ApiModelProperty(value = "quest url")
    private java.lang.String landingUrl;
	/**name*/
	@Excel(name = "name", width = 15)
    @ApiModelProperty(value = "name")
    private java.lang.String name;
	/**email*/
	@Excel(name = "email", width = 15)
    @ApiModelProperty(value = "email")
    private java.lang.String email;
	/**status*/
	@Excel(name = "status", width = 15)
    @ApiModelProperty(value = "status")
    private java.lang.String status;
	/**action id*/
	@Excel(name = "action id", width = 15)
    @ApiModelProperty(value = "action id")
    private java.lang.Integer actionId;
	/**action type*/
	@Excel(name = "action type", width = 15)
    @ApiModelProperty(value = "action type")
    private java.lang.String actionType;
	/**action config*/
	@Excel(name = "action config", width = 15)
    @ApiModelProperty(value = "action config")
    private java.lang.String actionConfig;
	/**last sync time*/
	@Excel(name = "last sync time", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "last sync time")
    private java.util.Date syncTime;
}
