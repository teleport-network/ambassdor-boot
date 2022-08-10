package org.jeecg.modules.amnft.service;

import org.jeecg.modules.amnft.entity.DeliveredHistory;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: delivered history
 * @Author: jeecg-boot
 * @Date:   2022-08-10
 * @Version: V1.0
 */
public interface IDeliveredHistoryService extends IService<DeliveredHistory> {

  /**
   * 通过主表id查询子表数据
   *
   * @param mainId
   * @return List<DeliveredHistory>
   */
	public List<DeliveredHistory> selectByMainId(String mainId);
}
