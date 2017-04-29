<template>
  <div class="homebk">

  </div>
</template>
<script>
  import {mapGetters} from 'vuex'
  export default{
    data () {
      return {

      }
    },
    computed: mapGetters([
      'role'
    ]),
    methods: {

    }
  }

</script>

<style>
  .homebk{
    width: 100%;
    background-color: #D3DAD3;
    background: url("./../../assets/bg.png") no-repeat;
    background-size: 100%;
    height: 90%;
    position: absolute;
    background-position-y: -50px;
  }
</style>
