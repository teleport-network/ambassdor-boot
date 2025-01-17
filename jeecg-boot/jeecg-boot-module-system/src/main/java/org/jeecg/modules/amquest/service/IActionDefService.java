package org.jeecg.modules.amquest.service;

import org.jeecg.modules.amquest.entity.ActionDef;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: quest def
 * @Author: jeecg-boot
 * @Date:   2022-09-01
 * @Version: V1.0
 */
public interface IActionDefService extends IService<ActionDef> {

  /**
   * 通过主表id查询子表数据
   *
   * @param mainId
   * @return List<ActionDef>
   */
	public List<ActionDef> selectByMainId(String mainId);
}
