<template>
<div>
  <div class="header">
    <a href=""><img class="logo" src="/static/img/zfood/logo.jpg" alt=""></a>
    <a v-on:click="Logout"  style="float: right;margin-top: 0.5em;margin-right:1em; color: #F68447;font-weight: 500;cursor: pointer;">退出</a>
  </div>
  <div class="leftBar">
    <!-- 头像 -->
    <div class="headContent">
      <div class="headImgBack"></div>
      <div class="headImg">
        <form id="imgForm" method="post" enctype="multipart/form-data">
          <input id="uploadFile" name="uploadFile" type="file" style="display: none" data-user="NHVZG" @change="FileChange"/>
          <label for="uploadFile">
            <img id="uploadFileimg" class="userImg" style="cursor:pointer;" title="点击更改图片"  :src="[headImgSrc===''?'/static/img/zfood/logo-gray-square.jpg':headImgSrc]"><!--v-bind:src="this.headImgSrc"-->
          </label>
        </form>
      </div>
      <div style="text-align: center;margin-top: 10px;"><button @click="OnSubmitImg" class="btn subButton">保存头像</button></div>
    </div>
    <!-- 导航 -->
    <div class="navBar">

      <div class="nav-edit margin-top-5">
        <ul id="Tab" class="nav navbar-nav text-center">
          <li class="margin-top-2 active"><a v-on:click="TagChange" class="nav-link a-orange" href="#userMessage" data-toggle="tab">个人资料</a></li><div class="dropdown-divider"></div>
          <li class="margin-top-2"><a v-on:click="TagChange"class="nav-link a-orange" href="#foodOrder" data-toggle="tab">我的订单</a></li><div class="dropdown-divider"></div>
          <li class="margin-top-2"><a v-on:click="TagChange" class="nav-link a-orange" href="#coupon" data-toggle="tab">我的优惠</a></li><div class="dropdown-divider"></div>
          <li class="margin-top-2 margin-bottom-5"><a v-on:click="TagChange" class="nav-link a-orange" href="#favorite" data-toggle="tab">我的收藏</a></li>
        </ul>
      </div>

    </div>
  </div>
  <!-- 主体 -->
  <!-- 主体 -->
  <div>
    <div class="editContent" :style="{height:orderPageHeight}" style="margin-bottom: 10px;min-height: 32em;">
      <div class="headImgBack"><div class="usrName">{{userMessage.name}}</div></div>
      <div class="tab-content" >

        <div class="tab-pane fade in active" id="userMessage">
            <div class="textContent">
              <div class="lineText">用户名:</div><input v-model="userMessage.name" class="form-control editText" type="text" name="name"/>
              <br>
              <div class="lineText">默认手机:</div><input v-model="userMessage.phone" class="form-control editText" type="text" name="phone"/>
              <br>
              <div class="lineText">地址:</div><input v-model="userMessage.address" class="form-control editText" type="text" name="address"/>
              <br>
              <button class="btn subButton "  style="width: 8em; margin-top: 2em;" @click="SubmitUserMes">保存</button>
            </div>
        </div>

        <div class="tab-pane fade" id="foodOrder">
          <div class="orderItem" v-for="(v,i) in orderItem">
            <!--商家图片-->
            <div style="display: inline-block;">
            <div class="imgBox">
              <div class="cover">
            <img class="orderPic" :src="v.headPic"/>
              </div>
            </div>
            </div>
            <!--订单信息-->
            <div  @click="getMesg(v)" data-toggle="modal" data-target="#myModal"  class="orderMessage">
              <p class="margin-left-2" style="font-size: 1.5em;">{{v.shopName}}</p>
              <!--<div style="margin-left: 150px;display: inline-block;">
                <div style="display: inline-block;">
                  <div class="margin-left-2">{{v.orderItems[0].foodname}}</div>
                  <div class="margin-left-2"v-if="v.orderItems.length>=2">{{v.orderItems[1].foodname}}</div>
                  <div class="margin-left-2" v-if="v.orderItems.length>2">....</div>
                </div>
                <div style="float: right">
                  <div style="width:10px;">
                    <div >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{v.orderItems[0].num*v.orderItems[0].unitprice}}￥</div>
                    <div v-if="v.orderItems.length>=2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{v.orderItems[1].num}}￥</div>
                  </div>
                </div>
              </div>-->
              <div style="position:absolute;bottom: 5px;width: 100%;">
                <span class="margin-left-2" style="font-size: 1em;">总计：<span style="color: red">{{v.price}}￥</span></span>
                <div class="" style="font-size: 1em;float: right;margin-right: 5px;">&nbsp;&nbsp;&nbsp;&nbsp;下单时间:{{v.orderTime}}</div>
                <span  class="margin-left-2" style="float: right;font-size: 1em;" v-if="v.state==='-1'">商家未接单</span>
                <span  class="margin-left-2" style="float: right;font-size: 1em;" v-if="v.state==='0'">订单完成</span>
                <span  class="margin-left-2" style="float: right;font-size: 1em;" v-else-if="v.state==='1'">正在配送</span>
                <span  class="margin-left-2" style="float: right;font-size: 1em;" v-else-if="v.state==='2'">等待配送</span>
                <span  class="margin-left-2" style="float: right;font-size: 1em;" v-else-if="v.state==='3'">已接单</span>
                <span  class="margin-left-2" style="float: right;font-size: 1em;" v-else-if="v.state==='4'">等待接单</span>
              </div>
            </div>
          </div>
          <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h4>订单详情</h4>
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                  </button>
                </div>

                <div class="modal-body">
                  商家:{{msg.shopName}}<br>
                  下单时间:{{msg.orderTime}}<br>
                  接收人:{{msg.receiverName}}<br>
                  接收地址:{{msg.address}}<br>
                  联系方式:{{msg.phone}}<br>
                  订单状态:<span v-if="msg.state==='0'">订单完成</span>
                                 <span v-else-if="msg.state==='1'">正在配送</span>
                                 <span v-else-if="msg.state==='2'">等待配送</span>
                                 <span v-else-if="msg.state==='3'">已接单</span>
                                 <span v-else-if="msg.state==='4'">等待接单</span>
                  <br>
                  评分:{{msg.score}}<br>
                  菜单项:
                  <div v-for="(s,f) in msg.orderItems" style="margin-left: 20px;">
                    {{s.foodname}} {{s.unitprice}}￥x{{s.num}}份<br>
                  </div>
                  总价:{{msg.price}}￥<br>
                  我的评价:{{msg.comment}}<br>
                </div>

                <div class="modal-footer">
                  <button type="button" class="btn btn-default" data-dismiss="modal">确定
                  </button>
                </div>
              </div><!-- /.modal-content -->
            </div><!-- /.modal -->
          </div>
        </div>

        <div class="tab-pane fade" id="coupon">
              <div v-for="(v,i) in couponItem">
                <div>

                  <div v-if="v.state===1" :style="couponActiveStyle" style="margin:0 auto;margin-top:40px;height: 229px;width: 454px;position: relative;">
                    <div style="margin-left: 20px;">
                      <p style="font-size: 2em;">{{v.name}}</p>
                        <p style="margin-left: 20px;">{{v.detail}}</p>
                        <p style="float: right; margin-right: 40px;margin-top: 40px;">{{v.shopName}}</p>
                        <div style="margin-left: 20px;" v-if="v.foodName!==null">{{v.foodName}}限定</div>
                        <p style="bottom: 0;position: absolute;margin-left: 20px;">{{v.startTime}}至{{v.endTime}}期间使用</p>
                    </div>
                  </div>

                  <div v-else :style="couponOutStyle" style="margin:0 auto;margin-top:40px;height: 229px;width: 454px;position: relative;">
                    <div style="margin-left: 20px;">
                      <p style="font-size: 2em;">{{v.name}}</p>
                      <p style="margin-left: 20px;">{{v.detail}}</p>
                      <p style="float: right; margin-right: 40px;margin-top: 40px;">{{v.shopName}}</p>
                      <div  style="margin-left: 20px;" v-if="v.foodName!==null">{{v.foodName}}限定</div>
                      <p style="bottom: 0;position: absolute;margin-left: 20px;">{{v.startTime}}至{{v.endTime}}期间使用</p>
                    </div>
                  </div>

                </div>
              </div>
        </div>

        <div class="tab-pane fade" id="favorite">
          <div style="background-color: #F68447; width: 200px;color: white;margin-top: 20px;">收藏商家</div>
          <div style="display: inline-block;margin-bottom: 20px;" v-for="(v,i) in favShops">
            <div style="text-align: center; overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">
            <div class="imgBox">
              <div class="cover">
                <img class="orderPic" :src="v.shopPic"/>
              </div>
            </div>
            <div>{{v.favShopName}}</div>
            </div>
          </div>

          <div style="background-color: #F68447; width: 200px;color: white;">收藏食品</div>
          <div style="display: inline-block;margin-bottom: 20px;" v-for="(v,i) in favFoods">
            <div style="text-align: center; overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">
              <div class="imgBox">
                <div class="cover">
                  <img class="orderPic" :src="v.foodPic"/>
                </div>
              </div>
              <div>{{v.foodName}}</div>
            </div>
          </div>
        </div>

      </div>
    </div>
  </div>
