<template>
  <div style=" background-color: white;height: 500px;">

    <div id="accordion">

      <div class="card">
        <div class="card-header" id="headingTwo">
          <div  data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
            <span >下单人:</span>
            <span style="margin-left: 50px;">下单时间:</span>
            <span style="margin-left: 50px;">下单状态:</span>
            <span style="margin-left: 50px;">总额:</span>
          </div>
        </div>

        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
          <div class="card-body">
            AAA
          </div>
        </div>

      </div>

    </div>

    <button @click="onClickBotton">Click</button>
  </div>
</template>

<style scoped>
</style>

<script>
  export default{
    data () {
      return {msg: '这个是Home模板页'}
    },
    methods:{
      onClickBotton(){
        //this.$router.push({name:'ShopIndex',params:{shopId:'2'} ,query:{age:1000}  });
      }
    }

  }
</script>
