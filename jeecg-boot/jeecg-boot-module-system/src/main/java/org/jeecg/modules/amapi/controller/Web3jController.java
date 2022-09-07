package org.jeecg.modules.amapi.controller;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;

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


        } catch(IOException ex) {
            throw new RuntimeException("Error whilst sending json-rpc requests", ex);
        }
    }
}
