<template>
  <a-layout-header class="header">
    <span style="float: left">
      <slot name="app_logo"/>
      <slot name="menu"/>
    </span>
    <span style="float: right;">
      <slot name="support"/>
      <slot name="avatar"/>
     <a-avatar style="color: white; background-color: rgb(231,77,110)">U</a-avatar>
    </span>
    <!--<anti-error-mask-layout :antiErrorPointer="antiErrorPointerLocal"/>-->
  </a-layout-header>
</template>

<script>
/*本组件为DEMO交付专用组件，非常节俭！*/

export default {
  name: "WsvHeaderB1",
  props: {
    //antiErrorPointer: {
    //  type: Boolean,
    //  required: false,
    //  default: false,
    //},
    //antiErrorValue: {
    //  type: Number,
    //  required: false,
    //  /*低于500像素宽度则提示页面错乱*/
    //  default: 500
    //}
  },
  components: {
  },
  data() {
    return {
      //antiErrorPointerLocal: this.antiErrorPointer,
      //antiErrorValueLocal: this.antiErrorValue,
    }
  },
  created() {
    this.init()
  },
  mounted() {
    ///*监听页面宽度，防止过窄*/
    //window.addEventListener('resize', this.handleResize)
  },
  beforeUnmount() {
    ///*消除监听*/
    //window.removeEventListener('resize', this.handleResize)
  },
  methods: {
    init() {},
    handleResize() {
      /*页面过窄触发器*/
      //this.antiErrorPointerLocal = window.innerWidth < this.antiErrorValueLocal;
    },
  },

}
</script>

<style scoped>
.header {
  padding-left: 12px;
  padding-right: 12px;
  background: white;
}

</style>