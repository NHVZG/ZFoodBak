package com.nhvzg.controller.food;

import com.github.pagehelper.PageHelper;
import com.nhvzg.entity.Food;
import com.nhvzg.result.FoodShortMsg;
import com.nhvzg.service.FoodService;
import com.nhvzg.tools.JsonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by NHVZG on 2018/2/27.
 */
@RestController
@CrossOrigin
public class FoodController {
    @Autowired
    private FoodService foodService;

    @PostMapping("/food/shop")
    //提供shopId
    public Map getFoodByShop(@RequestBody String json) throws IOException {
        Map food= JsonTools.GetObject(json,Map.class);
        PageHelper.startPage((Integer) food.get("page"), 6);
        List list= foodService.getAllFoodByShopId((String) food.get("shopId"));
        int count=foodService.getCountByShopId((String) food.get("shopId"));
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("foodList",list);
        map.put("count",count%6==0?count/6:count/6+1);
        map.put("active",food.get("page")==null?1:food.get("page"));
        //String response=JsonTools.GetJson(map);
        return map;
    }

    @PostMapping("/food/coupon")
    public List<FoodShortMsg>getAllShortFood(@RequestBody String json) throws IOException {
        Food food=JsonTools.GetObject(json,Food.class);
        return foodService.getAllShortFoodByShopid(food.getShopId());
    }


    @PostMapping("/food/edit")
    //必须提供foodId
    public void FoodEdit(@RequestParam(value = "files",required = false)MultipartFile[] files,@RequestParam("msg") String json) throws IOException {
        List<Food> foodlist=JsonTools.GetList(json,Food.class);
        foodService.updateFood(foodlist);
        if(files.length==0)return;
        for(int i=0,j=0;i<foodlist.size();i++){
            if(foodlist.get(i).getPicChange().equals("null"))continue;
            File filepath = new File("E:\\Code\\Work\\ZFoodFront\\ZFoodFront\\static\\img\\food",foodlist.get(i).getFoodId()+".jpg");
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            files[j].transferTo(filepath);
            j++;
        }
    }

    @PostMapping("/food/add")
    //shopId name price sendprice packprice pic kind
    public void FoodAdd(@RequestParam(value ="file", required=false)MultipartFile file, @RequestParam("msg")String json) throws IOException {
        Food food=JsonTools.GetObject(json,Food.class);
        String foodId=foodService.addFood(food,file==null);
        if(file==null)return;
        File filepath = new File("E:\\Code\\Work\\ZFoodFront\\ZFoodFront\\static\\img\\food",foodId+".jpg");
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        file.transferTo(filepath);
    }

    @PostMapping("/food/del")
    //foodId 可多个·
    public void FoodDel(@RequestBody String json) throws IOException {
        List ids=JsonTools.GetList(json,String.class);
        foodService.deleteList(ids);
    }


    @PostMapping("/food/user")
    public List<Food> getAllFoodByShopIId(@RequestBody String json) throws IOException {
        Food food=JsonTools.GetObject(json,Food.class);
        return foodService.getAllFoodByShopId(food.getShopId());
    }
}
