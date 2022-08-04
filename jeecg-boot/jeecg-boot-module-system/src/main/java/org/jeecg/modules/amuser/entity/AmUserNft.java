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
 * @Description: nft list
 * @Author: jeecg-boot
 * @Date:   2022-08-01
 * @Version: V1.0
 */
@Data
@TableName("am_user_nft")
@ApiModel(value="am_user_nft对象", description="nft list")
public class AmUserNft implements Serializable {
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
	/**NFT拥有种类*/
	@Excel(name = "NFT拥有种类", width = 15)
    @ApiModelProperty(value = "NFT拥有种类")
    private java.lang.String nftType;
	/**发放原因*/
	@Excel(name = "发放原因", width = 15)
    @ApiModelProperty(value = "发放原因")
    private java.lang.String issueBy;
	/**发放时间*/
	@Excel(name = "发放时间", width = 15)
    @ApiModelProperty(value = "发放时间")
    private java.lang.String issueTime;
	/**铸造请求*/
	@Excel(name = "铸造请求", width = 15)
    @ApiModelProperty(value = "铸造请求")
    private java.lang.String mintRequest;
	/**NFT合约地址*/
	@Excel(name = "NFT合约地址", width = 15)
    @ApiModelProperty(value = "NFT合约地址")
    private java.lang.String address;
	/**交易hash*/
	@Excel(name = "交易hash", width = 15)
    @ApiModelProperty(value = "交易hash")
    private java.lang.String txHash;
	/**图片url*/
	@Excel(name = "图片url", width = 15)
    @ApiModelProperty(value = "图片url")
    private java.lang.String url;
	/**FK*/
    @ApiModelProperty(value = "FK")
    private java.lang.String ambassadorUserId;
}
