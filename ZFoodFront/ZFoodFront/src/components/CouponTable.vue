<template>
  <div>
    <div class="tableDiv">
      <table id="couponTable" class="table-bordered table table-striped" style="table-layout: fixed;">
        <thead>
        <tr>
          <th style=" width: 5%;"><input id="checkAll" type="checkbox" @click="SelectAll" v-model="checkAll"></th>
          <th style=" width: 10%;" class="text-center th-font">名称</th>
          <th class="text-center th-font">详情</th>
          <th class="text-center th-font" style=" width: 30%;">使用区间</th>
          <!--<th class="text-center th-font" style=" width: 15%;">开始时间</th>
          <th class="text-center th-font" style=" width: 15%;">结束时间</th>-->
          <th class="text-center th-font" style=" width: 15%;">优惠值</th>
          <th class="text-center th-font">优惠条件</th>
          <th class="text-center th-font">关联食物</th>
        </tr>
        </thead>
        <tr v-for="(v,i) in couponMap[couponActive]" :data-coupon-id="v.couponId" :data-index="i">
          <td><input type="checkbox" v-model="checkBar" :value="v"></td>
          <td><input class="textCell" type="text" @change="Edit" v-model="couponMap[couponActive][i]['name']" :readonly="isReadOnly"/></td>
          <td><input class="textCell" type="text" @change="Edit" v-model="couponMap[couponActive][i]['detail']" :readonly="isReadOnly"/></td>
          <td class="calend">
            <vue-datepicker-local
                                  :disabled="isReadOnly"
                                  @change="ChangeDate"
                                  :index=i
                                  :noList="true"
                                  :startTime="couponMap[couponActive][i]['starttime']"
                                  :endTime="couponMap[couponActive][i]['endtime']"
                                  >
            </vue-datepicker-local>
          </td>
          <!--<td class="calend"><vue-datepicker-local  @confirm="DateFormat" v-model="couponMap[couponActive][i]['starttime']" clearable></vue-datepicker-local></td> show-buttons <td><input class="textCell" type="text" @change="Edit" v-model="couponMap[couponActive][i]['starttime']"/></td>
          <td class="calend"><vue-datepicker-local  @confirm="DateFormat" v-model="couponMap[couponActive][i]['endtime']" clearable ></vue-datepicker-local></td><td><input class="textCell" type="text" @change="Edit" v-model="couponMap[couponActive][i]['endtime']"/></td>-->
          <td>
            <input class="textCell" type="text" style="width:50%;" @change="Edit" :readonly="isReadOnly"
                   v-model="couponMap[couponActive][i]['discount']"/>
            <div class="dropdown" style="width:20%;display:inline-block;">
              <button class="btn dropdown-toggle" type="button" :disabled="isReadOnly"
                      style="background-color:#f2f2f2;font-size:0.5em;padding:5px;box-shadow:none;"
                      id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                {{couponMap[couponActive][i]['percent'] === 1 ? '%' : '￥'}}
              </button>
              <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <a class="dropdown-item" @click="ChangeDiscountType" :data-index="i" data-value='1'>%</a>
                <a class="dropdown-item" @click="ChangeDiscountType" :data-index="i" data-value='0'>￥</a>
              </div>
            </div>
          </td>
          <td><input class="textCell" type="text" @change="Edit" v-model="couponMap[couponActive ][i]['condition']" :readonly="isReadOnly"/>
          </td>
          <td>
            <button class="btn" :class="couponMap[couponActive][i]['foodName']===null?'dropdown-toggle':''" @click="GetFoodList(i)"
                    :data-foodId="[couponMap[couponActive ][i]['extra']]"
                    style="padding:5px;font-size:0.5em;background-color:#f2f2f2;box-shadow:none;" data-toggle="modal"
                    :disabled="isReadOnly"
                    data-target="#chooseFood">
              {{couponMap[couponActive][i]['foodName']}}
            </button>
          </td>
        </tr>
      </table>
      <div class="modal fade" id="chooseFood" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
           aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content" style="width: 650px;">
            <div class="modal-header"><span style="font-size: 1.2em;color: #007bff;">选择关联菜品</span>
              <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>
            <div class="modal-body">
              <div class="row">
                <div class="col-10" style="margin: 0 auto;">
                  <div class="list-group" id="list-tab" role="tablist">
                    <a v-for="(f,i) in foodMsg['msg']" class="list-group-item list-group-item-action" data-toggle="list"
                       href="#food" @click="ChooseFood(f)" :data-index="i">
                      <div style="display: inline-block;">
                        <div
                          style="width: 150px;justify-content:center; display:flex;flex-direction:row;align-items:center;">
                          <img :src="f.pic===null?'/static/img/zfood/logo-b.jpg':f.pic" style="height: 80px;"/>
                        </div>
                      </div>
                      <div style="display:inline-block;">
                        <!--<p>{{f.name}} {{f.price}} {{f.sendprice}} {{f.packprice}}</p>-->
                        <div style="display: inline-block;font-size: 0.2rem;">
                          <p>食物名:</p>
                          <p>价格:</p>
                          <p>配送费:</p>
                          <p>包装费:</p>
                        </div>
                        <div style="display: inline-block;font-size: 0.2rem;margin-left: 5px;">
                          <p>{{f.name}}</p>
                          <p>{{f.price}}￥</p>
                          <p>{{f.sendprice}}￥</p>
                          <p>{{f.packprice}}￥</p>
                        </div>
                      </div>
                    </a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <nav style="display:inline-block;" aria-label="Page navigation example">
        <ul class="pagination">
          <li class="page-item" @click="ChangePage(couponActive-1)"><a class="page-link" href="#">Previous</a></li>
          <li v-if="pagerBars[0]>1"><a class="page-link">...</a></li>
          <li v-for="(v,i) in pagerBars" class="page-item" v-bind:class="[v ===couponActive?'active':'']"
              @click="ChangePage(pagerBars[i])" style="cursor: pointer;"><a class="page-link">{{v}}</a></li>
          <li v-if="pagerBars[6]<couponCounts"><a class="page-link">...</a></li>
          <li class="page-item" @click="ChangePage(couponActive+1)"><a class="page-link" href="#">Next</a></li>
        </ul>
      </nav>

      <div style="right:20px; bottom:0;display: inline-block;position: absolute;">
        <button style="line-height:1.25em;margin-bottom: 1rem;" class="btn btn-primary" @click="SaveCoupon" v-show="!isReadOnly">保存</button>
        <button style="line-height:1.25em;margin-bottom: 1rem;" class="btn btn-primary" @click="AddCoupon">增加</button>
        <button style="line-height:1.25em;margin-bottom: 1rem;" class="btn btn-primary" @click="DelCoupon">删除</button>
      </div>

    </div>
  </div>
