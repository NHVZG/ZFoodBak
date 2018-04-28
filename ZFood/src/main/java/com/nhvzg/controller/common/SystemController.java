package com.nhvzg.controller.common;

import com.nhvzg.result.FoodKindMsg;
import com.nhvzg.result.FoodShortMsg;
import com.nhvzg.service.FoodService;
import com.nhvzg.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by NHVZG on 2018/4/18.
 */

@RestController
public class SystemController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private ShopService shopService;

    @GetMapping("/indexData")
    public Map getHotKindFoods(){
        List<FoodKindMsg> kindMsgList= foodService.getHotKindFood();    //热门分类
        List<FoodShortMsg> foodShortMsgList= foodService.randomFoods(); //随机
        Map map=new HashMap();
        map.put("kindFoods",kindMsgList);
        map.put("RandomFoods",foodShortMsgList);
        return map;
    }

    @PostMapping("/search")
    public Map search(@RequestBody String queryText){
        List foodList=foodService.queryFoods(queryText);
        List shopList=shopService.queryShops(queryText);
        Map map=new HashMap();
        map.put("foodList",foodList);
        map.put("shopList",shopList);
        return map;
    }

    @PostMapping("/search/kind")
    public Map searchKind(@RequestBody String queryText){
        List foodList=foodService.queryFoodsKind(queryText);
        List shopList=new ArrayList();
        Map map=new HashMap();
        map.put("foodList",foodList);
        map.put("shopList",shopList);
        return map;
    }
}
