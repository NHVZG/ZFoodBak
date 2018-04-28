<template>
    <div>
        <zfood-header  :userState="0"></zfood-header>
        <div class="contentBlock">
          <div class="imgBox">
            <div class="imgFrame">
            <img class="foodImg" :src="foodMsg.pic===null?'/static/img/zfood/logo-gray-square.jpg':foodMsg.pic"/>
            </div>
          </div>

          <div class="foodMsgBlock">
            <div class="foodName" style="min-height: 1.5em;">{{foodMsg.name}}</div>
            <div style="display: inline-block;float: right;margin-top: 1em;" @click="Collect">
            <svg  class="icon" style="width: 3em; height:3em;vertical-align: middle;fill: currentColor;overflow: hidden;" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1861">
                <path id="svg_2" d="m117.83648,363.33043c0,0 244.99804,-43.33299 243.8273,-44.99963c1.17074,1.66664 111.16987,-233.33148 109.99913,-234.99813c1.17074,1.66665 99.50328,9.99992 98.33255,8.33327c1.17073,1.66665 111.16986,239.99808 111.16986,239.99808c0,0 274.9978,41.66633 274.9978,41.66633c0,0 8.33327,74.9994 8.33327,74.9994c0,0 -186.66518,193.33179 -186.66518,193.33179c0,0 34.99972,266.66454 34.99972,266.66454c0,0 -54.99956,39.99968 -54.99956,39.99968c0,0 -243.33139,-106.66582 -244.50213,-108.33246c1.17074,1.66664 -238.82734,109.99911 -239.99809,108.33247c1.17075,1.66664 -75.49531,-36.66638 -75.49531,-36.66638c0,0 49.9996,-266.66454 49.9996,-266.66454c0,0 -178.33191,-196.6651 -178.33191,-198.33175c0,-1.66665 9.99992,-71.6661 9.99992,-71.6661l38.33303,-11.66657z" stroke-width="0" stroke="#000"
                  :fill="foodMsg.favFoodId!==null?'#f7de4f':'#d2d2d2'"></path>
                <path stroke-width="0" stroke="#000" d="m749.62489,980.15289c-15.67289,0 -31.24622,-3.88267 -45.08444,-11.30667l-192.52623,-103.63733l-192.46933,103.60889c-31.20356,16.64 -70.31467,14.39289 -99.04356,-5.30489c-29.51111,-20.33778 -44.74311,-55.92178 -38.84089,-90.69511l37.70312,-225.25156l-162.70223,-162.47466c-25.00266,-24.88889 -33.46489,-60.98489 -22.05866,-94.19378c11.34933,-32.91022 40.064,-56.576 74.96533,-61.75289l221.32622,-32.896l96.128,-199.58044c15.488,-32.08534 48.85333,-52.80712 84.992,-52.80712c36.16711,0 69.51822,20.736 84.96356,52.80712l96.15644,199.58044l221.29778,32.91022c34.87289,5.16267 63.616,28.8 74.97956,61.696c11.392,33.28 2.94399,69.36178 -22.03023,94.25067l-162.75911,162.46044l37.74578,225.28c5.84533,34.77333 -9.38667,70.35734 -38.81244,90.63822c-15.98578,10.90845 -34.61689,16.66845 -53.93067,16.66845zm-237.61067,-175.70133c4.63645,0 9.27289,1.13777 13.48267,3.38489l205.93778,110.86222c12.37333,6.64178 28.31644,5.80266 39.936,-2.13334c11.392,-7.83644 17.12355,-21.00622 14.89066,-34.304l-40.16355,-239.75822c-1.50756,-9.088 1.45066,-18.33244 7.96444,-24.832l173.14133,-172.84267c9.45778,-9.42933 12.68623,-23.02577 8.40534,-35.48444c-4.38045,-12.672 -15.70133,-21.87378 -29.52534,-23.92178l-236.21688,-35.12889c-9.31556,-1.37955 -17.36534,-7.296 -21.44711,-15.78666l-102.69867,-213.14845c-6.03022,-12.52978 -19.25689,-20.608 -33.70667,-20.608s-27.69067,8.09245 -33.76355,20.62222l-102.64178,213.13423c-4.08178,8.49066 -12.13156,14.40711 -21.44711,15.78666l-236.23111,35.12889c-13.62489,2.03378 -25.20178,11.43467 -29.53956,23.99289c-4.26667,12.416 -1.024,25.984 8.43378,35.39911l173.11289,172.85689c6.51378,6.49956 9.472,15.744 7.96444,24.832l-40.13511,239.744c-2.24711,13.312 3.48444,26.43911 14.976,34.36089c11.43467,7.83644 27.34933,8.77511 39.95022,2.06222l205.85245,-110.83378c4.19555,-2.24711 8.832,-3.38488 13.46844,-3.38488zm-311.69422,-347.66223c-6.84089,0 -12.88533,-4.96355 -14.02311,-11.94666c-1.25156,-7.75111 4.01067,-15.06134 11.76178,-16.31289l9.44355,-1.536c7.79378,-1.33689 15.06134,4.01066 16.31289,11.76178c1.25156,7.75111 -4.01067,15.06133 -11.76178,16.31288l-9.44355,1.536c-0.75378,0.128 -1.536,0.18489 -2.28978,0.18489zm52.224,-8.47644c-6.68444,0 -12.64356,-4.72178 -13.93778,-11.53422c-1.49333,-7.70845 3.55556,-15.17511 11.264,-16.65423l153.92711,-29.696l58.05511,-133.14844c3.15734,-7.18222 11.54845,-10.496 18.71645,-7.35289c7.21067,3.14311 10.51022,11.52 7.36711,18.71645l-61.056,140.01777c-1.87733,4.29512 -5.74578,7.39556 -10.35378,8.27734l-161.28,31.11822c-0.91022,0.18489 -1.80622,0.256 -2.70222,0.256z" id="svg_1"
                      :fill="foodMsg.favFoodId!==null?'#e5b814':'#adadad'"></path>
            </svg>
            </div>
            <div class="foodName" style="border: dashed  2px;display: block;"></div>
            <div><a class="shopName" :href="'/ShopIndex/'+foodMsg.shopId">商店名:{{foodMsg.shopName}}</a></div>
            <div class="otherMsg" style="margin-top: 2em;">价格:{{foodMsg.price}}￥</div>
            <div class="otherMsg">包装费:{{foodMsg.packPrice===null?0:foodMsg.packprice}}￥</div>
            <div class="otherMsg">成交量:{{foodMsg.score===null?0:foodMsg.score}}</div>
            <div style="display:inline-block;margin-top: 1em;" class="otherMsg" v-if="foodCoupons.length>0">可使用优惠券:</div>
            <button v-for="(v,i) in foodCoupons" class="couponItem btn" @click="PickCoupon(v)" v-on:mouseenter="MouseHoverChange(v,$event)" v-on:mouseleave="MouseHoverChange(v)">
              <!--{{!v.extra?v.extra:v.name}}--><!--extra作为临时存储焦点状态-->
              <!--{{ButtonText(v)}}-->
              {{v.name}}
              <transition name="fade">
                <div
                  class="btn"
                  style="padding:0 0.3em;left:0;position: absolute;top: 0;width:100%;background-color:#F68447;"
                  v-if="v.foodName"
                  :style="v.extra==='已领取'?'cursor:not-allowed;':'cursor:pointer'">{{v.extra}}</div>
              </transition>
            </button>
            <div class="shoppingCartBlock">
              <a style="cursor: pointer" @click="ChangeNum(shoppingCartNum===null?0:parseInt(shoppingCartNum)-1)"><img class="numButton" src="/static/img/zfood/sub-6x-g.png"></a>
              <input class="form-control" style="display: inline-block;width: 10em;border: none;color:#F68447;text-align: center;font-weight:700" v-model="shoppingCartNum"/>
              <a style="cursor: pointer" @click="ChangeNum((shoppingCartNum===null?0:parseInt(shoppingCartNum))+1)"><img class="numButton" src="/static/img/zfood/plus-6x-g.png"></a>
              <a style="margin-left: 2em;cursor: pointer" @click="AddShoppingCart"><img style="width: 3.5em;" src="/static/img/zfood/cart-8x%20-gray-r.png" title="加入购物车"/> </a>
            </div>
          </div>

        </div>

    </div>
