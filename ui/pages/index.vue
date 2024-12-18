<template>
  <div style="width: 100%; min-height: 100vh; display: inline-flex; ">
    <div class="messages"></div>
    <div class="radar"></div>
  <div/>
</template>

<script>
export default {
  name: 'IndexPage'
}
</script>

<style>
*{
  margin: 0; padding: 0;
}

.messages{
  padding: 10px;
  width: 10%;
  background: #232323;
}

.radar{
  width: 90%;
  position: relative;
  background: #232323;
}
</style>
