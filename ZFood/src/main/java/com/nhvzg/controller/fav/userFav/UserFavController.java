package com.nhvzg.controller.fav.userFav;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.nhvzg.entity.FavFood;
import com.nhvzg.entity.FavShop;
import com.nhvzg.result.FavFoodMsg;
import com.nhvzg.result.FavShopMsg;
import com.nhvzg.service.FavService;
import com.nhvzg.tools.JsonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by NHVZG on 2018/2/26.
 */
@RestController
@CrossOrigin
public class UserFavController {
    @Autowired
    private FavService service;

    @PostMapping ("/favShop")
    public List<FavShopMsg> FavShop(HttpServletRequest request){
        //PageHelper.startPage(2, 3);
        return service.getAllFavShop((String)request.getAttribute("userId"));
    }

    @PostMapping("/favShop/add")
    @ResponseBody
    //usrId shopId
    public void FavShopAdd(@RequestBody String json) throws IOException {
        FavShop favShop= JsonTools.GetObject(json,FavShop.class);
        service.addFavShop(favShop);
    }

    @PostMapping("/favShop/del")
    @ResponseBody
    //favShopId
    public void FavShopDel(@RequestBody String json) throws IOException {
        System.out.println("@@@@"+json);
        String favShopId=JsonTools.GetObject(json,FavShop.class).getFavShopId();
        service.removeFavShop(favShopId);
    }






    @PostMapping("/favFood")
    @ResponseBody
    public List<FavFoodMsg> FavFood(HttpServletRequest request){
        return service.getAllFavFood((String) request.getAttribute("userId"));
    }
    @PostMapping("/favFood/add/list")
    public void AddFavFoodList(@RequestBody String json,HttpServletRequest request) throws IOException {
        List list=JsonTools.GetList(json,FavFood.class);
        service.addFavFoodsList(list, (String) request.getAttribute("userId"));
    }
    @PostMapping("/favFood/del/list")
    public void DelFavFoodList(@RequestBody String json) throws IOException {
        List list=JsonTools.GetList(json,String.class);
        service.deleteFavFoodList(list);
    }

    @PostMapping("/favFood/del")
    @ResponseBody
    //favFoodId
    public void FavFoodDel(@RequestBody String json) throws IOException {
        service.removeFavFood(JsonTools.GetObject(json,FavFood.class).getFavFoodId());
    }


    @PostMapping("/favFood/add/one")
    //userId foodId
    public String FavFoodAdd(@RequestBody String json,HttpServletRequest request) throws IOException {
        return service.addFavFood(json,(String)request.getAttribute("userId"));
    }





    @Deprecated
    @PostMapping("/favShop/add/list")
    public void FavShopAddList(@RequestBody String json) throws IOException {
        List list=JsonTools.GetList(json,FavShop.class);
        service.addFavShopList(list);
    }
}
