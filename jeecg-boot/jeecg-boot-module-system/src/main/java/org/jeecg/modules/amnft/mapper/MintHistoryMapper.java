package org.jeecg.modules.amnft.mapper;

import java.util.List;
import org.jeecg.modules.amnft.entity.MintHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: mint history
 * @Author: jeecg-boot
 * @Date:   2022-08-01
 * @Version: V1.0
 */
public interface MintHistoryMapper extends BaseMapper<MintHistory> {

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
    * @return List<MintHistory>
    */
	public List<MintHistory> selectByMainId(@Param("mainId") String mainId);

}
