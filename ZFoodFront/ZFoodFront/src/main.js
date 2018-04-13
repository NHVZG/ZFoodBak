// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'

//import  ZFoodAjax from './zajax'

import './lib/bootstrap/css/bootstrap.css'
import './lib/bootstrap/js/bootstrap.bundle'
import './lib/bootstrap/js/bootstrap'
import $ from 'jquery'
/*import rate from 'vue-rate';*/

/*Vue.use(rate);*/
Vue.prototype.$http = axios;
//Vue.prototype.$zajax=ZFoodAjax;

Vue.prototype.$zPost=function(url,data,success,contextType="application/json"){
  /*var allData= document.cookie.split("; ");
   var token='';
   for (var i = 0; i < allData.length; i++) {
   var arr = allData[i].split("=");
   if (arr[0] ==='token') {
   token= arr[1];
   }
   }*/
  this.$http.post('/zfood'+url,data,{headers: {"Content-Type": contextType}}).then(
    response=>{
      if(response.data['errorCode']===403){alert('重新登陆!');this.$goRoute('Login');return;}
      if(success!==null&&success!==''){alert(success);}
    },
    response=>{alert(response);}
  );
};
Vue.prototype.$zGet=function(url,data,success,contextType="") {
  this.$http.post('/zfood'+url,data,{headers: {"Content-Type": contextType}}).then(
    response=>{}
  )
};
Vue.prototype.$getTokenData=function (key) {
  let token=this.$getCookieData('token');
  let data=token.split('.');
  let Base64 = require('js-base64').Base64;
  let result2 = Base64.decode(data[1]);
  let json=JSON.parse(result2);
  return json['X-data']['user'][key];
};
Vue.prototype.$getCookieData=function (key) {
  let cookie = document.cookie;
  let cookiedata = cookie.split("; ");
  for ( let i = 0; i < cookiedata.length; i++) {
    let arr = cookiedata[i].split("=");
    if (arr[0] ==='token'){
      return arr[1];
    }
  }
  return "";
};
Vue.prototype.$checkUserState=function () {
  let state=this.$getTokenData('state');
  let list=['/UserManage','/ShopManage','/CourierManage','/UserManage'];
  return list[state];
};
Vue.prototype.$goRoute = function (index) {
  this.$router.push(index);
};
//localStorage
Vue.prototype.$setLocalStorage=function(key,value){
  window.localStorage.setItem(key,value);
};
Vue.prototype.$getLocalStorage=function(key){
  return window.localStorage.getItem(key);
};
Vue.prototype.$removeLocalStorage=function(key){
  window.localStorage.removeItem(key);
};
Vue.prototype.$clearLocalStorage=function(){
  window.localStorage.clear();
};
//cookie
Vue.prototype.$clearCookie=function () {
  var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
  if(keys) {
    for(var i = keys.length; i--;)
      document.cookie = keys[i] + '=0;expires=' + new Date(0).toUTCString()+";path=/";
  }
};


const service = axios.create({
  //baseURL: process.env.BASE_API, // node环境的不同，对应不同的baseURL
  timeout: 5000, // 请求的超时时间
  //设置默认请求头，使post请求发送的是formdata格式数据// axios的header默认的Content-Type好像是'application/json;charset=UTF-8',我的项目都是用json格式传输，如果需要更改的话，可以用这种方式修改
  // headers: {
  // "Content-Type": "application/x-www-form-urlencoded"
  // },
  withCredentials: true // 允许携带cookie
});



/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
})
