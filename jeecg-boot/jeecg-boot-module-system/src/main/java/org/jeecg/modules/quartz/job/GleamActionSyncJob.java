package org.jeecg.modules.quartz.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.util.RestUtil;
import org.jeecg.modules.amactivity.entity.AdminActivity;
import org.jeecg.modules.amactivity.service.IAdminActivityService;
import org.jeecg.modules.amqaction.entity.QuestAction;
import org.jeecg.modules.amqaction.service.IQuestActionService;
import org.jeecg.modules.amquest.entity.ActionDef;
import org.jeecg.modules.amquest.entity.Quest;
import org.jeecg.modules.amquest.service.IActionDefService;
import org.jeecg.modules.amquest.service.IQuestService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@DisallowConcurrentExecution
@Slf4j
public class GleamActionSyncJob implements Job {

    @Autowired
    private IQuestService questService;

    @Autowired
    private IActionDefService actionDefService;

    @Autowired
    private IQuestActionService questActionService;

    @Autowired
    private IAdminActivityService adminActivityService;

    private String parameter;

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }


    @Override
    @Transactional
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if (StringUtils.isNotBlank(this.parameter)) {
            Quest find = questService.query().eq("quest_key", this.parameter).one();
            processInQuest(find.getQuestKey());
            return;
        }
        List<Quest> allQuests = questService.list();
        for (Quest q : allQuests) {
            try {
                processInQuest(q.getQuestKey());
            } catch (Exception e) {
                log.error("Exception:", e);
                log.info("Skip to process quest: {}", q.getQuestKey());
            }

        }

    }

    private void processInQuest(String questKey) throws JobExecutionException {
        if (StringUtils.isEmpty(questKey)) {
            throw new JobExecutionException("invalid questKey...");
        }
        JSONObject variablesForFirstCall = new JSONObject();
        //variables.put("page", 1);
        variablesForFirstCall.put("per_page", 500);
        JSONObject resp = RestUtil.get("https://gleam.io/api/v2/sites/1979945-teleport/competitions/" + questKey+ "/actions", variablesForFirstCall);
        JSONObject pagination = resp.getJSONObject("pagination");
        JSONArray dataArray =  resp.getJSONArray("data");
        if (pagination == null) {
            throw new JobExecutionException("invalid pagination info...");
        }
        if (dataArray == null) {
            throw new JobExecutionException("invalid data...");
        }

        Integer result = pagination.getInteger("result");
        Integer page = pagination.getInteger("page");
        Integer totalPages = pagination.getInteger("total_pages");
        Integer pageSize = pagination.getInteger("page_size");
        Integer totalCount = pagination.getInteger("total_count");
        log.info("====[pagination]===={}",pagination);

        for ( Object obj : dataArray) {
            try {
                processAction(obj);
            } catch (Exception e) {
                log.error("Exception:", e);
                log.info("Skip to process action");
            }
        }

        if(totalPages > 1) {
            //process other pages
            while (page <= totalPages) {
                JSONObject variables = new JSONObject();
                variables.put("page", page+1);
                variables.put("per_page", 500);
                resp = RestUtil.get("https://gleam.io/api/v2/sites/1979945-teleport/competitions/"+ questKey+ "/actions", variables);
                pagination = resp.getJSONObject("pagination");
                dataArray =  resp.getJSONArray("data");
                if (pagination == null) {
                    throw new JobExecutionException("invalid pagination info...");
                }
                if (dataArray == null) {
                    throw new JobExecutionException("invalid data...");
                }
                result = pagination.getInteger("result");
                page = pagination.getInteger("page");
                totalPages = pagination.getInteger("total_pages");
                pageSize = pagination.getInteger("page_size");
                totalCount = pagination.getInteger("total_count");
                log.info("====[pagination]===={}",pagination);
                for ( Object obj : dataArray) {
                    try {
                        processAction(obj);
                    } catch (Exception e) {
                        log.error("Exception:", e);
                        log.info("Skip to process action: {}");
                    }
                }
            }

        }

    }

    private void processAction(Object obj) {
        JSONObject action = JSONObject.parseObject(JSON.toJSONString(obj));
        //log.info("====>>>action:{}", action);
        String id = action.getString("id");
        String name = action.getString("name");
        String email = action.getString("email");
        String status = action.getString("status");
        JSONObject actionObj = action.getJSONObject("action");
        String actionType = actionObj.getString("type");
        String landingUrl = action.getString("landing_url");

        QuestAction find = questActionService.getById(id);
        //status no changes and existing QuestAction
        if (find != null && status.equalsIgnoreCase(find.getStatus()) && this.parameter == null) {
            return;
        }
        // persist to QuestAction
        QuestAction toSave = new QuestAction();
        toSave.setId(id);
        toSave.setActionId(actionObj.getInteger("id"));
        Matcher m = Pattern.compile(".*\\/gleam.io\\/([a-zA-Z0-9-]+)(\\/?).*").matcher(landingUrl);
        if (m.matches()) {
            toSave.setQuestKey(m.group(1));
        }
        toSave.setLandingUrl(landingUrl);
        toSave.setName(name);
        toSave.setEmail(email);
        toSave.setStatus(status);
        toSave.setActionType(actionType);
        toSave.setActionConfig(actionObj.toJSONString());
        toSave.setSyncTime(new Date());
        questActionService.saveOrUpdate(toSave);
        // persist to AdminActivity
        if (!"Valid".equalsIgnoreCase(status)) {
            return;
        }
        ActionDef actionDef = actionDefService.getById(toSave.getActionId());
        if (actionDef == null || Optional.ofNullable(actionDef.getReward()).orElse(0) == 0) {
            return;
        }
        Quest quest = questService.getById(actionDef.getQuestFk());
        if (quest == null) {
            return;
        }
        long aacount = adminActivityService.query().eq("id", id).count();
        if (aacount >0) {
            return;
        }
        AdminActivity activity = new AdminActivity();
        activity.setId(id);
        activity.setStatus("0");
        activity.setTitle("Points Rewards for " + quest.getTitle());
        activity.setSender(toSave.getEmail());
        activity.setSendTime(new Date());
        activity.setInputAmount(actionDef.getReward().doubleValue());
        //1. point; 2. token; 3. nft
        activity.setType("point");
        activity.setQuestRef(quest.getTitle());
        activity.setActionRef(actionDef.getType());
        adminActivityService.saveOrUpdate(activity);
        log.info("Increased Admin Activity:{}", activity);
    }

}
