package org.jeecg.modules.amapi.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.amactivity.entity.AdminActivity;
import org.jeecg.modules.amactivity.service.IAdminActivityService;
import org.jeecg.modules.amuser.entity.AmbassadorUser;
import org.jeecg.modules.amuser.service.IAmbassadorUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@Api(tags="administration")
@RestController
@Slf4j
public class AdministrationController {

    @Autowired
    private IAdminActivityService adminActivityService;

    @Autowired
    private IAmbassadorUserService ambassadorUserService;

    @PutMapping("/amactivity/adminActivity/approve")
    @Transactional
    public Result<?> approvePoints(@RequestBody AdminActivity adminActivity) {
        log.info("======>>>>>approvePoints:{}", adminActivity);
        adminActivity.setStatus("1");
        adminActivityService.updateById(adminActivity);
        String email = adminActivity.getSender();
        Optional<AmbassadorUser> ambassadorUserOpt = ambassadorUserService.query().eq("email", email).oneOpt();
        AmbassadorUser ambassadorUser = ambassadorUserOpt.get();
        BigDecimal pointCache = Optional.ofNullable(ambassadorUser.getPointCache()).orElse(BigDecimal.ZERO);
        ambassadorUser.setPointCache(pointCache.add(BigDecimal.valueOf(adminActivity.getInputAmount())));
        ambassadorUserService.updateById(ambassadorUser);
        return Result.OK("success");
    }
}
