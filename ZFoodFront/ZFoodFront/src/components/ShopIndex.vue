<template>
  <div style="padding-bottom: 2em;">
    <!--头部栏-->
    <div class="header">
      <a href=""><img class="logo" src="/static/img/zfood/logo.jpg" alt="" ></a><!--动态路由参数需要以/开始-->
      <div class="headerRightButton">


        <div style="vertical-align:bottom;padding-bottom:0.7em;display: inline-block;margin-left: 0.15em;width: 3.5em;">
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

      <div style="position: absolute;right:7em;" title="收藏商家" @click="CollectShop">
        <svg  class="icon" style="width: 2.5em; height:2.5em;vertical-align: middle;fill: currentColor;overflow: hidden;" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1861">
          <path id="svg_2" d="m117.83648,363.33043c0,0 244.99804,-43.33299 243.8273,-44.99963c1.17074,1.66664 111.16987,-233.33148 109.99913,-234.99813c1.17074,1.66665 99.50328,9.99992 98.33255,8.33327c1.17073,1.66665 111.16986,239.99808 111.16986,239.99808c0,0 274.9978,41.66633 274.9978,41.66633c0,0 8.33327,74.9994 8.33327,74.9994c0,0 -186.66518,193.33179 -186.66518,193.33179c0,0 34.99972,266.66454 34.99972,266.66454c0,0 -54.99956,39.99968 -54.99956,39.99968c0,0 -243.33139,-106.66582 -244.50213,-108.33246c1.17074,1.66664 -238.82734,109.99911 -239.99809,108.33247c1.17075,1.66664 -75.49531,-36.66638 -75.49531,-36.66638c0,0 49.9996,-266.66454 49.9996,-266.66454c0,0 -178.33191,-196.6651 -178.33191,-198.33175c0,-1.66665 9.99992,-71.6661 9.99992,-71.6661l38.33303,-11.66657z" stroke-width="0" stroke="#000"
                :fill="!favShop.isFavShop?'#d2d2d2':'#f7de4f'"></path>
          <path stroke-width="0" stroke="#000" d="m749.62489,980.15289c-15.67289,0 -31.24622,-3.88267 -45.08444,-11.30667l-192.52623,-103.63733l-192.46933,103.60889c-31.20356,16.64 -70.31467,14.39289 -99.04356,-5.30489c-29.51111,-20.33778 -44.74311,-55.92178 -38.84089,-90.69511l37.70312,-225.25156l-162.70223,-162.47466c-25.00266,-24.88889 -33.46489,-60.98489 -22.05866,-94.19378c11.34933,-32.91022 40.064,-56.576 74.96533,-61.75289l221.32622,-32.896l96.128,-199.58044c15.488,-32.08534 48.85333,-52.80712 84.992,-52.80712c36.16711,0 69.51822,20.736 84.96356,52.80712l96.15644,199.58044l221.29778,32.91022c34.87289,5.16267 63.616,28.8 74.97956,61.696c11.392,33.28 2.94399,69.36178 -22.03023,94.25067l-162.75911,162.46044l37.74578,225.28c5.84533,34.77333 -9.38667,70.35734 -38.81244,90.63822c-15.98578,10.90845 -34.61689,16.66845 -53.93067,16.66845zm-237.61067,-175.70133c4.63645,0 9.27289,1.13777 13.48267,3.38489l205.93778,110.86222c12.37333,6.64178 28.31644,5.80266 39.936,-2.13334c11.392,-7.83644 17.12355,-21.00622 14.89066,-34.304l-40.16355,-239.75822c-1.50756,-9.088 1.45066,-18.33244 7.96444,-24.832l173.14133,-172.84267c9.45778,-9.42933 12.68623,-23.02577 8.40534,-35.48444c-4.38045,-12.672 -15.70133,-21.87378 -29.52534,-23.92178l-236.21688,-35.12889c-9.31556,-1.37955 -17.36534,-7.296 -21.44711,-15.78666l-102.69867,-213.14845c-6.03022,-12.52978 -19.25689,-20.608 -33.70667,-20.608s-27.69067,8.09245 -33.76355,20.62222l-102.64178,213.13423c-4.08178,8.49066 -12.13156,14.40711 -21.44711,15.78666l-236.23111,35.12889c-13.62489,2.03378 -25.20178,11.43467 -29.53956,23.99289c-4.26667,12.416 -1.024,25.984 8.43378,35.39911l173.11289,172.85689c6.51378,6.49956 9.472,15.744 7.96444,24.832l-40.13511,239.744c-2.24711,13.312 3.48444,26.43911 14.976,34.36089c11.43467,7.83644 27.34933,8.77511 39.95022,2.06222l205.85245,-110.83378c4.19555,-2.24711 8.832,-3.38488 13.46844,-3.38488zm-311.69422,-347.66223c-6.84089,0 -12.88533,-4.96355 -14.02311,-11.94666c-1.25156,-7.75111 4.01067,-15.06134 11.76178,-16.31289l9.44355,-1.536c7.79378,-1.33689 15.06134,4.01066 16.31289,11.76178c1.25156,7.75111 -4.01067,15.06133 -11.76178,16.31288l-9.44355,1.536c-0.75378,0.128 -1.536,0.18489 -2.28978,0.18489zm52.224,-8.47644c-6.68444,0 -12.64356,-4.72178 -13.93778,-11.53422c-1.49333,-7.70845 3.55556,-15.17511 11.264,-16.65423l153.92711,-29.696l58.05511,-133.14844c3.15734,-7.18222 11.54845,-10.496 18.71645,-7.35289c7.21067,3.14311 10.51022,11.52 7.36711,18.71645l-61.056,140.01777c-1.87733,4.29512 -5.74578,7.39556 -10.35378,8.27734l-161.28,31.11822c-0.91022,0.18489 -1.80622,0.256 -2.70222,0.256z" id="svg_1"
                :fill="!favShop.isFavShop?'#adadad':'#e5b814'"></path>
        </svg>
        <!--<div v-if="favShop.isFavShop">
          <svg  class="icon" style="width: 2.5em; height: 2.5em;vertical-align: middle;fill: currentColor;overflow: hidden;" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1861">
            <path id="svg_2" d="m117.83648,363.33043c0,0 244.99804,-43.33299 243.8273,-44.99963c1.17074,1.66664 111.16987,-233.33148 109.99913,-234.99813c1.17074,1.66665 99.50328,9.99992 98.33255,8.33327c1.17073,1.66665 111.16986,239.99808 111.16986,239.99808c0,0 274.9978,41.66633 274.9978,41.66633c0,0 8.33327,74.9994 8.33327,74.9994c0,0 -186.66518,193.33179 -186.66518,193.33179c0,0 34.99972,266.66454 34.99972,266.66454c0,0 -54.99956,39.99968 -54.99956,39.99968c0,0 -243.33139,-106.66582 -244.50213,-108.33246c1.17074,1.66664 -238.82734,109.99911 -239.99809,108.33247c1.17075,1.66664 -75.49531,-36.66638 -75.49531,-36.66638c0,0 49.9996,-266.66454 49.9996,-266.66454c0,0 -178.33191,-196.6651 -178.33191,-198.33175c0,-1.66665 9.99992,-71.6661 9.99992,-71.6661l38.33303,-11.66657z" stroke-width="0" stroke="#000" fill="#f7de4f"/>
            <path stroke-width="0" stroke="#000" d="m749.62489,980.15289c-15.67289,0 -31.24622,-3.88267 -45.08444,-11.30667l-192.52623,-103.63733l-192.46933,103.60889c-31.20356,16.64 -70.31467,14.39289 -99.04356,-5.30489c-29.51111,-20.33778 -44.74311,-55.92178 -38.84089,-90.69511l37.70312,-225.25156l-162.70223,-162.47466c-25.00266,-24.88889 -33.46489,-60.98489 -22.05866,-94.19378c11.34933,-32.91022 40.064,-56.576 74.96533,-61.75289l221.32622,-32.896l96.128,-199.58044c15.488,-32.08534 48.85333,-52.80712 84.992,-52.80712c36.16711,0 69.51822,20.736 84.96356,52.80712l96.15644,199.58044l221.29778,32.91022c34.87289,5.16267 63.616,28.8 74.97956,61.696c11.392,33.28 2.94399,69.36178 -22.03023,94.25067l-162.75911,162.46044l37.74578,225.28c5.84533,34.77333 -9.38667,70.35734 -38.81244,90.63822c-15.98578,10.90845 -34.61689,16.66845 -53.93067,16.66845zm-237.61067,-175.70133c4.63645,0 9.27289,1.13777 13.48267,3.38489l205.93778,110.86222c12.37333,6.64178 28.31644,5.80266 39.936,-2.13334c11.392,-7.83644 17.12355,-21.00622 14.89066,-34.304l-40.16355,-239.75822c-1.50756,-9.088 1.45066,-18.33244 7.96444,-24.832l173.14133,-172.84267c9.45778,-9.42933 12.68623,-23.02577 8.40534,-35.48444c-4.38045,-12.672 -15.70133,-21.87378 -29.52534,-23.92178l-236.21688,-35.12889c-9.31556,-1.37955 -17.36534,-7.296 -21.44711,-15.78666l-102.69867,-213.14845c-6.03022,-12.52978 -19.25689,-20.608 -33.70667,-20.608s-27.69067,8.09245 -33.76355,20.62222l-102.64178,213.13423c-4.08178,8.49066 -12.13156,14.40711 -21.44711,15.78666l-236.23111,35.12889c-13.62489,2.03378 -25.20178,11.43467 -29.53956,23.99289c-4.26667,12.416 -1.024,25.984 8.43378,35.39911l173.11289,172.85689c6.51378,6.49956 9.472,15.744 7.96444,24.832l-40.13511,239.744c-2.24711,13.312 3.48444,26.43911 14.976,34.36089c11.43467,7.83644 27.34933,8.77511 39.95022,2.06222l205.85245,-110.83378c4.19555,-2.24711 8.832,-3.38488 13.46844,-3.38488zm-311.69422,-347.66223c-6.84089,0 -12.88533,-4.96355 -14.02311,-11.94666c-1.25156,-7.75111 4.01067,-15.06134 11.76178,-16.31289l9.44355,-1.536c7.79378,-1.33689 15.06134,4.01066 16.31289,11.76178c1.25156,7.75111 -4.01067,15.06133 -11.76178,16.31288l-9.44355,1.536c-0.75378,0.128 -1.536,0.18489 -2.28978,0.18489zm52.224,-8.47644c-6.68444,0 -12.64356,-4.72178 -13.93778,-11.53422c-1.49333,-7.70845 3.55556,-15.17511 11.264,-16.65423l153.92711,-29.696l58.05511,-133.14844c3.15734,-7.18222 11.54845,-10.496 18.71645,-7.35289c7.21067,3.14311 10.51022,11.52 7.36711,18.71645l-61.056,140.01777c-1.87733,4.29512 -5.74578,7.39556 -10.35378,8.27734l-161.28,31.11822c-0.91022,0.18489 -1.80622,0.256 -2.70222,0.256z" id="svg_1" fill="#e5b814"/>
          </svg>
        </div>
        <div v-else>
          <svg  class="icon" style="width: 2.5em; height: 2.5em;vertical-align: middle;fill: currentColor;overflow: hidden;" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1861">
            <path fill="#BBBBBB" id="svg_1" d="m749.62489,980.15289c-15.67289,0 -31.24622,-3.88267 -45.08444,-11.30667l-192.52623,-103.63733l-192.46933,103.60889c-31.20356,16.64 -70.31467,14.39289 -99.04356,-5.30489c-29.51111,-20.33778 -44.74311,-55.92178 -38.84089,-90.69511l37.70312,-225.25156l-162.70223,-162.47466c-25.00266,-24.88889 -33.46489,-60.98489 -22.05866,-94.19378c11.34933,-32.91022 40.064,-56.576 74.96533,-61.75289l221.32622,-32.896l96.128,-199.58044c15.488,-32.08534 48.85333,-52.80712 84.992,-52.80712c36.16711,0 69.51822,20.736 84.96356,52.80712l96.15644,199.58044l221.29778,32.91022c34.87289,5.16267 63.616,28.8 74.97956,61.696c11.392,33.28 2.94399,69.36178 -22.03023,94.25067l-162.75911,162.46044l37.74578,225.28c5.84533,34.77333 -9.38667,70.35734 -38.81244,90.63822c-15.98578,10.90845 -34.61689,16.66845 -53.93067,16.66845zm-237.61067,-175.70133c4.63645,0 9.27289,1.13777 13.48267,3.38489l205.93778,110.86222c12.37333,6.64178 28.31644,5.80266 39.936,-2.13334c11.392,-7.83644 17.12355,-21.00622 14.89066,-34.304l-40.16355,-239.75822c-1.50756,-9.088 1.45066,-18.33244 7.96444,-24.832l173.14133,-172.84267c9.45778,-9.42933 12.68623,-23.02577 8.40534,-35.48444c-4.38045,-12.672 -15.70133,-21.87378 -29.52534,-23.92178l-236.21688,-35.12889c-9.31556,-1.37955 -17.36534,-7.296 -21.44711,-15.78666l-102.69867,-213.14845c-6.03022,-12.52978 -19.25689,-20.608 -33.70667,-20.608s-27.69067,8.09245 -33.76355,20.62222l-102.64178,213.13423c-4.08178,8.49066 -12.13156,14.40711 -21.44711,15.78666l-236.23111,35.12889c-13.62489,2.03378 -25.20178,11.43467 -29.53956,23.99289c-4.26667,12.416 -1.024,25.984 8.43378,35.39911l173.11289,172.85689c6.51378,6.49956 9.472,15.744 7.96444,24.832l-40.13511,239.744c-2.24711,13.312 3.48444,26.43911 14.976,34.36089c11.43467,7.83644 27.34933,8.77511 39.95022,2.06222l205.85245,-110.83378c4.19555,-2.24711 8.832,-3.38488 13.46844,-3.38488zm-311.69422,-347.66223c-6.84089,0 -12.88533,-4.96355 -14.02311,-11.94666c-1.25156,-7.75111 4.01067,-15.06134 11.76178,-16.31289l9.44355,-1.536c7.79378,-1.33689 15.06134,4.01066 16.31289,11.76178c1.25156,7.75111 -4.01067,15.06133 -11.76178,16.31288l-9.44355,1.536c-0.75378,0.128 -1.536,0.18489 -2.28978,0.18489zm52.224,-8.47644c-6.68444,0 -12.64356,-4.72178 -13.93778,-11.53422c-1.49333,-7.70845 3.55556,-15.17511 11.264,-16.65423l153.92711,-29.696l58.05511,-133.14844c3.15734,-7.18222 11.54845,-10.496 18.71645,-7.35289c7.21067,3.14311 10.51022,11.52 7.36711,18.71645l-61.056,140.01777c-1.87733,4.29512 -5.74578,7.39556 -10.35378,8.27734l-161.28,31.11822c-0.91022,0.18489 -1.80622,0.256 -2.70222,0.256z"/>
          </svg>
        </div>-->
      </div>

      <div style="margin:0 33em;width: 19em;">
      <div class="menu-block nav navbar-nav" id="Tab">
        <a class="menu-item" href="#Foods" data-toggle="tab" data-title="0" v-on:click="TabChange">菜单</a>
        <a class="margin-left-3em menu-item" href="#Coupons" data-toggle="tab" data-title="1" v-on:click="TabChange">优惠券</a>
        <a class="margin-left-3em menu-item" href="#Comments" data-toggle="tab" data-title="2" v-on:click="TabChange">评价</a>
      </div>
      </div>


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

          <div style="position: absolute;right: 0.4em;top:0.4em;" @click="Collect(i)">
            <!--未收藏-->

            <svg  class="icon" style="width: 1.3em; height: 1.3em;vertical-align: middle;fill: currentColor;overflow: hidden;" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1861">
              <path id="svg_2" d="m117.83648,363.33043c0,0 244.99804,-43.33299 243.8273,-44.99963c1.17074,1.66664 111.16987,-233.33148 109.99913,-234.99813c1.17074,1.66665 99.50328,9.99992 98.33255,8.33327c1.17073,1.66665 111.16986,239.99808 111.16986,239.99808c0,0 274.9978,41.66633 274.9978,41.66633c0,0 8.33327,74.9994 8.33327,74.9994c0,0 -186.66518,193.33179 -186.66518,193.33179c0,0 34.99972,266.66454 34.99972,266.66454c0,0 -54.99956,39.99968 -54.99956,39.99968c0,0 -243.33139,-106.66582 -244.50213,-108.33246c1.17074,1.66664 -238.82734,109.99911 -239.99809,108.33247c1.17075,1.66664 -75.49531,-36.66638 -75.49531,-36.66638c0,0 49.9996,-266.66454 49.9996,-266.66454c0,0 -178.33191,-196.6651 -178.33191,-198.33175c0,-1.66665 9.99992,-71.6661 9.99992,-71.6661l38.33303,-11.66657z" stroke-width="0" stroke="#000"
                    :fill="v.favFoodId===null?'#d2d2d2':'#f7de4f'"></path>
              <path stroke-width="0" stroke="#000" d="m749.62489,980.15289c-15.67289,0 -31.24622,-3.88267 -45.08444,-11.30667l-192.52623,-103.63733l-192.46933,103.60889c-31.20356,16.64 -70.31467,14.39289 -99.04356,-5.30489c-29.51111,-20.33778 -44.74311,-55.92178 -38.84089,-90.69511l37.70312,-225.25156l-162.70223,-162.47466c-25.00266,-24.88889 -33.46489,-60.98489 -22.05866,-94.19378c11.34933,-32.91022 40.064,-56.576 74.96533,-61.75289l221.32622,-32.896l96.128,-199.58044c15.488,-32.08534 48.85333,-52.80712 84.992,-52.80712c36.16711,0 69.51822,20.736 84.96356,52.80712l96.15644,199.58044l221.29778,32.91022c34.87289,5.16267 63.616,28.8 74.97956,61.696c11.392,33.28 2.94399,69.36178 -22.03023,94.25067l-162.75911,162.46044l37.74578,225.28c5.84533,34.77333 -9.38667,70.35734 -38.81244,90.63822c-15.98578,10.90845 -34.61689,16.66845 -53.93067,16.66845zm-237.61067,-175.70133c4.63645,0 9.27289,1.13777 13.48267,3.38489l205.93778,110.86222c12.37333,6.64178 28.31644,5.80266 39.936,-2.13334c11.392,-7.83644 17.12355,-21.00622 14.89066,-34.304l-40.16355,-239.75822c-1.50756,-9.088 1.45066,-18.33244 7.96444,-24.832l173.14133,-172.84267c9.45778,-9.42933 12.68623,-23.02577 8.40534,-35.48444c-4.38045,-12.672 -15.70133,-21.87378 -29.52534,-23.92178l-236.21688,-35.12889c-9.31556,-1.37955 -17.36534,-7.296 -21.44711,-15.78666l-102.69867,-213.14845c-6.03022,-12.52978 -19.25689,-20.608 -33.70667,-20.608s-27.69067,8.09245 -33.76355,20.62222l-102.64178,213.13423c-4.08178,8.49066 -12.13156,14.40711 -21.44711,15.78666l-236.23111,35.12889c-13.62489,2.03378 -25.20178,11.43467 -29.53956,23.99289c-4.26667,12.416 -1.024,25.984 8.43378,35.39911l173.11289,172.85689c6.51378,6.49956 9.472,15.744 7.96444,24.832l-40.13511,239.744c-2.24711,13.312 3.48444,26.43911 14.976,34.36089c11.43467,7.83644 27.34933,8.77511 39.95022,2.06222l205.85245,-110.83378c4.19555,-2.24711 8.832,-3.38488 13.46844,-3.38488zm-311.69422,-347.66223c-6.84089,0 -12.88533,-4.96355 -14.02311,-11.94666c-1.25156,-7.75111 4.01067,-15.06134 11.76178,-16.31289l9.44355,-1.536c7.79378,-1.33689 15.06134,4.01066 16.31289,11.76178c1.25156,7.75111 -4.01067,15.06133 -11.76178,16.31288l-9.44355,1.536c-0.75378,0.128 -1.536,0.18489 -2.28978,0.18489zm52.224,-8.47644c-6.68444,0 -12.64356,-4.72178 -13.93778,-11.53422c-1.49333,-7.70845 3.55556,-15.17511 11.264,-16.65423l153.92711,-29.696l58.05511,-133.14844c3.15734,-7.18222 11.54845,-10.496 18.71645,-7.35289c7.21067,3.14311 10.51022,11.52 7.36711,18.71645l-61.056,140.01777c-1.87733,4.29512 -5.74578,7.39556 -10.35378,8.27734l-161.28,31.11822c-0.91022,0.18489 -1.80622,0.256 -2.70222,0.256z" id="svg_1"
                    :fill="v.favFoodId===null?'#adadad':'#e5b814'"></path>
            </svg>
            <!--<div v-if="v.favFoodId===null">
           <svg  class="icon" style="width: 1.3em; height: 1.3em;vertical-align: middle;fill: currentColor;overflow: hidden;" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1861">
             <path fill="#BBBBBB" id="svg_1" d="m749.62489,980.15289c-15.67289,0 -31.24622,-3.88267 -45.08444,-11.30667l-192.52623,-103.63733l-192.46933,103.60889c-31.20356,16.64 -70.31467,14.39289 -99.04356,-5.30489c-29.51111,-20.33778 -44.74311,-55.92178 -38.84089,-90.69511l37.70312,-225.25156l-162.70223,-162.47466c-25.00266,-24.88889 -33.46489,-60.98489 -22.05866,-94.19378c11.34933,-32.91022 40.064,-56.576 74.96533,-61.75289l221.32622,-32.896l96.128,-199.58044c15.488,-32.08534 48.85333,-52.80712 84.992,-52.80712c36.16711,0 69.51822,20.736 84.96356,52.80712l96.15644,199.58044l221.29778,32.91022c34.87289,5.16267 63.616,28.8 74.97956,61.696c11.392,33.28 2.94399,69.36178 -22.03023,94.25067l-162.75911,162.46044l37.74578,225.28c5.84533,34.77333 -9.38667,70.35734 -38.81244,90.63822c-15.98578,10.90845 -34.61689,16.66845 -53.93067,16.66845zm-237.61067,-175.70133c4.63645,0 9.27289,1.13777 13.48267,3.38489l205.93778,110.86222c12.37333,6.64178 28.31644,5.80266 39.936,-2.13334c11.392,-7.83644 17.12355,-21.00622 14.89066,-34.304l-40.16355,-239.75822c-1.50756,-9.088 1.45066,-18.33244 7.96444,-24.832l173.14133,-172.84267c9.45778,-9.42933 12.68623,-23.02577 8.40534,-35.48444c-4.38045,-12.672 -15.70133,-21.87378 -29.52534,-23.92178l-236.21688,-35.12889c-9.31556,-1.37955 -17.36534,-7.296 -21.44711,-15.78666l-102.69867,-213.14845c-6.03022,-12.52978 -19.25689,-20.608 -33.70667,-20.608s-27.69067,8.09245 -33.76355,20.62222l-102.64178,213.13423c-4.08178,8.49066 -12.13156,14.40711 -21.44711,15.78666l-236.23111,35.12889c-13.62489,2.03378 -25.20178,11.43467 -29.53956,23.99289c-4.26667,12.416 -1.024,25.984 8.43378,35.39911l173.11289,172.85689c6.51378,6.49956 9.472,15.744 7.96444,24.832l-40.13511,239.744c-2.24711,13.312 3.48444,26.43911 14.976,34.36089c11.43467,7.83644 27.34933,8.77511 39.95022,2.06222l205.85245,-110.83378c4.19555,-2.24711 8.832,-3.38488 13.46844,-3.38488zm-311.69422,-347.66223c-6.84089,0 -12.88533,-4.96355 -14.02311,-11.94666c-1.25156,-7.75111 4.01067,-15.06134 11.76178,-16.31289l9.44355,-1.536c7.79378,-1.33689 15.06134,4.01066 16.31289,11.76178c1.25156,7.75111 -4.01067,15.06133 -11.76178,16.31288l-9.44355,1.536c-0.75378,0.128 -1.536,0.18489 -2.28978,0.18489zm52.224,-8.47644c-6.68444,0 -12.64356,-4.72178 -13.93778,-11.53422c-1.49333,-7.70845 3.55556,-15.17511 11.264,-16.65423l153.92711,-29.696l58.05511,-133.14844c3.15734,-7.18222 11.54845,-10.496 18.71645,-7.35289c7.21067,3.14311 10.51022,11.52 7.36711,18.71645l-61.056,140.01777c-1.87733,4.29512 -5.74578,7.39556 -10.35378,8.27734l-161.28,31.11822c-0.91022,0.18489 -1.80622,0.256 -2.70222,0.256z"></path>
           </svg>
            </div>
            &lt;!&ndash;收藏&ndash;&gt;
            <div v-else>
            <svg  class="icon" style="width: 1.3em; height: 1.3em;vertical-align: middle;fill: currentColor;overflow: hidden;" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1861">
              <path id="svg_2" d="m117.83648,363.33043c0,0 244.99804,-43.33299 243.8273,-44.99963c1.17074,1.66664 111.16987,-233.33148 109.99913,-234.99813c1.17074,1.66665 99.50328,9.99992 98.33255,8.33327c1.17073,1.66665 111.16986,239.99808 111.16986,239.99808c0,0 274.9978,41.66633 274.9978,41.66633c0,0 8.33327,74.9994 8.33327,74.9994c0,0 -186.66518,193.33179 -186.66518,193.33179c0,0 34.99972,266.66454 34.99972,266.66454c0,0 -54.99956,39.99968 -54.99956,39.99968c0,0 -243.33139,-106.66582 -244.50213,-108.33246c1.17074,1.66664 -238.82734,109.99911 -239.99809,108.33247c1.17075,1.66664 -75.49531,-36.66638 -75.49531,-36.66638c0,0 49.9996,-266.66454 49.9996,-266.66454c0,0 -178.33191,-196.6651 -178.33191,-198.33175c0,-1.66665 9.99992,-71.6661 9.99992,-71.6661l38.33303,-11.66657z" stroke-width="0" stroke="#000" fill="#f7de4f"></path>
              <path stroke-width="0" stroke="#000" d="m749.62489,980.15289c-15.67289,0 -31.24622,-3.88267 -45.08444,-11.30667l-192.52623,-103.63733l-192.46933,103.60889c-31.20356,16.64 -70.31467,14.39289 -99.04356,-5.30489c-29.51111,-20.33778 -44.74311,-55.92178 -38.84089,-90.69511l37.70312,-225.25156l-162.70223,-162.47466c-25.00266,-24.88889 -33.46489,-60.98489 -22.05866,-94.19378c11.34933,-32.91022 40.064,-56.576 74.96533,-61.75289l221.32622,-32.896l96.128,-199.58044c15.488,-32.08534 48.85333,-52.80712 84.992,-52.80712c36.16711,0 69.51822,20.736 84.96356,52.80712l96.15644,199.58044l221.29778,32.91022c34.87289,5.16267 63.616,28.8 74.97956,61.696c11.392,33.28 2.94399,69.36178 -22.03023,94.25067l-162.75911,162.46044l37.74578,225.28c5.84533,34.77333 -9.38667,70.35734 -38.81244,90.63822c-15.98578,10.90845 -34.61689,16.66845 -53.93067,16.66845zm-237.61067,-175.70133c4.63645,0 9.27289,1.13777 13.48267,3.38489l205.93778,110.86222c12.37333,6.64178 28.31644,5.80266 39.936,-2.13334c11.392,-7.83644 17.12355,-21.00622 14.89066,-34.304l-40.16355,-239.75822c-1.50756,-9.088 1.45066,-18.33244 7.96444,-24.832l173.14133,-172.84267c9.45778,-9.42933 12.68623,-23.02577 8.40534,-35.48444c-4.38045,-12.672 -15.70133,-21.87378 -29.52534,-23.92178l-236.21688,-35.12889c-9.31556,-1.37955 -17.36534,-7.296 -21.44711,-15.78666l-102.69867,-213.14845c-6.03022,-12.52978 -19.25689,-20.608 -33.70667,-20.608s-27.69067,8.09245 -33.76355,20.62222l-102.64178,213.13423c-4.08178,8.49066 -12.13156,14.40711 -21.44711,15.78666l-236.23111,35.12889c-13.62489,2.03378 -25.20178,11.43467 -29.53956,23.99289c-4.26667,12.416 -1.024,25.984 8.43378,35.39911l173.11289,172.85689c6.51378,6.49956 9.472,15.744 7.96444,24.832l-40.13511,239.744c-2.24711,13.312 3.48444,26.43911 14.976,34.36089c11.43467,7.83644 27.34933,8.77511 39.95022,2.06222l205.85245,-110.83378c4.19555,-2.24711 8.832,-3.38488 13.46844,-3.38488zm-311.69422,-347.66223c-6.84089,0 -12.88533,-4.96355 -14.02311,-11.94666c-1.25156,-7.75111 4.01067,-15.06134 11.76178,-16.31289l9.44355,-1.536c7.79378,-1.33689 15.06134,4.01066 16.31289,11.76178c1.25156,7.75111 -4.01067,15.06133 -11.76178,16.31288l-9.44355,1.536c-0.75378,0.128 -1.536,0.18489 -2.28978,0.18489zm52.224,-8.47644c-6.68444,0 -12.64356,-4.72178 -13.93778,-11.53422c-1.49333,-7.70845 3.55556,-15.17511 11.264,-16.65423l153.92711,-29.696l58.05511,-133.14844c3.15734,-7.18222 11.54845,-10.496 18.71645,-7.35289c7.21067,3.14311 10.51022,11.52 7.36711,18.71645l-61.056,140.01777c-1.87733,4.29512 -5.74578,7.39556 -10.35378,8.27734l-161.28,31.11822c-0.91022,0.18489 -1.80622,0.256 -2.70222,0.256z" id="svg_1" fill="#e5b814"></path>
            </svg>
            </div>-->
          </div>
          <div class="foodImgBlock">
            <img class="foodImg" :src="v.pic===null?'/static/img/zfood/logo-gray-square.jpg':v.pic">
          </div>
          <p class="foodDetails">{{v.name}}</p>
          <p style="color: salmon;text-align: center;margin: 0;font-weight: 500;font-size: 0.7em;">{{v.price}}￥</p>
          <div style="width: 11em;margin: 0 auto;">
            <a style="cursor: pointer" @click="SubNum(i)"><img class="numButton"  style="float: left;" src="/static/img/zfood/sub-6x-g.png"></a>
            <a style="cursor: pointer" @click="AddNum(i)"><img class="numButton" style="float: right;" src="/static/img/zfood/plus-6x-g.png"></a>
            <div style="text-align: center;color: #000000;font-weight: 500;"><span>{{getFoodNum[i]}}</span></div>
          </div>
        </div>

      </div>

      <div class="tab-pane fade" id="Coupons">
        <div v-for="(v,i) in couponMap" :style="couponStyle" style="height: 135px;width: 510px; margin: 0 auto;margin-bottom:2em;position: relative; ">
          <div style="display: inline-block;color: #b7512e;">
            <span style="font-size: 1.5em;margin-left: 1.6em;">{{v.name}}</span>
            <div style="width: 350px;padding-left: 55px;word-wrap:break-word;font-size: 0.8em;">{{v.detail}}</div>
            <div style="position: absolute;bottom: 0.2em;padding-left: 0.5em;color: white">{{v.starttime}}<span v-if="v.starttime!==null">至</span>{{v.endtime}}</div>
          </div>
          <button
            @click="GetCoupons(i)"
            class="btn"
            style="background-color: rgba(237,119,56,0.9);color: white;float: right;width: 5em;margin-right: 1.5em;margin-top: 3em;"
            :class="v.userId===null?'':'disabled'"
            :disabled="v.userId!==null">{{v.userId===null?'领取':'已领取'}}</button>
        </div>
      </div>

      <div class="tab-pane fade" id="Comments">

        <div class="order" v-for="(v,i) in orderMap" v-if="v.state!==-1">
          <div class="orderHeader" ><div style="padding-left:1.2em;font-size:1.2em;color: white;">{{v.orderTime.split(' ')[0]}}</div></div>
          <div v-for="(oi, item) in v.orderItems">
            <div style="margin-top: 0.5em;">
              <div style="display: inline-block;float: left;color: #f5926b;min-width: 4em;margin-left: 2em;">
                {{oi.foodname}}:
              </div>
              <div style="display: inline-block;">{{oi.unitprice}}￥&times;{{oi.num}}份</div>
            </div>
          </div>

          <div style="position: absolute;bottom:1em;width: 100%;">
            <div style="margin-left: 1.2em;color: #f5926b;" >评分: <div style="display: inline-block;margin-left: -10px;"><rate  :length="5"  v-model="v.score" :disabled="true"></rate></div></div>
            <div style="margin-left: 1.2em;color: #f5926b;">评价:</div>
            <div style="margin: 0 2em;background-color: #f9f4f8;border-radius: 3px;padding-left: 0.3em;padding-top:0.1em;box-shadow: 0  0 4px #fb8848;color: #a9a9a9;">{{v.comment===null?'用户没有评价':v.comment}}</div>
          </div>
        </div>
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
    /*text-align: center;*/
    background-color: white;
    height: 3em;
    padding-top: 0.2em;
  }
  .menu-block {
    display: inline-block;
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
    position: relative;
    width: 13em;
    height: 17em;
    margin-left: 2em;
    margin-top: 1em;
    display: inline-block;
    background-color: white;
    vertical-align: text-top;
    box-shadow: 0  0 4px #fb8848;
    border-radius: 4px;
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
    max-width: 9em;
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
  /*评价*/
  .order{
    padding-bottom: 8em;
    position: relative;
    margin: 0 auto;
    width: 35em;
    margin-bottom: 3em;
    min-height: 12em;
    background-color: white;
    border-radius: 5px;
    box-shadow: 0  0 4px #fb8848;
  }
  .orderHeader{
    height: 2em;
    background-color: #fb8848;
  }
</style>

<script>
  import Rate from '../components/Rate.vue';
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
        shopMsg: [],

        foodMap: [],

        favShop:{isFavShop:false,orgin:false,favShopId:null},
        userId:this.$getLocalStorage('userId'),

        couponMap:[],
        couponStyle:{
          backgroundImage: "url(" + require("../assets/coupon-shop.png") + ")"
        },
        orderMap:{}
      }
    },
    components:{
      Rate
    },
    beforeRouteLeave (to, from, next) {
      //收藏商家
      if(this.favShop.orgin!==this.favShop.isFavShop){
          let url=this.favShop.isFavShop?'zfood/favShop/add':'zfood/favShop/del';
          let favShop=this.favShop.isFavShop?JSON.stringify({'shopId': this.shopId,'userId':this.userId}):JSON.stringify({'favShopId':this.favShop.favShopId});
          this.$http.post(url,favShop,{headers: {"Content-Type": "application/json"}});
      }
      //收藏商品
      let foodsDel=[];
      let foodsAdd=[];
      let orderItem=[];
      this.foodMap.forEach((v,i)=>{
          if(v['orgin']!==undefined&&v['favFoodId']===null){//收藏取消
            foodsDel.push(v['orgin']);
          }
          if(v['orgin']===undefined&&v['favFoodId']==='true'){//收藏添加
              foodsAdd.push({'userId':this.userId,'foodId':v['foodId']});
          }
          if(v['foodNum']>0){//购物车
              let orderitem={};
              orderitem['foodId']=v['foodId'];
              orderitem['foodname']=v['name'];
              orderitem['unitprice']=v['price'];
              orderitem['num']=v['foodNum'];
              orderitem['packprice']=v['packprice'];
              orderItem.push(orderitem);
          }
      });
      if(foodsAdd.length>0) {
        let addData = JSON.stringify(foodsAdd);
        this.$http.post('zfood/favFood/add/list',addData,{headers: {"Content-Type": "application/json"}});
      }
      if(foodsDel.length>0) {
        let delData = JSON.stringify(foodsDel);
        this.$http.post('zfood/favFood/del/list', delData, {headers: {"Content-Type": "application/json"}});
      }
      //领取券单
      let couponAdd=[];
      if(this.couponMap.length>0) {
        this.couponMap.forEach((v, i) => {
          if (v['change']) {
            let couponItem = {};
            couponItem['userId'] = this.userId;
            couponItem['couponid'] = v['couponId'];
            couponItem['starttime'] = v['starttime'];
            couponItem['endtime'] = v['endtime'];
            couponAdd.push(couponItem);
          }
        })
      };
      if(couponAdd.length>0){
          let couponData=JSON.stringify(couponAdd);
          this.$http.post('/zfood/userCouponItem/add/list',couponData, {headers: {"Content-Type": "application/json"}});
      }
      //购物车
      if(orderItem.length>0){
          console.log(orderItem);
          let orderItemData=JSON.stringify({'orderItems':orderItem,'shopId':this.shopId});
          this.$http.post('/zfood/shoppingCart/add',orderItemData,{headers: {"Content-Type": "application/json"}});
      }
      next();
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
            console.log(response.data);
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
          this.foodMap = response.data.foodList;
          this.favShop.favShopId=response.data.favShop;
          if(response.data.favShop!==null)
            this.favShop.orgin=this.favShop.isFavShop=true;
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
      TabChange(e){
          if(e.path["0"].dataset.title==='1'){//优惠券
            let data = JSON.stringify({'shopId': this.shopId});
            this.$http.post('/zfood/coupon/user', data, {headers: {"Content-Type": "application/json"}}).then(
                response=>{
                    this.couponMap=response.data;
                }
            );
          }else if(e.path["0"].dataset.title==='2'){
            let data = JSON.stringify({'shopId': this.shopId});
            this.$http.post('/zfood/order/shop/history',data,{headers: {"Content-Type": "application/json"}}).then(
              response=>{
                this.orderMap=response.data;
              }
            );
          }
      },
      //食物
      SubNum(index){
          this.$set( this.foodMap[index],'foodNum',this.foodMap[index]['foodNum']-1>0?this.foodMap[index]['foodNum']-1:undefined);
        //this.foodMap[index]['foodNum']=this.foodMap[index]['foodNum']===undefined?1:this.foodMap[index]['foodNum']-1;
      },
      AddNum(index){
        if(this.foodMap[index]['foodNum']===undefined)
          this.$set( this.foodMap[index],'foodNum',1);
        else
          this.$set( this.foodMap[index],'foodNum',this.foodMap[index]['foodNum']+1);
        //this.foodMap[index]['foodNum']=this.foodMap[index]['foodNum']===undefined?1:this.foodMap[index]['foodNum']+1;
      },
      Collect(index){
          if(this.foodMap[index]['favFoodId']!=='true'&&this.foodMap[index]['favFoodId']!==null){
            this.foodMap[index]['orgin']=this.foodMap[index]['favFoodId'];
          }
          let type=this.foodMap[index]['favFoodId']===null?'true':null;
          this.$set(this.foodMap[index],'favFoodId',type);
      },
      CollectShop(){
          this.favShop.isFavShop=!this.favShop.isFavShop;
      },
      //优惠券
      GetCoupons(index){
          this.couponMap[index]['userId']=this.$getLocalStorage('userId');
          this.couponMap[index]['change']=true;
      }
    }
  }
</script>
