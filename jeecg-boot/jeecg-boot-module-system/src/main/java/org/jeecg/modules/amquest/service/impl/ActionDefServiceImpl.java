package org.jeecg.modules.amquest.service.impl;

import org.jeecg.modules.amquest.entity.ActionDef;
import org.jeecg.modules.amquest.mapper.ActionDefMapper;
import org.jeecg.modules.amquest.service.IActionDefService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: quest def
 * @Author: jeecg-boot
 * @Date:   2022-08-09
 * @Version: V1.0
 */
@Service
public class ActionDefServiceImpl extends ServiceImpl<ActionDefMapper, ActionDef> implements IActionDefService {
	
	@Autowired
	private ActionDefMapper actionDefMapper;
	
	@Override
	public List<ActionDef> selectByMainId(String mainId) {
		return actionDefMapper.selectByMainId(mainId);
	}
}
