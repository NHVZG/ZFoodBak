package com.nhvzg.dao;

import com.nhvzg.entity.Order;
import com.nhvzg.entity.OrderItem;
import com.nhvzg.result.OrderMessage;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> getOrderByUser(String userId);

    List<OrderMessage>getOrderMsgByUser(String userId);

    List<OrderMessage>getShopOrderCurrent(String shopId);

    List<OrderMessage>getShopOrderHistory(String shopId);

    List<OrderItem>selectOrderItem(String order_id);


}