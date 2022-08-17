package org.jeecg.modules.amuser.mapper;

import java.util.List;
import org.jeecg.modules.amuser.entity.AmUserNft;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: nft list
 * @Author: jeecg-boot
 * @Date:   2022-08-12
 * @Version: V1.0
 */
public interface AmUserNftMapper extends BaseMapper<AmUserNft> {

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
    * @return List<AmUserNft>
    */
	public List<AmUserNft> selectByMainId(@Param("mainId") String mainId);

}
