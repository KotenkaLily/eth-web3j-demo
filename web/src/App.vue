<template>
  <a-config-provider>
    <div id="app">
      <router-view />
    </div>
  </a-config-provider>

</template>

<script>
//window.onload = function() {
//  document.addEventListener('touchstart', function(event) {
//    if (event.touches.length > 1) {
//      event.preventDefault()
//    }
//  })
//  document.addEventListener('gesturestart', function(event) {
//    event.preventDefault()
//  })
//}
export default {
  components: {  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: left;
  color: #2c3e50;
}
</style>