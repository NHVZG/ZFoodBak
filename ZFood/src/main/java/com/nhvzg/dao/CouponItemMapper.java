package com.nhvzg.dao;

import com.nhvzg.entity.CouponItem;
import com.nhvzg.result.UserCouponMsg;

import java.util.List;

public interface CouponItemMapper {
    int deleteByPrimaryKey(String couponItemId);

    int insert(CouponItem record);

    int insertSelective(CouponItem record);

    CouponItem selectByPrimaryKey(String couponItemId);

    int updateByPrimaryKeySelective(CouponItem record);

    int updateByPrimaryKey(CouponItem record);

    List<CouponItem> getAll();

    List<UserCouponMsg> getCouponsByUser(String userId);
}