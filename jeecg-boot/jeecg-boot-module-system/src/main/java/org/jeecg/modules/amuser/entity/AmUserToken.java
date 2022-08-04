package org.jeecg.modules.amuser.entity;

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
 * @Description: token list
 * @Author: jeecg-boot
 * @Date:   2022-08-01
 * @Version: V1.0
 */
@Data
@TableName("am_user_token")
@ApiModel(value="am_user_token对象", description="token list")
public class AmUserToken implements Serializable {
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
	/**发放数量*/
	@Excel(name = "发放数量", width = 15)
    @ApiModelProperty(value = "发放数量")
    private java.math.BigDecimal amount;
	/**发放原因*/
	@Excel(name = "发放原因", width = 15)
    @ApiModelProperty(value = "发放原因")
    private java.lang.String issueBy;
	/**发放时间*/
	@Excel(name = "发放时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "发放时间")
    private java.util.Date issueDate;
	/**锁定时间*/
	@Excel(name = "锁定时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "锁定时间")
    private java.util.Date lockTime;
	/**主表FK*/
    @ApiModelProperty(value = "主表FK")
    private java.lang.String ambassadorUserId;
}
