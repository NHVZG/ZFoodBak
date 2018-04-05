package com.nhvzg.dao;

import com.nhvzg.entity.Shop;
import com.nhvzg.result.ShopUserMsg;

public interface ShopMapper {
    int deleteByPrimaryKey(String shopId);

    int insert(Shop record);

    int insertSelective(Shop record);

    Shop selectByPrimaryKey(String shopId);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);

    ShopUserMsg getShopByUser(String userId);

    int updateHeadImg(Shop shop);
}