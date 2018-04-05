package com.nhvzg.controller.edit.shop;

import com.nhvzg.entity.Shop;
import com.nhvzg.result.ShopUserMsg;
import com.nhvzg.service.ShopService;
import com.nhvzg.tools.JsonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by NHVZG on 2018/2/27.
 */
@RestController
@CrossOrigin
public class ShopEditController {
    @Autowired
    private ShopService shopService;

    @PostMapping("/shop/msg")//用户获取
    public Shop getShopByShopId(@RequestBody String json) throws IOException {
        Shop shop=JsonTools.GetObject(json,Shop.class);
        return shopService.getShopByShopId(shop.getShopId());
    }

    @PostMapping("/shop/message")//商家获取
    public ShopUserMsg getShopMessage(HttpServletRequest request) throws IOException {
        return shopService.getShopByUser((String)request.getAttribute("userId"));
    }
    @PostMapping("/shop/headImg")
    public void  ShopImg(MultipartFile file,HttpServletRequest request) throws IOException {
        String shopId=request.getParameter("shopId");
        File filepath = new File("E:\\Code\\Work\\ZFoodFront\\ZFoodFront\\static\\img\\shop",shopId+".jpg");
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        file.transferTo(filepath);
        shopService.UpLoadImgByShopId(shopId);
    }

    @PostMapping("/shop/edit")
    //shopname phone address
    public void ShopEdit(@RequestBody String json) throws IOException {
        ShopUserMsg msg= JsonTools.GetObject(json,ShopUserMsg.class);
        shopService.updateShop(msg);
    }

}
