package org.jeecg.modules.amactivity.entity;

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
 * @Description: admin activity
 * @Author: jeecg-boot
 * @Date:   2022-08-18
 * @Version: V1.0
 */
@Data
@TableName("admin_activity")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="admin_activity对象", description="admin activity")
public class AdminActivity implements Serializable {
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
	/**Title*/
	@Excel(name = "Title", width = 15)
    @ApiModelProperty(value = "Title")
    private java.lang.String title;
	/**Sender*/
	@Excel(name = "Sender", width = 15)
    @ApiModelProperty(value = "Sender")
    private java.lang.String sender;
	/**Send time*/
	@Excel(name = "Send time", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "Send time")
    private java.util.Date sendTime;
	/**Type*/
	@Excel(name = "Type", width = 15, dicCode = "activity_type")
	@Dict(dicCode = "activity_type")
    @ApiModelProperty(value = "Type")
    private java.lang.String type;
	/**Amount*/
	@Excel(name = "Amount", width = 15)
    @ApiModelProperty(value = "Amount")
    private java.lang.Double inputAmount;
	/**Quest*/
	@Excel(name = "Quest", width = 15)
    @ApiModelProperty(value = "Quest")
    private java.lang.String questRef;
	/**Action Id*/
	@Excel(name = "Action Id", width = 15)
    @ApiModelProperty(value = "Action Id")
    private java.lang.String actionRef;
	/**Status*/
	@Excel(name = "Status", width = 15, dicCode = "activity_status")
	@Dict(dicCode = "activity_status")
    @ApiModelProperty(value = "Status")
    private java.lang.String status;
}
