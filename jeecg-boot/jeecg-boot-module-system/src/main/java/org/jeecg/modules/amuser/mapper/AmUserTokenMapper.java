package org.jeecg.modules.amuser.mapper;

import java.util.List;
import org.jeecg.modules.amuser.entity.AmUserToken;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: token list
 * @Author: jeecg-boot
 * @Date:   2022-08-18
 * @Version: V1.0
 */
public interface AmUserTokenMapper extends BaseMapper<AmUserToken> {

	/**
	 * 通过主表id删除子表数据
	 *
	 * @param mainId 主表id
	 * @return boolean
	 */
	public boolean deleteByMainId(@Param("mainId") String mainId);

   /**
    * 通过主表id查询子表数据
    *
    * @param mainId 主表id
    * @return List<AmUserToken>
    */
	public List<AmUserToken> selectByMainId(@Param("mainId") String mainId);

}
