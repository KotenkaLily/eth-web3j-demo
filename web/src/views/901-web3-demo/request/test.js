import axios from '@/common/axios/request.js'

export function getBlockNumber() {
    return axios({
        url: '/chainInfo/blockNumber',
        method: 'get',
    })
}
export function getAccounts() {
    return axios({
        url: '/chainInfo/accounts',
        method: 'get',
    })
}
export function getGasPrice() {
    return axios({
        url: '/chainInfo/gasPrice',
        method: 'get',
    })
}
export function getChainId() {
    return axios({
        url: '/chainInfo/chainId',
        method: 'get',
    })
}
export function getCoinbase() {
    return axios({
        url: '/chainInfo/coinbase',
        method: 'get',
    })
}
export function getBlockInfo(parameter={}) {
    return axios({
        url: '/chainInfo/getBlockInfo',
        method: 'get',
        params:parameter
    })
}
export function saveBlockInfo(parameter={}) {
    return axios({
        url: '/chainInfo/saveInfo',
        method: 'get',
        params:parameter
    })
}
export function getTransactionByBlockNumber(parameter={}) {
    return axios({
        url: '/chainInfo/getTransactionByBlockNumber',
        method: 'get',
        params:parameter
    })
}
export function getTransactionInfoByHash(parameter={}) {
    return axios({
        url: '/chainInfo/getTransactionInfoByHash',
        method: 'get',
        params:parameter
    })
}
export function getEtherCurrent() {
    return axios({
        url: '/erc20/ether',
        method: 'get',
    })
}
export function deployErc20Contract(parameter={}) {
    return axios({
        url: '/erc20/deployErc20',
        method: 'post',
        data:parameter
    })
}
export function getCoinTotal(parameter={}) {
    return axios({
        url: '/erc20/coinTotal',
        method: 'get',
        params:parameter
    })
}
export function getBalance(parameter={}) {
    return axios({
        url: '/erc20/balance',
        method: 'get',
        params:parameter
    })
}
export function postApprove(parameter={}) {
    return axios({
        url: '/erc20/approve',
        method: 'post',
        data:parameter
    })
}

export function postTransfer(parameter={}) {
    return axios({
        url: '/erc20/transfer',
        method: 'post',
        data:parameter
    })
}

export function getAllowance(parameter={}) {
    return axios({
        url: '/erc20/allowance',
        method: 'get',
        params:parameter
    })
}
