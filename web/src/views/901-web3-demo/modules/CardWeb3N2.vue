<template>
  <a-card hoverable style="width: 300px">
    <a-card-meta :title="title" :description="description">
      <template #avatar>
        <img alt :src="require('@/views/901-web3-demo/assets/trx.png')" :width="imgWidth"/>
      </template>
    </a-card-meta>
    <a-divider/>
    <a-card-meta title="TRX供应量" description="90,577,456,767">
    </a-card-meta>
    <br/>
    <a-card-meta title="TRX质押">
      <template #description>
        <span>48.5%</span>
        <span style="float: right;color: red">2.3%<arrow-up-outlined /></span>
      </template>
    </a-card-meta>
  </a-card>
</template>

<script>

import {ArrowUpOutlined} from "@ant-design/icons-vue";

export default {
  name: "CardWeb3N2",
  components: {
    ArrowUpOutlined,
  },
  props: {
    title: {type: String},
    description: {type: String},
    imgWidth: {type: Number, default: 50},
  },
  mounted() {},
  data() {
    return {}
  }
}
</script>

<style scoped>

</style>