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

    <div style="background-color: #e0a800;height: 50px;">
      <div style="display:inline-block;margin:0 auto;width: 100px;background-color: red;height: 100%;"></div>
      <div style="background-color: #20c997;width: 50px;height: 100%;display: inline-block;float: right;position: absolute;"></div>
    </div>
    <div id = "left">我是左边</div>
    <div id = "right">我是右边</div>
    <div id = "center">我是中间</div>
  </div>
</template>

<style scoped>
  #left,#right{width: 200px;height: 200px; background-color: #ffe6b8;position: absolute;top:120px;}
  #left{left:0px;}
  #right{right: 0px;}
  #center{margin:2px 210px ;background-color: #eee;height: 200px; }
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
