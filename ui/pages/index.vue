<template>
  <div style="width: 100%; min-height: 100vh; display: inline-flex;">
    <div class="messages"></div>
    <div class="radar">
      <Port v-for="port in ports"
            :key="port.name"
            :port="port"
            :click-callback="addRoute"></Port>
    </div>
  </div>
</template>

<script>
export default {
  data(){
    return{
      socket: null,
      ports: []
    }
  },
  mounted() {
    this.defineSocket()
    setInterval(this.wakeSocketUp, 5000)
  },
  methods:{
    defineSocket(){
      this.socket = new WebSocket("ws://localhost:8083/websocket")

      this.socket.onopen = () => {
        this.socket.onmessage = (msg, ctx) => {
          let message = JSON.parse(msg.data)
          if(message.source === "AIRPORT"){
            this.setAirPort(message.airPort)
          }
        }
      }
    },
    addRoute(){

    },
    wakeSocketUp(){
      if(this.socket){
        if(this.ports.length === 0){
          this.socket.send("update")
        }
      }else{
        this.defineSocket()
      }
    },
    setAirPort(port){
      let ind = -1
      this.ports.forEach((row, i) => {
        if(row.name === port.name){
          ind = i
        }
      })
      if(ind>=0){
        this.ports.splice(ind, 1)
      }
      this.ports.push(port)
    }
  }
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
  background: transparent url("assets/grass.png");
  min-height: 100vh;
}
</style>
