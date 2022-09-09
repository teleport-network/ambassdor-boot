package org.jeecg.modules.amapi.controller;

import java.io.IOException;
import java.math.BigInteger;

import lombok.extern.slf4j.Slf4j;
import org.web3j.contracts.token.ERC20BasicInterface;
import org.web3j.contracts.token.ERC20Interface;
import org.web3j.crypto.Credentials;
import org.web3j.generated.contracts.TeleportUniverse;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.tx.gas.DefaultGasProvider;

@Slf4j
public class Web3jController {

    public static void main(String[] args) {
        try {
            log.info("Connecting to Ethereum ...");
            Web3j web3 = Web3j.build(new HttpService("https://rinkeby.infura.io/v3/9aa3d95b3bc440fa88ea12eaa4456161"));
            log.info("Successfuly connected to Ethereum");
            // web3_clientVersion returns the current client version.
            Web3ClientVersion clientVersion = web3.web3ClientVersion().send();

            //eth_blockNumber returns the number of most recent block.
            EthBlockNumber blockNumber = web3.ethBlockNumber().send();

            //eth_gasPrice, returns the current price per gas in wei.
            EthGasPrice gasPrice =  web3.ethGasPrice().send();

            // Print result
            log.info("Client version: " + clientVersion.getWeb3ClientVersion());
            log.info("Block number: " + blockNumber.getBlockNumber());
            log.info("Gas price: " + gasPrice.getGasPrice());

            TeleportUniverse teleportUniverse = TeleportUniverse.load("0xeE5317B83A7d06e379A0a4D29c23256A5f77d0ce", web3, Credentials.create("b01843c1f5b8242689442dbb5c348dbc2cbc9eecbe614158b08ec810255aeb5c"), new DefaultGasProvider());
            BigInteger tokenId = BigInteger.valueOf(11);
            //tokenId.
            TransactionReceipt receipt = teleportUniverse.safeTransferFrom("", "", tokenId).send();
            //receipt.
            if (teleportUniverse.isValid()) {
                BigInteger maxSupply = teleportUniverse.maxSupply().send();
                log.info("maxSupply: " + maxSupply);
            }


        } catch(Exception ex) {
            throw new RuntimeException("Error whilst sending json-rpc requests", ex);
        }
    }
}
