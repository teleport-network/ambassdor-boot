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
 * @Description: mint history
 * @Author: jeecg-boot
 * @Date:   2022-08-01
 * @Version: V1.0
 */
@Data
@TableName("mint_history")
@ApiModel(value="mint_history对象", description="mint history")
public class MintHistory implements Serializable {
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
	/**contract address*/
	@Excel(name = "contract address", width = 15)
    @ApiModelProperty(value = "contract address")
    private java.lang.String contractAddress;
	/**mint account*/
	@Excel(name = "mint account", width = 15)
    @ApiModelProperty(value = "mint account")
    private java.lang.String mintAccount;
	/**contract_in_binary*/
	@Excel(name = "contract_in_binary", width = 15)
    @ApiModelProperty(value = "contract_in_binary")
    private java.lang.String contractInBinary;
	/**tx hash*/
	@Excel(name = "tx hash", width = 15)
    @ApiModelProperty(value = "tx hash")
    private java.lang.String txHash;
	/**nft fk*/
    @ApiModelProperty(value = "nft fk")
    private java.lang.String nftId;
}
