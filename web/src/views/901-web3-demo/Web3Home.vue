<template>

  <wsv-header-t1>
    <template #app_logo>
      <img style="width: 70px;margin-left: 50px;" alt :src="require('./assets/eth.jpeg')">
    </template>
    <template #menu>
      <web3-menu/>
    </template>
  </wsv-header-t1>
  <a-layout>
    <a-layout-content style="background: white ;text-align: left">
      <div class="content" style="margin-left: 2.5%; margin-right: 2.5%;margin-top: 1%;">
        <a-row :gutter="24">
          <!--第一行-->
          <a-col :span="14">
            <a-row>
              <a-col :span="24">
                <div style="height: 70px">
                  <a-input style="font-size: 20px; border-width: 2px;" v-model:value="searchContent"
                           placeholder="搜索区块/合约/代币/账户/交易Hash">
                    <template #prefix>
                      <search-outlined style="color: rgb(231,77,110)"/>
                    </template>
                    <template #suffix>
                      <a-tooltip title="您可以搜索各种不同的信息">
                        <info-circle-outlined style="color: rgb(231,77,110)"/>
                      </a-tooltip>
                    </template>
                  </a-input>
                </div>
              </a-col>
            </a-row>
            <a-row>
              <a-col :span="24" style="margin-left: 20px">
                <div style="font-size: small">
                  <fire-outlined/>
                  热搜概念：<a>BTC</a>、<a>ETH</a>、<a>USDT</a>、<a>JST</a>、<a>ASG</a>...
                </div>
              </a-col>
            </a-row>
            <a-row style="margin-top: 20px">
              <a-col :span="24">
                <div style="height: 200px">
                  <a-row>
                    <a-col :span="12">
                      <card-web3-n1 title="账户数量" description="500" style="width: 100%"/>
                    </a-col>
                    <a-col :span="12">
                      <card-web3-n1 title="总锁定价值" description="$100,000,000,000" style="width: 100%"/>
                    </a-col>
                  </a-row>
                  <a-row>
                    <a-col :span="12">
                      <card-web3-n1 title="总交易数量" description="2,323,333" style="width: 100%"/>
                    </a-col>
                    <a-col :span="12">
                      <card-web3-n1 title="总交易量" description="$23,239,042,002,423" style="width: 100%"/>
                    </a-col>
                  </a-row>
                </div>
              </a-col>
            </a-row>
            <a-row style="margin-top: 20px">
              <a-col :span="24">
                <div style="font-size: small;margin-left: 20px">
                  <notification-outlined/>
                  <span>&nbsp;公告：暂时停止对nile/tronex/shasta接口的支持， <a>点击查看详细说明 </a></span>
                  <span style="float: right"><a>更多></a></span>
                </div>
              </a-col>
            </a-row>
          </a-col>
          <a-col :span="10">
            <a-row>
              <a-col :span="24">
                <div>
                  <img alt src="@/views/901-web3-demo/assets/advts.png" style="width: 100%"/>
                </div>
              </a-col>
            </a-row>
            <a-row style="margin-top: 20px">
              <a-col :span="24">
                <card-web3-n2 title="TRX" description="$0.0692" style="width: 100%"/>
              </a-col>
            </a-row>

          </a-col>
        </a-row>
        <a-row>
          <a-col span="17">
            <card-web3-n3></card-web3-n3>
          </a-col>
          <a-col span="7">
            <div id="chart1" style="width: 100%;height: 100%;margin-top: 10px">
            </div>
          </a-col>
        </a-row>
      </div>

    </a-layout-content>
  </a-layout>


</template>

<script>
import * as echarts from "echarts";
import {
  MailOutlined,
  FolderOutlined,
  NodeIndexOutlined,
  ProfileOutlined,
  PieChartOutlined, SearchOutlined, InfoCircleOutlined, FireOutlined, NotificationOutlined,
} from "@ant-design/icons-vue";
import {ref} from "vue";
import WsvHeaderT1 from "@/components/layouts/base-headers/WsvHeaderT1";
import Web3Menu from "@/views/901-web3-demo/modules/Web3Menu";
import CardWeb3N1 from "@/views/901-web3-demo/modules/CardWeb3N1";
import CardWeb3N2 from "@/views/901-web3-demo/modules/CardWeb3N2";
import CardWeb3N3 from "@/views/901-web3-demo/modules/CardWeb3N3";


export default {
  name: "Web3Home",
  components: {
    //eslint-disable-next-line vue/no-unused-components
    PieChartOutlined,
    //eslint-disable-next-line vue/no-unused-components
    MailOutlined,
    //eslint-disable-next-line vue/no-unused-components
    FolderOutlined,
    //eslint-disable-next-line vue/no-unused-components
    ProfileOutlined,
    //eslint-disable-next-line vue/no-unused-components
    NodeIndexOutlined,
    SearchOutlined,
    InfoCircleOutlined,
    FireOutlined,
    NotificationOutlined,
    WsvHeaderT1,
    Web3Menu,
    CardWeb3N1,
    CardWeb3N2,
    CardWeb3N3,
  },
  watch: {},
  data() {
    return {
      searchContent: '',
      selectedKeysHead: ref(['1']),
      selectedKeysLeft: ref(['1']),
      collapsed: false
    }
  },
  methods: {
    init() {
      setTimeout(() => {
        this.initCharts1();
      }, 300)
    },
    initCharts1() {
      let myChart1 = echarts.init(document.getElementById('chart1'));
      let option1 = {
        title: {
          //text: 'Stacked Area Chart',
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            label: {
              backgroundColor: '#6a7985'
            }
          }
        },
        legend: {
          data: ['总锁仓', '质押',]
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [
          {
            type: 'category',
            boundaryGap: false,
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: [
          {
            name: '总锁仓',
            type: 'line',
            stack: 'Total',
            areaStyle: {},
            emphasis: {
              focus: 'series'
            },
            data: [150, 232, 201, 154, 190, 330, 410]
          },
          {
            name: '质押',
            type: 'line',
            stack: 'Total',
            areaStyle: {},
            emphasis: {
              focus: 'series'
            },
            data: [320, 332, 301, 334, 390, 330, 320]
          },
        ]
      };
      myChart1.setOption(option1);
    },
  },
  mounted() {
    this.init()
  }
}
</script>

<style scoped>
.logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  /*background: rgba(255, 255, 255, 0.3);*/
}

/*menu中theme=dark的配置，修改背景和字体*/
.ant-menu.ant-menu-dark, .ant-menu-dark .ant-menu-sub, .ant-menu.ant-menu-dark .ant-menu-sub {
  /*color: rgba(255, 255, 255, 0.65);*/
  /*background: rgb(52, 107, 159);*/
}

.menu {
  padding-top: 8px;
}

.content {
  padding: 10px 20px 20px;
}

.a-menu .a-sub-menu {
  /*background: white;*/
}

.site-layout-content {
  min-height: 280px;
  padding: 24px;
  /*background: #fff;*/
}

[data-theme='dark'] .site-layout-content {
  background: #141414;
}


.layout-sider {
  background: white;
  /*min-height: 100vh;*/
  min-height: 500px;
  /*flex-direction: column;*/
}

/*切换按钮*/
:deep(.ant-layout-sider-trigger) {
  background: white;
}

:deep(.ant-layout-sider-trigger .anticon) {
  /*color: #e74d6e;*/
  color: rgb(0, 21, 41);
}

</style>