package org.jeecg.modules.amquest.mapper;

import java.util.List;
import org.jeecg.modules.amquest.entity.ActionDef;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: quest def
 * @Author: jeecg-boot
 * @Date:   2022-08-11
 * @Version: V1.0
 */
public interface ActionDefMapper extends BaseMapper<ActionDef> {

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
    * @return List<ActionDef>
    */
	public List<ActionDef> selectByMainId(@Param("mainId") String mainId);

}
