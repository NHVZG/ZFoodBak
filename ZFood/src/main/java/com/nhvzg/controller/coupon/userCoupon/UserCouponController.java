package com.nhvzg.controller.coupon.userCoupon;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nhvzg.entity.CouponItem;
import com.nhvzg.entity.FavShop;
import com.nhvzg.result.OrderMessage;
import com.nhvzg.result.UserCouponMsg;
import com.nhvzg.service.CouponItemService;
import com.nhvzg.tools.JsonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by NHVZG on 2018/2/26.
 */

//主要是CouponItem表的操作
@RestController
@CrossOrigin
public class UserCouponController {
    @Autowired
    private CouponItemService service;

    @PostMapping("/userCouponItem")
    //userId
    public List<UserCouponMsg> UserCouponItem(@RequestBody String json, HttpServletRequest request) throws IOException {
        CouponItem item=new CouponItem();
        item.setUserId((String) request.getAttribute("userId"));
        return service.getUserCouponItem(item);
    }

    @PostMapping("/userCouponItem/add/list")
    public void CouponItemAddList(@RequestBody String json) throws IOException {
        List list=JsonTools.GetList(json,CouponItem.class);
        service.addCouponItemList(list);
    }

    //获取总价和优惠值
    @PostMapping("/userCouponItem/getAmount")
    public Map GetAmount(@RequestBody String json) throws IOException {
        List couponList=JsonTools.GetPartObjectToList(json,UserCouponMsg.class,"couponList");
        List orderItems= JsonTools.GetPartObjectToList(json,OrderMessage.class,"orderItems");
        return service.getAmount(couponList,orderItems);
    }


    @Deprecated
    @PostMapping("/userCouponItem/add")
    //starttime endtime  couponid userId
    public void CouponItemAdd(@RequestBody String json) throws IOException {
        CouponItem item= JsonTools.GetObject(json,CouponItem.class);
        service.addCouponItem(item);
    }

    @PostMapping("/userCouponItem/del")
    //暂时不用
    public void CouponItemDel(@RequestBody String json) throws IOException {
        CouponItem item = JsonTools.GetObject(json,CouponItem.class);
        service.removeCouponItem(item.getCouponItemId());
    }
}
