package org.jeecg.modules.amuser.service.impl;

import org.jeecg.modules.amuser.entity.AmUserToken;
import org.jeecg.modules.amuser.mapper.AmUserTokenMapper;
import org.jeecg.modules.amuser.service.IAmUserTokenService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: token list
 * @Author: jeecg-boot
 * @Date:   2022-08-18
 * @Version: V1.0
 */
@Service
public class AmUserTokenServiceImpl extends ServiceImpl<AmUserTokenMapper, AmUserToken> implements IAmUserTokenService {
	
	@Autowired
	private AmUserTokenMapper amUserTokenMapper;
	
	@Override
	public List<AmUserToken> selectByMainId(String mainId) {
		return amUserTokenMapper.selectByMainId(mainId);
	}
}
