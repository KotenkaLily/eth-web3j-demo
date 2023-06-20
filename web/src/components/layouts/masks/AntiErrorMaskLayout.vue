<template>
  <div>
    <!--防爆遮罩层-->
    <transition
        name="antiErrorMaskInOut"
        enter-active-class="antiErrorMaskFadeIn"
        leave-active-class="antiErrorMaskFadeOut"
    >
      <div class="antiErrorMask" v-if="antiErrorPointer">
        <div style="font-size: 36px;
        color: white;
        font-family: 'STZhongsong','Sitka Display',serif;text-align: center;
        margin-top: 33%;
">
          页面过窄导致布局错乱，请回调页面宽度来正常使用。
        </div>
      </div>
    </transition>
  </div>
</template>

<script>
import 'animate.css'

export default {
  name: "AntiErrorMaskLayout",
  props: {
    antiErrorPointer: {
      type: Boolean,
      required: true,
    },
  }
}
</script>

<style scoped>
.antiErrorMask {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background: rgba(231, 77, 110);
  opacity: 0.66;
  z-index: 1999;
}

@keyframes fadeSIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 0.66;
  }
}

@keyframes fadeSOut {
  from {
    opacity: 0.66;
  }
  to {
    opacity: 0;
  }
}

.antiErrorMaskFadeIn {
  animation: fadeSIn;
  animation-duration: 0.75s;
}

.antiErrorMaskFadeOut {
  animation: fadeSOut;
  animation-duration: 0.75s;
}
</style>