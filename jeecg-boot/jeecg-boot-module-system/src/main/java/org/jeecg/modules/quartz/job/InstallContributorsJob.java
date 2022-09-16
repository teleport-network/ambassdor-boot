package org.jeecg.modules.quartz.job;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.amactivity.entity.AdminActivity;
import org.jeecg.modules.amactivity.service.IAdminActivityService;
import org.jeecg.modules.amnft.entity.Nft;
import org.jeecg.modules.amnft.service.INftService;
import org.jeecg.modules.amuser.entity.AmbassadorUser;
import org.jeecg.modules.amuser.service.IAmUserNftService;
import org.jeecg.modules.amuser.service.IAmbassadorUserService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@DisallowConcurrentExecution
@Slf4j
public class InstallContributorsJob implements Job {

    @Autowired
    private IAmbassadorUserService ambassadorUserService;

    @Autowired
    private IAmUserNftService amUserNftService;

    @Autowired
    private IAdminActivityService adminActivityService;

    @Autowired
    private INftService nftService;

    @Override
    @Transactional
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<AmbassadorUser> allContributors = ambassadorUserService.query().eq("role", "contributor").list();
        List<AmbassadorUser> newContributors = allContributors.stream().filter(
                (u) -> !adminActivityService.query().eq("sender", u.getEmail()).like("value", "CLV1").exists()
        ).collect(Collectors.toList());
        log.info("Contributors not get CLV1 NFT Rewards:{}", newContributors.size());
        for (AmbassadorUser user: newContributors) {
            Nft rewardNft = nftService.query().eq("name", "CLV1").one();
            JSONObject value = new JSONObject();
            value.put("nftName", rewardNft.getName());
            value.put("tokenId", rewardNft.getNextIndex());

            AdminActivity activity = new AdminActivity();
            activity.setStatus("0");
            activity.setTitle("NFT Rewards for new Contributor");
            //1. point; 2. token; 3. nft; 4. bonus
            activity.setType("nft");
            activity.setSender(user.getEmail());
            activity.setSendTime(new Date());
            activity.setValue(value.toJSONString());
            activity.setInputAmount(1.0);
            adminActivityService.saveOrUpdate(activity);
            log.info("Inserted NFT Rewards Activity:{}", activity);
            // update nft inventory
            rewardNft.setNextIndex(rewardNft.getNextIndex()+1);
            rewardNft.setInventory(rewardNft.getInventory()-1);
            rewardNft.setDelivered(rewardNft.getDelivered()+1);
            nftService.updateById(rewardNft);
        }

    }
}
