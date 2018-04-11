package com.nhvzg.dao;

import com.nhvzg.entity.Order;
import com.nhvzg.entity.OrderItem;
import com.nhvzg.result.OrderMessage;

import java.util.List;
import java.util.Map;

public interface OrderMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> getOrderByUser(String userId);

    //用户及商家获取订单
    List<OrderMessage>getOrderMsgByUser(String userId);

    List<OrderMessage>getShopOrderCurrent(String shopId);

    List<OrderMessage>getShopOrderHistory(String shopId);

    List<OrderItem>selectOrderItem(String order_id);

    //购物车
    List<OrderMessage> getShoppingCart(String userId);

    List<OrderMessage> getShoppingCartByShop(Map map);

    void updateOrderItemNum(List list);

    void addOrderItem(List list);

    void updateOrderPrice(String orderId);

    void commitOrderState(OrderMessage order);
}