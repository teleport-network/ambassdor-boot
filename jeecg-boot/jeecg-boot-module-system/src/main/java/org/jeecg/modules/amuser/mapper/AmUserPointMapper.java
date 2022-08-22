package org.jeecg.modules.amuser.mapper;

import java.util.List;
import org.jeecg.modules.amuser.entity.AmUserPoint;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: points list
 * @Author: jeecg-boot
 * @Date:   2022-08-18
 * @Version: V1.0
 */
public interface AmUserPointMapper extends BaseMapper<AmUserPoint> {

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
    * @return List<AmUserPoint>
    */
	public List<AmUserPoint> selectByMainId(@Param("mainId") String mainId);

}
