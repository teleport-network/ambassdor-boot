package org.jeecg.modules.amapi.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.amqaction.entity.QuestAction;
import org.jeecg.modules.amqaction.service.IQuestActionService;
import org.jeecg.modules.amquest.entity.ActionDef;
import org.jeecg.modules.amquest.entity.Quest;
import org.jeecg.modules.amquest.service.IActionDefService;
import org.jeecg.modules.amquest.service.IQuestService;
import org.jeecg.modules.amuser.entity.AmbassadorUser;
import org.jeecg.modules.amuser.service.IAmbassadorUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags="userprofile")
@RestController
@RequestMapping("/am/profile")
@Slf4j
public class UserProfileController {

    @Autowired
    private IAmbassadorUserService ambassadorUserService;

    @Autowired
    private IQuestActionService questActionService;

    @Autowired
    private IActionDefService actionDefService;

    @Autowired
    private IQuestService questService;

    @GetMapping
    public Result<?> getCurrentUser() {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String email = sysUser.getEmail();
        AmbassadorUser user = ambassadorUserService.query().eq("email", email).one();
        JSONObject resp = new JSONObject();
        resp.put("user", user);
        return Result.OK("success", resp);
    }

    @GetMapping("/action")
    public Result<?> getCurrentUserActions() {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String email = sysUser.getEmail();
        List<QuestAction> findAction = questActionService.query().eq("email", email).list();
        return Result.OK("success", findAction);
    }

    @GetMapping("/joined-quest")
    public Result<?> getJoinedQuestList() {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String email = sysUser.getEmail();
        List<QuestAction> findAction = questActionService.query().eq("email", email).list();
        List<Quest> find = findAction.stream().map(a -> {
            ActionDef actionDef = actionDefService.query().eq("id", a.getActionId()).one();
            return actionDef;
        }).map( d -> {
            return questService.getById(d.getQuestFk());
        }).distinct().collect(Collectors.toList());
        return Result.OK("success", find);
    }

}
