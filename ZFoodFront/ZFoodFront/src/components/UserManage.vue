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
        <div style="min-height: 32em;background-color: white; padding-bottom: 30px;margin-bottom: 20px;">
          <div class="orderItem" v-for="(v,i) in orderItem">
            <!--商家图片-->
            <div style="display: inline-block;height: 100%;float:left;">
              <div class="imgBox">
                <div class="imgCover">
                  <img class="orderPic" :src="v.headPic" :class="v.imgClass"/>
                </div>
              </div>
            </div>

            <!--订单信息-->
            <div  @click="getMesg(i)" data-toggle="modal" data-target="#myModal"  class="orderMessage">
              <p  style="font-size: 1.5em;">{{v.shopName}}</p>

              <div>
                <div class="margin-left-2" style="font-size: 1em;position: absolute;bottom: 10px;">
                  总计：<span style="color: red;padding-right: 10px;">{{v.price}}￥</span>
                <span class="" style="font-size: 1em;float: right;margin-right: 5px;padding-left: 20px;">下单时间:{{v.orderTime}}</span>
                <span>{{GetOrderState(v.state) }}</span>
                </div>
              </div>

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
                  <div>
                    <div style="display: inline-block;margin-right: 20px;">
                      商家:<br>
                      下单时间:<br>
                      接收人:<br>
                      接收地址:<br>
                      联系方式:<br>
                      订单状态:<br>
                      <div v-if="msg.courierName!==null">配送员:</div>
                      备注:<br>
                      菜单项:<br>
                    </div>
                    <div style="display: inline-block;vertical-align: top;">
                      {{msg.shopName}}<br>
                      {{msg.orderTime}}<br>
                      {{msg.receiverName}}<br>
                      {{msg.address}}<br>
                      {{msg.phone}}<br>
                      {{GetOrderState(msg.state)}}<br>
                      <div v-if="msg.courierName!==null">{{msg.courierName}}</div>
                      {{msg.remark}}
                      <div v-for="(s,f) in msg.orderItems">
                        <div style="display: inline-block;margin-right: 10px;min-width: 80px;">{{s.foodname}}</div>
                        <div style="display: inline-block;margin-right: 10px;min-width: 80px;"><span style="color:#F68447;">{{s.unitprice}}￥</span></div>
                        <div style="display: inline-block;">&times;{{s.num}}份</div>
                      </div>
                    </div>
                  </div>
                  <div style="margin-top: 20px;">
                    <div style="display: inline-block;margin-right: 20px;">
                      优惠:<br>
                      付款:<br>
                      <div style="height: 40px;padding:10px 0;"  v-if="OrderCommentState(msg.state)">评分:</div>
                      <div style="height: 40px;padding:8px 0 10px;"  v-if="OrderCommentState(msg.state)">配送评分:</div>
                      <div v-if="OrderCommentState(msg.state)">我的评价:</div>
                    </div>
                    <div style="display: inline-block;vertical-align: top;">
                      <span style="color:#d2c9c9;">{{msg.preferential===null?0:msg.preferential}}￥<br></span>
                      <span style="color:#F68447;">{{msg.price}}￥<br></span>
                      <div  v-if="OrderCommentState(msg.state)"><div style="display: inline-block;margin-left: -10px;"><rate :length="5"  v-model="msg.score" :readonly="StarRateState(msg.score)"></rate></div></div>
                      <div  v-if="OrderCommentState(msg.state)"><div style="display: inline-block;margin-left: -10px;"><rate :length="5" v-model="msg.sendscore" :readonly="StarRateState(msg.sendscore)"></rate></div></div>
                      <input class="form-control"  v-model="msg.comment"  v-if="OrderCommentState(msg.state)" :readonly="msg['orginComment']!==null"/>
                    </div>
                  </div>
                </div>

                <div v-if="OrderCommentState(msg.state)" class="modal-footer">
                  <button type="button" style="background-color: #F68447;color: white;" class="btn btn-default" @click="SaveOrder">确定并保存<!-- data-dismiss="modal" --></button>
                </div>
              </div><!-- /.modal-content -->
            </div><!-- /.modal -->
          </div>
        </div>

        <div class="tab-pane fade" id="coupon">
          <div style="min-height: 32em;background-color: white; padding-bottom: 30px;margin-bottom: 20px;">
              <div v-for="(v,i) in couponItem">

                  <div :style="couponStyle[v.state+1]" style="margin:0 auto;margin-top:30px;height: 135px;width: 510px;color: #b7512e;position: relative;">
                    <div style="display: inline-block;">
                    <div style="margin-left:1em;font-size: 1.5em;margin-bottom: 0.2em;">{{v.name}}</div>
                    <div style="margin-left:2em;font-size: 0.8em;word-wrap:break-word;width: 330px;">{{v.detail}}</div>
                    <div v-if="v.endTime!==null&&v.startTime!=null" style="margin-left:2em;margin-bottom:0.3em;position: absolute;bottom: 0;">{{v.startTime}}至{{v.endTime}}期间使用</div>
                    <div v-else style="margin-left:2em;margin-bottom:0.3em;position: absolute;bottom: 0;">无期限限制</div>
                    </div>
                    <div style="display: inline-block;float: right;bottom: 20px;right:10px;position: absolute;color: white">
                      <a style="cursor: pointer;" @click="ToShop(v.shopId)">{{v.shopName}}</a>
                    </div>
                  </div>

              </div>
          </div>
        </div>

        <div class="tab-pane fade" id="favorite">

          <div style="min-height: 32em;background-color: white; padding-bottom: 30px;margin-bottom: 20px;">
          <div>
          <div style="background-color: #F68447; width: 200px;color: white;margin-top: 20px;">收藏商家</div>

          <div style="display: inline-block;margin-bottom: 20px;cursor: pointer;" v-for="(v,i) in favShops" @click="ToShop(v.shopId)">
            <div style="text-align: center; overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">
            <div class="imgBox">
              <div class="imgCover">
                <img class="orderPic" :src="v.shopPic===null?'/static/img/zfood/logo-gray-square.jpg':v.shopPic" :class="v.imgClass"/>
              </div>
            </div>
            <div>{{v.favShopName}}</div>
            </div>
          </div>

          <div style="background-color: #F68447; width: 200px;color: white;">收藏食品</div>
          <div style="display: inline-block;margin-bottom: 20px;cursor: pointer;" v-for="(v,i) in favFoods" @click="ToShop(v.shopId)">
            <div style="text-align: center; overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">
              <div class="imgBox">
                <div class="imgCover">
                  <img class="orderPic" :src="v.foodPic===null?'/static/img/zfood/logo-gray-square.jpg':v.foodPic" :class="v.imgClass"/>
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
    height:150px;
    width: 800px;
    border-radius: 8px;
    background-color: #eae7f4;
    box-shadow: 0 0 7px #F68447;
  }
  .imgBox{
    display:flex;flex-direction:row;
    justify-content:center;
    align-items:center;
    margin-left: 10px;
    height: 150px;
    width:150px;
  }
  .imgCover{
    width:120px;
    height: 120px;
    border-radius: 50%;
    overflow: hidden;
  }
  .img-w{
    width:120px;
  }
  .img-h{
    height: 120px;
  }
  .orderMessage{
    position: relative;
    display: inline-block;
    height: 100%;
    width: 80%;
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
              //'static/img/user/'+this.$getLocalStorage('userId')+'.jpg' //imgs: 'this.src="' +require('../assets/img/zfood/logo-gray-square.jpg')+'"'
              headImgSrc:this.$getLocalStorage('headImg'), //用户图片


              orderItem:[],
              couponItem:[],
              favFoods:[],
              favShops:[],
              orderContentHeight:'',

              msg:'',
              currentOrderIndex:null,
              tempScore:0,//保存临时状态的打分
              tempSendScore:0,
              saveType:false,
              couponStyle:[
                {backgroundImage: "url(" + require("../assets/coupon-shop-out.png") + ")"},
                {backgroundImage: "url(" + require("../assets/coupon-shop-used.png") + ")"},
                {backgroundImage: "url(" + require("../assets/coupon-shop.png") + ")"}
              ]
            /*
           couponActiveStyle: {
           backgroundImage: "url(" + require("../assets/coupon-shop.png") + ")"
           },
           couponOutStyle:{
           backgroundImage: "url(" + require("../assets/coupon-shop-out.png") + ")"
           },
           couponUsedStyle:{
           backgroundImage: "url(" + require("../assets/coupon-shop-used.png") + ")"
           }
          */
            }
        },
      mounted(){
        let _this=this;
        $('#myModal').on('hidden.bs.modal', function (e) {
            if(!_this.saveType){
              _this.orderItem[_this.currentOrderIndex]['score']=_this.tempScore;
              _this.orderItem[_this.currentOrderIndex]['sendscore']=_this.tempSendScore;
            }else{
              _this.saveType=!_this.saveType;
            }
        });
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
        ToShop(shopId){
            this.$goRoute('/ShopIndex/'+shopId);
        },
        SaveOrder(){
          this.$set(this.msg,'orginComment',this.msg['comment']);
          let comment={};
          comment['orderId']=this.orderItem[this.currentOrderIndex]['orderId'];
          this.orderItem[this.currentOrderIndex]['comment']!==null?comment['comment']=this.orderItem[this.currentOrderIndex]['comment']:'';
          this.orderItem[this.currentOrderIndex]['score']!==null?comment['score']=this.orderItem[this.currentOrderIndex]['score']:'';
          this.orderItem[this.currentOrderIndex]['sendscore']!==null?comment['sendscore']=this.orderItem[this.currentOrderIndex]['sendscore']:'';
          let data=JSON.stringify(comment);
          this.$http.post('/zfood/order/edit/comment',data,{headers: {"Content-Type": "application/json"}});
          this.saveType=true;
        },
        StarRateState(score){
          if(score===null)
              return false;
          return true;
        },
        OrderCommentState(state){
            if(parseInt(state)<1)
                return true;
            return false;
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
            /*if(e.target.innerHTML==='个人资料')
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
            }*/
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
                      this.orderItem=response.data;this.OrderPageInit();}
                }
            );
        },
        OrderPageInit(){
          //图片初始化
          this.orderItem.forEach((v,i)=>{
            let img=new Image();
            img.addEventListener('load', (e) => {
              this.$nextTick(() => {
                this.$set(v, 'imgClass', img.height > img.width ? 'img-w' : 'img-h');
              });
            });
            img.src=v.headPic;
          });
       /*   this.ContentHeight('auto');*/
        },
        getMesg(index){
          this.currentOrderIndex=index;
          this.msg=this.orderItem[index];
          this.$set(this.msg,'orginComment',this.msg['comment']);
          this.tempScore=this.msg.score;
          this.tempSendScore=this.msg.sendscore;
        },
        GetOrderState(state){
            switch (state){
              case '-1':return'商家退单';
              case '0':return'订单完成';
              case '1':return'正在配送';
              case '2':return'等待配送';
              case'3':return '已接单';
              case'4':return '等待接单';
            }
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
        CouponPageInit(){/*this.ContentHeight('auto');*/},
        //fav
        FavPageInit(){
          /*this.ContentHeight('auto');*/
          this.favShops.forEach((v,i)=>{
              let img=new Image();
              img.addEventListener('load', (e) => {
                this.$set(v, 'imgClass', img.height > img.width ? 'img-w' : 'img-h');
              });
             img.src=v.shopPic===null?'/static/img/zfood/logo-gray-square.jpg':v.shopPic;
          });
          this.favFoods.forEach((v,i)=>{
            let img=new Image();
            img.addEventListener('load', (e) => {
              this.$set(v, 'imgClass', img.height > img.width ? 'img-w' : 'img-h');
            });
            img.src=v.foodPic===null?'/static/img/zfood/logo-gray-square.jpg':v.foodPic;
          });
        },
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
                        else {
                            this.favFoods = response.data;
                            this.FavPageInit();
                        }
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
