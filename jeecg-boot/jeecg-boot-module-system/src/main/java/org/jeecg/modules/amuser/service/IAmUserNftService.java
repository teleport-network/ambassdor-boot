package org.jeecg.modules.amuser.service;

import org.jeecg.modules.amuser.entity.AmUserNft;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: nft list
 * @Author: jeecg-boot
 * @Date:   2022-08-01
 * @Version: V1.0
 */
public interface IAmUserNftService extends IService<AmUserNft> {

  /**
   * 通过主表id查询子表数据
   *
   * @param mainId
   * @return List<AmUserNft>
   */
	public List<AmUserNft> selectByMainId(String mainId);
}
