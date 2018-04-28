<template>
  <div>
   <!-- <div class="header">
      <a href=""><img class="logo" src="/static/img/zfood/logo-b.jpg" alt=""></a>
      <a v-on:click="Logout"  style="float: right;margin-top: 0.5em;margin-right:1em; color: #007bff;font-weight: 500;cursor: pointer;">退出</a>
    </div>-->
    <my-header :userState="1"></my-header>

    <div class="leftBar">
      <!-- 头像 -->
      <div class="headContent">
        <div class="headImgBack"></div>
        <div class="headImg">
          <form id="imgForm" method="post" enctype="multipart/form-data">
            <input id="uploadFile" name="uploadFile" type="file" style="display: none" data-user="NHVZG" @change="FileChange"/>
            <label for="uploadFile">
              <img id="uploadFileimg" class="userImg" style="cursor:pointer;" title="点击更改图片"  :src="[headImgSrc==='null'?'/static/img/zfood/logo-gray-square.jpg':headImgSrc]"><!--v-bind:src="this.headImgSrc"-->
            </label>
          </form>
        </div>
        <div style="text-align: center;margin-top: 10px;"><button @click="OnSubmitImg" class="btn subButton">保存头像</button></div>
      </div>
      <!-- 导航 -->
      <div class="navBar">

        <div class="nav-edit margin-top-5">
          <ul id="Tab" class="nav navbar-nav ">
            <li class="text-center margin-top-2 active"><a v-on:click="TagChange" class="nav-link a-orange" href="#shopMessage" data-toggle="tab">商店资料</a></li><div class="dropdown-divider"></div>
            <li class="text-center margin-top-2"><a v-on:click="TagChange"class="nav-link a-orange" href="#foodManage" data-toggle="tab">菜品管理</a></li><div class="dropdown-divider"></div>
            <li class="text-center margin-top-2"><a v-on:click="TagChange" class="nav-link a-orange" href="#couponManage" data-toggle="tab">优惠券单</a></li><div class="dropdown-divider"></div>
            <li class="margin-top-2 text-center "><a v-on:click="TagChange" class="nav-link a-orange" href="#orderNow" data-toggle="tab"style="display: inline-block;">当前订单</a><span class="badge" style="background-color: #ed5f01;color: white;" v-if="newOrdersCount>0">{{newOrdersCount}}</span></li><div class="dropdown-divider"></div>
            <li class="text-center margin-top-2 margin-bottom-5"><a v-on:click="TagChange" class="nav-link a-orange" href="#orderHistory" data-toggle="tab">历史订单</a></li>
          </ul>
        </div>

      </div>
    </div>

    <div class="editContent" style="margin-bottom: 10px;min-height: 32em;">
      <div class="headImgBack"><div class="usrName">{{shopMsg.shopName}}</div></div>

    <div class="tab-content">

      <div class="tab-pane fade in active" id="shopMessage">
        <form>
          <div class="textContent">
          <div class="lineText">商家名称:</div><input class="form-control editText" type="text" name="shopName" v-model="shopMsg.shopName"><br>
            <div class="lineText">商家地址:</div><input class="form-control editText" type="text" name="shopAddress" v-model="shopMsg.address"><br>
            <div class="lineText">商家电话:</div><input class="form-control editText" type="text" name="shopPhone" v-model="shopMsg.phone"><br>
            <div class="lineText">营业额:</div><span style="color: red;font-size: 2em;">{{shopMsg.income}}￥</span>
          <div><input class="btn subButton" style="width: 8em; margin-top: 2em;"   value="保存" @click="SubmitShoprMsg"></div><!--type="submit"-->
          </div>
        </form>
      </div>

      <div class="tab-pane fade pane" id="foodManage">
        <food-table>
        <!--  <template scope="props">
              <img class="smallpic" :src="[props.value.pic]"/>
          </template>-->
        </food-table>
      </div>

      <div class="tab-pane fade pane" id="couponManage">
        <coupon-table></coupon-table>
      </div>

      <div class="tab-pane fade" id="orderNow">

        <div style="padding: 50px;">
          <!--<div class="orderItem" v-for="(v,i) in currentOrders">
            <div  data-toggle="modal" data-target="#myModal"  class="orderMessage">
              <p  style="font-size: 1.5em;">{{v.shopName}}</p>
              <div style="position:absolute;bottom: 5px;width: 100%;">
                <span class="margin-left-2" style="font-size: 1em;">总计：<span style="color: red">{{v.price}}￥</span></span>
                <div class="" style="font-size: 1em;float: right;margin-right: 5px;">&nbsp;&nbsp;&nbsp;&nbsp;下单时间:{{v.orderTime}}</div>
                <span  class="margin-left-2" style="float: right;font-size: 1em;" v-if="v.state==='0'">订单完成</span>
                <span  class="margin-left-2" style="float: right;font-size: 1em;" v-else-if="v.state==='1'">正在配送</span>
                <span  class="margin-left-2" style="float: right;font-size: 1em;" v-else-if="v.state==='2'">已接单</span>
                <span  class="margin-left-2" style="float: right;font-size: 1em;" v-else-if="v.state==='3'">等待接单</span>
              </div>
            </div>
          </div>-->
          <div id="accordion">

            <div v-for="(v,i) in currentOrders" class="card" style="margin-bottom:20px;">
              <div class="card-header" id="headingTwo">
                <div class="btn btn-link collapsed" style="text-decoration: none;cursor: pointer" data-toggle="collapse"
                     :data-target="'#'+v.orderId" aria-expanded="false" aria-controls="collapseTwo">
                  <span>下单人:{{v.receiverName}}</span>
                  <span style="margin-left: 30px;">下单时间:{{v.orderTime}}</span>
                  <span style="margin-left: 30px;">下单状态:

                    <!--待接单-->
                    <span
                           v-if="v.state==='4'"
                           :id="v.orderId+'menuNew'"
                           style="color: #ed5f01;"
                           class="dropdown dropdown-toggle"
                           data-toggle="dropdown"
                           aria-haspopup="true"
                           aria-expanded="false">{{stateText(v.state)}}
                      <div  class="dropdown-menu" :aria-labelledby="v.orderId+'menuNew'">
                            <a class="dropdown-item"  :data-index="i" @click="ToReceive">接收订单</a>
                            <a class="dropdown-item"  :data-index="i" @click="ToReject">拒绝订单</a>
                     </div>
                    </span>

                    <span
                      v-if="v.state==='3'"
                      :id="v.orderId+'menuComplete'"
                      style="color: #ed5f01;"
                      class="dropdown dropdown-toggle"
                      data-toggle="dropdown"
                      aria-haspopup="true"
                      aria-expanded="false">{{stateText(v.state)}}
                    <div class="dropdown-menu" :aria-labelledby="v.orderId+'menuComplete'">
                            <a class="dropdown-item" :data-index="i" @click="ToSend">准备完成</a>
                     </div>
                    </span>

                    <span v-if="v.state==='2'" style="color: #ed5f01;">
                      {{stateText(v.state)}}
                    </span>

                    <span v-else-if="v.state==='1'">
                      {{stateText(v.state)}}
                    </span>

                    <span v-else-if="v.state==='-1'">
                      {{stateText(v.state)}}
                    </span>

                  </span>
                  <span style="margin-left: 30px;">总额:{{v.price-v.sendprice}}</span>
                  <div style="display:inline-block;margin-left: 30px;max-width:160px;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;vertical-align: text-top;" v-if="v.remark  ">备注:{{v.remark}}</div>
                </div>
              </div>
              <div :id="v.orderId" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                <div class="card-body">
                  <div v-for="(oi,item) in v.orderItems">{{oi.foodname}}&nbsp;&nbsp;{{oi.unitprice}}￥&times;{{oi.num}}</div>
                </div>
              </div>
            </div>

          </div>
        </div>
      </div>

      <div class="tab-pane fade" id="orderHistory">

        <div style="padding: 50px;">
          <div id="accordion1">

            <div v-for="(v,i) in historyOrders" class="card" style="margin-bottom:20px;">
              <div class="card-header" id="headingOne">
                <div class="btn btn-link collapsed" style="text-decoration: none;cursor: pointer" data-toggle="collapse"
                     :data-target="'#'+v.orderId" aria-expanded="false" aria-controls="collapseTwo">
                  <span>下单人:{{v.receiverName}}</span>
                  <span style="margin-left: 30px;">下单时间:{{v.orderTime}}</span>
                  <span style="margin-left: 30px;">下单状态:

                    <span v-if="v.state==='0'">
                      {{stateText(v.state)}}
                    </span>

                    <span v-else-if="v.state==='-1'">
                      {{stateText(v.state)}}
                    </span>

                  </span>
                  <span style="margin-left: 30px;">总额:{{v.price-v.sendprice}}</span>
                  <span style="display:inline-block;margin-left: 30px;max-width:160px;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;vertical-align: text-top;" v-if="v.remark">备注:{{v.remark}}</span>
                </div>
              </div>
              <div :id="v.orderId" class="collapse" aria-labelledby="headingOne" data-parent="#accordion1">
                <div class="card-body">
                  <div v-for="(oi,item) in v.orderItems">{{oi.foodname}}&nbsp;&nbsp;{{oi.unitprice}}￥&times;{{oi.num}}</div>
                </div>
              </div>
            </div>

          </div>
        </div>

        <!--<div id="accordion">

          <div v-for="" class="card">

            <div class="card-header" id="headingTwo">
                <div class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                  Collapsible Group Item #2
                </div>
            </div>
            <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
              <div class="card-body">
                AAA
              </div>
            </div>


          </div>

        </div>-->

      </div>

    </div>
  </div>

  </div>
