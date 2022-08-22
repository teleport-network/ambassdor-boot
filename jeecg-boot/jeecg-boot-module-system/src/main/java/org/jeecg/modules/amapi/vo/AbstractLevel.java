package org.jeecg.modules.amapi.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

@Data
@Builder
public class AbstractLevel {
    private String name;
    /**Minimal Points for this Level*/
    private Integer pointsMin;
    /**Maximal Points for this Level*/
    private Integer pointsMax;
    /**Level Index*/
    private Integer lvlIndex;
}
