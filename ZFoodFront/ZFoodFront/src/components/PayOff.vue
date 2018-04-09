<template>
    <div>

        <div class="orderBlock" v-for="(v,i) in orderList">
          <div class="orderBlockHeader">{{v.shopName}}</div>

          <div v-for="(oi,item) in v.orderItems">
            <div style="margin-top: 0.5em;">
              <div style="display: inline-block;float: left;color: #f5926b;min-width: 4em;margin-left: 2em;">
                {{oi.foodname}}:
              </div>

              <div>
                ￥{{oi.unitprice}}
                <div class="numButtom">
                <div class="subButton"><img @click="SubOrderItemNum(i,item)" style=" width: 1.3em;" src="/static/img/zfood/sub-6x-rn.png"/></div>
                  <div style="display:inline-block;min-width: 2em;text-align: center;">{{oi.num}}</div>
                <div class="plusButtom"><img @click="AddOrderItemNum(i,item)" style=" width: 1.3em;" src="/static/img/zfood/plus-6x-rn.png"/></div>
                  <button @click="DelOrderItem(i,item)" class="btn" style="height: 1.5em;font-size: 1em;padding-top: 0;margin-left:0.1em;color: white;background-color:#fb8848; ">删除</button>
              </div>
              </div>

            </div>
          </div>

          <div style="position: absolute;bottom: 1em;width: 100%;">
            <div style="margin-left: 2em;color:#fb8848; ">备注:</div>
            <div>
            <input class="form-control" style="width: 70%;margin-left:2em;display: inline-block;"/>
            <button style="display: inline-block;color: white;background-color: #fb8848;float: right;margin-right: 1em;" class="btn" @click="ToPay(i)" data-toggle="modal" data-target="#MyModal">付款</button>
            </div>
          </div>
        </div>

      <div class="modal fade" id="MyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header" style="background-color: #e9e9e9">
              <h5>订单详情</h5>
              <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>
            <div class="modal-body">

            </div>
          </div>
        </div>
      </div>

    </div>
</template>

<style>
  .orderBlock{
    width: 35em;
    min-height: 20em;
    margin: 5em auto;
    background-color: white;
    border-radius: 10px;
    box-shadow: 0 0 5px #fb8848;
    position: relative;
  }
  .orderBlockHeader{
    background-color: #fb8848;
    color: white;
    font-size: 1.7em;
    padding-left: 0.2em;
  }
  .plusButtom{
    cursor: pointer;
    width: 1.5em;
    text-align: center;
    background-color: #f5f2f6;
    display: inline-block;
    border-radius: 8px;
    border-top-left-radius: 0;
    border-bottom-left-radius: 0;
  }
  .subButton{
    cursor: pointer;
    width: 1.5em;
    text-align: center;
    background-color: #f5f2f6;
    display: inline-block;
    margin-left: 5em;
    border-radius: 8px;
    border-top-right-radius: 0;
    border-bottom-right-radius: 0;
  }
  .numButtom{
    float: right;
    margin-right: 1em;
  }
</style>

<script>
    export default{
        data () {
            return {
                orderList:[],
                userMsg:{}
            }
        },
      methods:{
        ToPay(orderNo){

        },
        SubOrderItemNum(orderNo,orderItemNo){
            if(this.orderList[orderNo]['orderItems'][orderItemNo]['num']>1) {
              this.orderList[orderNo]['orderItems'][orderItemNo]['num']--;
              this.orderList[orderNo]['price']=this.orderList[orderNo]['price']-this.orderList[orderNo]['orderItems'][orderItemNo]['unitprice'];
            }
        },
        AddOrderItemNum(orderNo,orderItemNo){
          this.orderList[orderNo]['orderItems'][orderItemNo]['num']++;
          this.orderList[orderNo]['price']=this.orderList[orderNo]['price']+this.orderList[orderNo]['orderItems'][orderItemNo]['unitprice'];
        },
        DelOrderItem(orderNo,orderItemNo){
          if(this.orderList[orderNo]['orderItems'].length===1){
            this.orderList.splice(orderNo,1);
          }else{
            this.orderList[orderNo]['orderItems'].splice(orderItemNo,1);
          }
        }
      },
      mounted(){
            this.$http.post('/zfood/shoppingCart',{headers: {"Content-Type": "application/json"}}).then(
                response=>{
                  this.orderList=response.data;
                }
            );
            this.$http.post('/zfood/user/message',{headers: {"Content-Type": "application/json"}}).then(
              response=>{
                    this.userMsg=response.data;
                    console.log(this.userMsg);
                }
            );
      }
    }
</script>
