<template>

<div>
  <div class="header">
    <a @click="ToIndex" style="cursor: pointer;"><img class="logo" :src="logoImg[userState]" alt="" ></a><!--动态路由参数需要以/开始     src="/static/img/zfood/logo.jpg"-->
    <div class="headerRightButton">

      <div style="vertical-align:bottom;padding-bottom:0.7em;display: inline-block;margin-left: 0.15em;width: 3.5em;" v-if="userState===0">
        <a @click="ToPayOff" style="cursor: pointer"><img style="width: 2.5em;" src="/static/img/zfood/cart-8x -gray.png"></a>
      </div>

      <div id="imgBox" class="imgBox dropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        <img :src="userHeadImgStyle.headImgSrc" :class="userHeadImgStyle.headImgClass" class="userHeader">
      </div>
      <div class="dropdown-menu" aria-labelledby="imgBox" style="width: 50px;min-width: 7em;">
        <div v-if="menuType">
          <a class="dropdown-item" @click="ToLogin">登陆</a>
        </div>
        <div v-else>
          <a class="dropdown-header" style="font-weight: 600;" :style="'color:'+userNameColor[userState]">{{userName}}</a>
          <a class="dropdown-item" @click="ToManage">我的资料</a>
          <a class="dropdown-item" @click="ToLogOut">退出</a>
        </div>
      </div>


    </div>
  </div>
</div>

</template>

<style scoped>
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
</style>

<script>
    export default{
        props:{
            userState:[Number]
        },
        data () {
            return {
              logoImg:[
                  '/static/img/zfood/logo.jpg',
                  '/static/img/zfood/logo-b.jpg',
                  '/static/img/zfood/logo-g.jpg'
              ],
              userHeadImgStyle: {
                headImgSrc: this.$getLocalStorage('headImg') === '' || this.$getLocalStorage('headImg') === null ? '/static/img/zfood/logo-gray-square.jpg' : this.$getLocalStorage('headImg'),
                headImgClass: '',
              },
              userNameColor:['#F68447','#007bff','#62c54e'],
              userName: '',
              menuType: this.$getLocalStorage('userId') === null,
              userId:this.$getLocalStorage('userId'),
            }
        },
      methods:{
        ToIndex(){
          this.$goRoute('/Index');
        },
        ToManage(){
          this.$goRoute('/UserManage');
        },
        ToLogOut(){
          this.$clearLocalStorage();
          this.$clearCookie();
          alert('已退出当前账户');
          this.$goRoute('/Login');
        },
        ToLogin(){
          this.$goRoute('/Login');
        },
        ToPayOff(){
          this.$goRoute('/PayOff');
        },
      },
      mounted(){
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
      }
    }
</script>
