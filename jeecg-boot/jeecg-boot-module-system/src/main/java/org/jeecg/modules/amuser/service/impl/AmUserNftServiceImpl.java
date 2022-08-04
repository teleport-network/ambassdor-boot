package org.jeecg.modules.amuser.service.impl;

import org.jeecg.modules.amuser.entity.AmUserNft;
import org.jeecg.modules.amuser.mapper.AmUserNftMapper;
import org.jeecg.modules.amuser.service.IAmUserNftService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: nft list
 * @Author: jeecg-boot
 * @Date:   2022-08-01
 * @Version: V1.0
 */
@Service
public class AmUserNftServiceImpl extends ServiceImpl<AmUserNftMapper, AmUserNft> implements IAmUserNftService {
	
	@Autowired
	private AmUserNftMapper amUserNftMapper;
	
	@Override
	public List<AmUserNft> selectByMainId(String mainId) {
		return amUserNftMapper.selectByMainId(mainId);
	}
}
