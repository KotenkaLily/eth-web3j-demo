package org.example.web3.common.config;

import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;

public class MyGasProvider implements ContractGasProvider {
    private static final BigInteger GAS_PRICE = BigInteger.valueOf(22_000_000_000L);
    private static final BigInteger GAS_LIMIT = BigInteger.valueOf(4_300_000);

    @Override
    public BigInteger getGasPrice(String contractFunc) {
        return GAS_PRICE;
    }

    @Override
    public BigInteger getGasLimit(String contractFunc) {
        return GAS_LIMIT;
    }

    @Override
    public BigInteger getGasPrice() {
        return GAS_PRICE;
    }

    @Override
    public BigInteger getGasLimit() {
        return GAS_LIMIT;
    }
}
