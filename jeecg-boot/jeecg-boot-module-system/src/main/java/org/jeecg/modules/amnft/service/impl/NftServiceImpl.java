package org.jeecg.modules.amnft.service.impl;

import org.jeecg.modules.amnft.entity.Nft;
import org.jeecg.modules.amnft.entity.MintHistory;
import org.jeecg.modules.amnft.entity.DeliveredHistory;
import org.jeecg.modules.amnft.mapper.MintHistoryMapper;
import org.jeecg.modules.amnft.mapper.DeliveredHistoryMapper;
import org.jeecg.modules.amnft.mapper.NftMapper;
import org.jeecg.modules.amnft.service.INftService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: nft
 * @Author: jeecg-boot
 * @Date:   2022-08-10
 * @Version: V1.0
 */
@Service
public class NftServiceImpl extends ServiceImpl<NftMapper, Nft> implements INftService {

	@Autowired
	private NftMapper nftMapper;
	@Autowired
	private MintHistoryMapper mintHistoryMapper;
	@Autowired
	private DeliveredHistoryMapper deliveredHistoryMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		mintHistoryMapper.deleteByMainId(id);
		deliveredHistoryMapper.deleteByMainId(id);
		nftMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			mintHistoryMapper.deleteByMainId(id.toString());
			deliveredHistoryMapper.deleteByMainId(id.toString());
			nftMapper.deleteById(id);
		}
	}
	
}
