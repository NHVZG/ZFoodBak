package com.nhvzg.controller.order;

import com.nhvzg.entity.Order;
import com.nhvzg.result.OrderMessage;
import com.nhvzg.service.OrderService;
import com.nhvzg.tools.JsonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by NHVZG on 2018/2/27.
 */

//Order相关
@RestController
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

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



    @PostMapping("/order/add")
    //userId shopId ordertime receivername phone address remark price state
    public void addOrder(@RequestBody String json) throws IOException {
        Order order=JsonTools.GetObject(json,Order.class);
        orderService.addOrder(order);
    }

    @PostMapping("/order/edit/courier")
    //orderId courierId 更改订单的配送员
    public void editOrderCourier(@RequestBody String json) throws IOException {
        Order order=JsonTools.GetObject(json,Order.class);
        orderService.editOrderCourier(order);
    }
    @PostMapping("/order/edit/state")
    //orderId state 更改订单状态
    public void editOrderState(@RequestBody String json) throws IOException {
        Order order=JsonTools.GetObject(json,Order.class);
        orderService.ediOrderState(order);
    }
}
