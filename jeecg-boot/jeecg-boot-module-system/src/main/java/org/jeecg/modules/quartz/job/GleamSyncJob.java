package org.jeecg.modules.quartz.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.RestUtil;
import org.jeecg.modules.amquest.entity.Quest;
import org.jeecg.modules.amquest.service.IQuestService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriBuilder;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@DisallowConcurrentExecution
@Slf4j
public class GleamSyncJob implements Job {
    @Autowired
    private IQuestService questService;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JSONObject resp = RestUtil.get("https://gleam.io/api/v2/sites/1979945-teleport/competitions", null);
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
        Integer pageSize = pagination.getInteger("total_pages");
        Integer totalPages = pagination.getInteger("page_size");
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
                pageSize = pagination.getInteger("total_pages");
                totalPages = pagination.getInteger("page_size");
                totalCount = pagination.getInteger("total_count");
                for ( Object obj : dataArray) {
                    try {
                        processQuest((JSONObject) obj);
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
            return;
        }
        String name = quest.getString("name");
        Date createAt = quest.getDate("created_at");
        Date startsAt = quest.getDate("starts_at");
        Date endsAt = quest.getDate("ends_at");
        JSONObject prize = quest.getJSONObject("prize");
        Quest toSave = new Quest();
        toSave.setQuestKey(key);
        toSave.setTitle(name);
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
        questService.save(toSave);
    }

}
