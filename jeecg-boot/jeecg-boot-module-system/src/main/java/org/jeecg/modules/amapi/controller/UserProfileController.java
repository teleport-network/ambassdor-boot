package org.jeecg.modules.amapi.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.amactivity.entity.AdminActivity;
import org.jeecg.modules.amactivity.service.IAdminActivityService;
import org.jeecg.modules.amapi.vo.AbstractLevel;
import org.jeecg.modules.amlevel.entity.AmbassadorLevel;
import org.jeecg.modules.amlevel.entity.ContributorLevel;
import org.jeecg.modules.amlevel.service.IAmbassadorLevelService;
import org.jeecg.modules.amlevel.service.IContributorLevelService;
import org.jeecg.modules.amqaction.entity.QuestAction;
import org.jeecg.modules.amqaction.service.IQuestActionService;
import org.jeecg.modules.amquest.entity.ActionDef;
import org.jeecg.modules.amquest.entity.Quest;
import org.jeecg.modules.amquest.service.IActionDefService;
import org.jeecg.modules.amquest.service.IQuestService;
import org.jeecg.modules.amuser.entity.AmbassadorUser;
import org.jeecg.modules.amuser.service.IAmbassadorUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Autowired
    private IAmbassadorLevelService ambassadorLevelService;

    @Autowired
    private IContributorLevelService contributorLevelService;

    @Autowired
    private IAdminActivityService adminActivityService;

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
    public Result<?> getCurrentUserActions(@RequestParam(required = false, name = "questKey") String questKey) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String email = sysUser.getEmail();
        List<QuestAction> findAction;
        if (StringUtils.isNotBlank(questKey)) {
            findAction = questActionService.query().eq("email", email).eq("quest_key", questKey).list();
        } else {
            findAction = questActionService.query().eq("email", email).list();
        }
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

    @PostMapping("/collect-point")
    public Result<?> collectPoints() {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String email = sysUser.getEmail();
        AmbassadorUser user = ambassadorUserService.query().eq("email", email).one();
        if (user.getPoint() == null) {
            user.setPoint(BigDecimal.ZERO);
        }
        BigDecimal pointsToAdd = user.getPointCache() == null ? BigDecimal.ZERO : user.getPointCache();
        BigDecimal newPoint = user.getPoint().add(pointsToAdd);
        user.setPoint(newPoint);
        user.setPointCache(BigDecimal.ZERO);
        String lvlName = levelCompute(user);
        if (!lvlName.equalsIgnoreCase(user.getLevel())) {
            user.setLevel(lvlName);
            // process user level-up;
            processLevelUpToken(user);
            processLevelUpNft(user);
        }
        ambassadorUserService.saveOrUpdate(user);
        return Result.OK("success", user);
    }

    private void processLevelUpToken(AmbassadorUser user) {
        Integer tokenReward;
        if ("ambassador".equalsIgnoreCase(user.getRole())) {
            AmbassadorLevel amlvl = ambassadorLevelService.query().eq("name",user.getLevel()).one();
            tokenReward = amlvl.getToken();
        } else {
            ContributorLevel cnlvl = contributorLevelService.query().eq("name", user.getLevel()).one();
            tokenReward = cnlvl.getToken();
        }
        if (tokenReward == 0) {
            log.info("Zero Token Rewards for level-up {}.", user.getLevel());
            return;
        }

        AdminActivity activity = new AdminActivity();
        activity.setStatus("0");
        activity.setTitle("Token Rewards for Level-Up: " + user.getLevel());
        //1. point; 2. token; 3. nft
        activity.setType("token");
        activity.setSender(user.getEmail());
        activity.setSendTime(new Date());
        activity.setInputAmount(tokenReward.doubleValue());
        adminActivityService.saveOrUpdate(activity);
        log.info("Inserted Admin Activity:{}", activity);
    }

    private void processLevelUpNft(AmbassadorUser user) {

    }

    private String levelCompute(AmbassadorUser user) {
        BigDecimal point = user.getPoint();
        if("ambassador".equalsIgnoreCase(user.getRole())) {
            List<AmbassadorLevel> levels = ambassadorLevelService.list().stream()
                    .sorted((i,j)->Integer.compare(i.getLvlIndex(), j.getLvlIndex())).collect(Collectors.toList());
            List<AbstractLevel> abstractLevels = new ArrayList<>();
            AbstractLevel lvl1 = AbstractLevel.builder()
                    .name(levels.get(0).getName())
                    .lvlIndex(levels.get(0).getLvlIndex())
                    .pointsMin(0)
                    .pointsMax(levels.get(0).getPoints())
                    .build();
            abstractLevels.add(lvl1);
            for (int i=1; i<levels.size(); i++) {
                AmbassadorLevel aml = levels.get(i);
                AbstractLevel lvl = AbstractLevel.builder()
                        .name(aml.getName())
                        .lvlIndex(aml.getLvlIndex())
                        .pointsMin(abstractLevels.get(i-1).getPointsMax())
                        .pointsMax(abstractLevels.get(i-1).getPointsMax() + aml.getPoints())
                        .build();
                abstractLevels.add(lvl);
            }
            Optional<AbstractLevel> lvIndex = abstractLevels.stream()
                    .filter(lv ->  user.getPoint().compareTo(BigDecimal.valueOf(lv.getPointsMin())) >=0
                            && user.getPoint().compareTo(BigDecimal.valueOf(lv.getPointsMax())) <=0 )
                    .findAny();
            return lvIndex.orElse(abstractLevels.get(abstractLevels.size()-1)).getName();
        } else {
            List<ContributorLevel> levels = contributorLevelService.list().stream()
                    .sorted((i,j)->Integer.compare(i.getLvlIndex(), j.getLvlIndex())).collect(Collectors.toList());
            List<AbstractLevel> abstractLevels = new ArrayList<>();
            AbstractLevel lvl1 = AbstractLevel.builder()
                    .name(levels.get(0).getName())
                    .lvlIndex(levels.get(0).getLvlIndex())
                    .pointsMin(0)
                    .pointsMax(levels.get(0).getPoints())
                    .build();
            abstractLevels.add(lvl1);
            for (int i=1; i<levels.size(); i++) {
                ContributorLevel cnl = levels.get(i);
                AbstractLevel lvl = AbstractLevel.builder()
                        .name(cnl.getName())
                        .lvlIndex(cnl.getLvlIndex())
                        .pointsMin(abstractLevels.get(i-1).getPointsMax())
                        .pointsMax(abstractLevels.get(i-1).getPointsMax() + cnl.getPoints())
                        .build();
                abstractLevels.add(lvl);
            }
            Optional<AbstractLevel> lvIndex = abstractLevels.stream()
                    .filter(lv ->  user.getPoint().compareTo(BigDecimal.valueOf(lv.getPointsMin())) >=0
                            && user.getPoint().compareTo(BigDecimal.valueOf(lv.getPointsMax())) <=0 )
                    .findAny();
            return lvIndex.isPresent()? lvIndex.get().getName() : "full-class";
        }

    }

}
