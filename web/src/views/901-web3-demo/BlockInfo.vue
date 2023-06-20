<template>
  <wsv-header-t1>
    <template #app_logo>
      <img style="width: 70px;margin-left: 50px;" alt src="./assets/eth.jpeg">
    </template>
    <template #menu>
      <web3-menu/>
    </template>
  </wsv-header-t1>
  <a-layout>
    <a-layout-content style="background: white ;text-align: left">
      <div class="content" style="margin: 12px 20px;">
        <a-row>
          <a-col :span="12">
            <!--block1，区块链信息_查-->
            <a-card
                style="margin: 4px 8px"
                title="查询类">
              <a-row>
                <a-col :span="12">
                  <div style="margin: 3px 2px">
                    <a-button type="default" @click="blockNumber">区块数量</a-button>
                  </div>
                  <div style="margin: 6px 2px">
                    <a-button type="default" @click="blockInformation">获取区块信息by区块编号-小心弹窗</a-button>
                  </div>
                  <div style="margin: 6px 2px">
                    <a-button type="default" @click="transactions">获取交易信息by区块编号-小心弹窗</a-button>
                  </div>
                  <div style="margin: 6px 2px">
                    <a-button type="default" @click="transInfo">获取交易详情by交易Hash-小心弹窗</a-button>
                  </div>
                  <div style="margin: 6px 2px">
                    <a-button type="default" @click="chainId">获取chainId</a-button>
                  </div>
                  <div style="margin: 6px 2px">
                    <a-button type="default" @click="coinB">获取coinBase</a-button>
                  </div>
                  <div style="margin: 6px 2px">
                    <a-button type="default" @click="gasPrice">获取gas价格</a-button>
                  </div>
                  <div style="margin: 6px 2px">
                    <a-button type="default" @click="accounts">获取账户列表-小心弹窗</a-button>
                  </div>
                  <div style="margin: 6px 2px">
                    <a-button type="default" @click="accounts">获取合约地址列-小心弹窗</a-button>
                  </div>
                </a-col>
                <a-col :span="12">
                  <div>
                    区块编号：
                    <br/>
                    <a-input style="width: 60px" v-model:value="blockNumParam"/>
                  </div>
                  <a-divider/>
                  <div>
                    Txn Hash：
                    <br/>
                    <a-input style="width: 260px" v-model:value="transHash"/>
                  </div>
                </a-col>
              </a-row>
            </a-card>
            <a-card
                style="margin: 4px 8px"
                title="操作类">
              <a-row>
                <a-col :span="12">
                  <div style="margin: 6px 2px">
                    <a-button type="default" @click="deploy">部署合约-测试币（新建一个区块）</a-button>
                  </div>
                  <div style="margin: 6px 2px">
                    <a-button type="default" @click="save">保存区块信息到Mongo</a-button>
                  </div>
                </a-col>
                <a-col :span="12">
                  <div>
                    发行总量:<br/>
                    <a-input style="width: 160px" v-model:value="coinAmount"/>
                  </div>
                </a-col>
              </a-row>
            </a-card>


          </a-col>
          <a-col :span="12">
            <a-card
                style="margin: 4px 8px;font-size: 20px"
                title="结果"
            >
              <div style="height: 66px">上次合约部署地址：<br/>
                {{ contractAddress }}
              </div>
              <br/>
              <div>区块数(编号要-1)：{{ blockNum }}</div>
              <br/>
              <div>gas价格：{{ gas }}</div>
              <br/>
              <div>区块链id：{{ blockChainId }}</div>
              <br/>
              <div>coinBase：{{ coinBase }}</div>
              <br/>
            </a-card>
          </a-col>
        </a-row>
        <!--block2，区块链_改-->
        <div>
          <modal-b1 v-model:visible="modals.visibleM1" title="账户列表" :content="accountList"/>
          <modal-b1 v-model:visible="modals.visibleM2" title="区块信息" :content="blockInfo"/>
          <modal-b1 v-model:visible="modals.visibleM3" title="交易信息" :content="transaction"/>
          <modal-b1 v-model:visible="modals.visibleM4" title="交易详情" :content="transactionInfo"/>
        </div>
      </div>
    </a-layout-content>
  </a-layout>
</template>

<script>
import {
  deployErc20Contract,
  getAccounts, getBlockInfo,
  getBlockNumber,
  getChainId,
  getCoinbase,
  getGasPrice, getTransactionByBlockNumber, getTransactionInfoByHash, saveBlockInfo
} from "./request/test";
import WsvHeaderT1 from "@/components/layouts/base-headers/WsvHeaderT1";
import Web3Menu from "@/views/901-web3-demo/modules/Web3Menu";
import ModalB1 from "@/components/modals/base/ModalB1";

export default {
  name: "BlockInfo",
  components: {
    WsvHeaderT1,
    Web3Menu,
    ModalB1,
  },
  data() {
    return {
      modals: {
        visibleM1: false,
        visibleM2: false,
        visibleM3: false,
        visibleM4: false,
      },
      key: 0,
      coinAmount: 2000000,
      contractAddress: "",
      blockNum: '',
      accountList: {},
      visibleM: false,
      visibleB: false,
      gas: '',
      blockChainId: '',
      coinBase: '',
      blockInfo: {},
      blockNumParam: 0,
      transaction: '',
      transactionInfo: '',
      transHash: '',
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    init() {
      console.log(this.$route)
    },
    save() {
      saveBlockInfo().then(res => {
        console.log('saved', res)
      })
    },
    blockNumber() {
      getBlockNumber().then(res => {
        console.log(res)
        this.blockNum = res.data.resultObject
      })
    },
    deploy() {
      let data = {
        coinName: "coin",
        symbol: "symbol",
        coinTotal: this.coinAmount
      }
      deployErc20Contract(data).then(res => {
        console.log(res)
        this.contractAddress = res.data.resultObject
      })
    },
    accounts() {
      this.modals.visibleM1 = true;
      getAccounts().then(res => {
        console.log(res)
        this.accountList = res.data.resultObject
      })
    },
    gasPrice() {
      getGasPrice().then(res => {
        console.log(res)
        this.gas = res.data.resultObject
      })
    },
    chainId() {
      getChainId().then(res => {
        console.log(res)
        this.blockChainId = res.data.resultObject
      })
    },
    coinB() {
      getCoinbase().then(res => {
        console.log(res)
        this.coinBase = res.data.resultObject
      })
    },
    blockInformation() {
      let data = {
        blockNumber: this.blockNumParam
      }
      getBlockInfo(data).then(res => {
        console.log(res)
        this.blockInfo = res.data.resultObject
      })
      this.modals.visibleM2 = true;
    },
    transactions() {
      let data = {
        blockNumber: this.blockNumParam
      }
      getTransactionByBlockNumber(data).then(res => {
        console.log(res)
        this.transaction = res.data.resultObject
      })
      this.modals.visibleM3 = true;
    },

    transInfo() {
      let data = {
        txHash: this.transHash
      }
      getTransactionInfoByHash(data).then(res => {
        console.log(res)
        this.transactionInfo = res.data.resultObject
      })
      this.modals.visibleM4 = true;
    },
  }
}
</script>

<style scoped>

</style>