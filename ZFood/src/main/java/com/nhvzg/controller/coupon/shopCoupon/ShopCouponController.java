package com.nhvzg.controller.coupon.shopCoupon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.nhvzg.entity.Coupon;
import com.nhvzg.result.ShopCouponMsg;
import com.nhvzg.service.CouponService;
import com.nhvzg.tools.JsonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by NHVZG on 2018/2/26.
 */
//主要是Coupon表的操作
@RestController
@CrossOrigin
public class ShopCouponController {
    @Autowired
    private CouponService service;

    @PostMapping("/coupon/shop")
    //shopId
    public Map<String, Object> getUserCoupon(@RequestBody String json, HttpServletResponse response) throws IOException {
        Map coupon=JsonTools.GetObject(json,Map.class);
        PageHelper.startPage((Integer) coupon.get("page"), 6);
        List list= service.getShopCouponMsg((String) coupon.get("shopId"));
        int count=service.getCountByShopId((String) coupon.get("shopId"));
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("couponList",list);
        map.put("count",count%6==0?count/6:count/6+1);
        map.put("active",coupon.get("page")==null?1:coupon.get("page"));
        return map;
    }

    @PostMapping("/coupon/add/list")
    public void CouponAddList(@RequestBody String json) throws IOException {
        List<ShopCouponMsg> list=JsonTools.GetList(json,ShopCouponMsg.class);
        service.insertList(list);
    }
    @PostMapping("/coupon/del/list")
    public void CouponDelList(@RequestBody String json) throws IOException {
        List ids=JsonTools.GetList(json,ShopCouponMsg.class);
        service.deleteList(ids);
    }

    @PostMapping("/coupon/edit/list")
    public void CouponUpadateList(@RequestBody String json) throws IOException {
        List<ShopCouponMsg> couponList=JsonTools.GetList(json,ShopCouponMsg.class);
        service.updateList(couponList);
    }


    @PostMapping("/coupon/user")
    //shopId
    public List<ShopCouponMsg>getShopCouponToUser(@RequestBody String json) throws IOException {
        Coupon coupon=JsonTools.GetObject(json,Coupon.class);
        return service.getShopCouponMsg(coupon.getShopId());
    }

    @Deprecated
    @PostMapping("/coupon/add")
    //shopId  name detail starttime endtime discount percent usage condition extra
    public void  CouponAdd(@RequestBody String json) throws IOException {
        Coupon coupon= JsonTools.GetObject(json,Coupon.class);
        service.addCoupon(coupon);
    }
    @Deprecated
    @PostMapping("/coupon/del")
    //couponId
    public void CouponDel(@RequestBody String json) throws IOException {
        Coupon coupon=JsonTools.GetObject(json,Coupon.class);
        service.removeCoupon(coupon.getCouponId());
    }
}
