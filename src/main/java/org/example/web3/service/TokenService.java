package org.example.web3.service;

import org.example.web3.common.res.DemoResult;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface TokenService {
    DemoResult<Map<String, Object>> doGetEther() throws ExecutionException, InterruptedException;

    DemoResult<Map<String, Object>> doDeployErc20(String coinName, String symbol, Long coinTotal) throws ExecutionException, InterruptedException;

    DemoResult<Map<String, Object>> getTotal(String contractAddress) throws ExecutionException, InterruptedException;

    DemoResult<Map<String, Object>> getBalance(String contractAddress, String accountAddress) throws ExecutionException, InterruptedException;

    DemoResult<Map<String, Object>> doApprove(String contractAddress, String approveAddress, int tokenValue) throws ExecutionException, InterruptedException;

    DemoResult<Map<String, Object>> doPostTransfer(String contractAddress, String transferAddress, int tokenValue) throws ExecutionException, InterruptedException;

    DemoResult<Map<String, Object>> doGetAllowance(String contractAddress, String allowanceAddress) throws ExecutionException, InterruptedException;
}
