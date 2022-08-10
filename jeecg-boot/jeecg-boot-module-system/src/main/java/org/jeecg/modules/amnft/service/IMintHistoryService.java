package org.jeecg.modules.amnft.service;

import org.jeecg.modules.amnft.entity.MintHistory;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: mint history
 * @Author: jeecg-boot
 * @Date:   2022-08-10
 * @Version: V1.0
 */
public interface IMintHistoryService extends IService<MintHistory> {

  /**
   * 通过主表id查询子表数据
   *
   * @param mainId
   * @return List<MintHistory>
   */
	public List<MintHistory> selectByMainId(String mainId);
}
