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

    //�û����̼һ�ȡ����
    List<OrderMessage>getOrderMsgByUser(String userId);//���������ﳵ

    List<OrderMessage>getShopOrderCurrent(String shopId);

    List<OrderMessage>getShopOrderHistory(String shopId);

    List<OrderItem>selectOrderItem(String order_id);

    //���ﳵ
    List<OrderMessage> getShoppingCart(String userId);

    List<OrderMessage> getShoppingCartByShop(Map map);

    void updateOrderItemNum(List list);

    void addOrderItem(List list);

    void updateOrderPrice(String orderId);

    void commitOrderState(OrderMessage order);

    //����
    void saveScore(OrderMessage orderMessage);

    //����Ա��ȡ�ɽӶ���
    List<OrderMessage>getCourierReceiveOrder();
    //����Ա��ȡ�ѽӶ���
    List<OrderMessage>getCourierCurrentOrders(String courierId);
    //����Ա��ȡ��ǰ����
    List<OrderMessage>getCourierHistoryOrder(String courierId);
    //�����̵�����
    void updateShopIncome(Order order);
    //��������Ա����
    void updateCourierIncome(Order order);

}