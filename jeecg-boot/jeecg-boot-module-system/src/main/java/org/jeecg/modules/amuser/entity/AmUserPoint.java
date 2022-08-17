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
 * @Description: points list
 * @Author: jeecg-boot
 * @Date:   2022-08-12
 * @Version: V1.0
 */
@Data
@TableName("am_user_point")
@ApiModel(value="am_user_point对象", description="points list")
public class AmUserPoint implements Serializable {
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
    private java.lang.Integer amount;
	/**发放原因*/
	@Excel(name = "发放原因", width = 15)
    @ApiModelProperty(value = "发放原因")
    private java.lang.String issueBy;
	/**关联gleam任务*/
	@Excel(name = "关联gleam任务", width = 15)
    @ApiModelProperty(value = "关联gleam任务")
    private java.lang.String actionUrl;
	/**确认发放时间*/
	@Excel(name = "确认发放时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "确认发放时间")
    private java.util.Date confirmTime;
	/**ambassador_user*/
    @ApiModelProperty(value = "ambassador_user")
    private java.lang.String ambassadorUserId;
}
