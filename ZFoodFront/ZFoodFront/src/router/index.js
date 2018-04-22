import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/Login'
import UserManage from '@/components/UserManage'
import ShopManage from '@/components/ShopManage'
import FoodTable from '@/components/FoodTable'
import CouponTable from '@/components/CouponTable'
import ShopIndex from '@/components/ShopIndex'
import PayOff from '@/components/PayOff'
import Header from '@/components/Header'
import CourierManage from '@/components/CourierManage'
import Index from '@/components/Index'
import ScrollBar from '@/components/ScrollBar'
import Food from '@/components/Food'
import Test from '@/components/test'
import OrderItem from '@/components/test/OrderItem'

Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path:'/login',
      name:'Login',
      component:Login
    },
    {
      path:'/shopManage',
      name:'ShopManage',
      component:ShopManage
    },
    {
      path:'/FoodTable',
      name:'FoodTable',
      component:FoodTable
    },
    {
      path:'/UserManage',
      name:'UserManage',
      component:UserManage
    },
    {
      path:'/CouponTable',
      name:'CouponTable',
      component:CouponTable
    },
    {
      path:'/Test',
      name:'Test',
      component:Test
    },
    {
      path:'/ShopIndex/:shopId',
      name:'ShopIndex',
      component:ShopIndex,
      /*children:[
        {path:'/ShopIndex/:shopId',component:ShopIndex}
      ]*/
    },
    {
      path:'/Index',
      name:'Index',
      component:Index
    },
    {
      path:'/orderitem',
      name:'OrderItem',
      component:OrderItem
    },
    {
      path:'/PayOff',
      name:'PayOff',
      component:PayOff
    },
    {
      path:'/Header',
      name:'Header',
      component:Header
    },
    {
      path:'/CourierManage',
      name:'CourierManage',
      component:CourierManage
    },
    {
      path:'/ScrollBar',
      name:'ScrollBar',
      component:ScrollBar
    },
    {
      path:'/Food/:foodId',
      name:'Food',
      component:Food
    }
  ]
})
