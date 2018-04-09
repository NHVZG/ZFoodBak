package com.nhvzg.service;

import com.nhvzg.dao.OrderMapper;
import com.nhvzg.entity.Order;
import com.nhvzg.entity.OrderItem;
import com.nhvzg.result.OrderMessage;
import com.nhvzg.tools.UUIDTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<OrderMessage>getShoppingCart(String userId){
        return orderMapper.getShoppingCart(userId);
    }

    public void addShoppingCartByShop(String shopId,String userId,List<OrderItem> items){
        Map map=new HashMap();
        map.put("userId",userId);
        map.put("shopId",shopId);
        List<OrderMessage> list=orderMapper.getShoppingCartByShop(map);
        List<OrderItem> addList=new ArrayList();
        List<OrderItem> updateList=new ArrayList();
        if(list.size()>0){//已有订单 继续添加进购物车
            List<OrderItem> itemList=list.get(0).getOrderItems();
            for(int i=0;i<items.size();i++){
                int j=0;
                for(;j<itemList.size();j++){
                    if(items.get(i).getFoodId().equals(itemList.get(j).getFoodId())){//已有菜单项
                        OrderItem orderItem=itemList.get(j);
                        orderItem.setNum(orderItem.getNum()+items.get(i).getNum());
                        updateList.add(orderItem);
                        break;
                    }
                }
                if(j==itemList.size()){//新增菜单项 入购物车
                    OrderItem orderItem=items.get(i);
                    orderItem.setOrderItemId(UUIDTools.getPrimaryKey());
                    orderItem.setOrderId(list.get(0).getOrderId());
                    addList.add(items.get(i));
                }
            }
            if(updateList.size()>0)
                orderMapper.updateOrderItemNum(updateList);
            if(addList.size()>0)
                orderMapper.addOrderItem(addList);
            orderMapper.updateOrderPrice(list.get(0).getOrderId());//更新订单的总额
        }
        else{//新增的订单
            String orderId=UUIDTools.getPrimaryKey();
            Order order=new Order();
            order.setOrderId(orderId);
            order.setShopId(shopId);
            order.setUserId(userId);
            order.setState("5");
            double sum=0;
            for (OrderItem item : items) {
                sum = item.getNum() * item.getUnitprice() + sum;
                item.setOrderId(orderId);
                item.setOrderItemId(UUIDTools.getPrimaryKey());
            }
            order.setPrice(sum);
            orderMapper.insert(order);
            orderMapper.addOrderItem(items);
        }
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

    public void editOrderState(Order order){
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
