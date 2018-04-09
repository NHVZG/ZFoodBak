package com.nhvzg.dao;

import com.nhvzg.entity.Coupon;
import com.nhvzg.result.ShopCouponMsg;

import java.util.List;
import java.util.Map;

public interface CouponMapper {
    int deleteByPrimaryKey(String couponId);

    int insert(Coupon record);

    int insertSelective(Coupon record);

    Coupon selectByPrimaryKey(String couponId);

    int updateByPrimaryKeySelective(Coupon record);

    int updateByPrimaryKey(Coupon record);

    List<Coupon> getShopCoupon(String shopId);

    List<ShopCouponMsg> getShopCouponMsg(String shopId);

    int getCountByShopId(String shopId);

    int updateCouponList(List list);

    int insertCouponList(List list);

    int deleteCouponList(List list);

    List<ShopCouponMsg>getShopCouponWithUser(Map map);

}