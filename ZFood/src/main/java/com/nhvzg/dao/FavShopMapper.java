package com.nhvzg.dao;

import com.nhvzg.entity.FavShop;
import com.nhvzg.result.FavShopMsg;

import java.util.List;
import java.util.Map;

public interface FavShopMapper {
    int deleteByPrimaryKey(String favShopId);

    int insert(FavShop record);

    int insertSelective(FavShop record);

    FavShop selectByPrimaryKey(String favShopId);

    int updateByPrimaryKeySelective(FavShop record);

    int updateByPrimaryKey(FavShop record);

    List<FavShop> getAll();

    int addList(List<FavShop> list);

    List<FavShopMsg>getFavShop(String userId);

    List<FavShop> checkFavShop(Map map);
}