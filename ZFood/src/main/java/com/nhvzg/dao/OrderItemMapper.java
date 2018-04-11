package com.nhvzg.dao;

import com.nhvzg.entity.OrderItem;

import java.util.Map;

public interface OrderItemMapper {
    int deleteByPrimaryKey(String orderItemId);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(String orderItemId);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);

    void changNum(Map map);
}