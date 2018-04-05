<template>
  <div style="padding-bottom: 2em;">
    <!--头部栏-->
    <div class="header">
      <a href=""><img class="logo" src="/static/img/zfood/logo.jpg" alt=""></a><!--动态路由参数需要以/开始-->
      <div class="headerRightButton">


        <div style="vertical-align:bottom;padding-bottom:0.7em;display: inline-block;margin-left: 0.15em;width: 3.5em;">
          <img style="width: 2.5em;" src="/static/img/zfood/cart-8x -gray.png"><!--border-radius: 50%;-->
        </div>

        <div id="imgBox" class="imgBox dropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <img :src="userHeadImgStyle.headImgSrc" :class="userHeadImgStyle.headImgClass" class="userHeader">
        </div>
        <div class="dropdown-menu" aria-labelledby="imgBox" style="width: 50px;min-width: 7em;">
          <div v-if="menuType">
            <a class="dropdown-item" @click="ToLogin">登陆</a>
          </div>
          <div v-else>
            <a class="dropdown-header" style="color: #F68447;font-weight: 600;">{{userName}}</a>
            <a class="dropdown-item" @click="ToManage">我的资料</a>
            <a class="dropdown-item" @click="ToLogOut">退出</a>
          </div>
        </div>


      </div>
    </div>

    <!--填充-->
    <div class="fill">
      <!--名称-->
      <div class="fillMsgBlock">
        <span style="color: white;font-size: 2em;">{{shopMsg.shopname}}</span>
      </div>
      <!--详细信息-->
      <div class="detailMsgblock">
        <div class="detailOptions">
          <div>地址:</div>
          <div>电话:</div>
        </div>
        <div class="details">
          <div>{{shopMsg.address}}</div>
          <div>{{shopMsg.phone}}</div>
        </div>
      </div>

    </div>

    <!--菜单-->
    <div class="menu">
      <span class="menu-block nav navbar-nav" id="Tab">
        <a class="menu-item" href="#Foods" data-toggle="tab">菜单</a>
        <a class="margin-left-3em menu-item" href="#Coupons" data-toggle="tab">优惠券</a>
        <a class="margin-left-3em menu-item" href="#Comments" data-toggle="tab">评价</a>
      </span>
    </div>


    <!--商店头像-->
    <div class="shopHeaderDiv">
      <div class="shopHeader">
        <div class="shopHeaderBox"><img :src="shopMsg.headpic" :class="shopMsg.shopImgClass"></div>
      </div>
    </div>

    <div class="tab-content shopTabContent">

      <div class="tab-pane fade active show" id="Foods">
        <div class="foodBlock" v-for="(v,i) in foodMap">
          <div class="foodImgBlock">
            <img class="foodImg" :src="v.pic===null?'/static/img/zfood/logo-gray-square.jpg':v.pic">
          </div>
          <p class="foodDetails">{{v.name}}</p>
          <p style="color: salmon;text-align: center;margin: 0;font-weight: 500;font-size: 0.7em;">{{v.price}}￥</p>
          <div style="width: 11em;margin: 0 auto;">
            <a style="cursor: pointer" @click="SubNum(i)"><img class="numButton"  style="float: left;" src="/static/img/zfood/sub-6x-r.png"></a>
            <a style="cursor: pointer" @click="AddNum(i)"><img class="numButton" style="float: right;" src="/static/img/zfood/plus-6x-r.png"></a>
            <div style="text-align: center;color: #000000;font-weight: 500"><span style="background-color: #e2e2e2">{{getFoodNum[i]}}</span></div>
          </div>
        </div>
      </div>

      <div class="tab-pane fade" id="Coupons">
        <p>Coupons</p>
      </div>

      <div class="tab-pane fade" id="Comments">
        <p>Comments</p>
      </div>
    </div>

  </div>
</template>

