package org.jeecg.modules.amquest.service;

import org.jeecg.modules.amquest.entity.ActionDef;
import org.jeecg.modules.amquest.entity.Quest;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: quest
 * @Author: jeecg-boot
 * @Date:   2022-08-10
 * @Version: V1.0
 */
public interface IQuestService extends IService<Quest> {

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
