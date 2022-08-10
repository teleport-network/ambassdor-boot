package org.jeecg.modules.amquest.service;

import org.jeecg.modules.amquest.entity.ActionDef;
import org.jeecg.modules.amquest.entity.Quest;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: quest
 * @Author: jeecg-boot
 * @Date:   2022-08-09
 * @Version: V1.0
 */
public interface IQuestService extends IService<Quest> {

	/**
	 * 添加一对多
	 *
	 * @param quest
	 * @param actionDefList
	 */
	public void saveMain(Quest quest,List<ActionDef> actionDefList) ;
	
	/**
	 * 修改一对多
	 *
	 * @param quest
	 * @param actionDefList
	 */
	public void updateMain(Quest quest,List<ActionDef> actionDefList);
	
	/**
	 * 删除一对多
	 *
	 * @param id
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 *
	 * @param idList
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
