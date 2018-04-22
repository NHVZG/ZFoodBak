package com.nhvzg.service;

import com.nhvzg.dao.FoodMapper;
import com.nhvzg.entity.Food;
import com.nhvzg.result.FoodKindMsg;
import com.nhvzg.result.FoodShortMsg;
import com.nhvzg.tools.UUIDTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by NHVZG on 2018/2/27.
 */
@Service
public class FoodService {
    @Autowired
    private FoodMapper foodMapper;

    //店铺获取自家食品
    public List<Food> getAllFoodByShopId(String shopId){
        return foodMapper.getFoodByShopId(shopId);
    }
    //优惠券关联食物
    public List<FoodShortMsg>getAllShortFoodByShopid(String shopId){
        return foodMapper.getAllFoodShortMsg(shopId);
    }
    //批量更新食物信息
    public void updateFood(List food){
        foodMapper.updateList(food);
      /*  Food f=foodMapper.selectByPrimaryKey(food.getFoodId());
        food.setFoodId(f.getFoodId());
        food.setScore(f.getScore());
        foodMapper.updateByPrimaryKey(food);*/
    }
    //添加食物信息
    public String addFood(Food food,boolean file){
        food.setFoodId(UUIDTools.getPrimaryKey());
        if(!file)
            food.setPic("static/img/food/"+food.getFoodId()+".jpg");
        foodMapper.insert(food);
        return food.getFoodId();
    }
    //批量删除食物信息
    public void deleteList(List list){
        foodMapper.deleteList(list);
    }
    //分页获取食物总数
    public int getCountByShopId(String shopId){
        return foodMapper.getCountByShopId(shopId);
    }

    //所有食品
    public List<FoodShortMsg> getAllFood(String shopId,String userId){
        Map map=new HashMap();
        map.put("shopId",shopId);
        map.put("userId",userId);
        return foodMapper.getAllFoodwithShopAndUser(map);
    }

    //获取首页种类
    public List<FoodKindMsg> getHotKindFood(){
        return foodMapper.getKindsFood();
    }
    //获取首页随机食物
    public List<FoodShortMsg>randomFoods(){return foodMapper.randomFoods();}
    @Deprecated
    public void removeFood(String fooId){
        foodMapper.deleteByPrimaryKey(fooId);
    }
}
