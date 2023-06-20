<template>
  <a-modal
      :title="title"
      :visible="visible"
      :style="'width: 66%'"
      @cancel="$emit('update:visible', false)"
  >
    <template #footer>
      <a-button type="primary" key="back" @click="$emit('update:visible', false)">返回</a-button>
    </template>
    <div style="height: 280px;overflow-y: auto;">
        {{ content }}
    </div>
  </a-modal>

</template>

<script>
export default {
  /*
  * a very simple modal，使用案例：
  * <modal-b1 v-model:visible="modals.visibleM1" title="账户列表" :content="accountList"/>
  * */
  name: "ModalB1",
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