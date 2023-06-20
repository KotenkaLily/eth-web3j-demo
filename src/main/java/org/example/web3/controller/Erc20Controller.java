package org.example.web3.controller;

import org.example.web3.common.res.DemoResult;
import org.example.web3.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author newonexd
 * @description erc20控制器
 * @date 2022/6/22 21:44
 */
@RestController
@RequestMapping("erc20")
public class Erc20Controller {
    @Autowired
    private TokenService tokenService;


    /**
     * @return BigInteger
     * @description 获取该账户下的Ether总数
     * @author newonexd
     * @date 2022/6/22 21:34
     */
    @CrossOrigin
    @GetMapping("/ether")
    public DemoResult<Map<String, Object>> doGetEther() throws Exception {
        return tokenService.doGetEther();
    }

    /**
     * @param coinName  Erc20Token 名称
     * @param symbol    Erc20Token 简写
     * @param coinTotal 总发行量
     * @return String 合约地址
     * @description 部署Erc20 合约
     * @author newonexd
     * @date 2022/6/22 21:34
     */
    @CrossOrigin
    @PostMapping("/deployErc20")
    public DemoResult<Map<String, Object>> doDeployErc20(@RequestParam(value = "coinName") String coinName,
                                                         @RequestParam(value = "symbol") String symbol,
                                                         @RequestParam(value = "coinTotal") Long coinTotal) throws Exception {
        return tokenService.doDeployErc20(coinName, symbol, coinTotal);
    }


    /**
     * @param contractAddress 部署的合约地址
     * @return BigInteger  总发行量
     * @description 查询总发行量
     * @author newonexd
     * @date 2022/6/22 21:35
     */
    @CrossOrigin
    @GetMapping("/coinTotal")
    public DemoResult<Map<String, Object>> getTotal(@RequestParam(value = "contractAddress") String contractAddress) throws Exception {
        return tokenService.getTotal(contractAddress);
    }


    /**
     * @param contractAddress 合约地址
     * @param accountAddress  账户地址
     * @return BigInteger Erc20Token总量
     * @description 获取账户下Erc20Token总量
     * @author newonexd
     * @date 2022/6/22 21:36
     */
    @CrossOrigin
    @GetMapping("/balance")
    public DemoResult<Map<String, Object>> getBalance(@RequestParam(value = "contractAddress") String contractAddress,
                                                      @RequestParam(value = "accountAddress") String accountAddress) throws Exception {
        return tokenService.getBalance(contractAddress, accountAddress);
    }


    /**
     * @param contractAddress 合约地址
     * @param approveAddress  被授权的账户地址
     * @param tokenValue      授权Token总数
     * @return String 该笔交易的哈希值
     * @description 授权他人账户地址一定数量的Erc20Token 币
     * @author newonexd
     * @date 2022/6/22 21:36
     */
    @CrossOrigin
    @PostMapping("/approve")
    public DemoResult<Map<String, Object>> doApprover(@RequestParam(value = "contractAddress") String contractAddress,
                                                      @RequestParam(value = "accountAddress") String approveAddress,
                                                      @RequestParam(value = "tokenValue") int tokenValue) throws Exception {
        return tokenService.doApprove(contractAddress, approveAddress, tokenValue);
    }

    /**
     * @param contractAddress 合约地址
     * @param tokenValue      token数量
     * @return BigInteger 被授权消费的Erc20数量
     * @description 查询指定地址下被允许消费的Erc20Token数量
     * @author newonexd
     * @date 2022/6/22 21:37
     */
    @CrossOrigin
    @PostMapping("/transfer")
    public DemoResult<Map<String, Object>> doPostTransfer(@RequestParam(value = "contractAddress") String contractAddress, @RequestParam(value = "accountAddress") String transferAddress,
                                                          @RequestParam(value = "tokenValue") int tokenValue) throws Exception {
        return tokenService.doPostTransfer(contractAddress, transferAddress, tokenValue);

    }

    /**
     * @param contractAddress  合约地址
     * @param allowanceAddress 被授权的账户地址
     * @return BigInteger 被授权消费的Erc20数量
     * @description 查询指定地址下被允许消费的Erc20Token数量
     * @author newonexd
     * @date 2022/6/22 21:37
     */
    @CrossOrigin
    @GetMapping("/allowance")
    public DemoResult<Map<String, Object>> doGetAllowrance(@RequestParam(value = "contractAddress") String contractAddress,
                                                           @RequestParam(value = "accountAddress") String allowanceAddress) throws Exception {
        return tokenService.doGetAllowance(contractAddress, allowanceAddress);
    }

}
