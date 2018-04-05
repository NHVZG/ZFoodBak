<template>
    <div class="tableDiv">
      <table id="foodtable" class="table-bordered table table-striped" style="table-layout: fixed;">
        <thead>
        <tr>
          <th style=" width: 5%;"><input id="checkAll" type="checkbox" name="" data-check></th>
          <th style=" width: 10%;" class="text-center th-font">图片</th>
          <th class="text-center th-font">菜名</th>
          <th class="text-center th-font">标签</th>
          <th class="text-center th-font">单价</th>
          <th class="text-center th-font">包装费</th>
          <th class="text-center th-font">运送费</th>
        </tr>
        </thead>

         <!--
         <tr><td><input type="checkbox" name="checkB"></td><td><img style="cursor:pointer;" class="smallpic" src="static/img/zfood/logo.jpg"></td><td><input style="width: 100%; box-sizing: border-box; border:0px;background-color:transparent; " type="text" value="22"/></td><td></td><td></td><td></td><td></td></tr>
          <tr><td><input type="checkbox" name="checkB"></td><td><input id="uploadFilesh" name="uFile" type="file" style="display: none" data-user="NHVZG" @change="FileChange"/>
            <label for="uploadFilesh"><img id="uploadFileshimg" style="cursor:pointer;" class="smallpic" src="static/img/zfood/logo.jpg"></label></td><td></td><td></td><td></td><td></td><td></td></tr>
          <tr><td><input type="checkbox" name="checkB"></td><td><img id="uploadFileimg1" class="smallpic" src="static/img/zfood/logo.jpg"></td><td>3</td><td>3</td><td>3</td><td>3</td><td>3</td></tr>
          <tr><td><input type="checkbox" name="checkB"></td><td><img style="cursor:pointer;" class="smallpic" src="static/img/zfood/logo.jpg"></td><td></td><td></td><td></td><td></td><td></td></tr>
          <tr><td><input type="checkbox" name="checkB"></td><td><img style="cursor:pointer;" class="smallpic" src="static/img/zfood/logo.jpg"></td><td></td><td></td><td></td><td></td><td></td></tr>
          <tr><td><input type="checkbox" name="checkB"></td><td><img style="cursor:pointer;" class="smallpic" src="static/img/zfood/logo.jpg"></td><td></td><td></td><td></td><td></td><td></td></tr>
          -->
        <!--菜单表-->
          <tr v-for="(v,i) in foodMap[active]" :data-foodid="v.foodId" :data-index="i">
            <td><input type="checkbox" name="checkB" data-check></td>
            <td style="text-align: center;"><input :id="[v.foodId]" type="file" style="display: none;" @change="FoodsImgChange" ref="imgRef"/><label :for="[v.foodId]"><img :id="[v.foodId]+'img'" style="cursor:pointer;" class="smallpic" :src="[v.pic===null?'/static/img/zfood/logo-b.jpg':v.pic]"></label></td>
            <!--<td ><input class="textCell" type="text" :value="v.name" @change="Edit" name="foodName"/></td>
            <td ><input class="textCell" type="text" :value="v.kind" @change="Edit" name="foodKind"/></td>
            <td ><input class="textCell" type="text" :value="v.price" @change="Edit" name="foodPrice"/></td>
            <td ><input class="textCell" type="text" :value="v.packprice" @change="Edit" name="packPrice"/></td>
            <td ><input class="textCell" type="text" :value="v.sendprice" @change="Edit" name="sendPrice"/></td>-->
            <td ><input class="textCell" type="text"  @change="Edit" name="foodName" v-model="foodMap[active][i]['name']"/></td>
            <td ><input class="textCell" type="text"  @change="Edit" name="foodKind" v-model="foodMap[active][i]['kind']"/></td>
            <td ><input class="textCell" type="text" @change="Edit" name="foodPrice" v-model="foodMap[active][i]['price']"/></td>
            <td ><input class="textCell" type="text" @change="Edit" name="packPrice" v-model="foodMap[active][i]['packprice']"/></td>
            <td ><input class="textCell" type="text"  @change="Edit" name="sendPrice" v-model="foodMap[active][i]['sendprice']"/></td>
        </tr>
      </table>
      <div style="left:20px; bottom:20px;">
    <!--分页控件-->
      <nav style="display:inline-block;" aria-label="Page navigation example">
        <ul class="pagination">
          <li class="page-item"  @click="ChangePage"><a class="page-link" href="#">Previous</a></li>
          <li v-if="pageBar[0]>1" class="page-item"><a class="page-link">...</a></li>
          <li  v-for="(v,i) in pageBar" :id="[i]+'-pageItem'" class="page-item" v-bind:class="[v ===active?'active':'']" @click="ChangePage" style="cursor: pointer;"><!--v-if="v<7"-->
            <a  class="page-link">{{v}}</a>
            <!--<a v-if="v==6" a class="page-link" >...</a>-->
          </li>
          <li v-if="pageBar[6]<counts" class="page-item"><a class="page-link">...</a></li>
          <li class="page-item" @click="ChangePage"><a class="page-link" href="#">Next</a></li>
        </ul>
      </nav>

        <div style="right:20px; bottom:0;display: inline-block;position: absolute;">
          <button style="line-height:1.25em;margin-bottom: 1rem;" class="btn btn-primary" @click="SaveFood">保存</button>
          <button style="line-height:1.25em;margin-bottom: 1rem;" class="btn btn-primary" data-toggle="modal" data-target="#addFood">增加</button>
          <button style="line-height:1.25em;margin-bottom: 1rem;" class="btn btn-primary" @click="DelFood">删除</button>
        </div>
        <div class="modal fade" id="addFood" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content" style="width: 650px;">
              <div class="modal-header"><span style="font-size: 2em;color: #007bff;">添加菜品</span><button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button></div>
              <div class="modal-body">
                <!--菜名 :<input type="text" class="form-control" style="display: inline-block;width:300px;"/><br>
                标签 :<input type="text" class="form-control" style="display: inline-block;width:300px;"/><br>
                单价 : <input type="text" class="form-control" style="display: inline-block;width:300px;"/><br>
                包装费	:<input type="text" class="form-control" style="display: inline-block;width:300px;"/><br>
                运送费	:<input type="text" class="form-control" style="display: inline-block;width:300px;"/>-->
                <div class="textContent">
                  <div class="lineText">菜名:</div><input class="form-control editText" type="text" v-model="newFood.name" ><br>
                  <div class="lineText">标签 :</div><input class="form-control editText" type="text" v-model="newFood.kind"><br>
                  <div class="lineText">单价: </div><input class="form-control editText" type="text" v-model="newFood.price"><br>
                  <div class="lineText">包装费	:</div><input class="form-control editText" type="text" v-model="newFood.packprice"><br>
                  <div class="lineText">运送费	:</div><input class="form-control editText" type="text" v-model="newFood.sendprice"><br>
                </div>
                <div style="float: right;">
                    <p style="text-align: center;color: #007bff;">点击添加图片</p>
                  <input id="emptyfood" type="file" style="display: none;" ref="newPic" @change="FoodsImgChange"/><label for="emptyfood"><img id="emptyfoodimg" style="cursor:pointer;width: 200px;max-height: 320px;" src="/static/img/zfood/logo-b.jpg"></label>
                  <div style="text-align: center;"><input @click="AddFood" class="btn-primary btn subButton" style="width: 8em;margin-top: 2em;"  type="submit" value="保存"></div>
                </div>
              </div>

            </div>
          </div>
        </div>
      </div>
      <!--<div class="table-footer">
        <nav style="display:inline-block;" aria-label="Page navigation example">
          <ul class="pagination">
            <li class="page-item"><a class="page-link" href="#">Previous</a></li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item"><a class="page-link" href="#">Next</a></li>
          </ul>
        </nav>
      </div>

      <div class="button-block">
        <button class="btn btn-primary" @click="add">增加</button>
        <button class="btn btn-primary" @click="del">删除</button>
      </div>-->
    </div>
