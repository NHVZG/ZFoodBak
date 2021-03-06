package com.nhvzg.controller.order;

import com.nhvzg.entity.Order;
import com.nhvzg.entity.OrderItem;
import com.nhvzg.result.OrderMessage;
import com.nhvzg.result.UserCouponMsg;
import com.nhvzg.service.CouponItemService;
import com.nhvzg.service.OrderService;
import com.nhvzg.tools.JsonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by NHVZG on 2018/2/27.
 */

//Order相关
@RestController
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CouponItemService couponItemService;

    @PostMapping("/order/user")
    //userId
    //根据用户获取订单
    public List<OrderMessage> getOrderByUser(@RequestBody String json,HttpServletRequest request) throws IOException {
        Order order= new Order();//JsonTools.GetObject(json, Order.class);
        order.setUserId((String) request.getAttribute("userId"));
        return orderService.getOrderMessageByUser(order);
    }
    @PostMapping("/order/shop/current")
    public List<OrderMessage>getOrderByShopCurrent(@RequestBody String json) throws IOException {
        Order order=JsonTools.GetObject(json,Order.class);
        List list= orderService.getOrderCurrentByShop(order);
        return list;
    }
    @PostMapping("/order/shop/history")
    public List<OrderMessage>getOrderByShopHistory(@RequestBody String json) throws IOException {
        Order order=JsonTools.GetObject(json,Order.class);
        List list=orderService.getOrderHistoryByShop(order);
        return list;
    }

    //购物车
    @PostMapping("/shoppingCart")
    public List<OrderMessage> getShoppingCart(HttpServletRequest request){
        return orderService.getShoppingCart((String) request.getAttribute("userId"));
    }
    @PostMapping("/shoppingCart/add")//商家页面添入购物车
    public void addShoppingCart(@RequestBody String json,HttpServletRequest request) throws IOException {
        OrderMessage data=JsonTools.GetObject(json,OrderMessage.class);
        orderService.addShoppingCartByShop(data.getShopId(),(String)request.getAttribute("userId"),data.getOrderItems());
    }
    @PostMapping("/shoppingCart/edit")
    public void editShoppingCart(@RequestBody String json,HttpServletRequest request) throws IOException {
        OrderItem orderItem=JsonTools.GetObject(json,OrderItem.class);
        orderService.changOrderItemNum(orderItem.getOrderItemId(),orderItem.getNum());
    }
    @PostMapping("/shoppingCart/del")
    public void delShoppingCart(@RequestBody String json,HttpServletRequest request) throws IOException {
        Map map=JsonTools.GetObject(json,Map.class);
        orderService.removeShoppingCartItem((String) map.get("orderItemId"), (String) map.get("orderId"));
    }
    @PostMapping("/shoppingCart/commit")
    public void commitOrder(@RequestBody String json,HttpServletRequest request) throws IOException {
        //List orderItems=JsonTools.GetPartObjectToList(json,OrderMessage.class,"orderItems");
        List couponsItems=JsonTools.GetPartObjectToList(json,UserCouponMsg.class,"couponsItems");
        OrderMessage order=JsonTools.GetPartObjectToObject(json, OrderMessage.class,"order");
        if(couponsItems.size()>0) {
            couponItemService.setCouponItemListState(couponsItems);
        }
        orderService.commitOrder(order);
    }
    @PostMapping("/order/del")
    public void delOrder(@RequestBody String json) throws IOException {//数据库级联删除订单及订单项
        String orderId=JsonTools.GetObject(json,String.class);
        orderService.deleteOrderWithItem(orderId);
    }

    @PostMapping("/order/edit/courier")
    @Scope("prototype")
    //orderId courierId 更改订单的配送员        配送员接单时 将订单金额加入商家
    public boolean editOrderCourier(@RequestBody String json) throws IOException {
        Order order=JsonTools.GetObject(json,Order.class);
        return orderService.editOrderCourier(order);
    }

    @PostMapping("/order/courier/receive")//可接订单
    public List<OrderMessage>getCourierReceiveOrder(){
        return orderService.getCourierReceiveOrder();
    }

    @PostMapping("/order/courier/current")//已接订单
    public List<OrderMessage>getCourierCurrentOrder(@RequestBody String json) throws IOException {
        String courierId=JsonTools.GetObject(json,String.class);
        return orderService.getCourierCurrentOrders(courierId);
    }

    @PostMapping("/order/courier/history")//历史订单
    public List<OrderMessage>getCourierHistoryOrder(@RequestBody String json) throws IOException {
        String courierId=JsonTools.GetObject(json,String.class);
        return orderService.getCourierHistoryOrder(courierId);
    }

    @PostMapping("/order/complete")//完成订单
    public void completeOrder(@RequestBody String json) throws IOException {
        Order order=JsonTools.GetObject(json,Order.class);
        orderService.editOrderState(order);//更新订单装阿嚏
        orderService.updateAllIncome(order);//更新商家和配送员的收入
        orderService.updateFoodScore(order.getOrderId());
    }

    @PostMapping("/order/edit/state")
    @Scope("prototype")
    //orderId state 更改订单状态
    public void editOrderState(@RequestBody String json) throws IOException {
        Order order=JsonTools.GetObject(json,Order.class);
        orderService.editOrderState(order);
    }


    @PostMapping("/order/edit/comment")
    public void editOrderComment(@RequestBody String json,HttpServletRequest request) throws IOException {
       OrderMessage orderMessage=JsonTools.GetObject(json,OrderMessage.class);
       orderMessage.setUserId((String) request.getAttribute("userId"));
       orderService.saveComment(orderMessage);
    }



    @Deprecated
    @PostMapping("/order/add")
    //userId shopId ordertime receivername phone address remark price state
    public void addOrder(@RequestBody String json) throws IOException {
        Order order=JsonTools.GetObject(json,Order.class);
        orderService.addOrder(order);
    }

}
