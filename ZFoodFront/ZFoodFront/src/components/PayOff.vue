<template>
    <div>

        <!--<div class="orderBlock" v-for="(v,i) in orderList">
          <div class="orderBlockHeader">{{v.shopName}}</div>

          <div>
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
          </div>


          <div style="height: 8em;"></div>
          <div style="">&lt;!&ndash;position: absolute;bottom: 1em;width: 100%;&ndash;&gt;

            <div v-if="c!==undefined" v-for="(c,item) in couponChoose" style="background-color: #20c997;margin-bottom: 0;clear: both;">asdas</div>&lt;!&ndash;undefined可取消&ndash;&gt;
            <div
                 data-toggle="modal" data-target="#CouponLIST"
                 @click="CouponList(i)"
                 style="background-color: #e1e1e1;margin: 0 1em 0 1.6em;height: 2.5em;color: #a9a9a9;font-size: 1.2rem;border-radius:5px; ">
              <div style="margin-left: 0.5em;display: inline-block;padding-top: 0.5em;">选择优惠券</div>
              <img src="/static/img/zfood/chevron-right-8x.png" style="float: right;height: 1.5em;width:1em;margin-top: 0.5em;margin-right: 0.2em;">
            </div>

            <div style="margin-left: 2em;color:#fb8848; ">备注:</div>
            <div>
            <input class="form-control" style="width: 70%;margin-left:2em;display: inline-block;"/>
            <button style="display: inline-block;color: white;background-color: #fb8848;float: right;margin-right: 1em;" class="btn" @click="ToPay(i)" data-toggle="modal" data-target="#MyModal">付款</button>
            </div>
          </div>
        </div>-->
      <MyHeader :userState="0"></MyHeader>
      <div class="orderBlock" v-for="(v,i) in orderList">

        <div class="orderBlockHeader">{{v.shopName}}</div>

        <div style="min-height: 5em;">
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
        </div>


        <!--<div style="height: 8em;"></div>-->

        <div style="margin-top: 2em;"><!--position: absolute;bottom: 1em;width: 100%;-->

          <div v-if="c!==undefined&&c['order']===i" v-for="(c,item) in couponChoose">
            <div style="background-color: #ebebeb;margin: 0 1em 0 1.6em;min-height: 3.5em;color: #b8b8b8;font-size: 1.2rem;border-radius:5px;margin-top: 0.4em;padding-left:0.5em; " >
              <div style="display: inline-block;">
                <div>{{couponList[item]['name']}}</div>
                <div style="font-size: 0.4em;margin-left: 0.5em;word-wrap:break-word;">{{couponList[item]['detail']}}
                </div>
              </div>

              <div style="display: inline-block;float: right;font-size: 2em;cursor: pointer;" @click="DelCouponChoose(item)">&times;</div>
            </div>
          </div><!--undefined可取消-->
          <div
            data-toggle="modal" data-target="#CouponLIST"
            @click="CouponList(i)"
            style="background-color: #e1e1e1;margin: 0 1em 0 1.6em;height: 2.5em;color: #a9a9a9;font-size: 1.2rem;border-radius:5px;margin-top: 0.4em; ">
            <div style="margin-left: 0.5em;display: inline-block;padding-top: 0.5em;">选择优惠券</div>
            <img src="/static/img/zfood/chevron-right-8x.png" style="float: right;height: 1.5em;width:1em;margin-top: 0.5em;margin-right: 0.2em;">
          </div>

          <div style="margin-left: 2em;color:#fb8848; ">备注:</div>
          <div>
            <input v-model="v.remark" class="form-control" style="width: 70%;margin-left:2em;display: inline-block;"/>
            <button style="display: inline-block;color: white;background-color: #fb8848;float: right;margin-right: 1em;" class="btn" @click="ToPay(i)" data-toggle="modal" data-target="#MyModals">付款</button>
          </div>
        </div>

      </div>


      <div class="modal fade" id="CouponLIST" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="min-width: 40em;">
          <div class="modal-content" style="min-height: 35em;">
            <div class="modal-header" style="background-color: #fba374;color: white;">
              <h4>选择优惠券</h4>
              <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>


            <div class="modal-body">

              <div>
                  <!--<a v-for="(f,index) in couponList" class="list-group-item list-group-item-action"  href="#" style="word-wrap:break-word;"
                       @click="ChooseCoupons(index)" :class="couponChoose[index]===undefined?'':'active activeFont'">
                    <div style="font-size: 1.2em;">{{f.name}}</div>
                    <div style="font-size: 0.8em;">{{f.detail}}</div>
                  </a>--><!--:style="f.state===-1?couponOutStyle:couponStyle"-->
                <div
                  style="height: 120px;width: 480px; margin: 0 auto;margin-bottom:2em;position: relative; word-wrap:break-word;border: 0;padding: 0"
                  :style="CouponImgStyle(f.state)"
                  href="#"
                  v-for="(f,index) in couponList">

                  <div style="display: inline-block;color: #b7512e;">
                    <span style="font-size: 1.5em;margin-left: 1em;">{{f.name}}</span>
                    <div style="width: 350px;padding-left: 32px;word-wrap:break-word;font-size: 0.8em;">{{f.detail}}</div>
                    <div style="position: absolute;bottom: 0.1em;padding-left: 0.5em;color: white">{{f.startTime}}<span v-if="f.startTime!==null">至</span>{{f.endTime}}</div>
                  </div>
                  <button
                    v-if="f.state!==-1&&f.state!==0"
                    @click="ChooseCoupons(index)"
                    class="btn"
                    :disabled="couponChoose[index]!==undefined&&couponChoose[index]['order']!==currentOrderIndex||ButtonText(index)==='不可用'"
                    :class="couponChoose[index]!==undefined&&couponChoose[index]['order']!==currentOrderIndex||ButtonText(index)==='不可用'?'disabled':''"
                    style="background-color: rgba(237,119,56,0.9);color: white;float: right;width: 5em;margin-right: 0.7em;margin-top: 2.5em;">
                    {{ButtonText(index)}}</button>
                </div>

              </div>


            </div>

          </div>
        </div>
      </div>

      <div class="modal fade" id="MyModals" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" >
          <div class="modal-content" style="min-height: 35em;">
            <div class="modal-header" style="background-color: #e9e9e9">
              <h4>订单详情</h4>
              <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>
            <div class="modal-body" style="padding-bottom: 20em;">
              <div>
              <div style="display: inline-block;vertical-align: text-top;">
                <div class="optionText" style="margin-top: 1.4rem;">收货人:</div>
                <div class="optionText" style="margin-top: 1.6rem;">收货地址:</div>
                <div class="optionText" style="margin-top: 1.6rem;" >联系电话:</div>
              </div>
              <div style="display: inline-block; width: 18em;float: right;margin-right: 2em;">
              <input class="form-control" v-model="userMsg.name" style="margin-top: 1.2rem;border: 0;background-color: transparent;font-size: 1.3em;color: #fb8848;"/>
              <input class="form-control"v-model="userMsg.address" style="margin-top: 1.4rem;border: 0;background-color: transparent;font-size: 1.3em;color: #fb8848;"/>
              <input class="form-control" v-model="userMsg.phone" style="margin-top: 1.2rem;border: 0;background-color: transparent;font-size: 1.3em;color: #fb8848;"/>
              </div>
              </div>
              <div style="position: absolute;bottom: 1.2em;font-size: 1.3em;">
                <div style="display: inline-block;font-size: 0.7em;color: #a7a7a7;">
                原价:<br>
                包装费:<br>
                运送费:<br>
                优惠:
                </div>
                <div style="display: inline-block;font-size: 0.7em;margin-left: 2em;color: #a7a7a7">
                  {{amount}}￥<br>
                  {{packPrice}}￥<br>
                  {{sendPrice}}￥<br>
                  {{preferential}}￥</div>
                <div style="font-size: 1em;"> 总价:
                  <span style="color: #fb8848;font-size: 1.5em;margin-left: 1em;">{{amount+packPrice+sendPrice-preferential}}￥</span>
                </div>
              </div>
              <button style="float: right;position: absolute;bottom: 1.3em;right: 1.2em;color: white;background-color: #fb8848" class="btn" @click="CommitOrder">确认并付款</button>
            </div>
          </div>
        </div>
      </div>

    </div>