</template>

<style scoped>
  .fade-enter-active, .fade-leave-active {
    transition: opacity .5s;
  }
  .fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
    opacity: 0;
  }
  .contentBlock{
    width: 90%;
    min-height: 35em;
    margin: 5em auto;
    background-color: white;
    box-shadow: 0  0 2px #dadada;
  }
  .imgBox{
    display: inline-block;
    width: 30em;
    margin-left: 2em;
    margin-top: 3.5em;
    height: 25em;
  }
  .imgFrame{
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: row;
    justify-content:center;
    align-items: center;
  }
  .foodImg{
    box-shadow: 0 0 5px gray;
    max-width: 28em;
    max-height: 23em;
  }
  .foodMsgBlock{
    margin: 3.5em 2.5em;
    vertical-align: top;
    min-height: 25em;
    min-width: 30em;
    display: inline-block;
  }
  .foodName{
    font-size: 3em;
    font-weight: 600;
    color: #F68447;
    margin-bottom: 0.8em;
    display: inline-block;
  }
  .shopName{
    color: #8c8c8c;
    margin-top: 0.4em;
    font-size: 1.5em;
    text-decoration: none;
  }
  .otherMsg{
    color: #8c8c8c;
    margin-top: 0.8em;
    font-size: 1em;
  }
  .shoppingCartBlock{
    margin-top: 2em;
  }
  .numButton{
    width: 2em;

  }
  .couponItem{
  /*  cursor: pointer;
    padding:0 0.3em;
    display: inline-block;*/
    min-width: 4em;
    position: relative;
    padding:0 0.3em;
    background-color: #F68447;
    color: white;
   /* border-radius: 5px;*/
    margin-left: 0.2em;
  }
  .triangle-right{
    display: inline-block;
    width: 0;
    height: 0;
    vertical-align: top;
    margin-top: 0.9em;
    border-top: 0.9em solid transparent;
    border-left: 10px solid #F68447;
    border-bottom: 0.8em solid transparent;
  }
