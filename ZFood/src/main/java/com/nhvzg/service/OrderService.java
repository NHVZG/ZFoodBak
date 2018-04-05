package com.nhvzg.service;

import com.nhvzg.dao.OrderMapper;
import com.nhvzg.entity.Order;
import com.nhvzg.result.OrderMessage;
import com.nhvzg.tools.UUIDTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by NHVZG on 2018/2/27.
 */
@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    public List<Order> getOrderByUser(Order order){
        return orderMapper.getOrderByUser(order.getUserId());
    }

    public void addOrder(Order order){
        order.setOrderId(UUIDTools.getPrimaryKey());
        orderMapper.insert(order);
    }

    public void editOrderCourier(Order order){
        Order o=orderMapper.selectByPrimaryKey(order.getOrderId());
        o.setCourierId(order.getCourierId());
        orderMapper.updateByPrimaryKey(o);
    }

    public void ediOrderState(Order order){
        Order o=orderMapper.selectByPrimaryKey(order.getOrderId());
        o.setState(order.getState());
        orderMapper.updateByPrimaryKey(o);
    }

    public List<OrderMessage> getOrderMessageByUser(Order order){
        List<OrderMessage>list= orderMapper.getOrderMsgByUser(order.getUserId());
        return list;
    }

    public List<OrderMessage> getOrderCurrentByShop(Order order){
        List<OrderMessage> list=orderMapper.getShopOrderCurrent(order.getShopId());
        return list;
    }

    public List<OrderMessage> getOrderHistoryByShop(Order order){
        List<OrderMessage> list =orderMapper.getShopOrderHistory(order.getShopId());
        return list;
    }
}
