<template>
  <a-menu
      v-model:selectedKeys="selectedKeysHead"
      theme="light"
      mode="horizontal"
      :style="{ lineHeight: '64px' }"
      class="top_menu"
  >
    <a-menu-item key="Web3Home" @click="gotoName('Web3Home')">首页</a-menu-item>
    <a-menu-item key="BlockInfo" @click="gotoName('BlockInfo')">区块链</a-menu-item>
    <a-menu-item key="ContractManage" @click="gotoName('ContractManage')">合约</a-menu-item>
    <a-menu-item key="ValidatorX"  @click="gotoName('ValidatorX')">图片验证</a-menu-item>
    <a-menu-item key="data">数据分析</a-menu-item>
    <a-menu-item key="management">管理</a-menu-item>
  </a-menu>

</template>

<script>
import {ref} from "vue";

export default {
  name: "Web3Menu",
  props: {
    content: () => Object,
  },
  data() {
    return {
      selectedKeysHead: ref([this.$route.name]),
      menuFolded: false,
    }
  },
  methods: {
    gotoName(name) {
      this.$router.push({name: name})
    }
  }
}
</script>

<style scoped>

.top_menu {
  float: right;
  padding-left: 8px;
  border: white 0;
}
</style>