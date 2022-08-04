package org.jeecg.modules.amuser.service.impl;

import org.jeecg.modules.amuser.entity.AmUserQuest;
import org.jeecg.modules.amuser.mapper.AmUserQuestMapper;
import org.jeecg.modules.amuser.service.IAmUserQuestService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: quest list
 * @Author: jeecg-boot
 * @Date:   2022-08-01
 * @Version: V1.0
 */
@Service
public class AmUserQuestServiceImpl extends ServiceImpl<AmUserQuestMapper, AmUserQuest> implements IAmUserQuestService {
	
	@Autowired
	private AmUserQuestMapper amUserQuestMapper;
	
	@Override
	public List<AmUserQuest> selectByMainId(String mainId) {
		return amUserQuestMapper.selectByMainId(mainId);
	}
}
