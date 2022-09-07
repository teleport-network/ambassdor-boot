package org.jeecg.modules.amapi.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.amactivity.entity.AdminActivity;
import org.jeecg.modules.amactivity.service.IAdminActivityService;
import org.jeecg.modules.amuser.entity.AmUserPoint;
import org.jeecg.modules.amuser.entity.AmUserToken;
import org.jeecg.modules.amuser.entity.AmbassadorUser;
import org.jeecg.modules.amuser.service.IAmUserNftService;
import org.jeecg.modules.amuser.service.IAmUserPointService;
import org.jeecg.modules.amuser.service.IAmUserTokenService;
import org.jeecg.modules.amuser.service.IAmbassadorUserService;
import org.jeecg.modules.system.controller.LoginController;
import org.jeecg.modules.system.model.AmUserLoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

@Api(tags="administration")
@RestController
@Slf4j
public class AdministrationController {

    @Autowired
    private IAdminActivityService adminActivityService;

    @Autowired
    private IAmbassadorUserService ambassadorUserService;

    @Autowired
    private IAmUserPointService amUserPointService;

    @Autowired
    private IAmUserTokenService amUserTokenService;

    @Autowired
    private IAmUserNftService amUserNftService;

    @Autowired
    private LoginController loginController;

    @RequestMapping(value="/am/user-login", method = RequestMethod.POST)
    public Result<?> userLogin(@RequestBody AmUserLoginModel msg) {
        return loginController.userLogin(msg);
    }

    @PutMapping("/amactivity/adminActivity/approve")
    @Transactional
    public Result<?> approvePoints(@RequestBody AdminActivity adminActivity) {
        log.info("======>>>>>approvePoints:{}", adminActivity);
        adminActivity.setStatus("1");
        adminActivityService.updateById(adminActivity);

        if ("point".equalsIgnoreCase(adminActivity.getType())) {
            processPoint(adminActivity);
        } else if ("token".equalsIgnoreCase(adminActivity.getType())) {
            processToken(adminActivity);
        } else {
            processNft(adminActivity);
        }

        return Result.OK("success");
    }

    private void processPoint(AdminActivity adminActivity) {
        // update user point-cache
        String email = adminActivity.getSender();
        Optional<AmbassadorUser> ambassadorUserOpt = ambassadorUserService.query().eq("email", email).oneOpt();
        AmbassadorUser ambassadorUser = ambassadorUserOpt.orElseThrow(() -> new NoSuchElementException("User Not Found."));
        BigDecimal pointCache = Optional.ofNullable(ambassadorUser.getPointCache()).orElse(BigDecimal.ZERO);
        ambassadorUser.setPointCache(pointCache.add(BigDecimal.valueOf(adminActivity.getInputAmount())));
        ambassadorUserService.updateById(ambassadorUser);
        // add point entry for user
        AmUserPoint amUserPoint = new AmUserPoint();
        amUserPoint.setAmbassadorUserId(ambassadorUser.getId());
        amUserPoint.setAmount(adminActivity.getInputAmount().intValue());
        amUserPoint.setIssueBy(adminActivity.getQuestRef() + " " + adminActivity.getActionRef() + " Approved by Admin");
        amUserPoint.setActionUrl(adminActivity.getActionRef());
        amUserPoint.setConfirmTime(new Date());
        amUserPointService.save(amUserPoint);
    }

    private void processToken(AdminActivity adminActivity) {
        // update user token
        String email = adminActivity.getSender();
        Optional<AmbassadorUser> ambassadorUserOpt = ambassadorUserService.query().eq("email", email).oneOpt();
        AmbassadorUser ambassadorUser = ambassadorUserOpt.orElseThrow(() -> new NoSuchElementException("User Not Found."));
        // BigDecimal token = Optional.ofNullable(ambassadorUser.getToken()).orElse(BigDecimal.ZERO);
        // ambassadorUser.setToken(token.add(BigDecimal.valueOf(adminActivity.getInputAmount())));
        // ambassadorUserService.updateById(ambassadorUser);
        // add token entry for user
        AmUserToken amUserToken = new AmUserToken();
        amUserToken.setAmbassadorUserId(ambassadorUser.getId());
        amUserToken.setAmount(BigDecimal.valueOf(adminActivity.getInputAmount()));
        amUserToken.setIssueBy("Level-Up: " + ambassadorUser.getLevel());

        LocalDateTime now = LocalDateTime.now();

        amUserToken.setIssueDate(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()));
        LocalDateTime oneYearAfter = now.plusYears(1);
        amUserToken.setLockTime(Date.from(oneYearAfter.atZone(ZoneId.systemDefault()).toInstant()));
        amUserTokenService.save(amUserToken);
    }

    private void processNft(AdminActivity adminActivity) {

    }


}
