package com.nhvzg.service;

import com.nhvzg.dao.FoodMapper;
import com.nhvzg.entity.Food;
import com.nhvzg.result.FoodKindMsg;
import com.nhvzg.result.FoodShortMsg;
import com.nhvzg.result.UserCouponMsg;
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

    //���̻�ȡ�Լ�ʳƷ
    public List<Food> getAllFoodByShopId(String shopId){
        return foodMapper.getFoodByShopId(shopId);
    }
    //�Ż�ȯ����ʳ��
    public List<FoodShortMsg>getAllShortFoodByShopid(String shopId){
        return foodMapper.getAllFoodShortMsg(shopId);
    }
    //��������ʳ����Ϣ
    public void updateFood(List food){
        foodMapper.updateList(food);
      /*  Food f=foodMapper.selectByPrimaryKey(food.getFoodId());
        food.setFoodId(f.getFoodId());
        food.setScore(f.getScore());
        foodMapper.updateByPrimaryKey(food);*/
    }
    //���ʳ����Ϣ
    public String addFood(Food food,boolean file){
        food.setFoodId(UUIDTools.getPrimaryKey());
        if(!file)
            food.setPic("static/img/food/"+food.getFoodId()+".jpg");
        foodMapper.insert(food);
        return food.getFoodId();
    }
    //����ɾ��ʳ����Ϣ
    public void deleteList(List list){
        foodMapper.deleteList(list);
    }
    //��ҳ��ȡʳ������
    public int getCountByShopId(String shopId){
        return foodMapper.getCountByShopId(shopId);
    }

    //����ʳƷ
    public List<FoodShortMsg> getAllFood(String shopId,String userId){
        Map map=new HashMap();
        map.put("shopId",shopId);
        map.put("userId",userId);
        return foodMapper.getAllFoodwithShopAndUser(map);
    }

    //��ȡ����ʳ����Ϣ
    public FoodShortMsg getOneFood(String foodId,String userId){
        Map map=new HashMap();
        map.put("foodId",foodId);
        map.put("userId",userId);
        return foodMapper.getOneFoodWithUser(map);
    }
    public List<UserCouponMsg>getFoodCoupons(String foodId,String userId){
        Map map=new HashMap();
        map.put("foodId",foodId);
        map.put("userId",userId);
        return foodMapper.getFoodCoupons(map);
    }

    //����
    public List<FoodShortMsg> queryFoods(String queryText){
        return foodMapper.queryFoods(queryText);
    }

    public List<FoodShortMsg> queryFoodsKind(String queryText){
        return foodMapper.queryFoodsKind(queryText);
    }

    //��ȡ��ҳ����
    public List<FoodKindMsg> getHotKindFood(){
        return foodMapper.getKindsFood();
    }
    //��ȡ��ҳ���ʳ��
    public List<FoodShortMsg>randomFoods(){return foodMapper.randomFoods();}
    @Deprecated
    public void removeFood(String fooId){
        foodMapper.deleteByPrimaryKey(fooId);
    }
}
