<template>
  <a-modal
      :title="title"
      :visible="visible"
      :style="{width:'66%'}"
      @cancel="$emit('update:visible', false)"
  >
    <div style="min-height: 280px">
      <slot name="content"/>
    </div>
    <template #footer>
      <a-button type="primary" key="back" @click="$emit('update:visible', false)">返回</a-button>
    </template>
  </a-modal>

</template>

<script>
export default {
  /*
  * a quite simple modal，使用案例：
  * <modal-b2 v-model:visible="modals.visibleM1" title="账户列表"><template #content></template></modal-b2>
  * */
  name: "ModalB2",
  props:{
    visible:{
      type:Boolean,
      default: false
    },
    title:{
      type:String,
      default:'',
    },
    content:{
    //  一个泛型，使用Object会有vue warn
    }
  },
}
</script>

<style scoped>

</style>