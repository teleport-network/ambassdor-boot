package org.jeecg.modules.amnft.service.impl;

import org.jeecg.modules.amnft.entity.MintHistory;
import org.jeecg.modules.amnft.mapper.MintHistoryMapper;
import org.jeecg.modules.amnft.service.IMintHistoryService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: mint history
 * @Author: jeecg-boot
 * @Date:   2022-08-01
 * @Version: V1.0
 */
@Service
public class MintHistoryServiceImpl extends ServiceImpl<MintHistoryMapper, MintHistory> implements IMintHistoryService {
	
	@Autowired
	private MintHistoryMapper mintHistoryMapper;
	
	@Override
	public List<MintHistory> selectByMainId(String mainId) {
		return mintHistoryMapper.selectByMainId(mainId);
	}
}
