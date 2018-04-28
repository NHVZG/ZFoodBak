package com.nhvzg.service;

import com.nhvzg.dao.*;
import com.nhvzg.entity.Comment;
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
    @Autowired
    private OrderItemMapper itemMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private FoodMapper foodMapper;

    public List<OrderMessage>getShoppingCart(String userId){
        return orderMapper.getShoppingCart(userId);
    }

    //往购物车里添加
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
            Map map1=new HashMap();
            map1.put("orderId",list.get(0).getOrderId());
            map1.put("sendPrice",list.get(0).getSendprice());
            orderMapper.updateOrderPrice(map1);//更新订单的总额
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
                double packPrice=item.getPackprice()==null?0:item.getPackprice();
                sum = item.getNum() * item.getUnitprice() +packPrice+ sum;
                item.setOrderId(orderId);
                item.setOrderItemId(UUIDTools.getPrimaryKey());
            }
            order.setPrice(sum);
            orderMapper.insert(order);
            orderMapper.addOrderItem(items);
        }
    }

    //删除已有订单项及订单
    public void removeShoppingCartItem(String orderItemId,String orderId){
        itemMapper.deleteByPrimaryKey(orderItemId);
        if(orderId!=null&&!orderId.equals("")){
            orderMapper.deleteByPrimaryKey(orderId);
        }
    }

    //改变订单项数量
    public void changOrderItemNum(String orderItemId,int num){
        Map map=new HashMap();
        map.put("orderItemId",orderItemId);
        map.put("num",num);
        itemMapper.changNum(map);
    }

    //提交订单
    public void commitOrder(OrderMessage order){
        order.setState("4");
        orderMapper.commitOrderState(order);
    }

    //更改配送员
    public boolean editOrderCourier(Order order){
        Order o=orderMapper.selectByPrimaryKey(order.getOrderId());
        if(o.getCourierId()!=null&&!o.getCourierId().equals("")){//如果改单已被接收 则返回false
            return false;
        }
        o.setCourierId(order.getCourierId());
        orderMapper.updateByPrimaryKey(o);
        editOrderState(order);
        return true;
    }

    //获取配送员已接订单
    public List<OrderMessage>getCourierCurrentOrders(String courierId){
        return orderMapper.getCourierCurrentOrders(courierId);
    }

    public List<OrderMessage> getCourierReceiveOrder(){
        return orderMapper.getCourierReceiveOrder();
    }

    public List<OrderMessage>getCourierHistoryOrder(String courierId){
        return orderMapper.getCourierHistoryOrder(courierId);
    }

    public void editOrderState(Order order){
        Order o=orderMapper.selectByPrimaryKey(order.getOrderId());
        o.setState(order.getState());
        orderMapper.updateByPrimaryKey(o);
    }

    public void saveComment(OrderMessage orderMessage){
        List list=commentMapper.selectOrder(orderMessage.getOrderId());
        if(list.size()==0) {
            Comment comment = new Comment();
            comment.setUserId(orderMessage.getUserId());
            comment.setCommentId(UUIDTools.getPrimaryKey());
            comment.setOrderId(orderMessage.getOrderId());
            comment.setComment(orderMessage.getComment());
            comment.setSource(orderMessage.getScore());
            commentMapper.insert(comment);//插入评论表
        }
       /* if(orderMessage.getScore()!=null){//更新商店评分
            shopMapper.updateScore(orderMessage);
        }*/

        orderMapper.saveScore(orderMessage);//更新订单表
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

    //数据库级联删除(取消)订单
    public void deleteOrderWithItem(String  orderId){
        orderMapper.deleteByPrimaryKey(orderId);
    }

    //更新商店和配送员收入
    public void updateAllIncome(Order order){
        orderMapper.updateShopIncome(order);
        orderMapper.updateCourierIncome(order);
    }

    //修改食物的成交量
    public void updateFoodScore(String orderId){
        foodMapper.updateFoodScore(orderId);
    }


    @Deprecated
    public void addOrder(Order order){
        order.setOrderId(UUIDTools.getPrimaryKey());
        orderMapper.insert(order);
    }
    @Deprecated
    public List<Order> getOrderByUser(Order order){
        return orderMapper.getOrderByUser(order.getUserId());
    }
}
