<template>
    <div style="padding-bottom: 2em;min-width: 1200px;" id="Top">
        <zfood-header :userState="0"></zfood-header>

      <!--搜索-->
      <div class="header" >
        <div class="searchForm">
          <div id="formDiv" class="input-group" style="margin-top:0;position:relative;">
            <input id="searchBar" class="form-control" type="search" placeholder="Search" aria-label="Search"
                   style="box-shadow:none;" v-model="findText" @keyup.13="Finding"/>
            <span class="input-group-btn">
					<button
                  @click="Finding"
                  class="btn btn-info btn-search searchButton"
                  style="background-color:#F68447; border: solid #F68447; width: 80px">查找</button>
				</span>
          </div>
        </div>
      </div>

      <!--首页热门模块-->
      <div>

        <div class="navBar">
          <div class="navContent">
            <p style="margin-left:1em;font-size: 1.5rem;">分类</p>
            <scroll-bar>
            <ul style="padding: 0">
              <li v-for="(v,i) in kindList" style="list-style-type:none;" class="kind-li"><a :href="'#k'+i" style="color:white;text-decoration:none;padding-left: 2em;" class="scrollactive-item">{{v.kind}}<span style="float: right;margin-right: 1em;"><img style="width: 10px;" src="/static/img/zfood/chevron-right-8x-w.png"/></span></a></li>
            </ul>
            </scroll-bar>
          </div>
        </div>


        <div style="margin-bottom: 2em;">
          <!--轮播-->
          <div id="carouselExampleControls" class="carousel slide carouselBlock" data-ride="carousel">
            <div class="carousel-inner" >
              <div class="carousel-item active">
                <a @click="ToFood(randomFoodList[0])">
                <div style="position: relative;">
                  <div  class="carouselText"  style="top: 0;" v-if="randomFoodList[0]">{{randomFoodList[0].shopName}}</div>
                <div class=" carouselCover">
                  <div class="carouselImgBlock">
                    <img class="d-block  carousel-img " v-if="randomFoodList[0]"
                         :src="randomFoodList[0].pic===null?'/static/img/zfood/logo-gray-square.jpg':randomFoodList[0].pic"
                         alt="First slide">
                  </div>
                </div>
                <div class="carouselText"  style="bottom: 0;"  v-if="randomFoodList[0]">{{randomFoodList[0].name}}<span style="float: right;margin-right: 1em;">{{randomFoodList[0].price+'￥'}}</span></div>
                </div>
                </a>
              </div>
              <div class="carousel-item">
                <div style="position: relative;">
                  <a @click="ToFood(randomFoodList[1])">
                  <div  class="carouselText"  style="top: 0;" v-if="randomFoodList[1]">{{randomFoodList[1].shopName}}</div>
                <div class=" carouselCover">
                  <div class="carouselImgBlock">
                    <img class="d-block  carousel-img"  v-if="randomFoodList[1]"
                         :src="randomFoodList[1].pic===null?'/static/img/zfood/logo-gray-square.jpg':randomFoodList[1].pic"
                         alt="Second slide">
                  </div>
                </div>
                <div class="carouselText"   style="bottom: 0;" v-if="randomFoodList[1]">{{randomFoodList[1].name}}<span style="float: right;margin-right: 1em;">{{randomFoodList[1].price+'￥'}}</span></div>
                  </a>
                </div>
              </div>
              <div class="carousel-item">
                <div style="position: relative;">
                  <a @click="ToFood(randomFoodList[2])">
                  <div  class="carouselText"  style="top: 0;" v-if="randomFoodList[2]">{{randomFoodList[2].shopName}}</div>
                <div class=" carouselCover">
                  <div class="carouselImgBlock">
                    <img class="d-block carousel-img"  v-if="randomFoodList[2]"
                         :src="randomFoodList[2].pic===null?'/static/img/zfood/logo-gray-square.jpg':randomFoodList[2].pic"
                         alt="Third slide">
                  </div>
                </div>
                <div class="carouselText"   style="bottom: 0;" v-if="randomFoodList[2]">{{randomFoodList[2].name}}<span style="float: right;margin-right: 1em;">{{randomFoodList[2].price+'￥'}}</span></div>
                  </a>
                </div>
              </div>
            </div>
            <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
              <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
              <span class="carousel-control-next-icon" aria-hidden="true"></span>
              <span class="sr-only">Next</span>
            </a>
          </div>

          <div v-for="(v,i) in hotStaticList"  :class="v.styleClass" :style="'width:'+v.style" v-on:mouseenter="MouseHoverChange(i)" v-on:mouseleave="MouseHoverChange(i)">
              <a @click='ToFood(v)'>
            <img v-if="v" :src="v.pic" >
            <div class="imgFrame"><div class="imgFrameBorder"></div></div>

            <transition name="fade">
              <div v-if="v.show"><!--showList[i]-->
                <div class="blockDetails" >菜名:{{v.name}}<br>价格:{{v.price}}￥<br>店名:{{v.shopName}}</div>
              </div>
            </transition>
            </a>
          </div>

          </div>
      </div>

      <!--导航-->
    <scroll-bar>
      <div class="scrollBarBlock">
        <div v-for="(k,i) in kindList"><a :href="'#k'+i"  class="scrollactive-item">{{k.kind}}</a><div class="dropdown-divider" style="margin: 0"></div></div>
        <div><a href="#Top" style="background-color: #F68447;color: white;border-radius: 5px;width: 50px;margin: 0 auto;display: block;">Top</a></div>
      </div>
    </scroll-bar>

      <div v-for="(c,i) in kindList" :id="'k'+i" class="kindImgBlock">
        <div style="background-color:#F68447;color: white;font-size: 1.5em;padding-left: 1.5em;border-radius: 6px 6px 0 0;">{{c.kind}}<span style="float: right;font-size: 0.5em;margin-top: 0.5em;margin-right: 1em;"><a @click="FindingKind(c.kind)" style="cursor: pointer;">查看更多</a></span></div><!--指定宽度才能生效margin inlineBlock指定无效-->
        <div>
          <div style="width: 65em;margin: 0 auto;">
          <div class="foodBlock" v-for="(f,ii) in c.foodList">
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
          </div>
          </div>
        </div>

      </div>

    </div>
