package org.jeecg.modules.amnft.entity;

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
 * @Description: delivered history
 * @Author: jeecg-boot
 * @Date:   2022-08-10
 * @Version: V1.0
 */
@Data
@TableName("delivered_history")
@ApiModel(value="delivered_history对象", description="delivered history")
public class DeliveredHistory implements Serializable {
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
	/**delivered time*/
	@Excel(name = "delivered time", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "delivered time")
    private java.util.Date deliveredTime;
	/**from*/
	@Excel(name = "from", width = 15)
    @ApiModelProperty(value = "from")
    private java.lang.String fromAddress;
	/**to*/
	@Excel(name = "to", width = 15)
    @ApiModelProperty(value = "to")
    private java.lang.String toAddress;
	/**tx hash*/
	@Excel(name = "tx hash", width = 15)
    @ApiModelProperty(value = "tx hash")
    private java.lang.String txHash;
	/**url*/
	@Excel(name = "url", width = 15)
    @ApiModelProperty(value = "url")
    private java.lang.String scanUrl;
	/**nft fk*/
    @ApiModelProperty(value = "nft fk")
    private java.lang.String nftId;
}
