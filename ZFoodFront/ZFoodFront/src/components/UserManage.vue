<template>
<div>
  <!--<div class="header">
    <a href=""><img class="logo" src="/static/img/zfood/logo.jpg" alt=""></a>
    <a v-on:click="Logout"  style="float: right;margin-top: 0.5em;margin-right:1em; color: #F68447;font-weight: 500;cursor: pointer;">退出</a>
  </div>-->
  <MyHeader :userState="0"></MyHeader>
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
                <div style="font-size: 0.5em;margin-left: 2em;color: gray;">订单号:{{v.orderId}}</div>
                <div>{{i}}</div>
                <div class="margin-left-2" style="font-size: 1em;position: absolute;bottom: 10px;">
                  总计：<span style="color: red;padding-right: 10px;">{{v.price}}￥</span>
                <span class="" style="font-size: 1em;float: right;margin-right: 5px;padding-left: 20px;">下单时间:{{v.orderTime}}</span>
                <span>{{GetOrderState(v.state) }}</span>
                </div>
              </div>
              <button v-if="v.state>1" style="float: right;margin-right: 40px;color: white;background-color:#F68447; " class="btn" @click="CancelOrder(i, $event)">取消订单</button>
            </div><!--正在配送不可退-->

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
                      <div v-for="(s,f) in msg.orderItems" style="color:#F68447;">
                        <div style="display: inline-block;margin-right: 10px;min-width: 80px;">{{s.foodname}}</div>
                        <div style="display: inline-block;margin-right: 10px;min-width: 80px;"><span>{{s.unitprice}}￥</span></div>
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
                      <span style="color:#F68447;">{{msg.price-msg.preferential}}￥<br></span>
                    <!--  <div  v-if="OrderCommentState(msg.state)"><div style="display: inline-block;margin-left: -10px;"><rate :rate="msg.score" :over="msg.score" :length="5"  v-model="msg.score" :readonly="StarRateState(msg.score)"></rate></div></div>
                      <div  v-if="OrderCommentState(msg.state)"><div style="display: inline-block;margin-left: -10px;"><rate :rate="msg.sendscore" :over="msg.sendscore" :length="5" v-model="msg.sendscore" :readonly="StarRateState(msg.sendscore)"></rate></div></div>
                      <input class="form-control"  v-model="msg.comment"  v-if="OrderCommentState(msg.state)" :readonly="msg['orginComment']!==null"/>-->
                      <div  v-if="OrderCommentState(msg.state)"><div style="display: inline-block;margin-left: -10px;"><rate  :length="5"  v-model="msg.score" :readonly="StarRateState(msg.score)"></rate></div></div>
                      <div  v-if="OrderCommentState(msg.state)"><div style="display: inline-block;margin-left: -10px;"><rate  :length="5"  v-model="msg.sendscore" :readonly="StarRateState(msg.sendscore)"></rate></div></div>
                      <input class="form-control"  v-model="msg.comment"  v-if="OrderCommentState(msg.state)" :readonly="tempComment!==null"/>

                    </div>
                  </div>
                </div>

                <div class="modal-footer" v-if="OrderCommentState(msg.state)&&EditState()"><!--v-if="OrderCommentState(msg.state)&&EditState()" -->
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
          <div style="background-color: #F68447; width: 150px;color: white;margin-left: 20px;text-align: center;border-radius: 6px 6px 0  0;margin-top: 50px;">收藏商家</div>
           <div style="background-color: #F68447;margin-left:20px;margin-right:20px;height: 3px; border-radius: 6px;"></div>

          <div style="display: inline-block;margin-bottom: 20px;" v-for="(v,i) in favShops" @click="ToShop(v.shopId)">
            <div style="text-align: center; overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">
            <div class="imgBox">
              <div class="imgCover">
                <img class="orderPic" :src="v.shopPic===null?'/static/img/zfood/logo-gray-square.jpg':v.shopPic" :class="v.imgClass"/>
                <div style="position: absolute;bottom:1em;right: 1em;cursor: pointer;" @click="DeleteFavShop" :data-favShopId="v.favShopId" :data-index="i">
                  <svg  class="icon" style="width: 1.5em; height: 1.5em;vertical-align: middle;fill: currentColor;overflow: hidden;" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1861">
                    <path id="svg_2" d="m117.83648,363.33043c0,0 244.99804,-43.33299 243.8273,-44.99963c1.17074,1.66664 111.16987,-233.33148 109.99913,-234.99813c1.17074,1.66665 99.50328,9.99992 98.33255,8.33327c1.17073,1.66665 111.16986,239.99808 111.16986,239.99808c0,0 274.9978,41.66633 274.9978,41.66633c0,0 8.33327,74.9994 8.33327,74.9994c0,0 -186.66518,193.33179 -186.66518,193.33179c0,0 34.99972,266.66454 34.99972,266.66454c0,0 -54.99956,39.99968 -54.99956,39.99968c0,0 -243.33139,-106.66582 -244.50213,-108.33246c1.17074,1.66664 -238.82734,109.99911 -239.99809,108.33247c1.17075,1.66664 -75.49531,-36.66638 -75.49531,-36.66638c0,0 49.9996,-266.66454 49.9996,-266.66454c0,0 -178.33191,-196.6651 -178.33191,-198.33175c0,-1.66665 9.99992,-71.6661 9.99992,-71.6661l38.33303,-11.66657z" stroke-width="0" stroke="#000" fill="#f7de4f"></path>
                    <path stroke-width="0" stroke="#000" d="m749.62489,980.15289c-15.67289,0 -31.24622,-3.88267 -45.08444,-11.30667l-192.52623,-103.63733l-192.46933,103.60889c-31.20356,16.64 -70.31467,14.39289 -99.04356,-5.30489c-29.51111,-20.33778 -44.74311,-55.92178 -38.84089,-90.69511l37.70312,-225.25156l-162.70223,-162.47466c-25.00266,-24.88889 -33.46489,-60.98489 -22.05866,-94.19378c11.34933,-32.91022 40.064,-56.576 74.96533,-61.75289l221.32622,-32.896l96.128,-199.58044c15.488,-32.08534 48.85333,-52.80712 84.992,-52.80712c36.16711,0 69.51822,20.736 84.96356,52.80712l96.15644,199.58044l221.29778,32.91022c34.87289,5.16267 63.616,28.8 74.97956,61.696c11.392,33.28 2.94399,69.36178 -22.03023,94.25067l-162.75911,162.46044l37.74578,225.28c5.84533,34.77333 -9.38667,70.35734 -38.81244,90.63822c-15.98578,10.90845 -34.61689,16.66845 -53.93067,16.66845zm-237.61067,-175.70133c4.63645,0 9.27289,1.13777 13.48267,3.38489l205.93778,110.86222c12.37333,6.64178 28.31644,5.80266 39.936,-2.13334c11.392,-7.83644 17.12355,-21.00622 14.89066,-34.304l-40.16355,-239.75822c-1.50756,-9.088 1.45066,-18.33244 7.96444,-24.832l173.14133,-172.84267c9.45778,-9.42933 12.68623,-23.02577 8.40534,-35.48444c-4.38045,-12.672 -15.70133,-21.87378 -29.52534,-23.92178l-236.21688,-35.12889c-9.31556,-1.37955 -17.36534,-7.296 -21.44711,-15.78666l-102.69867,-213.14845c-6.03022,-12.52978 -19.25689,-20.608 -33.70667,-20.608s-27.69067,8.09245 -33.76355,20.62222l-102.64178,213.13423c-4.08178,8.49066 -12.13156,14.40711 -21.44711,15.78666l-236.23111,35.12889c-13.62489,2.03378 -25.20178,11.43467 -29.53956,23.99289c-4.26667,12.416 -1.024,25.984 8.43378,35.39911l173.11289,172.85689c6.51378,6.49956 9.472,15.744 7.96444,24.832l-40.13511,239.744c-2.24711,13.312 3.48444,26.43911 14.976,34.36089c11.43467,7.83644 27.34933,8.77511 39.95022,2.06222l205.85245,-110.83378c4.19555,-2.24711 8.832,-3.38488 13.46844,-3.38488zm-311.69422,-347.66223c-6.84089,0 -12.88533,-4.96355 -14.02311,-11.94666c-1.25156,-7.75111 4.01067,-15.06134 11.76178,-16.31289l9.44355,-1.536c7.79378,-1.33689 15.06134,4.01066 16.31289,11.76178c1.25156,7.75111 -4.01067,15.06133 -11.76178,16.31288l-9.44355,1.536c-0.75378,0.128 -1.536,0.18489 -2.28978,0.18489zm52.224,-8.47644c-6.68444,0 -12.64356,-4.72178 -13.93778,-11.53422c-1.49333,-7.70845 3.55556,-15.17511 11.264,-16.65423l153.92711,-29.696l58.05511,-133.14844c3.15734,-7.18222 11.54845,-10.496 18.71645,-7.35289c7.21067,3.14311 10.51022,11.52 7.36711,18.71645l-61.056,140.01777c-1.87733,4.29512 -5.74578,7.39556 -10.35378,8.27734l-161.28,31.11822c-0.91022,0.18489 -1.80622,0.256 -2.70222,0.256z" id="svg_1" fill="#e5b814"></path>
                  </svg>
                </div>
              </div>
            </div>
            <div>{{v.favShopName}}</div>
            </div>
          </div>

          <div style="background-color: #F68447; width: 150px;color: white;margin-left: 20px;text-align: center;border-radius: 6px 6px 0  0">收藏食品</div>
          <div style="background-color: #F68447;margin-left:20px;margin-right:20px;height: 3px; border-radius: 6px;"></div>
          <div style="display: inline-block;margin-bottom: 20px;" v-for="(v,i) in favFoods" @click="ToShop(v.shopId)">
            <div style="text-align: center; overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">
              <div class="imgBox">
                <div class="imgCover">
                  <img class="orderPic" :src="v.foodPic===null?'/static/img/zfood/logo-gray-square.jpg':v.foodPic" :class="v.imgClass"/>
                  <div style="position: absolute;bottom:1em;right: 1em;cursor: pointer;" @click="DeleteFavFood" :data-favfoodId="v.favFoodId" :data-index="i">
                  <svg  class="icon" style="width: 1.5em; height: 1.5em;vertical-align: middle;fill: currentColor;overflow: hidden;" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1861">
                    <path id="svg_2" d="m117.83648,363.33043c0,0 244.99804,-43.33299 243.8273,-44.99963c1.17074,1.66664 111.16987,-233.33148 109.99913,-234.99813c1.17074,1.66665 99.50328,9.99992 98.33255,8.33327c1.17073,1.66665 111.16986,239.99808 111.16986,239.99808c0,0 274.9978,41.66633 274.9978,41.66633c0,0 8.33327,74.9994 8.33327,74.9994c0,0 -186.66518,193.33179 -186.66518,193.33179c0,0 34.99972,266.66454 34.99972,266.66454c0,0 -54.99956,39.99968 -54.99956,39.99968c0,0 -243.33139,-106.66582 -244.50213,-108.33246c1.17074,1.66664 -238.82734,109.99911 -239.99809,108.33247c1.17075,1.66664 -75.49531,-36.66638 -75.49531,-36.66638c0,0 49.9996,-266.66454 49.9996,-266.66454c0,0 -178.33191,-196.6651 -178.33191,-198.33175c0,-1.66665 9.99992,-71.6661 9.99992,-71.6661l38.33303,-11.66657z" stroke-width="0" stroke="#000" fill="#f7de4f"></path>
                    <path stroke-width="0" stroke="#000" d="m749.62489,980.15289c-15.67289,0 -31.24622,-3.88267 -45.08444,-11.30667l-192.52623,-103.63733l-192.46933,103.60889c-31.20356,16.64 -70.31467,14.39289 -99.04356,-5.30489c-29.51111,-20.33778 -44.74311,-55.92178 -38.84089,-90.69511l37.70312,-225.25156l-162.70223,-162.47466c-25.00266,-24.88889 -33.46489,-60.98489 -22.05866,-94.19378c11.34933,-32.91022 40.064,-56.576 74.96533,-61.75289l221.32622,-32.896l96.128,-199.58044c15.488,-32.08534 48.85333,-52.80712 84.992,-52.80712c36.16711,0 69.51822,20.736 84.96356,52.80712l96.15644,199.58044l221.29778,32.91022c34.87289,5.16267 63.616,28.8 74.97956,61.696c11.392,33.28 2.94399,69.36178 -22.03023,94.25067l-162.75911,162.46044l37.74578,225.28c5.84533,34.77333 -9.38667,70.35734 -38.81244,90.63822c-15.98578,10.90845 -34.61689,16.66845 -53.93067,16.66845zm-237.61067,-175.70133c4.63645,0 9.27289,1.13777 13.48267,3.38489l205.93778,110.86222c12.37333,6.64178 28.31644,5.80266 39.936,-2.13334c11.392,-7.83644 17.12355,-21.00622 14.89066,-34.304l-40.16355,-239.75822c-1.50756,-9.088 1.45066,-18.33244 7.96444,-24.832l173.14133,-172.84267c9.45778,-9.42933 12.68623,-23.02577 8.40534,-35.48444c-4.38045,-12.672 -15.70133,-21.87378 -29.52534,-23.92178l-236.21688,-35.12889c-9.31556,-1.37955 -17.36534,-7.296 -21.44711,-15.78666l-102.69867,-213.14845c-6.03022,-12.52978 -19.25689,-20.608 -33.70667,-20.608s-27.69067,8.09245 -33.76355,20.62222l-102.64178,213.13423c-4.08178,8.49066 -12.13156,14.40711 -21.44711,15.78666l-236.23111,35.12889c-13.62489,2.03378 -25.20178,11.43467 -29.53956,23.99289c-4.26667,12.416 -1.024,25.984 8.43378,35.39911l173.11289,172.85689c6.51378,6.49956 9.472,15.744 7.96444,24.832l-40.13511,239.744c-2.24711,13.312 3.48444,26.43911 14.976,34.36089c11.43467,7.83644 27.34933,8.77511 39.95022,2.06222l205.85245,-110.83378c4.19555,-2.24711 8.832,-3.38488 13.46844,-3.38488zm-311.69422,-347.66223c-6.84089,0 -12.88533,-4.96355 -14.02311,-11.94666c-1.25156,-7.75111 4.01067,-15.06134 11.76178,-16.31289l9.44355,-1.536c7.79378,-1.33689 15.06134,4.01066 16.31289,11.76178c1.25156,7.75111 -4.01067,15.06133 -11.76178,16.31288l-9.44355,1.536c-0.75378,0.128 -1.536,0.18489 -2.28978,0.18489zm52.224,-8.47644c-6.68444,0 -12.64356,-4.72178 -13.93778,-11.53422c-1.49333,-7.70845 3.55556,-15.17511 11.264,-16.65423l153.92711,-29.696l58.05511,-133.14844c3.15734,-7.18222 11.54845,-10.496 18.71645,-7.35289c7.21067,3.14311 10.51022,11.52 7.36711,18.71645l-61.056,140.01777c-1.87733,4.29512 -5.74578,7.39556 -10.35378,8.27734l-161.28,31.11822c-0.91022,0.18489 -1.80622,0.256 -2.70222,0.256z" id="svg_1" fill="#e5b814"></path>
                  </svg>
                  </div>
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
    position: relative;
    box-shadow: 0 0 8px #dadada;
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
  import MyHeader from '../components/Header.vue';
  import Rate from '../components/Rate.vue';
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
              tempScore:null,//保存临时状态的打分
              tempSendScore:null,
              tempComment:null,
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
      components:{
        MyHeader,Rate
      },
      mounted(){
        let _this=this;
        $('#myModal').on('hidden.bs.modal', function (e) {
            if(_this.saveType){
              _this.saveType=!_this.saveType;
            }else{
              _this.orderItem[_this.currentOrderIndex]['score']=_this.tempScore;
              _this.orderItem[_this.currentOrderIndex]['sendscore']=_this.tempSendScore;
              _this.orderItem[_this.currentOrderIndex]['comment']=_this.tempComment;
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
        CancelOrder(index,e){
          e.cancelBubble = true;
          event.returnValue = confirm("确定取消该订单吗");
          if(!event.returnValue)
            return;
          let data=JSON.stringify(this.orderItem[index]['orderId']);
          this.$http.post('zfood/order/del',data,{headers: {"Content-Type": "application/json"}});
          this.orderItem.splice(index,1);
        },
        DeleteFavShop(e){
          e.cancelBubble = true;
          let data=JSON.stringify({'favShopId':e.path[2].dataset.favshopid});
          this.$http.post('zfood/favShop/del',data,{headers: {"Content-Type": "application/json"}});
          this.favShops.splice(e.path[2].dataset.index,1);
        },
        DeleteFavFood(e){
          e.cancelBubble = true;
          let data=JSON.stringify({'favFoodId':e.path[2].dataset.favfoodid});
          this.$http.post('zfood/favFood/del',data,{headers: {"Content-Type": "application/json"}});
          this.favFoods.splice(e.path[2].dataset.index,1);
        },
        ToShop(shopId){
            this.$goRoute('/ShopIndex/'+shopId);
        },
        SaveOrder(){
          this.saveType=true
          let commentData={};
          commentData['orderId']=this.orderItem[this.currentOrderIndex]['orderId'];
          commentData['comment']=this.tempComment===null?this.msg['comment']:null;
          commentData['score']=this.tempScore===null?this.msg['score']:null;
          commentData['sendscore']=this.tempSendScore===null?this.msg['sendscore']:null;
          this.$http.post('/zfood/order/edit/comment',JSON.stringify(commentData),{headers: {"Content-Type": "application/json"}});
          $('#myModal').modal('hide');
        },
        StarRateState(score){
          if(score===null)
              return false;
          return true;
        },
        EditState(){
          if(this.tempSendScore!==null&&this.tempScore!==null&&this.tempComment!==null)
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
          this.msg=this.orderItem[index];
          this.currentOrderIndex=index;
          this.tempScore=this.msg.score;
          this.tempSendScore=this.msg.sendscore;
          this.tempComment=this.msg['comment'];
          /* this.currentOrderIndex=index;
           this.msg=this.orderItem[index];
           this.$set(this.msg,'orginComment',this.msg['comment']);
           this.$set(this.msg,'score',this.orderItem[index]['score']);
           this.$set(this.msg,'sendscore',this.orderItem[index]['sendscore']);
           this.tempScore=this.msg.score;
           this.tempSendScore=this.msg.sendscore;*/
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
      watch:{
        currentOrderIndex:function (index){
          this.$set(this.msg,'orginComment',this.msg['comment']);
          this.$set(this.msg,'score',this.orderItem[index]['score']);
          this.$set(this.msg,'sendscore',this.orderItem[index]['sendscore']);
        },
        deep:true
      },
      created(){
          if(this.$checkUserState()!==this.$route.path)
            this.$router.push(this.$checkUserState());
      },
    }
</script>
