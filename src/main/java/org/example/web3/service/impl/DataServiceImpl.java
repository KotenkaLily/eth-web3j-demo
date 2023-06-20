package org.example.web3.service.impl;

import org.example.web3.common.param.DefaultParam;
import org.example.web3.common.res.DemoResult;
import org.example.web3.common.utils.ImgVerify;
import org.example.web3.entity.block.BlockChainInfo;
import org.example.web3.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.EthBlock;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DataServiceImpl implements DataService {
    @Autowired
    private Web3j web3j;

    @Autowired
    private MongoTemplate mongoTemplate;




    @Override
    public DemoResult<Map<String, Object>> saveCurrentBlockInfoToMongo() throws IOException {
        List<BlockChainInfo> list = new ArrayList<>();
        BigInteger i = web3j.ethBlockNumber().send().getBlockNumber();
        long currentTimeMillis = System.currentTimeMillis();
        for (long j = 0; j < i.longValue(); j++) {
            BlockChainInfo blockChainInfo = new BlockChainInfo();
            DefaultBlockParameterNumber defaultBlockParameterNumber = new DefaultBlockParameterNumber(j);
            EthBlock.Block block = web3j.ethGetBlockByNumber(defaultBlockParameterNumber, true).send().getBlock();
            BeanUtils.copyProperties(block, blockChainInfo);
            blockChainInfo.setTimestamp(String.valueOf(currentTimeMillis));
            list.add(blockChainInfo);
            mongoTemplate.insert(blockChainInfo, "ktest");
        }
        return DemoResult.mSuccess(true, "obj");
    }

    @Override
    public DemoResult<Map<String, Object>> getBlockInfoFromMongo() throws IOException {
        List<BlockChainInfo> list = new ArrayList<>();
        Query query = new Query();
//        query.addCriteria(Criteria.where("name").is(name));
        query.addCriteria(Criteria.where("timeStamp").maxDistance(0));
        List<BlockChainInfo> list1 = mongoTemplate.find(query, BlockChainInfo.class);
        return DemoResult.mSuccess(list1, "obj");
    }

}
