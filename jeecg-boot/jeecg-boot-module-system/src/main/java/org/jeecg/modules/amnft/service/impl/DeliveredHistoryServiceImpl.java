package org.jeecg.modules.amnft.service.impl;

import org.jeecg.modules.amnft.entity.DeliveredHistory;
import org.jeecg.modules.amnft.mapper.DeliveredHistoryMapper;
import org.jeecg.modules.amnft.service.IDeliveredHistoryService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: delivered history
 * @Author: jeecg-boot
 * @Date:   2022-08-01
 * @Version: V1.0
 */
@Service
public class DeliveredHistoryServiceImpl extends ServiceImpl<DeliveredHistoryMapper, DeliveredHistory> implements IDeliveredHistoryService {
	
	@Autowired
	private DeliveredHistoryMapper deliveredHistoryMapper;
	
	@Override
	public List<DeliveredHistory> selectByMainId(String mainId) {
		return deliveredHistoryMapper.selectByMainId(mainId);
	}
}
