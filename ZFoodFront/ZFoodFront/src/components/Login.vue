<template>
  <div class="content">
    <div id="card" class="card">
      <div class="login">
        <div class="block">
          <div style="width: 250px;margin: 0 auto;">
            <img class="logo" src="/static/img/zfood/logo.jpg" alt="">
          </div>
          <form @submit.prevent="login">

            <div name="formDiv">
              <div style="padding-top: 1em;">
                <input v-model="loginForm.name" class="form-control inputStyle" type="text" placeholder="用户名" style="box-shadow:none;border-width: 2px;" />
              </div>
              <div style="padding-top: 1em;">
                <input v-model="loginForm.password" class="form-control inputStyle" type="password" placeholder="密码" style="box-shadow:none;border-width: 2px;" />
              </div>

              <div class="submitDiv">
                <button class="btn submitButton" type="submit">登陆</button>
              </div>
            </div>

          </form>

          <div class="changeDiv">
            还没有账号?<a href="javascript:void(0)" id="toRegist">点击注册</a>
          </div>
        </div>
      </div>



      <div class="regist">
        <div class="block">
          <div style="width: 250px;margin: 0 auto;">
            <img class="logo" src="static/img/zfood/logo.jpg" alt="">
          </div>
          <form>
            <div name="formDiv">
              <div style="padding-top: 1em;">
                <input name="name" class="form-control inputStyle" type="text" placeholder="用户名" style="box-shadow:none;border-width: 2px;" />
              </div>
              <div style="padding-top: 1em;">
                <input name="password" class="form-control inputStyle" type="text" placeholder="密码" style="box-shadow:none;border-width: 2px;" />
              </div>
              <div style="padding-top: 1em;">
                <input name="password" class="form-control inputStyle" type="text" placeholder="确认密码" style="box-shadow:none;border-width: 2px;" />
              </div>
              <div class="submitDiv">
                <button class="btn submitButton">注册</button>
              </div>
            </div>

          </form>

          <div class="changeDiv">
            已有账号?<a href="javascript:void(0)" id="toLogin">点击登陆</a>
          </div>
        </div>
      </div>


    </div>
  </div>
</template>

<style scoped>
  @import "../lib/bootstrap/css/bootstrap.css";
  .content{
    padding-top: 3em;
  }

  .login{
    padding-top: 5em;
  }

  .block{
    margin:0 auto;
    width: 400px;
    height: 500px;
    background-color: white;

  }
  .inputStyle{
    margin:0 auto;
    width: 270px;
  }
  .logo{
    width: 250px;
  }
  .submitButton{
    margin-top: 2em;
    width: 220px;
    height: 40px;
    background-color: #F68447;
    color: white;
  }
  .submitDiv{
    margin:0 auto;
    width: 220px;
  }
  .changeDiv{
    padding-top: 1em;
    text-align: center;
  }

  .card{
    width: 400px;
    margin:0 auto;
    border:none;
  }


  /*.card {
      height: 280px;
      width: 225px;
      margin: 10px 10px 20px 10px;
      position: relative;
      cursor: pointer;
      -webkit-perspective: 300px;
      perspective: 300px;
  }*/

  .card .login {
    z-index: 900;
    background: #fff;
  }

  .card .regist {
    z-index: 900;
    background: #fff;
    -webkit-transform: rotateY(90deg);
    -moz-transform: rotateY(90deg);
    -ms-transform: rotateY(90deg);
    -o-transform: rotateY(90deg);
    transform: rotateY(90deg);
  }

  .card .login, .card .regist {
    float: none;
    position: absolute;
    top: 0;
    left: 0;
    width: inherit;
    height: inherit;
    padding: 1px;
    -webkit-transform-style: preserve-3d;
    transform-style: preserve-3d;
    -webkit-backface-visibility: hidden;
    backface-visibility: hidden;

    -webkit-transition-duration: 1s, 1s;
    -moz-transition-duration: 1s, 1s;
    -ms-transition-duration: 1s, 1s;
    -o-transition-duration: 1s, 1s;
    transition-duration: 1s, 1s;

  }



  /* flip */

  .card.flip .login {
    z-index: 900;
    -webkit-transform: rotateY(180deg);
    -moz-transform: rotateY(180deg);
    -ms-transform: rotateY(180deg);
    -o-transform: rotateY(180deg);
    transform: rotateY(180deg);
  }

  .card.flip .regist {
    z-index: 1000;
    -webkit-transform: rotateY(360deg);
    -moz-transform: rotateY(360deg);
    -ms-transform: rotateY(360deg);
    -o-transform: rotateY(360deg);
    transform: rotateY(360deg);
  }

