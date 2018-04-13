<template>
    <div>
      <my-header  :userState="2"></my-header>
      <div class="leftBar">
        <!-- 导航 -->
        <div class="navBar">

          <div class="nav-edit margin-top-5">
            <ul id="Tab" class="nav navbar-nav ">
              <li class="text-center margin-top-2 active">
                <a v-on:click="TagChange(0)" class="nav-link a-green" href="#courierMessage" data-toggle="tab">个人资料</a></li><div class="dropdown-divider"></div>
              <li class="text-center margin-top-2">
                <a v-on:click="TagChange(1)"class="nav-link a-green" href="#currentOrder" data-toggle="tab">当前订单</a></li><div class="dropdown-divider"></div>
              <li class="text-center margin-top-2">
                <a v-on:click="TagChange(2)"class="nav-link a-green" href="#receiveOrder" data-toggle="tab">可接订单</a></li><div class="dropdown-divider"></div>
              <li class="text-center margin-top-2 margin-bottom-5">
                <a v-on:click="TagChange(3)" class="nav-link a-green" href="#historyOrder" data-toggle="tab">已完成订单</a></li>
            </ul>
          </div>

        </div>
      </div>

      <div class="editContent" style="margin-bottom: 2em;min-height: 32em;">
        <div class="headImgBack"><div class="usrName">{{courierMsg.courierName}}</div></div>

        <div class="tab-content">

          <div class="tab-pane fade in active" id="courierMessage">
            <form>
              <div class="textContent">
                <div class="lineText">名称:</div><input class="form-control editText" type="text" name="shopName" v-model="courierMsg.courierName"><br>
                <div class="lineText">联系电话:</div><input class="form-control editText" type="text" name="shopPhone"  v-model="courierMsg.phone"><br>
                <div class="lineText">收入:</div><span style="color: red;font-size: 2em;">{{courierMsg.income}}￥</span>
                <div><input class="btn subButton" style="width: 8em; margin-top: 2em;"   value="保存" @click="SubmitCourierMsg"></div>
              </div>
            </form>
          </div>

          <div class="tab-pane fade pane" id="currentOrder">

            <div id="accordion0"  style="padding-bottom: 2em;">

              <div class="card orderItem" v-for="(v,i) in currentOrders">
                <div class="card-header" :id="v.orderId+'Pane'" :data-target="'#'+v.orderId" data-toggle="collapse"  aria-expanded="false" aria-controls="collapseOne">
                  <div style="float: left;margin-left: 0;color: #62c54e;font-size: 1.5em;display: inline-block;">
                    <div>{{v.shopName}}</div>
                    <div style="font-size: 0.2em;margin-left: 1em;">订单号:{{v.orderId}}</div>
                  </div>
                  <button style="margin-top:0.4em;float: right; background-color: #6ec561;color: white;" class="btn"  @click="CompleteOrder(i, $event)">完成订单</button>
                </div>

                <div :id="v.orderId" class="collapse" :aria-labelledby="v.orderId+'Pane'" data-parent="#accordion0">
                  <div class="card-body">
                    <div>

                      <div style="margin-bottom: 2em; margin-left: 1em;color: #62c54e;font-size: 1.2em;float: left;">
                        <div>商家地址</div>
                        <div style="text-align: center;">{{v.shopAddress}}</div>
                      </div>
                      <div style="float: right;color:  #62c54e;font-size: 1.2em;">
                        <div>目的地址</div>
                        <div style="text-align: center;">{{v.address}}</div>
                      </div>
                      <div style="padding-top: 2em;width: 30em;margin: 0 auto;text-align: center;color:  #1d7321; ">
                        <div v-for="(oi,index) in v.orderItems" >
                          <div style="display: inline-block;margin-right: 10px;min-width: 80px;">{{oi.foodname}}</div>
                          <div style="display: inline-block;">{{oi.num}}份</div>
                        </div>
                      </div>

                    </div>

                  </div>
                </div>
              </div>

            </div>

          </div>

          <div class="tab-pane fade pane" id="receiveOrder">

            <div id="accordion1"  style="padding-bottom: 2em;">

              <div class="card orderItem" v-for="(v,i) in receiveOrders">
                <div class="card-header" :id="v.orderId+'Pane'" :data-target="'#'+v.orderId" data-toggle="collapse"  aria-expanded="false" aria-controls="collapseOne">
                  <div style="float: left;margin-left: 0;color: #62c54e;font-size: 1.5em;display: inline-block;">
                    <div>{{v.shopName}}</div>
                    <div style="font-size: 0.2em;margin-left: 1em;">订单号:{{v.orderId}}</div>
                  </div>
                  <button style="float: right; background-color: #6ec561;color: white;margin-top: 0.4em;" class="btn"  @click="ReceiveOrder(i, $event)">接单</button>
                  <span style="float: right;color: #62c54e;font-size: 1.5em;margin-top: 0.3em;">配送费:<span>{{v.sendprice}}￥</span></span>
                </div>

                <div :id="v.orderId" class="collapse" :aria-labelledby="v.orderId+'Pane'" data-parent="#accordion1">
                  <div class="card-body">
                   <div>

                     <div style="margin-bottom: 2em; margin-left: 1em;color: #62c54e;font-size: 1.2em;float: left;">
                       <div>商家地址</div>
                       <div style="text-align: center;">{{v.shopAddress}}</div>
                     </div>
                     <div style="float: right;color:  #62c54e;font-size: 1.2em;">
                       <div>目的地址</div>
                       <div style="text-align: center;">{{v.address}}</div>
                     </div>
                     <div style="padding-top: 2em;width: 30em;margin: 0 auto;text-align: center;color:  #1d7321; ">
                       <div v-for="(oi,index) in v.orderItems" >
                         <div style="display: inline-block;margin-right: 10px;min-width: 80px;">{{oi.foodname}}</div>
                         <div style="display: inline-block;">{{oi.num}}份</div>
                       </div>
                     </div>

                   </div>

                  </div>
                </div>
              </div>

            </div>


          </div>

          <div class="tab-pane fade pane" id="historyOrder">

            <div id="accordion2"  style="padding-bottom: 2em;">

              <div class="card orderItem" v-for="(v,i) in historyOrders">
                <div class="card-header" :id="v.orderId+'Pane'" :data-target="'#'+v.orderId" data-toggle="collapse"  aria-expanded="false" aria-controls="collapseOne">
                  <div style="float: left;margin-left: 0;color: #62c54e;font-size: 1.5em;display: inline-block;">
                    <div>{{v.shopName}}</div>
                    <div style="font-size: 0.2em;margin-left: 1em;">订单号:{{v.orderId}}</div>
                  </div>
                  <span style="float: right;color: #62c54e;font-size: 1em;margin-top: 0.8em;">配送费:<span>{{v.sendprice}}￥</span></span>
                </div>

                <div :id="v.orderId" class="collapse" :aria-labelledby="v.orderId+'Pane'" data-parent="#accordion2">
                  <div class="card-body">
                    <div>

                      <div style="margin-bottom: 2em; margin-left: 1em;color: #62c54e;font-size: 1.2em;float: left;">
                        <div>商家地址</div>
                        <div style="text-align: center;">{{v.shopAddress}}</div>
                      </div>
                      <div style="float: right;color:  #62c54e;font-size: 1.2em;">
                        <div>目的地址</div>
                        <div style="text-align: center;">{{v.address}}</div>
                      </div>
                      <div style="padding-top: 2em;width: 30em;margin: 0 auto;text-align: center;color:  #1d7321; ">
                        <div v-for="(oi,index) in v.orderItems" >
                          <div style="display: inline-block;margin-right: 10px;min-width: 80px;">{{oi.foodname}}</div>
                          <div style="display: inline-block;">{{oi.num}}份</div>
                        </div>
                      </div>

                    </div>

                  </div>
                </div>
              </div>

            </div>
          </div>

          </div>
      </div>


    </div>
