package org.example.web3.controller;

import org.example.web3.common.res.DemoResult;
import org.example.web3.service.DataService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * @author newonexd
 * @description 获取区块链信息
 * @date 2022/6/22 21:14
 */
@RestController
@RequestMapping("/chainInfo")
public class BlockChainInfoController {

    private static final Logger logger = LoggerFactory.getLogger(BlockChainInfoController.class);

    @Autowired
    private DataService dataService;

    @Autowired
    private Web3j web3j;

    /**
     */
    @CrossOrigin
    @GetMapping("/getInfoFromMongo")
    public DemoResult<Map<String, Object>> getBlockInfoFromMongo() throws Exception {
        return dataService.getBlockInfoFromMongo();
    }
    /**
     */
    @CrossOrigin
    @GetMapping("/saveInfo")
    public DemoResult<Map<String, Object>> saveCurrentBlockInfoToMongo() throws Exception {
        return dataService.saveCurrentBlockInfoToMongo();
    }
    /**
     * @return
     * @description 获取最新的区块号
     * @author newonexd
     * @date 2022/6/22 21:11
     * <p>
     * * @return BigInteger
     */
    @CrossOrigin
    @GetMapping("/blockNumber")
    public DemoResult<Map<String, Object>> doGetLatestBlockNumber() throws Exception {
        EthBlockNumber ethBlockNumber = web3j.ethBlockNumber().sendAsync().get();
        BigInteger blockNumber = ethBlockNumber.getBlockNumber();
        logger.info("BlockNumber: {}", blockNumber);
        return DemoResult.mSuccess(blockNumber, "obj");

    }

    /**
     * @return
     * @description 获取所有账户
     * @author newonexd
     * @date 2022/6/22 21:11
     * <p>
     * * @return List<String>
     */
    @CrossOrigin
    @GetMapping("/accounts")
    public DemoResult<Map<String, Object>> doGetAllAccounts() throws Exception {
        EthAccounts ethAccounts = web3j.ethAccounts().sendAsync().get();
        List<String> accounts = ethAccounts.getAccounts();
        logger.info("Accounts: {}", accounts);
        return DemoResult.mSuccess(accounts, "obj");

    }

    /**
     * @return
     * @description 获取Gas价格
     * @author newonexd
     * @date 2022/6/22 21:11
     * <p>
     * * @return BigInteger
     */
    @CrossOrigin
    @GetMapping("/gasPrice")
    public DemoResult<Map<String, Object>> doGetEthGasPrice() throws Exception {
        EthGasPrice ethGasPrice = web3j.ethGasPrice().sendAsync().get();
        BigInteger gasPrice = ethGasPrice.getGasPrice();
        logger.info("Ethereum Gas Price: {}", gasPrice);
        return DemoResult.mSuccess(gasPrice, "obj");

    }

    /**
     * @return BigInteger
     * @description 获取链Id
     * @author newonexd
     * @date 2022/6/22 21:12
     */
    @CrossOrigin
    @GetMapping("/chainId")
    public DemoResult<Map<String, Object>> doGetChainId() throws Exception {
        EthChainId ethChainId = web3j.ethChainId().sendAsync().get();
        BigInteger chainId = ethChainId.getChainId();
        logger.info("Ethereum Chain Id: {}", chainId);
        return DemoResult.mSuccess(chainId, "obj");

    }


    /**
     * @return String
     * @return
     * @description 获取CoinBase
     * @author newonexd
     * @date 2022/6/22 21:12
     */
    @CrossOrigin
    @GetMapping("/coinbase")
    public DemoResult<Map<String, Object>> doGetCoinBase() throws Exception {
        EthCoinbase ethCoinbase = web3j.ethCoinbase().sendAsync().get();
        String coinBase = ethCoinbase.getAddress();
        logger.info("Ethereum CoinBase Address: {}", coinBase);
        return DemoResult.mSuccess(coinBase, "obj");

    }


    /**
     * @param blockNumber 区块号
     * @return String
     * @description 根据区块号获取区块信息
     * @author newonexd
     * @date 2022/6/22 21:12
     */
    @CrossOrigin
    @GetMapping("/getBlockInfo")
    public DemoResult<Map<String, Object>> doGetAll(@RequestParam(value = "blockNumber") Long blockNumber) throws Exception {
        DefaultBlockParameterNumber defaultBlockParameterNumber = new DefaultBlockParameterNumber(blockNumber);
        EthBlock ethBlock = web3j.ethGetBlockByNumber(defaultBlockParameterNumber, true).sendAsync().get();
        EthBlock.Block block = ethBlock.getBlock();
        Gson gson = new Gson();
        String info = gson.toJson(block);
        logger.info(info);
        return DemoResult.mSuccess(info, "obj");

    }


    /**
     * @param blockNumber 区块号
     * @return String
     * @description 根据区块号获取所有交易
     * @author newonexd
     * @date 2022/6/22 21:13
     */
    @CrossOrigin
    @GetMapping("/getTransactionByBlockNumber")
    public DemoResult<Map<String, Object>> doGetTransactionInfoByBlockNumber(@RequestParam(value = "blockNumber") Long blockNumber) throws Exception {
        DefaultBlockParameterNumber defaultBlockParameterNumber = new DefaultBlockParameterNumber(blockNumber);
        EthBlock ethBlock = web3j.ethGetBlockByNumber(defaultBlockParameterNumber, true).sendAsync().get();
        List<EthBlock.TransactionResult> transactionResults = ethBlock.getBlock().getTransactions();
        List<Transaction> txInfos = new ArrayList<>();

        transactionResults.forEach(txInfo -> {
            Transaction transaction = (Transaction) txInfo;
            txInfos.add(transaction);
        });
        Gson gson = new Gson();
        String transactionInfo = gson.toJson(txInfos);
        logger.info(transactionInfo);
        return DemoResult.mSuccess(transactionInfo, "obj");

    }


    /**
     * @param txHash 交易哈希值
     * @return String
     * @description 根据交易哈希值获取交易信息
     * @author newonexd
     * @date 2022/6/22 21:13
     */
    @CrossOrigin
    @GetMapping("/getTransactionInfoByHash")
    public DemoResult<Map<String, Object>> doGetTransactionInfoByHash(@RequestParam(value = "txHash") String txHash) throws Exception {
        EthTransaction transaction = web3j.ethGetTransactionByHash(txHash).sendAsync().get();
        Optional<Transaction> optionalTransaction = transaction.getTransaction();
        StringBuilder txInfo = new StringBuilder();
        if (optionalTransaction.isPresent()) {
            Transaction transactionInfo = optionalTransaction.get();
            Gson gson = new Gson();
            txInfo.append(gson.toJson(transactionInfo));
        }
        logger.info(txInfo.toString());
        return DemoResult.mSuccess(txInfo.toString(), "obj");

    }

    @CrossOrigin
    @GetMapping("/test")
    public String test(@RequestParam(value = "testV") String testV) throws Exception {
//        web3j.ethGetBalance()
        return testV.toString();
    }
}
