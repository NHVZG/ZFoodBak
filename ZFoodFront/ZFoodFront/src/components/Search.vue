<template>
  <div style="padding-bottom: 2em;min-width: 1200px;" id="Top">
    <zfood-header :userState="0"></zfood-header>

    <!--搜索-->
    <div :class="classType?'header':'header-middle'">
      <div :class="classType?'':'header-content'">
      <div class="searchForm">
        <div v-if="!classType" style="text-align: center"><img src="/static/img/zfood/logo.jpg" class="searchLogo"/></div>
        <div id="formDiv" class="input-group" style="margin-top:0;position:relative;">
          <input id="searchBar" class="form-control" type="search" placeholder="Search" aria-label="Search"
                 style="box-shadow:none;" v-model="searchText" @keyup.13="Finding"/>
          <span class="input-group-btn">
					<button
            @click="Finding"
            class="btn btn-info btn-search searchButton"
            style="background-color:#F68447; border: solid #F68447; width: 80px">查找</button>
				</span>
        </div>
      </div>
      </div>
    </div>

    <transition name="fade" mode="out-in">

        <div v-if="classType" class="searchContent">


          <div style="position: relative;" v-if="foodList.length>0">
            <div style="letter-spacing:-5px;position: absolute;z-index: 999;margin-left: -1em;margin-top: 1em;margin-bottom: 0.5em;">
              <div class="arrow-tail"></div>
              <div class="arrow-middle">食物</div>
              <div class="arrow"></div><!--display: inline-block;background-color: #F68447;color: white;min-width: 4em;-->
            </div>
            <div style="height: 2em;margin-top: 1em;margin-bottom: 0.5em;"></div>
            <div class="foodBlock" v-for="(f,ii) in foodList">
            <a @click='ToFood(f)' style="text-decoration:none;">
              <div>
                <div class="foodImgBlock">
                  <img class="foodImg" :src="f.pic===null?'/static/img/zfood/logo-gray-square.jpg':f.pic">
                </div>
                <p style="font-size: 0.8em;color: gray;text-align: center;margin-bottom: 0">{{f.shopName}}</p>
                <p class="foodDetails">{{f.foodName}}</p>
                <p style="color: salmon;text-align: center;margin: 0;font-weight: 500;font-size: 01.5em;">{{f.price}}￥</p>
              </div>
            </a>
          </div><!--食物列表-->
          </div>


          <div style="background-color: #f2f1f0;height: 1.5em;margin-top: 1.5em;"></div><!--分隔栏-->


          <div style="position: relative;" v-if="shopList.length>0">
            <div style="letter-spacing:-5px;position: absolute;z-index: 999;margin-left: -1em;margin-top: 0;margin-bottom: 0.5em;">
              <div class="arrow-tail"></div>
              <div class="arrow-middle">商店</div>
              <div class="arrow"></div><!--display: inline-block;background-color: #F68447;color: white;min-width: 4em;-->
            </div>
            <div style="height: 2em;margin-top: 1em;margin-bottom: 0.5em;"></div>

              <div v-for="(s,index) in shopList" style="display: inline-block;margin-bottom: 20px;margin-left: 1em;" class="ShopItem">
                <a @click="ToShop(s)">
                  <div style="text-align: center; overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">
                    <div class="imgBox">
                      <div class="imgCover">
                        <img class="orderPic" :src="s.headpic===null?'/static/img/zfood/logo-gray-square.jpg':s.headpic"
                             :class="s.imgClass"/>
                      </div>
                    </div>
                    <div style="color: salmon;font-size: 1.2em;font-weight: 600;word-wrap: break-word; width:150px;">
                      {{s.shopname}}
                    </div>
                  </div>
                </a>
          </div><!--商店列表-->
          </div>


        </div>
    </transition>


  </div>
</template>

