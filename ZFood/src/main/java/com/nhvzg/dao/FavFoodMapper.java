package com.nhvzg.dao;

import com.nhvzg.entity.FavFood;
import com.nhvzg.result.FavFoodMsg;

import java.util.List;

public interface FavFoodMapper {
    int deleteByPrimaryKey(String favFoodId);

    int insert(FavFood record);

    int insertSelective(FavFood record);

    FavFood selectByPrimaryKey(String favFoodId);

    int updateByPrimaryKeySelective(FavFood record);

    int updateByPrimaryKey(FavFood record);

    List<FavFood> getAll();

    List<FavFoodMsg> getFavFood(String userId);

    int addFavFoodsList(List list);

    int deleteFavFoodList(List list);
}