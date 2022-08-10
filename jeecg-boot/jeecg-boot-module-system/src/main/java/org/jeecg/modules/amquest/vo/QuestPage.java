package org.jeecg.modules.amquest.vo;

import java.util.List;
import org.jeecg.modules.amquest.entity.Quest;
import org.jeecg.modules.amquest.entity.ActionDef;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: quest
 * @Author: jeecg-boot
 * @Date:   2022-08-09
 * @Version: V1.0
 */
@Data
@ApiModel(value="questPage对象", description="quest")
public class QuestPage {

	/**主键*/
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
	/**title*/
	@Excel(name = "title", width = 15)
	@ApiModelProperty(value = "title")
    private java.lang.String title;
	/**quest link*/
	@Excel(name = "quest link", width = 15)
	@ApiModelProperty(value = "quest link")
    private java.lang.String url;
	/**rewards points*/
	@Excel(name = "rewards points", width = 15)
	@ApiModelProperty(value = "rewards points")
    private java.lang.Integer rewards;
	/**type*/
	@Excel(name = "type", width = 15)
	@ApiModelProperty(value = "type")
    private java.lang.String type;
	/**image*/
	@Excel(name = "image", width = 15)
	@ApiModelProperty(value = "image")
    private java.lang.String image;
	/**quest date*/
	@Excel(name = "quest date", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "quest date")
    private java.util.Date issueDate;
	/**queat duration*/
	@Excel(name = "queat duration", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "queat duration")
    private java.util.Date deadline;
	/**last sync time*/
	@Excel(name = "last sync time", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "last sync time")
    private java.util.Date syncTime;
	/**create time in gleam*/
	@Excel(name = "create time in gleam", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "create time in gleam")
    private java.util.Date gleamCreateAt;
	/**fraud_type*/
	@Excel(name = "fraud_type", width = 15)
	@ApiModelProperty(value = "fraud_type")
    private java.lang.String fraudType;
	
	@ExcelCollection(name="quest def")
	@ApiModelProperty(value = "quest def")
	private List<ActionDef> actionDefList;
	
}
