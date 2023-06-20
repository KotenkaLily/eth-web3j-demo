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
                  <div style="margin: 6px 2px">
                    <a-button type="default" @click="accounts">查看账户列表-弹窗</a-button>
                  </div>
                  <div style="margin: 6px 2px">
                    <a-button type="default" @click="getEthers">获取当前用户的ether数量</a-button>
                  </div>
                  <div style="margin: 6px 2px">
                    <a-button type="default" @click="getTotal">获取token总量by合约地址</a-button>
                  </div>
                  <div style="margin: 6px 2px">
                    <a-button type="default" @click="getBalanceByAddr">获取token余额by合约&账户地址</a-button>
                  </div>
                  <div style="margin: 6px 2px">
                    <a-button type="default" @click="getAllowance">查询某个地址允许消费的token数量</a-button>
                  </div>
                </a-col>
                <a-col :span="12">
                  <div>
                    合约地址:
                    <br/>
                    <a-input style="width: 160px" v-model:value="contractAddress"/>
                  </div>
                  <div>
                    账户地址:
                    <br/>
                    <a-input style="width: 160px" v-model:value="accountAddress"/>
                  </div>
                  <div>
                    token个数:
                    <br/>
                    <a-input style="width: 160px" v-model:value="tokenAmount"/>
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
                    <a-button type="default" @click="doTransfer">转给某账户多少个某合约的token</a-button>
                  </div>
                  <div style="margin: 6px 2px">
                    <a-button type="default" @click="doApprove">允许账户使用合约的token多少个</a-button>
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
          <!--block2，区块链_改-->
          <a-col :span="12">
            <a-card
                style="margin: 4px 8px;font-size: 20px"
                title="结果"
            >
              <div>ether余额：{{ ethers }}</div>
              <br/>
              <div>token总量：{{ totalCoin }}</div>
              <br/>
              <div>token余额：{{ balance }}</div>
              <br/>
              <div>允许消费的token数量：{{ tokenAllow }}</div>
              <br/>
            </a-card>
          </a-col>
        </a-row>
        <div>
          <modal-b1 v-model:visible="modals.visibleM1" title="账户列表" :content="accountList"/>
        </div>
      </div>
    </a-layout-content>
  </a-layout>
</template>

<script>
import {
  deployErc20Contract,
  getAccounts, getAllowance, getBalance,
  getCoinTotal, getEtherCurrent, postApprove, postTransfer,
} from "./request/test";
import WsvHeaderT1 from "@/components/layouts/base-headers/WsvHeaderT1";
import Web3Menu from "@/views/901-web3-demo/modules/Web3Menu";
import ModalB1 from "@/components/modals/base/ModalB1";

export default {
  name: "ContractManage",
  components: {
    WsvHeaderT1,
    Web3Menu,
    ModalB1,
  },
  data() {
    return {
      modals: {
        visibleM1: false
      },
      accountAddress: '',
      contractAddress: '',
      totalCoin: 0,
      coinAmount: 0,
      blockNum: '',
      visibleM: false,
      accountList: '',
      balance: '',
      tokenAmount: '',
      ethers: '',
      tokenAllow: '',
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    init() {
    },
    getTotal() {
      let data = {
        contractAddress: this.contractAddress
      }
      getCoinTotal(data).then(res => {
        console.log(res)
        this.totalCoin = res.data.resultObject
      })
    },
    accounts() {
      this.modals.visibleM1 = true;
      getAccounts().then(res => {
        console.log(res)
        this.accountList = res.data.resultObject
      })
    },

    handleCancelM() {
      this.visibleM = false;
    },
    getBalanceByAddr() {
      let data = {
        contractAddress: this.contractAddress,
        accountAddress: this.accountAddress
      }
      getBalance(data).then(res => {
        console.log(res)
        this.balance = res.data.resultObject
      })
    },
    doApprove() {
      let data = {
        tokenValue: this.tokenAmount,
        contractAddress: this.contractAddress,
        accountAddress: this.accountAddress
      }
      postApprove(data).then(res => {
        console.log(res)
        // this.balance = res.data.resultObject
      })
    },
    getAllowance() {
      let data = {
        tokenValue: this.tokenAmount,
        contractAddress: this.contractAddress,
        accountAddress: this.accountAddress
      }
      getAllowance(data).then(res => {
        console.log(res)
        this.tokenAllow = res.data.resultObject
      })
    },
    doTransfer() {
      let data = {
        tokenValue: this.tokenAmount,
        contractAddress: this.contractAddress,
        accountAddress: this.accountAddress
      }
      postTransfer(data).then(res => {
        console.log(res)
      })
    },
    getEthers() {
      getEtherCurrent().then(res => {
        console.log(res)
        this.ethers = res.data.resultObject
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
  }
}
</script>

<style scoped>

</style>