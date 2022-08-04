package org.jeecg.modules.amnft.service;

import org.jeecg.modules.amnft.entity.MintHistory;
import org.jeecg.modules.amnft.entity.DeliveredHistory;
import org.jeecg.modules.amnft.entity.Nft;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: nft
 * @Author: jeecg-boot
 * @Date:   2022-08-01
 * @Version: V1.0
 */
public interface INftService extends IService<Nft> {

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
