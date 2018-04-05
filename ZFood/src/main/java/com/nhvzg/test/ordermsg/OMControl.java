package com.nhvzg.test.ordermsg;

import com.nhvzg.result.OrderMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by NHVZG on 2018/3/17.
 */
@RestController
public class OMControl {
    @Autowired
    private OMService service;

    @RequestMapping("/omsg")
    public List<OrderMessage> getMSG(){
        return service.getOrderMessageByUser();
    }
}
