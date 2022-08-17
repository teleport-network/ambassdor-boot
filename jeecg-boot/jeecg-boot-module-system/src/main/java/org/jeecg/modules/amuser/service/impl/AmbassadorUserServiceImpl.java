package org.jeecg.modules.amuser.service.impl;

import org.jeecg.modules.amuser.entity.AmbassadorUser;
import org.jeecg.modules.amuser.entity.AmUserPoint;
import org.jeecg.modules.amuser.entity.AmUserToken;
import org.jeecg.modules.amuser.entity.AmUserNft;
import org.jeecg.modules.amuser.entity.AmUserQuest;
import org.jeecg.modules.amuser.mapper.AmUserPointMapper;
import org.jeecg.modules.amuser.mapper.AmUserTokenMapper;
import org.jeecg.modules.amuser.mapper.AmUserNftMapper;
import org.jeecg.modules.amuser.mapper.AmUserQuestMapper;
import org.jeecg.modules.amuser.mapper.AmbassadorUserMapper;
import org.jeecg.modules.amuser.service.IAmbassadorUserService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: ambassador user list
 * @Author: jeecg-boot
 * @Date:   2022-08-12
 * @Version: V1.0
 */
@Service
public class AmbassadorUserServiceImpl extends ServiceImpl<AmbassadorUserMapper, AmbassadorUser> implements IAmbassadorUserService {

	@Autowired
	private AmbassadorUserMapper ambassadorUserMapper;
	@Autowired
	private AmUserPointMapper amUserPointMapper;
	@Autowired
	private AmUserTokenMapper amUserTokenMapper;
	@Autowired
	private AmUserNftMapper amUserNftMapper;
	@Autowired
	private AmUserQuestMapper amUserQuestMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		amUserPointMapper.deleteByMainId(id);
		amUserTokenMapper.deleteByMainId(id);
		amUserNftMapper.deleteByMainId(id);
		amUserQuestMapper.deleteByMainId(id);
		ambassadorUserMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			amUserPointMapper.deleteByMainId(id.toString());
			amUserTokenMapper.deleteByMainId(id.toString());
			amUserNftMapper.deleteByMainId(id.toString());
			amUserQuestMapper.deleteByMainId(id.toString());
			ambassadorUserMapper.deleteById(id);
		}
	}
	
}
