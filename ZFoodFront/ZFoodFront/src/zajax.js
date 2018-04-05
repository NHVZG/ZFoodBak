/**
 * Created by NHVZG on 2018/3/13.
 */
import Vue from 'vue'
export default {
  zPost(url,data,success,contextType="application/json"){
    /*var allData= document.cookie.split("; ");
    var token='';
    for (var i = 0; i < allData.length; i++) {
      var arr = allData[i].split("=");
      if (arr[0] ==='token') {
        token= arr[1];
      }
    }*/
    Vue.prototype.$http.post('/zfood'+url,data,{headers: {"Content-Type": contextType}}).then(
      response=>{
        if(response.data['errorCode']===403){alert('重新登陆!');}
        if(success!==null&&success!==''){alert(success);}
        },
      response=>{alert(response);}
    );
  },
  zGet(){}
}