</template>

<style scoped>
  .calend>>>input{

    border:none;
  }
  .calend>>>.datepicker-range{
    min-width: 16em;
  }
  .calend>>> .datepicker-popup{
    width: 420px;
  }
  .tableDiv {
    margin: 0 auto;
    width: 95%;
    height: 100%;
    position: relative;
  }

  .th-font {
    font-weight: normal;
  }

  .textCell {
    width: 100%;
    box-sizing: border-box;
    border: 0px;
    background-color: transparent;
  }

  td {
    align-items: center;
  }

  .editText {
    width: 20em;
    display: inline-block;
  }

  .lineText {
    display: inline-block;
    width: 5em;
    margin-top: 2em;
  }

  .textContent {
    display: inline-block;
    margin-bottom: 2em;
  }
</style>

<script>
  import VueDatepickerLocal from 'vue-datepicker-local'
  export default{
    data () {
      return {
        couponMap: {},//分页数据
        couponCounts: 0,
        couponActive: 1,
        lastActive: 0,
        editChange: false,
        pagerBars: [],//分页栏
        checkBar: [],//选择的数据
        checkAll: false,
        foodMsg: {
          msg: []
        },
        isReadOnly:true,//不可编辑
        newCoupon: {
          "couponId": '',
          "shopId": this.$getLocalStorage('shopId'),
          "name": '',
          "detail": '',
          "starttime": '',
          "endtime": '',
          "discount": 0,
          "percent": 0,
          "usage": 0,
          "condition": '',
          "extra": '',//foodId
          "foodName": ''
        },
        currentIndex: 0,
      }
    },
    components: {
      VueDatepickerLocal
    },
    computed:{

    },
    methods: {
      ChangeDate(startDate,endDate,index){
          this.$set(this.couponMap[this.couponActive][index], 'starttime', startDate);
          this.$set(this.couponMap[this.couponActive][index], 'endtime', endDate);
          /*this.couponMap[this.couponActive][index]['starttime']=startDate;
          this.couponMap[this.couponActive][index]['endtime']=endDate;*/
          //console.log(startDate+' '+endDate+' '+index);
      },
      Edit(){
        this.editChange = true;
      },
      SelectAll(event){
        if (!event.currentTarget.checked) {
          this.checkBar = [];
        } else { //实现全选
          this.checkBar = [];
          let _this = this;
          this.couponMap[this.couponActive].forEach(function (item, i) {
            _this.checkBar.push(item);//对应value
          });
        }
        //$("input[name='checkC']").prop("checked", !this.checkAll);
      },
      ChangeDiscountType(target){
        this.couponMap[this.couponActive][target.path["0"].dataset.index]['percent'] = parseInt(target.path["0"].dataset.value);
      },
      GetFoodList(index){
        this.currentIndex = index;
        var data = JSON.stringify({'shopId': this.$getLocalStorage('shopId')});
        this.$http.post('/zfood/food/coupon', data, {headers: {"Content-Type": "application/json"}}).then(
          response => {
            if (response.data['errorCode'] === 403) {
              alert('重新登陆!');
              this.$goRoute('Login');
              return;
            }
            this.$set(this.foodMsg, 'msg', response.data);
          }
        );
      },
      ChooseFood(data){
        this.couponMap[this.couponActive][this.currentIndex]['extra'] = data['foodId'];
        this.couponMap[this.couponActive][this.currentIndex]['foodName'] = data['name'];
        //console.log(target.currentTarget);
      },
      DelCoupon(){
        if(this.checkBar.length===0){
          alert('请选择所需项目');
          return;
        }
        let data = JSON.stringify(this.checkBar);
        this.$http.post('/zfood/coupon/del/list',data,{headers: {"Content-Type": "application/json"}}).then(
          reponse=>{
            alert('删除成功');
          }
        );
        this.checkAll=false;
        this.checkBar = [];
      },
      SaveCoupon(){
        if(this.checkBar.length===0){
            alert('请选择所需项目');
            return;
        }
        let data = JSON.stringify(this.checkBar);
        let url='/zfood/coupon/add/list';
        //let url=this.couponActive>this.couponCounts?'/zfood/coupon/add/list':'/zfood/coupon/edit/list';     //18-4-11不可再修改优惠券
        if(this.couponActive>this.couponCounts) {
          this.$http.post(url, data, {headers: {"Content-Type": "application/json"}}).then(
            reponse => {
              alert('修改成功');
              this.isReadOnly=true;
            }
          );
          this.checkAll = false;
          this.checkBar = [];
          this.editChange = false;
          this.isReadOnly=true;
          this.couponMap[this.couponCounts + 1] = undefined;//---
        }
      },
      AddCoupon(){
        this.isReadOnly=false;
        this.AddNewRow();
      },
      ChangePage(index){
        this.checkAll = false;
        if (this.couponMap[this.couponCounts + 1] !== undefined) {//新的一页用于添加数据
          event.returnValue = confirm("还未保存当前页面数据 确定放弃吗");
          if (!event.returnValue)
            return;
          this.isReadOnly=true;
          this.checkBar = [];
          this.couponMap[this.couponCounts + 1] = undefined;
        }
        index = index < 1 ? 1 : index;
        index = index > this.couponCounts ? this.couponCounts : index;
        this.lastActive = this.couponActive;
        this.couponActive = index;
        let step = this.couponActive - this.lastActive;
        let size = this.pagerBars.length;
        if (step + this.pagerBars[size - 1] > this.couponCounts) {
          step = this.couponCounts - this.pagerBars[size - 1];//step-(step+this.pagerBars[size-1]-this.couponCounts)
        } else if (step + this.pagerBars[0] < 1) {
          step = 1 - this.pagerBars[0];//step+(1-(this.pagerBars[0]+step);
        }
        let p = step > 0 ? -6 : 0;
        let t = p + 6;
        for (let i = p; i <= t; i++) {
          this.pagerBars[Math.abs(i)] += step;
        }
        if (this.couponMap[this.couponActive] === undefined) {
          this.GetCurrentPageData();
        }
      },
      GetCurrentPageData(){
        var data = JSON.stringify({'shopId': this.$getLocalStorage('shopId'), "page": this.couponActive});
        this.$http.post('/zfood/coupon/shop', data, {headers: {"Content-Type": "application/json"}}).then(
          response => {
            if (response.data['errorCode'] === 403) {
              alert('重新登陆!');
              this.$goRoute('Login');
              return;
            }
            else {
              this.couponCounts = response.data['count'];
              this.$set(this.couponMap, this.couponActive, response.data['couponList']);
            }
          }
        );
      },
      AddNewRow(){//新建不保留之前的勾选
        let newRow = {
          "couponId": '',
          "shopId": "22bsajsdh3u",
          "name": '',
          "detail": '',
          "starttime":null,
          "endtime": null,
          "discount": '',
          "percent": 0,
          "usage": 0,
          "condition": '',
          "extra": '',//foodId
          "foodName": ''
        };
        this.couponActive = this.couponCounts + 1;
        if (this.couponMap[this.couponActive] === undefined) {
          this.couponMap[this.couponActive] = [];
          this.checkBar = [];
        }
        if (this.couponMap[this.couponActive].length < 6) {
          this.couponMap[this.couponCounts + 1].push(newRow);
          this.checkBar.push(newRow);
        } else {
          alert('请先提交本页数据');
        }
      },
    },
   watch:{

   },
   /* watch:{
      couponActive:function (n,l) {
        if (this.couponMap[n] === undefined) {
          var data = JSON.stringify({'shopId': this.$getLocalStorage('shopId'), "page": this.couponActive});
          this.$http.post('/zfood/coupon/shop', data, {headers: {"Content-Type": "application/json"}}).then(
            response => {
              if (response.data['errorCode'] === 403) {
                alert('重新登陆!');
                this.$goRoute('Login');
                return;
              }
              else {
                this.couponCounts = response.data['count'];
                this.$set(this.couponMap, this.couponActive, response.data['couponList']);
              }
            }
          );
        }
      }
    },*/
    mounted(){
    },
    created(){
      var data = JSON.stringify({'shopId': this.$getLocalStorage('shopId'), "page": 1});
      this.$http.post('/zfood/coupon/shop', data, {headers: {"Content-Type": "application/json"}}).then(//初始化数据
        response => {
          if (response.data['errorCode'] === 403) {
            alert('重新登陆!');
            this.$goRoute('Login');
            return;
          }
          else {
            this.couponCounts = response.data['count'];
            for (let i = 1; i <= this.couponCounts && i <= 7; i++)
              this.pagerBars.push(i);
            this.couponActive = response.data['active'];
            this.$set(this.couponMap, this.couponActive, response.data['couponList']);
          }
        }
      );
    }
  }
</script>
