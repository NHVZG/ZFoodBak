package com.nhvzg.service;

import com.nhvzg.dao.CouponMapper;
import com.nhvzg.entity.Coupon;
import com.nhvzg.result.ShopCouponMsg;
import com.nhvzg.tools.UUIDTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by NHVZG on 2018/2/26.
 */
@Service
public class CouponService {
    @Autowired
    private CouponMapper mapper;

    public List<ShopCouponMsg> getShopCouponMsg(String shopId){
        return mapper.getShopCouponMsg(shopId);
    }

    public int getCountByShopId(String shopId){
        return mapper.getCountByShopId(shopId);
    }

    public void updateList(List list){
        mapper.updateCouponList(list);
    }

    public void insertList(List list){
        for(int i=0,j=list.size();i<j;i++){
            ShopCouponMsg item=(ShopCouponMsg)list.get(i);
            item.setCouponId(UUIDTools.getPrimaryKey());
        }
        mapper.insertCouponList(list);
    }

    public void deleteList(List list){
        mapper.deleteCouponList(list);
    }

    @Deprecated
    public List<Coupon> getShopCoupon(String shopId){
        return mapper.getShopCoupon(shopId);
    }
    @Deprecated
    public void addCoupon(Coupon coupon){
        coupon.setCouponId(UUIDTools.getPrimaryKey());
        mapper.insert(coupon);
    }
    @Deprecated
    public void removeCoupon(String couponId){
        mapper.deleteByPrimaryKey(couponId);
    }
}