</template>

<style scoped>
  .tableDiv{
    margin:0 auto;
    width: 95%;
    height: 100%;
    position: relative;
    /*box-shadow: 0 0 5px #ccc;*/
    /*background-color: rgb(240,240,240);*/
  }
  .th-font{
     font-weight:normal;
   }
  .smallpic{
    height: 35px;
  }
  .textCell{
    width: 100%;
    box-sizing: border-box;
    border:0px;
    .background-color:transparent;
  }
  td{
    align-items:center;
  }
  .table-header{
    padding-left: 20px;
    color: white;
    width: 100%;
    height: 40px;
    border-radius: 5px 5px 0px 0px;
    background-color: #F68447;
  }
  .table-footer{
    position: absolute;
    bottom: 0;
  }
  .button-block{
    position: absolute;
    display: inline-block;
    right: 0;
    bottom: 1rem;
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
  .textContent{
    display: inline-block;
    margin-bottom: 2em;
  }
</style>

<script>
    export default{
        data () {
            return {
                foodMap:{},
                newFood:{
                  shopId: this.$getLocalStorage('shopId'),
                  name:'',
                  price:'',
                  sendprice:'',
                  packprice:'',
                  kind:'',
                  //pic:''
                },
                change:false,
                counts:0,
                active:1,
                lastactive:0,
                pageBar:[]
            }
        },
      computed:{
      },
      methods:{
        pageBarChange:function () {
            if(this.counts>6){
                let j=this.active-this.lastactive;
                for(let i=0;i<=6;i++){
                    if(this.pageBar[i]+j<=1||this.active===3) {//左侧
                      for(let k=0;k<=6;k++)
                          this.pageBar[k]=k+1;
                      break;
                    }
                    if(this.pageBar[i]+j>6){
                      for(let k=0;k<=6;k++)
                          this.pageBar[6-k]=this.counts-k;
                      break;
                    }
                    this.pageBar[i]=this.pageBar[i]+j;
                };
            }
        },
        ChangePage(target){
            if(this.change){
              event.returnValue = confirm("还未保存当前页面数据 确定放弃吗");
              if(!event.returnValue)
                  return;
            }
            this.change=false;
            this.lastactive=this.active;
            if(target.path[0].innerText==='Next')
                this.active=this.active+1;
            else if(target.path[0].innerText==='Previous')
              this.active=this.active-1;
            else
              this.active=parseInt(target.path[0].innerText);
            if(this.active>this.counts)this.active=this.counts;
            if(this.active<1)this.active=1;
            this.pageBarChange();
            $("input[data-check]").prop("checked", false);//勾选复位
            if(this.foodMap[this.active]===undefined||this.lastactive===this.counts){//this.lastactive===this.counts为了避免最后一次加载是最后一页数据 导致popover只绑定最后一页数据的数量 (<6)
              this.GetCurrentPageData();
            }
        },
        GetCurrentPageData(){
          var data = JSON.stringify({'shopId': this.$getLocalStorage('shopId'), "page":  this.active});
          this.$http.post('/zfood/food/shop', data, {headers: {"Content-Type": "application/json"}}).then(
            response=>{
              this.counts = response.data['count'];
              this.$set(this.foodMap,this.active,response.data['foodList']);
              this.preload(response.data['foodList']);    //加载完成 popover绑定
            }
          );
        },
        Edit(){
          this.change=true;
        },
        AddFood(){
          let img=this.$refs.newPic.files[0];
          let data = JSON.stringify(this.newFood);
          let formData = new FormData();
          formData.append('file',img);
          formData.append('msg',data);
          this.$http.post('/zfood/food/add',formData).then(
              reponse=>{
                  alert('添加成功');
                  this.foodMap={};//更新数据
                  this.active=1;
                  this.GetCurrentPageData();
              }
          );
          /*var table = document.getElementById("foodtable");
          var rows = table.rows.length;
          if (rows > 6)
            alert('请先提交后再增加');
          else
            $("#foodtable").append("<tr><td>2</td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");*/
        },
        DelFood(){
          let check = $("input[name='checkB']:checked");
          let foodList=[];
          check.each(function () {
            foodList.push($(this).parent("td").parent("tr").attr("data-foodid"));
            /* var row = $(this).parent("td").parent("tr");
             row.remove();*/
          });
          this.$http.post('/zfood/food/del',JSON.stringify(foodList), {headers: {"Content-Type": "application/json"}}).then(
            reponse=>{
                alert('删除成功');
                this.foodMap={};//更新数据
                this.active=1;
                this.GetCurrentPageData();
            }
          );
        },
        SaveFood(){
          let check = $("input[name='checkB']:checked");
          if(check.length===0){
              console.log(check);
              alert("请选择要保存的项");
              return;
          }
          let foodList=[];
          let t=this;
          let formData= new FormData();
          check.each(function () {
            let index=$(this).parent("td").parent("tr").attr("data-index");
            let img=t.$refs.imgRef[index].files[0];
            formData.append('files',img);
            if(img===undefined) {
              t.foodMap[t.active][index]['picChange'] = 'null';
            }else{t.foodMap[t.active][index]['picChange']='';}
            foodList.push(t.foodMap[t.active][index]);
          });
          formData.append('msg',JSON.stringify(foodList));
          this.$http.post('/zfood/food/edit',formData).then(
            response=>{ alert('修改成功');}
          );
          this.change=false;
          $("input[data-check]").prop("checked", false);//勾选复位
        },
        FileChange(file){
          var filelist = file.path[0].files[0];
          var reader = new FileReader();
          reader.onload = function (e) {
            var img = document.getElementById(file.path[0].id + "img");
            img.src = e.target.result;
          };
          reader.readAsDataURL(filelist);
        },
        preload(data){
          data.forEach((v,i)=>{
                var img=new Image();
                img.addEventListener('load',(e)=>{
                    this.$nextTick(()=>{
                       this.$emit('imgLoaded');
                    });
                });
              img.src=v.pic;
            });
        },
        FoodsImgChange(file){
          this.change=true;
          var filelist = file.path[0].files[0];
          var reader = new FileReader();
          reader.onload = function (e) {
            var img = document.getElementById(file.path[0].id+'img');
            img.src = e.target.result;
          };
          reader.readAsDataURL(filelist);
          let index=$(file)["0"].target.parentNode.parentNode.dataset.index;
          this.foodMap[this.active][index]['pic']='/static/img/food/'+this.foodMap[this.active][index]['foodId']+'.jpg';
        }
      },
      mounted(){
        this.$on('imgLoaded',()=>{
          $("#checkAll").click(function(){
            $("input[name='checkB']").prop("checked", this.checked);
          });
          $(".smallpic").popover({
            trigger:"hover ",
            placement:"left",
            html:true,
            content:function(){return '<img style="width:250px;" src="'+$(this)[0].src+'">';}
          })
        });
      },
      created(){
        var data = JSON.stringify({'shopId': this.$getLocalStorage('shopId'), "page": 1});
        this.$http.post('/zfood/food/shop', data, {headers: {"Content-Type": "application/json"}}).then(//初始化数据
          response => {
            if (response.data['errorCode'] === 403) {
              alert('重新登陆!');
              this.$goRoute('Login');
              return;
            }
            else {
              this.counts = response.data['count'];
              for(let i=1;i<=this.counts&&i<=7;i++)
                  this.pageBar.push(i);
              this.active= response.data['active'];
              this.foodMap[this.active]=response.data['foodList'];
              this.preload(response.data['foodList']);
            }
          }
        );
      }
    }
</script>