</template>

<style scoped>
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
  .KindImgBox{
    display: inline-block;
  }
  .kindImgBlock{
    border-radius: 6px 6px 0 0;
    height: 21em;
    width: 82%;
    margin: 0 auto 2em auto;
    background-color: white;
    box-shadow: 0 0 3px #F68447;
  }
  .fade-enter-active, .fade-leave-active {
    transition: opacity .5s;
  }
  .fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
    opacity: 0;
  }
  .header{
    background-color: white;
    height: 5em;
    /*padding-left:400px;*/
  }
  .searchForm{
    width: 550px;
    margin:0 auto;
  }
  .navBar{
    display: inline-block;
    margin-left: 4em;
    height: 25em;
    width: 15em;
    /*position: relative;*/
    float:left;

    background-color: #F68447;
  }
  .kind-li:hover{
    background-color: rgba(255, 255, 255,0.2);
  }
  .scrollBarBlock{
    font-size: 1.2rem;
    position: fixed;
    width: 5rem;
    right: 2rem;
    bottom: 4rem;
    border-radius: 5px;
    overflow: hidden;
    text-align: center;
  }
  .navContent{
    margin-top:-3em;
    height: 27em;
    width: 15em;
    background-color: #F68447;
    color: white;
  }
  .link{
    color: white;
  }
  #searchBar{
    width: 500px;
  }
  .tooltip-inner{
    max-width: 200px;
    padding: 3px 8px;
    color: #fff;
    text-align: center;
    background-color: #F68447;
    border-radius: 4px;
  }
  .tooltip.bs-tooltip-bottom .arrow::before, .tooltip.bs-tooltip-auto[x-placement^="bottom"] .arrow::before {
    margin-left: -3px;
    content: "";
    border-width: 0 5px 5px;
    border-bottom-color: #F68447;
  }
  .hotContent1{
    width: 20em;
    height: 15em;
    background-color: white;
    display: inline-block;
    margin:1em;
    margin-left: 4em;
  }
  .carouselBlock{
    width: 500px;
    display: inline-block;
    box-shadow: 0 0 5px #e1e1e1;
    margin:1.5em ;
  }
  .scrollactive-item{
    width: 100%;
    color: #c2c2c2;
    display: block;
    /*background-color: white;*/
  }
  .active-item{
    color: #F68447;
  }
  .carousel-img{
    width: 500px;
    margin: 0 auto;
  }
  .carouselImgBlock{
    height: 100%;
    display: flex;
    flex-direction: row;
    justify-content:center;
    align-items: center;
  }
  .carouselCover{
    height: 300px;
    overflow: hidden;
  }
  .carouselText{
    background-color: black;
    opacity:0.5;
    width: 100%;
    position: absolute;
    font-size: 1.2em;
    color: white;
    padding-left: 1em;

  }
  .blockDetails{
    text-align: center;
    opacity:0.5;
    color:white;
    font-size: 1.2em;
    background-color: black;
    width: 100%;
    height: 100%;
    position: absolute;
    bottom: 0;
    display: flex;
    flex-direction: row;
    justify-content:center;
    align-items: center;
  }
  .imgFrame{
    width: 100%;height: 100%;top:0;position: absolute;
    display: flex;
    flex-direction: row;
    justify-content:center;
    align-items: center;
  }
  .imgFrameBorder{
    top:0;
    margin:0.5em 0.5em;
    height: 95%;
    width: 100%;
    border: 1px solid white;
  }
  .blockTwo{
    box-shadow: 0 0 6px #F68447;
    overflow: hidden;
    margin-top: 1.5em;
    vertical-align: top;
    display: inline-block;
    background-color: white;
    width: 300px;
    height: 300px;
    position: relative;
  }

  .blockLine2{
    box-shadow: 0 0 6px #F68447;
    position: relative;
    overflow: hidden;
    margin-left: 1.4em;
    vertical-align: top;
    display: inline-block;
    background-color: white;
    height: 210px;
  }