</div>
</template>

<style scoped>
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
    margin-top:2em;
    height: 16em;
    width: 12em;
    background-color: white;
  }
  .headImgBack{
    height: 4em;
    width: 100%;
    background-color: #F68447;
  }
  .headImg{
    border-radius:50%;
    background-color: #F2F1F0;
    width: 10em;
    height: 10em;
    margin: 0 auto;
    margin-top:-2.5em;
    overflow: hidden;
    background-repeat: no-repeat;
    /*border:2px solid #F68447;*/
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
  .editContent{
    margin-left:19em;
    margin-top:2em;
    width: 75%;
    height: 32em;
    background-color: white;
  }
  .subButton{
    background-color:#F68447;
    color:white;
  }
  .a-orange{
    color: #F68447;
    font-weight: 400;
  }
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
  .usrName{
    color: white;
    float: left;
    font-size: 2em;
    margin-left: 1em;
    margin-top: 0.25em;
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
  .orderMessage{
    position: relative;
    height: 180px;
    width: 550px;
    margin-right:20px;
    margin-top: 1%;
    float: right;
  }

</style>

<script>
    export default{
        data () {
            return {
                userMessage:{
                    name:'',
                    phone:'',
                    address:''
                },
              headImgSrc:this.$getLocalStorage('headImg'),//'static/img/user/'+this.$getLocalStorage('userId')+'.jpg' //imgs: 'this.src="' +require('../assets/img/zfood/logo-gray-square.jpg')+'"'
              orderItem:[],
              couponItem:[],
              favFoods:[],
              favShops:[],
              orderContentHeight:'',
              msg:'',
              couponActiveStyle: {
                backgroundImage: "url(" + require("../assets/coupon.png") + ")"
              },
              couponOutStyle:{
                backgroundImage: "url(" + require("../assets/coupon-out.png") + ")"
              }
            }
        },
      mounted(){
        this.imgLoad();
        this.$on('preloaded', () => {//图片加载完成 才能获取宽高
          var preLoadImg=new Image();
          preLoadImg.src=$('#uploadFileimg').attr("src");
          this.imgReset(preLoadImg);
        });
        $(function () {
          $('#Tab li:eq(0) a').tab('show');//eq()遍历第一个显示
        });
        this.$http.post('/zfood/user/message',{headers: {"Content-Type": "application/json"}}).then(
          response=>{
            if(response.data['errorCode']===403){alert('重新登陆!');this.$goRoute('Login');return;}
            this.userMessage.name=response.data['name'];
            this.userMessage.phone=response.data['phone'];
            this.userMessage.address=response.data['address'];
          },
          response=>{alert(response);}
        );
        /*var imgR=this.imgReset;
        $("img").on('error',function () {
          $(this).attr('src', 'static/img/zfood/logo-gray-square.jpg');//static/img/zfood/logo-gray-square.jpg
          var img = document.getElementById("uploadFileimg");
          imgR(img);
        });*/
      },
      computed:{
          orderPageHeight:function () {
              return this.orderContentHeight;
          }
      },
      methods:{
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
          formData.append('userId', this.$getLocalStorage('userId'));
          this.$zPost('/user/headImg',formData,"上传成功",'');
        },
        SubmitUserMes(){
            var data=JSON.stringify(this.userMessage);
            this.$zPost('/user/common/edit',data,"修改成功");
        },
        Logout(){
            this.$clearLocalStorage();
            this.$clearCookie();
            alert('已退出当前账户');
            this.$goRoute('Login');
        },
        TagChange(e){
            if(e.target.innerHTML==='个人资料')
                this.ContentHeight(32+'em');
            if(e.target.innerHTML==='我的订单') {
                if(this.orderItem.length<=0)
                  this.GetOrder();
            }
            if(e.target.innerHTML==='我的优惠') {
                if(this.couponItem.length<=0)
                  this.getCoupon();
            }
            if(e.target.innerHTML==='我的收藏'){
              if(this.favFoods.length<=0&&this.favShops.length<=0)
               this.getFav();
            }
        },
        //order
        GetOrder(){
            this.$http.post('zfood/order/user',{headers: {"Content-Type": "application/json"}}).then(
                response=>{
                  if(response.data['errorCode']===403){alert('重新登陆!');this.$goRoute('Login');return;}
                  else{
                      console.log(response.data);
                      this.orderItem=response.data;this.OrderPageInit();}
                }
            );
        },
        OrderPageInit(){
          this.ContentHeight('auto');
        },
        getMesg(v){
          this.msg=v;
        },
        //coupon
        getCoupon(){
         this.$http.post('zfood/userCouponItem',{headers: {"Content-Type": "application/json"}}).then(
             response=>{
               if(response.data['errorCode']===403){alert('重新登陆!');this.$goRoute('Login');return;}
               else{this.couponItem=response.data;this.CouponPageInit();}
             }
         );
        },
        CouponPageInit(){this.ContentHeight('auto');},
        //fav
        getFav(){
          this.$http.post('zfood/favShop',{headers: {"Content-Type": "application/json"}}).then(
              response=>{
                if(response.data['errorCode']===403){alert('重新登陆!');this.$goRoute('Login');return;}
                else{

                    this.favShops=response.data;
                    this.$http.post('zfood/favFood',{headers: {"Content-Type": "application/json"}}).then(
                      response=> {
                        if (response.data['errorCode'] === 403) {
                        alert('重新登陆!');
                        this.$goRoute('Login');
                        return;
                      }
                        else {this.favFoods = response.data;this.ContentHeight('auto');}
                      }
                   );

                }
              }
        );
        },

        ContentHeight(h){
            this.orderContentHeight=h;
        },

      },
      created(){
          if(this.$checkUserState()!==this.$route.path)
            this.$router.push(this.$checkUserState());
      },
    }
</script>