</style>

<script>
  import ZfoodHeader from '../components/Header.vue';
  export default{
        data () {
            return {
                foodMsg:{
                    foodId:this.$route.params.foodId
                },
                foodCoupons:[],
                shoppingCartNum:null,
            }
        },
        components:{
          ZfoodHeader
        },
        methods:{
          ChangeNum(num){
              if(num<1)
                this.shoppingCartNum=null;
              else
                this.shoppingCartNum=num;
          },
          Collect(){
            if(this.foodMsg.favFoodId) {//取消收藏
              let delData = JSON.stringify([this.foodMsg.favFoodId]);
              this.foodMsg.favFoodId=null;
              this.$http.post('/zfood/favFood/del/list', delData, {headers: {"Content-Type": "application/json"}});
            }else{
              this.$http.post('/zfood/favFood/add/one',this.foodMsg.foodId,{headers: {"Content-Type": "application/json"}}).then(
                response=>{
                  this.foodMsg.favFoodId=response.data;
                }
              );
            }
          },
          ButtonText(v){
              return v.extra;
          },
          MouseHoverChange(v,e){
              if(v.foodName){
                this.$set(v,'foodName',false);
              }else{//获得焦点
                v['extra']=v.userId===null?'领取':'已领取';
                this.$set(v,'foodName',true);
              }
              let details=v.detail===null?'':'<div>'+v.detail+'</div>';
              let date=v.endtime===null?'':'<div>'+(v.starttime===null?'':v.starttime)+'~'+v.endtime+'</div>';
              let html=details+date;
              if(html!==''&&v.foodName) {//避免错误
                $(e.target).popover({
                  trigger: "hover",
                  placement: "top",
                  html: true,
                  content: function () {
                    return html;
                  }
                });
              }
          },
          PickCoupon(item){
              if(item['userId']===null) {
                let couponItem = {};
                couponItem['userId'] = this.$getLocalStorage('userId');
                couponItem['couponid'] = item['couponId'];
                couponItem['starttime'] = item['starttime'];
                couponItem['endtime'] = item['endtime'];
                let couponData = JSON.stringify([couponItem]);
                item['userId']=this.$getLocalStorage('userId');
                this.$http.post('/zfood/userCouponItem/add/list', couponData, {headers: {"Content-Type": "application/json"}});
              }
          },
          AddShoppingCart(){
              if(this.shoppingCartNum>0){
                let orderItem={};
                orderItem['foodId']=this.foodMsg.foodId;
                orderItem['foodname']=this.foodMsg.name;
                orderItem['unitprice']=this.foodMsg.price;
                orderItem['num']=this.shoppingCartNum;
                orderItem['packprice']=this.foodMsg.packPrice;
                let orderItemData=JSON.stringify({'orderItems':[orderItem],'shopId':this.foodMsg.shopId});
                this.$http.post('/zfood/shoppingCart/add',orderItemData,{headers: {"Content-Type": "application/json"}});
                alert('添加成功');
              }else{
                alert('至少增加一份');
              }
          }
        },
        mounted(){
            this.$http.post('/zfood/food/user/one',this.foodMsg.foodId,{headers: {"Content-Type": "application/json"}}).then(
                reponse=>{
                    this.foodMsg=reponse.data;
                    this.foodMsg.orgin=reponse.data.favFoodId;
                }
            );
          this.$http.post('/zfood/singleFood/coupon', this.foodMsg.foodId, {headers: {"Content-Type": "application/json"}}).then(
            response=>{
              this.foodCoupons=response.data;
            /*  this.$nextTick(function () {
                $(".couponItem").popover({
                  trigger:"click",
                  placement:"top",
                  html:true,
                  content:function(){return '<div></div>';}
                });
              });*/
            }
          );

        }
    }
</script>
