package org.jeecg.modules.amuser.service;

import org.jeecg.modules.amuser.entity.AmUserPoint;
import org.jeecg.modules.amuser.entity.AmUserToken;
import org.jeecg.modules.amuser.entity.AmUserNft;
import org.jeecg.modules.amuser.entity.AmUserQuest;
import org.jeecg.modules.amuser.entity.AmbassadorUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: ambassador user list
 * @Author: jeecg-boot
 * @Date:   2022-08-01
 * @Version: V1.0
 */
public interface IAmbassadorUserService extends IService<AmbassadorUser> {

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
