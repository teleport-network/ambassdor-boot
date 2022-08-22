package org.jeecg.modules.amuser.entity;

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
 * @Description: ambassador user list
 * @Author: jeecg-boot
 * @Date:   2022-08-18
 * @Version: V1.0
 */
@Data
@TableName("ambassador_user")
@ApiModel(value="ambassador_user对象", description="ambassador user list")
public class AmbassadorUser implements Serializable {
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
	/**evm address*/
    @Excel(name = "evm address", width = 15)
    @ApiModelProperty(value = "evm address")
    private java.lang.String address;
	/**邮箱*/
    @Excel(name = "邮箱", width = 15)
    @ApiModelProperty(value = "邮箱")
    private java.lang.String email;
	/**用户名*/
    @Excel(name = "用户名", width = 15)
    @ApiModelProperty(value = "用户名")
    private java.lang.String username;
	/**头像*/
    @Excel(name = "头像", width = 15)
    @ApiModelProperty(value = "头像")
    private java.lang.String avatar;
	/**角色*/
    @Excel(name = "角色", width = 15, dicCode = "user_role")
    @Dict(dicCode = "user_role")
    @ApiModelProperty(value = "角色")
    private java.lang.String role;
	/**国家/地区*/
    @Excel(name = "国家/地区", width = 15)
    @ApiModelProperty(value = "国家/地区")
    private java.lang.String location;
	/**当前token数量*/
    @Excel(name = "当前token数量", width = 15)
    @ApiModelProperty(value = "当前token数量")
    private java.math.BigDecimal token;
	/**当前points数量*/
    @Excel(name = "当前points数量", width = 15)
    @ApiModelProperty(value = "当前points数量")
    private java.math.BigDecimal point;
	/**当前未收集的points*/
    @Excel(name = "当前未收集的points", width = 15)
    @ApiModelProperty(value = "当前未收集的points")
    private java.math.BigDecimal pointCache;
	/**当前等级*/
    @Excel(name = "当前等级", width = 15, dicCode = "user_level")
    @Dict(dicCode = "user_level")
    @ApiModelProperty(value = "当前等级")
    private java.lang.String level;
	/**discord id*/
    @Excel(name = "discord id", width = 15)
    @ApiModelProperty(value = "discord id")
    private java.lang.String discordId;
	/**twitter id*/
    @Excel(name = "twitter id", width = 15)
    @ApiModelProperty(value = "twitter id")
    private java.lang.String twitterId;
	/**telegram id*/
    @Excel(name = "telegram id", width = 15)
    @ApiModelProperty(value = "telegram id")
    private java.lang.String telegramId;
}
