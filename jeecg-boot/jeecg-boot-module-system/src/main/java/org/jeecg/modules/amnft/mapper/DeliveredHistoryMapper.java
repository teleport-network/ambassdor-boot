package org.jeecg.modules.amnft.mapper;

import java.util.List;
import org.jeecg.modules.amnft.entity.DeliveredHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: delivered history
 * @Author: jeecg-boot
 * @Date:   2022-08-01
 * @Version: V1.0
 */
public interface DeliveredHistoryMapper extends BaseMapper<DeliveredHistory> {

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
    * @return List<DeliveredHistory>
    */
	public List<DeliveredHistory> selectByMainId(@Param("mainId") String mainId);

}
