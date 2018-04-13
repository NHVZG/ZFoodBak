package com.nhvzg.service;

import com.nhvzg.dao.FavFoodMapper;
import com.nhvzg.dao.FavShopMapper;
import com.nhvzg.entity.FavFood;
import com.nhvzg.entity.FavShop;
import com.nhvzg.result.FavFoodMsg;
import com.nhvzg.result.FavShopMsg;
import com.nhvzg.tools.UUIDTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by NHVZG on 2018/2/26.
 */

@Service
public class FavService {
    @Autowired
    private FavShopMapper shopMapper;
    @Autowired
    private FavFoodMapper foodMapper;

    public List<FavShopMsg> getAllFavShop(String userId){
        return shopMapper.getFavShop(userId);
    }
    public void addFavShop(FavShop favShop){
        favShop.setFavShopId(UUIDTools.getPrimaryKey());
        shopMapper.insert(favShop);
    }
    public void removeFavShop(String favShopId){
        shopMapper.deleteByPrimaryKey(favShopId);
    }
    public List checkFavShop(String shopId,String userId){
        Map map=new HashMap();
        map.put("shopId",shopId);
        map.put("userId",userId);
        return shopMapper.checkFavShop(map);
    }
    public List<FavFoodMsg>getAllFavFood(String id){
        return foodMapper.getFavFood(id);
    }


    public void addFavFoodsList(List list){
        for(int i=0;i<list.size();i++){
            ((FavFood)list.get(i)).setFavFoodId(UUIDTools.getPrimaryKey());
        }
        foodMapper.addFavFoodsList(list);
    }
    public void deleteFavFoodList(List list){
        foodMapper.deleteFavFoodList(list);
    }

    public void removeFavFood(String favFoodId){
        foodMapper.deleteByPrimaryKey(favFoodId);
    }

    @Deprecated
    public void addFavFood(FavFood food){
        food.setFavFoodId(UUIDTools.getPrimaryKey());
        foodMapper.insert(food);
    }


    @Deprecated
    public void addFavShopList(List<FavShop> list){
        for(FavShop shop:list){
            shop.setFavShopId(UUIDTools.getPrimaryKey());
        }
        shopMapper.addList(list);
    }
}
