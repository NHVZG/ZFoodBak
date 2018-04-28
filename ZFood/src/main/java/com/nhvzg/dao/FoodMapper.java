package com.nhvzg.dao;

import com.nhvzg.entity.Food;
import com.nhvzg.result.FoodKindMsg;
import com.nhvzg.result.FoodShortMsg;
import com.nhvzg.result.UserCouponMsg;

import java.util.List;
import java.util.Map;

public interface FoodMapper {
    int deleteByPrimaryKey(String foodId);

    int insert(Food record);

    int insertSelective(Food record);

    Food selectByPrimaryKey(String foodId);

    int updateByPrimaryKeySelective(Food record);

    int updateByPrimaryKey(Food record);

    List<Food> getFoodByShopId(String shopId);

    List<Food>getAll();

    int getCountByShopId(String shopId);

    int deleteList(List list);

    int updateList(List list);

    List<FoodShortMsg>getAllFoodShortMsg(String shopId);

    List<FoodShortMsg>getAllFoodwithShopAndUser(Map map);

    List<FoodKindMsg>getKindsFood();

    List<FoodShortMsg>randomFoods();

    int updateFoodScore(String orderId);

    FoodShortMsg getOneFoodWithUser(Map map);

    List<UserCouponMsg>getFoodCoupons(Map map);

    List<FoodShortMsg> queryFoods(String queryText);

    List<FoodShortMsg>queryFoodsKind(String queryText);
}