</style>

<script>
  import ZfoodHeader from '../components/Header.vue';
  import ScrollBar from '../components/ScrollBar.vue';
    export default{
        data() {
            return {
                msg: '这个是Home模板页',
                active:'active',
                findText:'',
                kindList:[],
                randomFoodList:[],
                hotStaticList:[],
                hotStaticStyle:['','','','','220px','330px','230px'],
                showList:[false,false,false,false],
                hotBlock:[],
            }
        },
      components:{
        ScrollBar,ZfoodHeader
      },
      methods:{
        Finding(e){
          this.$router.push({
            name: 'Search',
            params: {
                searchText:this.findText
            }
          });
        },
        FindingKind(kind){
          this.$router.push(
            {
              name: 'Search',
              params:{
                kind:kind
              }
            });
        },
        ToFood(food){
          this.$router.push(
            {
              name: 'Food',
              params:{
                foodId: food['foodId']
              }
            /*  params: {
                foodId: food['foodId'],
                foodName: food['foodName'],
                foodImg:food['pic'],
                price:food['price'],
                score:food['score'],
                shopId:food['shopId'],
                shopName:food['shopName'],
                packPrice:food['packprice']
              }*/
            });
        },
        MouseHoverChange(index){
          //this.$set(this.showList,index,!this.showList[index]);
          let v=this.hotStaticList[index];
          v.show=!v.show;
          this.$set(this.hotStaticList,index,v);//必须以这种方法生效
        }
      },
      created(){
        this.$http.get('/zfood/indexData',{headers: {"Content-Type": "application/json"}}).then(
          response=>{
            this.kindList=response.data.kindFoods;
            this.randomFoodList=response.data.RandomFoods;
            let _this=this;
            this.randomFoodList.forEach(function(v,i){
                if(i>2) {
                    let item=v;
                    item.show=false;
                    item.style=_this.hotStaticStyle[i];
                    item.styleClass=i>3?'blockLine2':'blockTwo';
                    _this.hotStaticList.push(item);
                }
            });
          }
        );
      },
      mounted(){

      }
    }
</script>
