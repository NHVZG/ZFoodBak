package com.nhvzg.test.ordermsg;

import com.nhvzg.dao.OrderMapper;
import com.nhvzg.result.OrderMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by NHVZG on 2018/3/17.
 */
@Service
public class OMService {
    @Autowired
    private OrderMapper mapper;

    public List<OrderMessage> getOrderMessageByUser(){
        List<OrderMessage>list= mapper.getOrderMsgByUser("e6879869Me55eM4d10Mada5M65eb7855dc46");
        return list;
    }
}