</template>

<style scoped>
  .activeFont{
    color: white;
  }
  .orderBlock{
    width: 35em;
    /*min-height: 20em;*/
    margin: 5em auto;
    background-color: white;
    border-radius: 10px;
    box-shadow: 0 0 5px #fb8848;
    /*padding-bottom: 10em;*/
    padding-bottom: 1em;
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
    border-radius: 5px;
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
    border-radius: 5px;
    border-top-right-radius: 0;
    border-bottom-right-radius: 0;
  }
  .numButtom{
    float: right;
    margin-right: 1em;
  }
  .optionText{
    font-size: 1.5em;
  }
</style>

<script>
  import MyHeader from '../components/Header.vue';
    export default{
        data () {
            return {
                orderList:[],
                userMsg:{},
                orderMark:'',

                amount:0,
                preferential:0,
                packPrice:0,
                sendPrice:2,

                couponList:[],
                couponChoose:{},
                currentOrderIndex:0,
                couponStyle:{
                  backgroundImage: "url(" + require("../assets/coupon-shop.png") + ")"
                },
                couponOutStyle:{
                backgroundImage: "url(" + require("../assets/coupon-shop-out.png") + ")"
              },
              couponUsedStyle:{
                backgroundImage: "url(" + require("../assets/coupon-shop-used.png") + ")"
              }
            }
        },
      components:{
        MyHeader
      },
      methods:{
        CouponImgStyle(state){
            switch (state){
              case 1:return this.couponStyle;
              case 0:return this.couponUsedStyle;
              case -1:return this.couponOutStyle;
            }
        },
        ButtonText(index){
          if(this.orderList[this.currentOrderIndex]===undefined)return '';
          let text='不可用';
          let type=true;
          let itemList=this.orderList[this.currentOrderIndex]['orderItems'];
          itemList.forEach((v, i) => {
            if (this.couponList[index]['extra'] === null ) {//通用
              type = false;
              if(this.couponList[index]['condition']!==null){
                if(this.orderList[this.currentOrderIndex]['price']<parseFloat(this.couponList[index]['condition'])){//有条件优惠 总价格未满足condition
                  type = true;
                }
              }
            }else {//关联了食物
              if (v['foodId'] === this.couponList[index]['extra']) {//关联了食物
                type = false;
                if(this.couponList[index]['condition']!==null){
                  if(v['num']*v['unitprice']<parseFloat(this.couponList[index]['condition'])){//有条件优惠 指定食物的价格未满足condition
                    type = true;
                  }
                }
              }
            }
          });
            if(type) {
                return text;
            }
            text=this.couponChoose[index]===undefined?'使用':'取消';
            return text;
        },
        DelCouponChoose(index){
            this.couponChoose[index]=undefined;
        },
        ChooseCoupons(index){
            //this.couponChoose[index]===undefined?this.AddPreferential(index):this.SubPreferential(index);
            let couponMap=this.couponChoose[index]===undefined?{'order':this.currentOrderIndex}:undefined;
            this.$set(this.couponChoose,index,couponMap);
        },
        /*AddPreferential(index){
          if(this.couponList[index]['extra']!==null){//只对关联食物优惠
            let itemList=this.orderList[this.currentOrderIndex]['orderItems'];
            itemList.forEach((v, i) => {
              if (v['foodId'] === this.couponList[index]['extra']){
                if(this.couponList[index]['percent']===1) {//百分比优惠
                  this.preferential = this.preferential +v['num'] * v['unitprice']-v['num'] * v['unitprice']*v['discount']/100;
                }else{
                  this.preferential = this.preferential +v['discount'];
                }
              }
            });
          }else{//通用优惠
            if(this.couponList[index]['percent']===1) {//百分比优惠
              this.preferential =this.preferential+this.orderList[this.currentOrderIndex]['price']-this.orderList[this.currentOrderIndex]['price']*this.couponList[index]['discount']/100;
            }else{
              this.preferential = this.preferential +this.couponList[index]['discount'];
            }
          }
        },*/
        CouponList(index){
            this.currentOrderIndex=index;
        },
        ToPay(orderNo){
          this.currentOrderIndex = orderNo;
          //this.amount = this.orderList[orderNo]['price'];
          let coupons = [];
          for (let key in this.couponChoose) {
              if(this.couponChoose[key]!==undefined) {
                  coupons.push(this.couponList[key]);
              }
          }
          let data = JSON.stringify({'couponList': coupons, 'orderItems': [this.orderList[this.currentOrderIndex]]});
          this.$http.post('/zfood/userCouponItem/getAmount', data, {headers: {"Content-Type": "application/json"}}).then(
            response => {
              this.preferential = response.data.preferential;
              this.amount = response.data.amount;
              this.packPrice = response.data.pack;
            }
          )
        },
        SubOrderItemNum(orderNo,orderItemNo){
            if(this.orderList[orderNo]['orderItems'][orderItemNo]['num']>1) {
              this.orderList[orderNo]['orderItems'][orderItemNo]['num']--;
              this.orderList[orderNo]['price']=this.orderList[orderNo]['price']-this.orderList[orderNo]['orderItems'][orderItemNo]['unitprice'];
              let data=JSON.stringify(this.orderList[orderNo]['orderItems'][orderItemNo]);
              this.$http.post('/zfood/shoppingCart/edit',data,{headers: {"Content-Type": "application/json"}});
            }
        },
        AddOrderItemNum(orderNo,orderItemNo){
          this.orderList[orderNo]['orderItems'][orderItemNo]['num']++;
          this.orderList[orderNo]['price']=this.orderList[orderNo]['price']+this.orderList[orderNo]['orderItems'][orderItemNo]['unitprice'];
          let data=JSON.stringify(this.orderList[orderNo]['orderItems'][orderItemNo]);
          this.$http.post('/zfood/shoppingCart/edit',data,{headers: {"Content-Type": "application/json"}});
        },
        DelOrderItem(orderNo,orderItemNo){
          if(this.orderList[orderNo]['orderItems'].length===1){//直接删除订单
            event.returnValue = confirm("确定删除订单吗");
            if (event.returnValue) {
              let data=JSON.stringify({'orderItemId':this.orderList[orderNo]['orderItems'][orderItemNo]['orderItemId'],'orderId':this.orderList[orderNo]['orderId']});
              this.$http.post('/zfood/shoppingCart/del',data,{headers: {"Content-Type": "application/json"}});
              this.orderList.splice(orderNo, 1);
            }
          }else{
            let data=JSON.stringify({'orderItemId':this.orderList[orderNo]['orderItems'][orderItemNo]['orderItemId']});
            this.$http.post('/zfood/shoppingCart/del',data,{headers: {"Content-Type": "application/json"}});
            this.orderList[orderNo]['price']=this.orderList[orderNo]['price']-this.orderList[orderNo]['orderItems'][orderItemNo]['unitprice']*this.orderList[orderNo]['orderItems'][orderItemNo]['num'];
            this.orderList[orderNo]['orderItems'].splice(orderItemNo,1);
          }
        },
        CommitOrder(){
          let coupons = [];
          for (let key in this.couponChoose) {
            if(this.couponChoose[key]!==undefined) {
              coupons.push(this.couponList[key]);
            }
          }
          this.orderList[this.currentOrderIndex]['sendprice']=this.sendPrice;
          this.orderList[this.currentOrderIndex]['price']=this.amount+this.packPrice+this.sendPrice;
          this.orderList[this.currentOrderIndex]['preferential']=this.preferential;
          this.orderList[this.currentOrderIndex]['orderTime']=new Date();
          this.orderList[this.currentOrderIndex]['receiverName']=this.userMsg['name'];
          this.orderList[this.currentOrderIndex]['address']=this.userMsg['address'];
          this.orderList[this.currentOrderIndex]['phone']=this.userMsg['phone'];
          let data = JSON.stringify({'couponsItems': coupons, 'order': this.orderList[this.currentOrderIndex]});
          this.$http.post('/zfood/shoppingCart/commit',data,{headers: {"Content-Type": "application/json"}});
          this.orderList.splice(this.currentOrderIndex,1);
          $('#MyModals').modal('hide');
          alert('下单成功');
        }
      },
      mounted(){
            this.$nextTick(function () {
              this.$http.post('/zfood/shoppingCart',{headers: {"Content-Type": "application/json"}}).then(
                response=>{
                  this.orderList=response.data;
                }
              );
            });
            this.$http.post('/zfood/user/message',{headers: {"Content-Type": "application/json"}}).then(
              response=>{
                    this.userMsg=response.data;
                }
            );
            this.$http.post('/zfood/userCouponItem',{headers: {"Content-Type": "application/json"}}).then(
                response=>{
                    this.couponList=response.data;
                }
            )
      }
    }
</script>