<style scoped>
  .arrow-tail{
    vertical-align: top;
    width: 0;
    height: 0;
    background-color: transparent;
    border-top: 1em solid #F68447;
    border-left: 0.5em solid transparent;
    border-bottom: 1em solid #F68447;
    display: inline-block;
  }
  .arrow-middle{
    height: 2em;
    width: 4em;
    background-color: #F68447;
    color: white;
    display: inline-block;
    vertical-align: top;
    letter-spacing:0;
    text-align:center;
    padding-top: 0.2em;
  }
  .arrow{
    width: 0;
    height: 0;
    background-color: transparent;
    border-top: 1em solid transparent;
    border-left: 0.5em solid #F68447;
    border-bottom: 1em solid transparent;
    display: inline-block;
  }

  .ShopItem:hover{
    background-color: #eeeeee;
    border-radius: 5px;
  }
  .fade-enter-active, .fade-leave-active {
    transition: opacity .5s;
  }
  .fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
    opacity: 0;
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

  .searchContent{
    margin: 3em auto;
    width: 80%;
    background-color: white;
  }
  .foodBlock {
    position: relative;
    width: 12em;
    height: 17em;
    margin-left: 1em;
    margin-top: 1em;
    display: inline-block;
    background-color: white;
    vertical-align: text-top;
    /* box-shadow: 0  0 4px #fb8848;*/
    /*border-radius: 4px;*/
  }
  .foodBlock:hover {
    position: relative;
    width: 12em;
    height: 17em;
    margin-left: 1em;
    margin-top: 1em;
    display: inline-block;
    background-color: #eeeeee;
    box-shadow: 0 0 8px #f9f9f9;
    vertical-align: text-top;
    border-radius: 5px;
  }
  .foodImgBlock {
    width: 10em;
    height: 11em;
    margin: 0 auto;
    margin-top: 0.5em;
    border-radius: 3px;
    box-sizing: content-box;
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
    color: black;
    /*color: #F68447;*/
    font-weight: 500;
  }


  .header{
    background-color: white;
    height: 5em;
    /*padding-left:400px;*/
  }
  .header-middle{
    min-height: 20em;
    min-width:35em;
    display: flex;
    flex-direction: row;
    justify-content:center;
    align-items: center;
  }
  .header-content{
    box-shadow: 0 0 4px rgba(255, 86, 34, 0.8);
    border-radius: 5px;
    margin-top: 3em;
    display: flex;
    flex-direction: row;
    justify-content:center;
    align-items: center;
    background-color: white;
    width: 70%;
    height: 25em;
  }
  .searchForm{
    width: 550px;
    margin:0 auto;
  }
  .searchLogo{
    margin-top: -4em;
    height: 12em;
  }
</style>

<script>
  import ZfoodHeader from '../components/Header.vue';
  export default{
        data () {
            return {
              searchText: this.$route.params.searchText,
              kind:this.$route.params.kind,
              foodList:[],
              shopList:[],
              classType:false
            }
        },
        components:{
          ZfoodHeader
        },
    methods:{
      Finding(e){
          if(this.searchText!==null&&this.searchText!=='') {
            this.classType=true;
            this.$http.post('/zfood/search', this.searchText, {headers: {"Content-Type": "application/json"}}).then(
              response => {
                this.foodList = response.data.foodList;
                this.shopList = response.data.shopList;
              }
            );
          }
      },
      ToFood(food){
        this.$router.push(
          {
            name: 'Food',
            params:{
              foodId: food['foodId']
            }
          });
      },
      ToShop(shop){
        this.$router.push(
          {
            name: 'ShopIndex',
            params:{
              shopId: shop['shopId']
            }
          });
      }
    },
    mounted(){
            this.classType=this.kind||this.searchText;
            let queryText=this.kind===undefined?this.searchText:this.kind;
            let url=this.kind===undefined?'/zfood/search':'/zfood/search/kind';
            if(queryText!==''&&queryText!==null) {
              this.$http.post(url, queryText, {headers: {"Content-Type": "application/json"}}).then(
                response => {
                  this.foodList = response.data.foodList;
                  this.shopList = response.data.shopList;
                }
              );
            }
    }
    }
</script>
