package com.nhvzg.service;

import com.nhvzg.dao.OrderItemMapper;
import com.nhvzg.entity.OrderItem;
import com.nhvzg.tools.UUIDTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by NHVZG on 2018/2/27.
 */
@Service
public class OrderItemService {
    @Autowired
    private OrderItemMapper orderItemMapper;

    public void addItem(OrderItem item){
        item.setOrderItemId(UUIDTools.getPrimaryKey());
        orderItemMapper.insert(item);
    }
}