</style>

<script>
  export default{
    data () {
      return {
        msg: '这个是Home模板页',
        loginForm:{
          name:'',
          password:''
        }
      }
    },
    mounted() {
      document.getElementById("toRegist").addEventListener("click",function(e){
        var t=document.getElementById("card");
        if(t.className.search("flip") === -1)//如果正面加入flip类选择器
          t.className = t.className + " flip";
        else
          t.className = "card";

      });
      document.getElementById("toLogin").addEventListener("click",function(e){
        var t=document.getElementById("card");
        if(t.className.search("flip") === -1)//如果正面加入flip类选择器
          t.className = t.className + " flip";
        else
          t.className = "card";

      });
    },
    methods:{
      login(){
        var formData = JSON.stringify(this.loginForm);
        this.$http.post('/zfood/login/in',formData,{headers: {"Content-Type": "application/json"}}).then(
            response => {
              console.log(response.data);
              if(response.data.search("success")!==-1) {
                var j=response.headers.token.split('.');
                let Base64 = require('js-base64').Base64;
                var result2 = Base64.decode(j[1]);
                var json=JSON.parse(result2);
                var exdate = new Date();
                exdate.setTime(exdate.getTime() + json['exp']);
                document.cookie="userId="+json['X-data']['user']['userId']+";expires="+exdate.toGMTString() + ";path=/";
                document.cookie="headImg="+json['X-data']['user']['headImg']+";expires="+exdate.toGMTString()+ ";path=/";
                document.cookie="token="+response.headers.token+";expires="+exdate.toGMTString()+ ";path=/";
                this.$setLocalStorage('userId',json['X-data']['user']['userId']);
                this.$setLocalStorage('headImg',json['X-data']['user']['headImg']);
                if(json['X-data']['user']['state']==='1') {
                  let s=response.data.replace('success','');
                  let j=JSON.parse(s);
                  this.$setLocalStorage('shopId', j['shopId']);
                  this.$setLocalStorage('headImg', j['headImg']);
                  this.$router.push(
                    {
                      name: 'ShopManage',
                      params: {
                        id: j['shopId'],
                        shopName: j['shopName'],
                        address:j['address'],
                        phone:j['phone'],
                        income:j['income'],
                        headImg:j['headImg'],
                        userName:j['userName'],
                        score:j['score']
                      }
                    });
                }else if(json['X-data']['user']['state']==='2') {
                  let s=response.data.replace('success','');
                  let j=JSON.parse(s);
                  this.$setLocalStorage('courierId', j['courierId']);
                  this.$setLocalStorage('headImg', j['headImg']);
                  this.$router.push(
                    {
                      name: 'CourierManage',
                      params: {
                        courierId:j['courierId'],
                        courierName:j['courierName'],
                        phone:j['phone'],
                        score:j['score'],
                        userName:j['userName'],
                        income:j['income'],
                        rateCount:j['rateCount'],
                        password:j['password']
                      }
                    });
                }
                //this.$goRoute('ShopManage');
                else
                  this.$goRoute('Index');
              }else{
                alert('用户名或密码错误');
              }
        }, response => {
                alert(response);
        });
      }
    },
    created(){
    }
  }
</script>
