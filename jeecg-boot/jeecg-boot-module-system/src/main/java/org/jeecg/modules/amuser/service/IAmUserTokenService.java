package org.jeecg.modules.amuser.service;

import org.jeecg.modules.amuser.entity.AmUserToken;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: token list
 * @Author: jeecg-boot
 * @Date:   2022-08-01
 * @Version: V1.0
 */
public interface IAmUserTokenService extends IService<AmUserToken> {

  /**
   * 通过主表id查询子表数据
   *
   * @param mainId
   * @return List<AmUserToken>
   */
	public List<AmUserToken> selectByMainId(String mainId);
}
