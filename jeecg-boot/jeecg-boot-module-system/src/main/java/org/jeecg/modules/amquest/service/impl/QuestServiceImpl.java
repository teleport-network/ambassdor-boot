package org.jeecg.modules.amquest.service.impl;

import org.jeecg.modules.amquest.entity.Quest;
import org.jeecg.modules.amquest.entity.ActionDef;
import org.jeecg.modules.amquest.mapper.ActionDefMapper;
import org.jeecg.modules.amquest.mapper.QuestMapper;
import org.jeecg.modules.amquest.service.IQuestService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: quest
 * @Author: jeecg-boot
 * @Date:   2022-09-01
 * @Version: V1.0
 */
@Service
public class QuestServiceImpl extends ServiceImpl<QuestMapper, Quest> implements IQuestService {

	@Autowired
	private QuestMapper questMapper;
	@Autowired
	private ActionDefMapper actionDefMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		actionDefMapper.deleteByMainId(id);
		questMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			actionDefMapper.deleteByMainId(id.toString());
			questMapper.deleteById(id);
		}
	}
	
}
