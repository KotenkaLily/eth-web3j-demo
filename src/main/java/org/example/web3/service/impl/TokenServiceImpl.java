package org.example.web3.service.impl;

import org.example.web3.common.res.DemoResult;
import org.example.web3.common.config.MyGasProvider;
import org.example.web3.entity.contract.MyToken;
import org.example.web3.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class TokenServiceImpl implements TokenService {
    @Autowired
    private Web3j web3j;

    /*This service doesn't get data from database but from the blockchain.*/
    private static final Credentials credentials;

    static {
        //根据私钥创建Java账户类
        credentials = Credentials.create("7fe480b857758fa90b189548a9c266717da97ced3053241f3f63303640ad348b");
    }

    @Override
    public DemoResult<Map<String, Object>> doGetEther() throws ExecutionException, InterruptedException {
        //获取最新的区块号
        BigInteger blockNumber = web3j.ethBlockNumber().sendAsync().get().getBlockNumber();
        log.info("The BlockNumber is: {}", blockNumber);
        //生成请求参数
        DefaultBlockParameterNumber defaultBlockParameterNumber = new DefaultBlockParameterNumber(blockNumber);
        //根据请求参数获取余额
        EthGetBalance ethGetBalance = web3j.ethGetBalance(credentials.getAddress(), defaultBlockParameterNumber)
                .sendAsync().get();
        log.info("Get Account Ether is: {}", ethGetBalance.getBalance());
        return DemoResult.mSuccess(ethGetBalance.getBalance(), "obj");
    }

    @Override
    public DemoResult<Map<String, Object>> doDeployErc20(String coinName, String symbol, Long coinTotal) throws ExecutionException, InterruptedException {
        MyToken contract = MyToken.deploy(web3j, credentials, new MyGasProvider(), coinName, symbol, BigInteger.valueOf(coinTotal)).sendAsync().get();
        log.info("MyToken Contract Address: {}", contract.getContractAddress());
        return DemoResult.mSuccess(contract.getContractAddress(), "obj");
    }

    @Override
    public DemoResult<Map<String, Object>> getTotal(String contractAddress) throws ExecutionException, InterruptedException {
        MyToken erc20 = loadContract(contractAddress);
        BigInteger coinTotal = erc20.totalSupply().sendAsync().get();
        log.info("CoinTotal is: {}", coinTotal);
        return DemoResult.mSuccess(coinTotal, "obj");
    }

    @Override
    public DemoResult<Map<String, Object>> getBalance(String contractAddress, String accountAddress) throws ExecutionException, InterruptedException {
        MyToken erc20 = loadContract(contractAddress);
        BigInteger balance = erc20.balanceOf(accountAddress).sendAsync().get();
        log.info("AccountAddress: {} hava Balance: {}", accountAddress, balance);
        return DemoResult.mSuccess(balance, "obj");
    }

    @Override
    public DemoResult<Map<String, Object>> doApprove(String contractAddress, String approveAddress, int tokenValue) throws ExecutionException, InterruptedException {
        MyToken erc20 = loadContract(contractAddress);
        TransactionReceipt transactionReceipt = erc20.approve(approveAddress, BigInteger.valueOf(tokenValue)).sendAsync().get();
        boolean result = transactionReceipt.isStatusOK();
        String transactionHash = transactionReceipt.getTransactionHash();
        log.info("Approve result: {},TxHash: {}", result, transactionHash);
        return DemoResult.mSuccess(transactionHash, "obj");
    }

    @Override
    public DemoResult<Map<String, Object>> doPostTransfer(String contractAddress, String transferAddress, int tokenValue) throws ExecutionException, InterruptedException {
        MyToken erc20 = loadContract(contractAddress);
        TransactionReceipt transactionReceipt = erc20.transfer(transferAddress, BigInteger.valueOf(tokenValue)).sendAsync().get();
        if (transactionReceipt.isStatusOK()) {
            log.info("Transfer token value : {}", tokenValue);

        } else {
            tokenValue = 0;
            log.info("Transfer token value : {}", tokenValue);
        }
        return DemoResult.mSuccess(tokenValue, "obj");
    }

    @Override
    public DemoResult<Map<String, Object>> doGetAllowance(String contractAddress, String allowanceAddress) throws ExecutionException, InterruptedException {
        MyToken erc20 = loadContract(contractAddress);
        BigInteger allowrance = erc20.allowance(credentials.getAddress(), allowanceAddress).sendAsync().get();
        log.info("Allowance : {}", allowrance);
        return DemoResult.mSuccess(allowrance, "obj");
    }

    /**
     * @param contractAddress 合约地址
     * @return ERC20
     * @description 根据合约地址加载合约信息
     * @author newonexd
     * @date 2022/6/26 11:41
     */
    private MyToken loadContract(String contractAddress) {
        return MyToken.load(contractAddress, web3j, credentials, new MyGasProvider());
    }
}
