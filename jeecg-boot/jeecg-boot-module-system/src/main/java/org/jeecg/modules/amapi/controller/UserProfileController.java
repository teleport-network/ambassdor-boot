package org.jeecg.modules.amapi.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.amuser.entity.AmbassadorUser;
import org.jeecg.modules.amuser.service.IAmbassadorUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="userprofile")
@RestController
@RequestMapping("/am/profile")
@Slf4j
public class UserProfileController {

    @Autowired
    private IAmbassadorUserService ambassadorUserService;

    @GetMapping
    public Result<?> getCurrentUser() {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String email = sysUser.getEmail();
        AmbassadorUser user = ambassadorUserService.query().eq("email", email).one();
        JSONObject resp = new JSONObject();
        resp.put("user", user);
        return Result.OK("success", resp);
    }

}
