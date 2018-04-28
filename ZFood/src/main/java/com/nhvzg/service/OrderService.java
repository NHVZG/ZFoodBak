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

    //�����ﳵ�����
    public void addShoppingCartByShop(String shopId,String userId,List<OrderItem> items){
        Map map=new HashMap();
        map.put("userId",userId);
        map.put("shopId",shopId);
        List<OrderMessage> list=orderMapper.getShoppingCartByShop(map);
        List<OrderItem> addList=new ArrayList();
        List<OrderItem> updateList=new ArrayList();
        if(list.size()>0){//���ж��� ������ӽ����ﳵ
            List<OrderItem> itemList=list.get(0).getOrderItems();
            for(int i=0;i<items.size();i++){
                int j=0;
                for(;j<itemList.size();j++){
                    if(items.get(i).getFoodId().equals(itemList.get(j).getFoodId())){//���в˵���
                        OrderItem orderItem=itemList.get(j);
                        orderItem.setNum(orderItem.getNum()+items.get(i).getNum());
                        updateList.add(orderItem);
                        break;
                    }
                }
                if(j==itemList.size()){//�����˵��� �빺�ﳵ
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
            orderMapper.updateOrderPrice(map1);//���¶������ܶ�
        }
        else{//�����Ķ���
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

    //ɾ�����ж��������
    public void removeShoppingCartItem(String orderItemId,String orderId){
        itemMapper.deleteByPrimaryKey(orderItemId);
        if(orderId!=null&&!orderId.equals("")){
            orderMapper.deleteByPrimaryKey(orderId);
        }
    }

    //�ı䶩��������
    public void changOrderItemNum(String orderItemId,int num){
        Map map=new HashMap();
        map.put("orderItemId",orderItemId);
        map.put("num",num);
        itemMapper.changNum(map);
    }

    //�ύ����
    public void commitOrder(OrderMessage order){
        order.setState("4");
        orderMapper.commitOrderState(order);
    }

    //��������Ա
    public boolean editOrderCourier(Order order){
        Order o=orderMapper.selectByPrimaryKey(order.getOrderId());
        if(o.getCourierId()!=null&&!o.getCourierId().equals("")){//����ĵ��ѱ����� �򷵻�false
            return false;
        }
        o.setCourierId(order.getCourierId());
        orderMapper.updateByPrimaryKey(o);
        editOrderState(order);
        return true;
    }

    //��ȡ����Ա�ѽӶ���
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
            commentMapper.insert(comment);//�������۱�
        }
       /* if(orderMessage.getScore()!=null){//�����̵�����
            shopMapper.updateScore(orderMessage);
        }*/

        orderMapper.saveScore(orderMessage);//���¶�����
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

    //���ݿ⼶��ɾ��(ȡ��)����
    public void deleteOrderWithItem(String  orderId){
        orderMapper.deleteByPrimaryKey(orderId);
    }

    //�����̵������Ա����
    public void updateAllIncome(Order order){
        orderMapper.updateShopIncome(order);
        orderMapper.updateCourierIncome(order);
    }

    //�޸�ʳ��ĳɽ���
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