</template>

<style scoped>
  /*头像*/
  .header{
    height: 3em;
    background-color: white;
  }
  .logo{
    height: 3em;
    margin-left: 2em;
  }
  .leftBar{
    float: left;
  }
  .headContent{
    margin-left:5em;
    margin-top:1em;
    height: 14.2em;
    width: 12em;
    background-color: white;
  }
  .headImgBack{
    height: 4em;
    width: 100%;
    background-color: #007bff;
  }
  .headImg{
    border-radius:50%;
    background-color: #F2F1F0;
    width: 9em;
    height: 9em;
    margin: 0 auto;
    margin-top:-2.5em;
    overflow: hidden;
    background-repeat: no-repeat;
    /*border:2px solid #F68447;*/
  }
  .headImgBack{
    height: 4em;
    width: 100%;
    background-color: #007bff;
  }
  /*左边菜单*/
  .navBar{
    position: fixed;
    width: 12em;
  /*height: 25em;*/
  margin-left:5em;
    margin-top:1.5em;
    margin-right:2em;
    background-color: white;
  }
  /*右部*/
  .usrName{
    color: white;
    float: left;
    font-size: 2em;
    margin-left: 1em;
    margin-top: 0.25em;
  }
  .editContent{
    margin-left:19em;
    margin-top:1em;
    width: 75%;
    min-height: 32em;
    background-color: white;
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
    background-color: #007bff;
    color:white;
  }
  /*食品表*/
  .pane{
    padding-top: 10px;
    /*
    height: 100%;
    width: 100%;*/
  }
  /*订单项*/
  .orderMessage{
    position: relative;
    height: 180px;
    width: 550px;
    margin-left:20px;
    margin-top: 1%;
  }
  .orderItem{
    margin-top: 25px;
    margin-left: 90px;
    height:200px;
    width: 800px;
    border-radius: 8px;
    background-color: #eae7f4;
  }
  .imgBox{
    display:flex;flex-direction:row;
    justify-content:center;
    align-items:center;
    margin-left: 10px;
    height: 200px;
    width:200px;
  }
  .cover{
    overflow: hidden;
    border-radius: 50%;
    height: 160px;
    width: 160px;
  }
  .orderPic{
    width: 160px;
    min-height: 160px;
    background-color: #0c5460;
  }
