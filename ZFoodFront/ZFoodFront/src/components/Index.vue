<template>
    <div style="padding-bottom: 2em;">
        <zfood-header :userState="0"></zfood-header>

      <div class="header">

        <form class="searchForm">
          <div id="formDiv" class="input-group" style="margin-top:0px;positon:relative;">
            <input id="searchBar" class="form-control" type="search" placeholder="Search" aria-label="Search"
                   style="box-shadow:none;"/>
            <span class="input-group-btn">
					<button class="btn btn-info btn-search searchButton"
                  style="background-color:#F68447; border: solid #F68447; width: 80px">查找</button>
				</span>
          </div>
        </form>
      </div>

      <div>
        <div class="navBar">
          <div class="navContent">
            <p style="margin-left:1em;font-size: 1.5rem;">分类</p>
            <ul class="list-ul">
              <li><a class="link" href="">1</a></li>
              <li><a class="link" href="">1</a></li>
              <li><a class="link" href="">1</a></li>
              <li><a class="link" href="">1</a></li>
              <li><a class="link" href="">1</a></li>
            </ul>
          </div>
        </div>

        <div>
          <div class="hotContent1 test-num">1</div>
          <div class="hotContent2 test-num">2</div>
          <div class="hotContent3 test-num">3</div>
        </div>
        <div>
          <div class="hotContent4 test-num">4</div>
          <div class="hotContent5 test-num">5</div>
        </div>
      </div>

    </div>
</template>

<style scoped>
  .header{
    background-color: white;
    height: 5em;
    /*padding-left:400px;*/
  }
  .logo{
    float: left;
    width: 120px;
    margin-top: -1em;
    margin-left: 7em;
  }
  .searchForm{
    width: 550px;
    margin:0 auto;
  }
  .navBar{
    display: inline-block;
    margin-left: 4em;
    height: 18em;
    width: 15em;
    /*position: relative;*/
    float:left;

    background-color: #F68447;
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
  .user-unlogin{
    border-radius:50%;
    background-color: #F2F1F0;
    width: 100px;
    height: 100px;
    margin-right: 5em;
  }
  .user-login{
    border-radius:50%;
    background-color: #F2F1F0;
    width: 80px;
    height: 80px;
    margin-right: 5em;
  }
  .list-ul{
  }
  .test-num{
    text-align: center;
    color: red;
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
  .hotContent2{
    width: 10em;
    height: 15em;
    background-color: white;
    display: inline-block;
  }
  .hotContent3{
    width: 17em;
    height: 15em;
    background-color: white;
    display: inline-block;
    margin-left:1em;
  }
  .hotContent4{
    width: 25em;
    height: 12em;
    background-color: white;
    display: inline-block;
    margin-left:4em;
  }
  .hotContent5{
    width: 23.5em;
    height: 12em;
    background-color: white;
    display: inline-block;
    margin-left:1em;
  }
  .itemContainer{
    display: flex;
    flex-wrap: wrap;
    justify-content:center;
    margin:4em;
    background-color: white;
    border-radius: 0px 0px 5px 5px;
  }
  .itemTitle{
    font-size: 1.5rem;
    padding-left: 20px;
    color: white;
    width: 100%;
    height: 40px;
    border-radius: 5px 5px 0px 0px;
    background-color: #F68447;
  }
  .item{
    margin:20px;
    width: 250px;
    height: 300px;
    border: 2px solid red;
    border-radius: 5px;
  }
  .picItem{
    background-color: yellow;
    height: 240px;
    width: 220px;
    margin-left: 10px;
  }
  .text{
    text-align: center;
    font-size: 20px;
  }

</style>

<script>
  import ZfoodHeader from '../components/Header.vue';
    export default{
        data () {
            return {
                msg: '这个是Home模板页',
            }
        },
        components:{
          ZfoodHeader
        },
      mounted(){
      }
    }
</script>
