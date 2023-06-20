<template>
  <a-card hoverable style="width: 300px">
    <a-card-meta :title="title" :description="description">
      <template #avatar>
        <img alt :src="require('@/views/901-web3-demo/assets/eth.jpeg')" :width="imgWidth"/>
      </template>
    </a-card-meta>
  </a-card>
</template>

<script>

export default {
  name: "CardWeb3N1",
  components: {},
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