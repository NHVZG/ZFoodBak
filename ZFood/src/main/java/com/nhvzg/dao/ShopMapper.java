package com.nhvzg.dao;

import com.nhvzg.entity.Shop;
import com.nhvzg.result.OrderMessage;
import com.nhvzg.result.ShopUserMsg;

import java.util.List;

public interface ShopMapper {
    int deleteByPrimaryKey(String shopId);

    int insert(Shop record);

    int insertSelective(Shop record);

    Shop selectByPrimaryKey(String shopId);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);

    ShopUserMsg getShopByUser(String userId);

    int updateHeadImg(Shop shop);

    void updateScore(OrderMessage msg);

    List<Shop> queryShops(String queryText);
}