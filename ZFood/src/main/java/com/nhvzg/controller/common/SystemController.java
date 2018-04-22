package com.nhvzg.controller.common;

import com.nhvzg.result.FoodKindMsg;
import com.nhvzg.result.FoodShortMsg;
import com.nhvzg.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

        @GetMapping("/indexData")
    public Map getHotKindFoods(){
        List<FoodKindMsg> kindMsgList= foodService.getHotKindFood();    //热门分类
        List<FoodShortMsg> foodShortMsgList= foodService.randomFoods(); //随机
        Map map=new HashMap();
        map.put("kindFoods",kindMsgList);
        map.put("RandomFoods",foodShortMsgList);
        return map;
    }

}
