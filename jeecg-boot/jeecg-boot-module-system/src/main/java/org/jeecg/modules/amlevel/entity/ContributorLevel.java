package org.jeecg.modules.amlevel.entity;

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
 * @Description: contributor level
 * @Author: jeecg-boot
 * @Date:   2022-08-22
 * @Version: V1.0
 */
@Data
@TableName("contributor_level")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="contributor_level对象", description="contributor level")
public class ContributorLevel implements Serializable {
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
	/**name*/
	@Excel(name = "name", width = 15)
    @ApiModelProperty(value = "name")
    private java.lang.String name;
	/**Points for Level-Up*/
	@Excel(name = "Points for Level-Up", width = 15)
    @ApiModelProperty(value = "Points for Level-Up")
    private java.lang.Integer points;
	/**Token Allocation*/
	@Excel(name = "Token Allocation", width = 15)
    @ApiModelProperty(value = "Token Allocation")
    private java.lang.Integer token;
	/**Level Index*/
	@Excel(name = "Level Index", width = 15)
    @ApiModelProperty(value = "Level Index")
    private java.lang.Integer lvlIndex;
	/**NFT Allocation*/
	@Excel(name = "NFT Allocation", width = 15)
    @ApiModelProperty(value = "NFT Allocation")
    private java.lang.String nft;
}
