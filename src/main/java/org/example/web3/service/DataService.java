package org.example.web3.service;

import org.example.web3.common.param.DefaultParam;
import org.example.web3.common.res.DemoResult;

import java.io.IOException;
import java.util.Map;

public interface DataService {

    DemoResult<Map<String, Object>> saveCurrentBlockInfoToMongo() throws IOException;

    DemoResult<Map<String, Object>> getBlockInfoFromMongo() throws IOException;

}