</style>

<script>
  import MyHeader from '../components/Header.vue';
  import FoodTable from '../components/FoodTable.vue';
  import CouponTable from '../components/CouponTable.vue';
  import VueDatepickerLocal from 'vue-datepicker-local'

  export default{
        data () {
            return {
                msg: '这个是Home模板页',
                foo: 'myPopover',
                headImgSrc:this.$getLocalStorage('headImg'),
                shopMsg:{
                  shopId: this.$route.params.id,
                  shopName: this.$route.params.shopName,
                  address:this.$route.params.address,
                  phone:this.$route.params.phone,
                  income:this.$route.params.income,
                  headImg:this.$route.params.headImg,
                  userName:this.$route.params.userName,
                  score:this.$route.params.score,
                  userId:this.$getLocalStorage('userId')
                },
              currentOrders:[],
              couriers:[],
              historyOrders:[],
              newOrdersCount:0
            }
        },
    components:{
      MyHeader,
      FoodTable,
      CouponTable,
      VueDatepickerLocal
    },
    computed:{

    },
    methods:{
      Logout(){
        this.$clearLocalStorage();
        this.$clearCookie();
        alert('已退出当前账户');
        this.$goRoute('Login');
      },
      FileChange(file){
        var filelist = file.path[0].files[0];
        var reader = new FileReader();
        var imgF=this.imgReset;
        reader.onload = function (e) {
          var img = document.getElementById(file.path[0].id + "img");
          img.onload=function (){//图片加载
            imgF(img);
          };
          img.src = e.target.result;
        };
        reader.readAsDataURL(filelist);
      },
      imgReset(img){
        var theImage = new Image();
        theImage.src =img.src;
        if(theImage.height>theImage.width) {
          document.getElementById("uploadFileimg").style.width = '10em';
          document.getElementById("uploadFileimg").style.height = 'auto';
        }
        else {
          document.getElementById("uploadFileimg").style.height = '10em';
          document.getElementById("uploadFileimg").style.width = 'auto';
        }
      },
      imgLoad(){
        var oImg = new Image();
        oImg.addEventListener('load', (e) => {//图片加载监听加载事件 异步 并非先执行
          this.$nextTick(() => {
            this.$emit('preloaded');//自定义preloaded事件
          });
        });
        oImg.src = $('#uploadFileimg').attr("src");  //绑定到头像图片
      },
      OnSubmitImg(){
        var form = document.getElementById("imgForm");
        var formData = new FormData(form);
        var file = formData.get('uploadFile');
        formData.append('file', file);
        formData.append('shopId', this.$getLocalStorage('shopId'));
        this.$zPost('/shop/headImg',formData,"上传成功",'');
      },
      GetOrderCurrent(){
        let data = JSON.stringify({'shopId': this.$getLocalStorage('shopId')});
        this.$http.post('/zfood/order/shop/current',data,{headers: {"Content-Type": "application/json"}}).then(
          response=>{
            if (response.data['errorCode'] === 403) {
              alert('重新登陆!');
              this.$goRoute('Login');
              return;
            }
            else {
              this.currentOrders = response.data;
              this.newOrdersCount=this.currentOrders.length;
              this.$http.post('/zfood/couriers',data,{headers: {"Content-Type": "application/json"}}).then(
                responses=>{
                  if (responses.data['errorCode'] === 403) {
                    alert('重新登陆!');
                    this.$goRoute('Login');
                    return;
                  }
                  else{
                    this.couriers=responses.data;
                  }
                }
              );
            }
          }
        );
      },
      TagChange(e){
        if(e.target.innerHTML==='当前订单'){
          this.GetOrderCurrent();
        }
        else if(e.target.innerHTML==='历史订单'){
            let data = JSON.stringify({'shopId': this.$getLocalStorage('shopId')});
            this.$http.post('/zfood/order/shop/history',data,{headers: {"Content-Type": "application/json"}}).then(
                response=>{
                  if (response.data['errorCode'] === 403) {
                    alert('重新登陆!');
                    this.$goRoute('Login');
                    return;
                  }
                  else{
                    this.historyOrders=response.data;
                  }
                }
            );
        }
      },
      stateText(state){
        switch(state){
         case '-1':return '已拒绝';
         case '0':return '已完成';
         case '1':return '正在配送';
         case '2':return '等待配送';
         case '3':return '已接单';
         case '4':return '等待接单';
         }
      },
      ToReceive(e){
        e.cancelBubble = true;
        this.currentOrders[e.path["0"].dataset.index]['state']='3';
        let data = JSON.stringify({'orderId': this.currentOrders[e.path["0"].dataset.index]['orderId'],'state':'3'});
        this.$http.post('/zfood/order/edit/state',data,{headers: {"Content-Type": "application/json"}}).then();
      },
      ToReject(e){
        e.cancelBubble = true;
        this.currentOrders[e.path["0"].dataset.index]['state']='-1';
        let data = JSON.stringify({'orderId': this.currentOrders[e.path["0"].dataset.index]['orderId'],'state':'-1'});
        this.$http.post('/zfood/order/edit/state',data,{headers: {"Content-Type": "application/json"}}).then();
      },
      ToSend(e){
        e.cancelBubble = true;
        this.currentOrders[e.path["0"].dataset.index]['state']='2';
        let data = JSON.stringify({'orderId': this.currentOrders[e.path["0"].dataset.index]['orderId'],'state':'2'});
        this.$http.post('/zfood/order/edit/state',data,{headers: {"Content-Type": "application/json"}}).then();
      },
      SubmitShoprMsg(){
        var data=JSON.stringify(this.shopMsg);
        this.$zPost('/shop/edit',data,"修改成功");
      },

      },
     created(){
       if(this.$checkUserState()!==this.$route.path)
         this.$router.push(this.$checkUserState());
       else if(this.shopMsg.id===undefined){
          this.$http.post('/zfood/shop/message',{headers: {"Content-Type": "application/json"}}).then(
              response=>{
                if(response.data['errorCode']===403){alert('重新登陆!');this.$goRoute('Login');return;}
                else{this.shopMsg=response.data;}
              }
          )
        }
      },
      mounted(){
       /* $(function (){
          $("[data-toggle='popover']").popover();
        });*/
        /*$(window).resize(function () {
          var divOne=$('*[data-dynamic="height"]');
          divOne.height(divOne.width());
        });
        $(document).ready(function(){
          var divOne=$('*[data-dynamic="height"]');
          divOne.height(divOne.width());
        });*//*$(window).resize(function () {
          var divOne=$('*[data-dynamic="height"]');
          divOne.height(divOne.width());
        });
        $(document).ready(function(){
          var divOne=$('*[data-dynamic="height"]');
          divOne.height(divOne.width());
        });*/
        this.imgLoad();
        this.$on('preloaded', () => {//图片加载完成 才能获取宽高
          var preLoadImg=new Image();
          preLoadImg.src=$('#uploadFileimg').attr("src");
          this.imgReset(preLoadImg);
        });
        $(function () {
          $('#Tab li:eq(0) a').tab('show');//eq()遍历第一个显示
        });
        this.GetOrderCurrent();
      }
    }
</script>