</template>

<style scoped>

  /*左边菜单*/
  .leftBar{
    float: left;
  }
  .navBar{
    position: fixed;
    width: 12em;
    /*height: 25em;*/
    margin-left:5em;
    margin-top:2em;
    margin-right:2em;
    background-color: white;
  }
  .a-green{
    color: #62c54e;
  }
  /*右部*/
  .usrName{
    color: white;
    float: left;
    font-size: 2em;
    margin-left: 1em;
  }
  .editContent{
    margin-left:19em;
    margin-top:2em;
    width: 75%;
    min-height: 32em;
    background-color: white;
  }
  .headImgBack{
    height: 3em;
    width: 100%;
    background-color: #62c54e;
  }
  /*基本资料*/
  .textContent{
    margin-left: 5em;
  }
  .editText{
    width: 20em;
    display: inline-block;
  }
  .lineText{
    display: inline-block;
    width:5em;
    margin-top: 2em;
  }
  .subButton{
    background-color: #62c54e;
    color:white;
  }
  /*可接订单*/
  .orderItem{
    width: 80%;
    margin: 1em 10%;
  }
</style>

<script>
  import MyHeader from '../components/Header.vue';
  export default{
        data () {
            return {
                courierMsg:{
                  courierId:this.$route.params.courierId,
                  courierName:this.$route.params.courierName,
                  phone:this.$route.params.phone,
                  score:this.$route.params.score,
                  userId:this.$getLocalStorage('userId'),
                  headImg:this.$getLocalStorage('headImg'),
                  userName:this.$route.params.userName,
                  income:this.$route.params.income,
                  rateCount:this.$route.params.rateCount,
                  password:this.$route.params.password
                },
              receiveOrders:[],
              currentOrders:[],
              historyOrders:[]
            }
        },
    components:{
      MyHeader
    },
    methods:{
      TagChange(index){
        if (index === 1) {
          let data=JSON.stringify(this.courierMsg.courierId);
          this.$http.post('/zfood/order/courier/current',data,{headers: {"Content-Type": "application/json"}}).then(
            response=>{
                  this.currentOrders=response.data;
              }
          )
        }
          else if(index===2){
              this.$http.post('/zfood/order/courier/receive',{headers: {"Content-Type": "application/json"}}).then(
                  response=>{
                      this.receiveOrders=response.data;
                  }
              );
          }
          else if(index===3){
          this.$http.post('/zfood/order/courier/history',JSON.stringify(this.courierMsg.courierId),{headers: {"Content-Type": "application/json"}}).then(
              response=>{
                  console.log(response.data);
                  this.historyOrders=response.data;
              }
          )
        }
      },
      CompleteOrder(index,e){
        e.cancelBubble=true;
        let order={
          'orderId':this.currentOrders[index]['orderId'],
          'sendprice':this.currentOrders[index]['sendprice'],
          'shopId':this.currentOrders[index]['shopId'],
          'price':this.currentOrders[index]['price'],
          'courierId':this.courierMsg.courierId,
          'state':'0'
        };
        this.$http.post('/zfood/order/complete',JSON.stringify(order),{headers: {"Content-Type": "application/json"}}).then(
            response=>{
              if( this.historyOrders.length>0) {
                this.currentOrders[index]['state']='0';
                this.historyOrders.push(this.currentOrders[index]);
              }
              this.currentOrders.splice(index,1);
            }
        )
      },
      ReceiveOrder(index,e){
          e.cancelBubble=true;
          let order={
              'orderId':this.receiveOrders[index]['orderId'],
              'courierId':this.courierMsg.courierId,
              'state':'1'
          };
          let data=JSON.stringify(order);
          this.$http.post('/zfood/order/edit/courier',data,{headers: {"Content-Type": "application/json"}}).then(
              response=>{
                  if(response.data){
                      if( this.currentOrders.length>0) {
                        this.receiveOrders[index]['state']='1';
                        this.currentOrders.push(this.receiveOrders[index]);
                      }
                    this.receiveOrders.splice(index,1);
                  }
              }
          );

      },
      SubmitCourierMsg(){
          let data=JSON.stringify(this.courierMsg);
          this.$http.post('/zfood/courier/msg/edit',data,{headers: {"Content-Type": "application/json"}}).then(
              response=>{
                if(response.data['errorCode']===403){alert('重新登陆!');this.$goRoute('Login');return;}
                else{
                    alert('修改成功');
                }
              }
          )
      }
    },
    mounted(){
      $(function () {
        $('#Tab li:eq(0) a').tab('show');//eq()遍历第一个显示
      });
    },
    created(){
      if(this.$checkUserState()!==this.$route.path)
        this.$router.push(this.$checkUserState());
      if(this.courierMsg.courierId===undefined) {
        this.$http.post('/zfood/courier/msg', {headers: {"Content-Type": "application/json"}}).then(
            response=>{
                this.courierMsg=response.data;
            }
        );
      }
      this.$http.post('/zfood/order/courier/receive',{headers: {"Content-Type": "application/json"}}).then(
        response=>{
          this.receiveOrders=response.data;
        }
      );
    }
    }
</script>
