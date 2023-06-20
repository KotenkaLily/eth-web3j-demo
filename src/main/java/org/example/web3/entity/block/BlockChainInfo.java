package org.example.web3.entity.block;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.web3j.protocol.core.methods.response.EthBlock;

import java.util.List;

@Document
@Data
public class BlockChainInfo {
    private String number;
    private String hash;
    private String parentHash;
    private String nonce;
    private String sha3Uncles;
    private String logsBloom;
    private String transactionsRoot;
    private String stateRoot;
    private String receiptsRoot;
    private String author;
    private String miner;
    private String mixHash;
    private String difficulty;
    private String totalDifficulty;
    private String extraData;
    private String size;
    private String gasLimit;
    private String gasUsed;
    private String timestamp;
    private List<EthBlock.TransactionResult> transactions;
    private List<String> uncles;
    private List<String> sealFields;
    private String baseFeePerGas;
}