<style scoped>
  /*头部栏*/
  .header {
    height: 3.5em;
    background-color: white;
  }

  .logo {
    height: 3.5em;
    margin-left: 2em;
  }

  .headerRightButton {
    height: 3.5em;
    float: right;
  }

  .imgBox {
    display: inline-block;
    width: 3.2em;
    height: 3.2em;
    border-radius: 50%;
    margin-right: 4em;
    margin-top: 0.15em;
    overflow: hidden;
  }

  .userHeadImg-h {
    height: 3.2em;
  }

  .userHeadImg-w {
    width: 3.2em;
  }

  /*填充*/
  .fill {
    height: 11em;
    background-color: #f5926b;
  }

  .fillMsgBlock {
    display: inline-block;
    padding-left: 20em;
    padding-top: 7.5em;
  }

  .detailOptions {
    display: inline-block;
    color: white;
    font-size: 1.1em;
    font-weight: 600;
  }

  .details {
    display: inline-block;
    float: right;
    margin-left: 0.5em;
    color: white;
    font-size: 1.1em;
    font-weight: 600;
  }

  .detailMsgblock {
    margin-top: 7.5em;
    margin-right: 5em;
    float: right;
  }

  /*商店头像*/
  .shopHeaderDiv {
    display: inline-block;
    float: left;
    margin-left: 5em;
  }

  .shopHeader {
    background-color: white;
    border-radius: 50%;
    margin-top: -11em;
    padding-top: 1em;
    width: 13em;
    height: 13em;
  }

  .shopHeaderBox {
    margin: 0 auto;
    border-radius: 50%;
    width: 11em;
    height: 11em;
    overflow: hidden;
  }

  .shopHeadImg-h {
    height: 11em;
  }

  .shopHeadImg-w {
    width: 11em;
  }

  /*菜单*/
  .menu {
    text-align: center;
    background-color: white;
    height: 3em;
    padding-top: 0.2em;
  }

  .menu-block {
    display: block;
    font-size: 1.4em;
    font-weight: 600;
  }

  .menu-item {
    color: #F68447;
    text-decoration: none;
    display: inline-block;
  }

  .margin-left-3em {
    margin-left: 3em;
  }

  /*正文*/
  .shopTabContent {
    margin: 0 auto;
    margin-top: 3em;
    width: 63em;
    padding-bottom: 2em;
  }

  /*食物*/
  .foodBlock {
    width: 13em;
    height: 17em;
    margin-left: 2em;
    margin-top: 1em;
    display: inline-block;
    background-color: white;
    vertical-align: text-top;
  }

  .foodImgBlock {
    width: 10em;
    height: 11em;
    margin: 0 auto;
    margin-top: 0.5em;
    border-radius: 3px;
    box-sizing: content-box;
    background-color: white;
    display: flex;
    flex-direction: row;
    justify-content:center;
    align-items: center;
  }

  .foodImg {
    box-shadow: 0 0 7px #b4b4b4;
    max-width: 10em;
    max-height: 11em;
  }

  .foodDetails {
    text-align: center;
    margin: 0;
    /*color: #F68447;*/
    font-weight: 500;
  }

  .numButton{
    width: 1.5em;
  }
</style>

<script>
  export default{
    data () {
      return {
        userHeadImgStyle: {
          headImgSrc: this.$getLocalStorage('headImg') === '' || this.$getLocalStorage('headImg') === null ? '/static/img/zfood/logo-gray-square.jpg' : this.$getLocalStorage('headImg'),
          headImgClass: ''
        },
        userName: '',
        menuType: this.$getLocalStorage('userId') === null,

        shopId: this.$route.params.shopId,
        shopMsg: {},
        foodMap: {}


      }
    },
    mounted(){
      //用户头像初始化
      let img = new Image();
      img.addEventListener('load', (e) => {
        this.$nextTick(() => {
          this.$set(this.userHeadImgStyle, 'headImgClass', img.height > img.width ? 'userHeadImg-w' : 'userHeadImg-h');
        });
      });
      img.src = this.userHeadImgStyle.headImgSrc;
      //用户名称初始化
      if (!this.menuType) {
        this.$http.post('/zfood/user/message', {headers: {"Content-Type": "application/json"}}).then(
          response => {
            this.userName = response.data['name'];
          }
        );
      }
      //商店基本信息初始化
      let data = JSON.stringify({'shopId': this.shopId});
      this.$http.post('/zfood/shop/msg', data, {headers: {"Content-Type": "application/json"}}).then(
        response => {
          this.shopMsg = response.data;
          if (this.shopMsg['headpic'] === null) {
            this.shopMsg['headpic'] = '/static/img/zfood/logo-gray-square.jpg';
          }
          let img = new Image();
          img.addEventListener('load', (e) => {
            this.$nextTick(() => {
              this.$set(this.shopMsg, 'shopImgClass', img.height > img.width ? 'shopHeadImg-w' : 'shopHeadImg-h');
            });
          });
          img.src = this.shopMsg.headpic;
        }
      );
      //菜单数据初始化
      this.$http.post('/zfood/food/user', data, {headers: {"Content-Type": "application/json"}}).then(
        response => {
          this.foodMap = response.data;
          console.log(response.data);
        }
      );
    },
    computed:{
      getFoodNum:function () {
        return this.foodMap.map(function (item) {
          //return item['foodNum']===undefined?'':item['foodNum'];
          return item.foodNum;
        })
      }
    },
    methods: {
      //主
      ToManage(){
        this.$goRoute('UserManage');
      },
      ToLogOut(){
        this.$clearLocalStorage();
        this.$clearCookie();
      },
      ToLogin(){
        this.$goRoute('Login');
      },
      //食物
      SubNum(index){
        if(this.foodMap[index]['foodNum']===undefined)
          this.$set( this.foodMap[index],'foodNum',1);
        else
          this.$set( this.foodMap[index],'foodNum',this.foodMap[index]['foodNum']-1>0?this.foodMap[index]['foodNum']-1:0);
        //this.foodMap[index]['foodNum']=this.foodMap[index]['foodNum']===undefined?1:this.foodMap[index]['foodNum']-1;
      },
      AddNum(index){
        if(this.foodMap[index]['foodNum']===undefined)
          this.$set( this.foodMap[index],'foodNum',1);
        else
          this.$set( this.foodMap[index],'foodNum',this.foodMap[index]['foodNum']+1);
        //this.foodMap[index]['foodNum']=this.foodMap[index]['foodNum']===undefined?1:this.foodMap[index]['foodNum']+1;
      }
    }
  }
</script>
