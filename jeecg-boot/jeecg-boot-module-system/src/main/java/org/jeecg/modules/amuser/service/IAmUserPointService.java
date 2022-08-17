package org.jeecg.modules.amuser.service;

import org.jeecg.modules.amuser.entity.AmUserPoint;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: points list
 * @Author: jeecg-boot
 * @Date:   2022-08-12
 * @Version: V1.0
 */
public interface IAmUserPointService extends IService<AmUserPoint> {

  /**
   * 通过主表id查询子表数据
   *
   * @param mainId
   * @return List<AmUserPoint>
   */
	public List<AmUserPoint> selectByMainId(String mainId);
}
