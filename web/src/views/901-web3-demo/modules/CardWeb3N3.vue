<template>
  <div style="font-size: 20px">交易<a-divider type="vertical"/>Transaction</div>
  <a-card hoverable style="width: 90%;height:220px;overflow-y: auto; margin-top: 10px">
    <a-card-meta>
      <template #description>
        <div v-for="obj in transactionList" v-bind:key="obj.key">
          <div style="margin-top: 4px;margin-bottom: 2px;color: black">
            <span>Hash:{{obj.hash}}</span><a-divider type="vertical"/>
            <span>币种:{{obj.token}}</span>&nbsp;
            <span>gas:{{obj.gas}}</span>
            <br/>
            <span>付款方:{{obj.from}}</span>&nbsp;
            <span>收款方:{{obj.to}}</span>&nbsp;
            <span>金额:{{obj.value}}</span>

          </div>
        </div>
      </template>
    </a-card-meta>
  </a-card>
</template>

<script>
const transaction={"hash":"0xd794f7892a305cd2ec34d5c4c8c9be937dba04573c7b296b7d03ff4e1044521e","nonce":"0x5","blockHash":"0x2a0580a445b4edf9b0ef99d97c95d20e02bd4633f379aa93420568b2abcbad75","blockNumber":"0x6","transactionIndex":"0x0","from":"0x6333bd68a96b61aaaa40cbe7996b92285918637d","to":"0x8e3f02a75e69ab57e1c3aa65b2467d0cd6c4e533","value":"0x0","gasPrice":"0x51f4d5c00","gas":"0x419ce0","input":"0xa9059cbb00000000000000000000000022e7ca0780a3f7a97587d09ad1e83e7a11937b4a00000000000000000000000000000000000000000000000000000000000007d0","r":"0x94f1f05eecb2c7d7db17324cdade4fc331d8b9e64c7141e05fe5c70348a95106","s":"0x2ea0ded670733381bfac588165c5c9cede6de6642e4505366e559fa81ae1af54","v":27,"type":"0x0"}
transaction.from="***".concat(transaction.from.substr(transaction.from.length-5))
transaction.to="***".concat(transaction.to.substr(transaction.to.length-5))
const transactionList=[
  {key:1,hash:transaction.hash,from:transaction.from,to:transaction.to,gas:transaction.gas,token:'RTX',value:transaction.value},
  {key:2,hash:transaction.hash,from:transaction.from,to:transaction.to,gas:transaction.gas,token:'RTX',value:transaction.value},
  {key:3,hash:transaction.hash,from:transaction.from,to:transaction.to,gas:transaction.gas,token:'RTX',value:transaction.value},
  {key:4,hash:transaction.hash,from:transaction.from,to:transaction.to,gas:transaction.gas,token:'RTX',value:transaction.value},
  {key:5,hash:transaction.hash,from:transaction.from,to:transaction.to,gas:transaction.gas,token:'RTX',value:transaction.value},
];
console.log(transaction)
export default {
  name: "CardWeb3N2",
  components: {},
  props: {
    title: {type: String},
    description: {type: String},
  },
  mounted() {},
  data() {
    return {
      transactionList,
    }
  }
}
</script>

<style scoped>

</style>