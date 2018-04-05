package com.nhvzg.controller.order;


import com.nhvzg.entity.OrderItem;
import com.nhvzg.service.OrderItemService;
import com.nhvzg.tools.JsonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by NHVZG on 2018/2/27.
 */

//OrderItem∫ÕOrderœ‡πÿ
@RestController
@CrossOrigin
@Deprecated
public class OrderItemController {
    @Autowired
    private OrderItemService itemService;

    @PostMapping("/order/item/add")
    //foodId foodname unitprice num
    public void addOrderItem(@RequestBody String json) throws IOException {
        OrderItem orderItem= JsonTools.GetObject(json,OrderItem.class);
        itemService.addItem(orderItem);
    }
}
