package org.jeecg.modules.amuser.service;

import org.jeecg.modules.amuser.entity.AmUserQuest;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: quest list
 * @Author: jeecg-boot
 * @Date:   2022-08-18
 * @Version: V1.0
 */
public interface IAmUserQuestService extends IService<AmUserQuest> {

  /**
   * 通过主表id查询子表数据
   *
   * @param mainId
   * @return List<AmUserQuest>
   */
	public List<AmUserQuest> selectByMainId(String mainId);
}
