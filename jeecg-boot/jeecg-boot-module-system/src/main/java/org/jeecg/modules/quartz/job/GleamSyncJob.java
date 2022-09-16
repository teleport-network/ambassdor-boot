package org.jeecg.modules.quartz.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.RestUtil;
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
import org.springframework.web.util.UriBuilder;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@DisallowConcurrentExecution
@Slf4j
public class GleamSyncJob implements Job {
    @Autowired
    private IQuestService questService;

    @Autowired
    private IActionDefService actionDefService;

    @Autowired
    private IQuestActionService questActionService;
    @Override
    @Transactional
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JSONObject variablesForFirstCall = new JSONObject();
        variablesForFirstCall.put("per_page", 500);
        JSONObject resp = RestUtil.get("https://gleam.io/api/v2/sites/1979945-teleport/competitions", variablesForFirstCall);
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

        for ( Object obj : dataArray) {
            try {
                processQuest(obj);
            } catch (Exception e) {
                log.error("Exception:", e);
                log.info("Skip to process quest: {}", obj);
            }
        }

        if(totalPages > 1) {
            //process other pages
            while (page <= totalPages) {
                JSONObject variables = new JSONObject();
                variables.put("page", page+1);
                variables.put("per_page", 500);
                resp = RestUtil.get("https://gleam.io/api/v2/sites/1979945-teleport/competitions", variables);
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
                for ( Object obj : dataArray) {
                    try {
                        processQuest(obj);
                    } catch (Exception e) {
                        log.error("Exception:", e);
                        log.info("Skip to process quest: {}", obj);
                    }
                }
            }

        }

    }

    private void processQuest(Object obj) {
        JSONObject quest = JSONObject.parseObject(JSON.toJSONString(obj));
        String key = quest.getString("key");
        long hasQuest = questService.query().eq("quest_key", key).count();
        if (hasQuest > 0) {
            log.info("Existing Quest:{}", key);
            return;
        }
        String name = quest.getString("name");
        Date createAt = quest.getDate("created_at");
        Date startsAt = quest.getDate("starts_at");
        Date endsAt = quest.getDate("ends_at");
        JSONObject prize = quest.getJSONObject("prize");

        Quest toSave = new Quest();
        toSave.setId(key);
        toSave.setQuestKey(key);
        toSave.setTitle(name);
        toSave.setGleamCreateAt(createAt);
        toSave.setIssueDate(startsAt);
        toSave.setDeadline(endsAt);
        String[] words = name.split("\\W+");
        String renaming = Stream.of(words).map(String::toLowerCase).collect(Collectors.joining("-"));
        String url = "https://gleam.io/"+key+"/"+renaming;
        toSave.setUrl(url);
        JSONArray images = prize.getJSONArray("images");
        if (images.size() >0 ){
            toSave.setImage((String) images.get(0));
        }
        toSave.setSyncTime(new Date());
        toSave.setDescription(prize.getString("description"));
        questService.save(toSave);
        // process action def
        JSONArray entryMethods = quest.getJSONArray("entry_methods");
        if (entryMethods == null) {
            log.info("Invalid entryMethods...");
            return;
        }
        for ( Object entry : entryMethods) {
            try {
                processEntry(entry, key);
            } catch (Exception e) {
                log.error("Exception:", e);
                log.info("Skip to process entry: {}", entry);
            }
        }

    }

    private void processEntry(Object obj, String key) {
        JSONObject action = JSONObject.parseObject(JSON.toJSONString(obj));
        String actionId = action.getString("id");
        long existedActDef = actionDefService.query().eq("id", actionId).count();
        if (existedActDef > 0) {
            log.info("Existing action:{}", actionId);
            return;
        }
        ActionDef actDefToSave = new ActionDef();
        actDefToSave.setId(actionId);
        actDefToSave.setQuestFk(key);
        actDefToSave.setType(action.getString("type"));
        actDefToSave.setWorth(action.getInteger("worth"));
        actDefToSave.setMandatory(action.getBoolean("mandatory").toString());
        actDefToSave.setConfig1(action.getString("config1"));
        actDefToSave.setConfig2(action.getString("config2"));
        actDefToSave.setConfig3(action.getString("config3"));
        actDefToSave.setConfig4(action.getString("config4"));
        actDefToSave.setConfig5(action.getString("config5"));
        actDefToSave.setConfig6(action.getString("config6"));
        actDefToSave.setConfig7(action.getString("config7"));
        actDefToSave.setConfig8(action.getString("config8"));
        actDefToSave.setConfig9(action.getString("config9"));
        actDefToSave.setPaid(action.getBoolean("paid").toString());
        actDefToSave.setImage(action.getString("image"));
        actDefToSave.setConfigToggle(action.getBoolean("config_toggle").toString());
        actDefToSave.setActionsRequired(action.getInteger("actions_required"));
        actDefToSave.setOriginal(action.toJSONString());
        actionDefService.save(actDefToSave);
    }

}
