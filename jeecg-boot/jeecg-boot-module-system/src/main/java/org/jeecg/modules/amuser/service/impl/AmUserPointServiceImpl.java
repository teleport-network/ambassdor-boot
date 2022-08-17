package org.jeecg.modules.amuser.service.impl;

import org.jeecg.modules.amuser.entity.AmUserPoint;
import org.jeecg.modules.amuser.mapper.AmUserPointMapper;
import org.jeecg.modules.amuser.service.IAmUserPointService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: points list
 * @Author: jeecg-boot
 * @Date:   2022-08-12
 * @Version: V1.0
 */
@Service
public class AmUserPointServiceImpl extends ServiceImpl<AmUserPointMapper, AmUserPoint> implements IAmUserPointService {
	
	@Autowired
	private AmUserPointMapper amUserPointMapper;
	
	@Override
	public List<AmUserPoint> selectByMainId(String mainId) {
		return amUserPointMapper.selectByMainId(mainId);
	}
}
