package org.jeecg.modules.amapi.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.amactivity.entity.AdminActivity;
import org.jeecg.modules.amactivity.service.IAdminActivityService;
import org.jeecg.modules.amuser.entity.AmUserNft;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.web3j.crypto.Credentials;
import org.web3j.generated.contracts.TeleportUniverse;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticEIP1559GasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
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

    @Value("${ambassador.chainId}")
    private Long chainId;

    @Value("${ambassador.rpc}")
    private String rpc;

    @Value("${ambassador.nft.contract}")
    private String contract;

    @Value("${ambassador.nft.pk}")
    private String privateKey;

    @Value("${ambassador.nft.owner}")
    private String owner;

    @RequestMapping(value="/am/user-login", method = RequestMethod.POST)
    public Result<?> userLogin(@RequestBody AmUserLoginModel msg) {
        return loginController.userLogin(msg);
    }

    @PutMapping("/amactivity/adminActivity/approve")
    @Transactional
    public Result<?> approveActivity(@RequestBody AdminActivity adminActivity) throws Exception {
        log.info("======>>>>>approve activity>>>>>:{}", adminActivity);
        if ("point".equalsIgnoreCase(adminActivity.getType())
        || "bonus".equalsIgnoreCase(adminActivity.getType())
        || "amend".equalsIgnoreCase(adminActivity.getType())) {
            processPoint(adminActivity);
        } else if ("token".equalsIgnoreCase(adminActivity.getType())) {
            processToken(adminActivity);
        } else if ("nft".equalsIgnoreCase(adminActivity.getType())){
            processNft(adminActivity);
        }
        adminActivity.setStatus("1");
        adminActivityService.updateById(adminActivity);
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
        // will not to update ambassadorUser token amount here,
        // ambassadorUser will update when the token is unlocked
        // BigDecimal token = Optional.ofNullable(ambassadorUser.getToken()).orElse(BigDecimal.ZERO);
        // ambassadorUser.setToken(token.add(BigDecimal.valueOf(adminActivity.getInputAmount())));
        // ambassadorUserService.updateById(ambassadorUser);
        // add token entry for am-user
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

    private void processNft(AdminActivity adminActivity) throws Exception {
        String email = adminActivity.getSender();
        Optional<AmbassadorUser> ambassadorUserOpt = ambassadorUserService.query().eq("email", email).oneOpt();
        AmbassadorUser ambassadorUser = ambassadorUserOpt.orElseThrow(() -> new NoSuchElementException("User Not Found."));
        // transfer nft
        log.info("Connecting to Ethereum ...");
        Web3j web3 = Web3j.build(new HttpService(rpc));
        log.info("Successfully connected to Ethereum");
        EthMaxPriorityFeePerGas maxPriorityFeePerGas = web3.ethMaxPriorityFeePerGas().send();
        EthBlockNumber blockNumber = web3.ethBlockNumber().send();
        EthBlock block = web3.ethGetBlockByNumber(DefaultBlockParameter.valueOf(blockNumber.getBlockNumber()), true).send();
        BigInteger baseFee = block.getBlock().getBaseFeePerGas();

        //TeleportUniverse.load
        StaticEIP1559GasProvider staticEIP1559GasProvider = new StaticEIP1559GasProvider( chainId, maxPriorityFeePerGas.getMaxPriorityFeePerGas().add(baseFee) , maxPriorityFeePerGas.getMaxPriorityFeePerGas(), DefaultGasProvider.GAS_LIMIT);
        TeleportUniverse teleportUniverse = TeleportUniverse.load(contract, web3, Credentials.create(privateKey), staticEIP1559GasProvider);
        JSONObject value = JSONObject.parseObject(adminActivity.getValue());
        BigInteger tokenId = value.getBigInteger("tokenId");
        String nftName = value.getString("nftName");

        //estimate gas
        RemoteFunctionCall<TransactionReceipt> estimateGasFunctionCall = teleportUniverse.safeTransferFrom(owner, ambassadorUser.getAddress(), tokenId);

        EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(
                owner, DefaultBlockParameterName.LATEST).sendAsync().get();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        Transaction tx = Transaction.createFunctionCallTransaction(owner, nonce, DefaultGasProvider.GAS_PRICE, BigInteger.valueOf(900000000L), contract, estimateGasFunctionCall.encodeFunctionCall());
        EthEstimateGas estimateGas = web3.ethEstimateGas(tx).send();
        //teleportUniverse.send
        staticEIP1559GasProvider = new StaticEIP1559GasProvider(chainId, maxPriorityFeePerGas.getMaxPriorityFeePerGas().add(baseFee) , maxPriorityFeePerGas.getMaxPriorityFeePerGas(), estimateGas.getAmountUsed());
        teleportUniverse.setGasProvider(staticEIP1559GasProvider);
        log.info("Estimate Gas:{}", staticEIP1559GasProvider);
        TransactionReceipt receipt = teleportUniverse.safeTransferFrom(owner, ambassadorUser.getAddress(), tokenId).send();
        String txHash = receipt.getTransactionHash();
        log.info("Get txHash:{}", receipt);

        // update nft entry for am-user
        AmUserNft amUserNft = new AmUserNft();
        amUserNft.setAmbassadorUserId(ambassadorUser.getId());
        amUserNft.setAddress(contract);
        amUserNft.setTxHash(txHash);
        amUserNft.setNftType(nftName);
        amUserNft.setUrl("");
        amUserNftService.save(amUserNft);
        web3.shutdown();
    }


}
