package com.nhvzg.service;

import com.nhvzg.dao.ShopMapper;
import com.nhvzg.entity.Shop;
import com.nhvzg.result.ShopUserMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by NHVZG on 2018/2/27.
 */
@Service
public class ShopService {
    @Autowired
    private ShopMapper shopMapper;

    public Shop getShopByShopId(String shopId){
        Shop shop=shopMapper.selectByPrimaryKey(shopId);
        shop.setUserId("");
        shop.setIncome(0.0);
        return shop;
    }

    public ShopUserMsg getShopByUser(String userId){
        ShopUserMsg shopUserMsg= shopMapper.getShopByUser(userId);
        return shopMapper.getShopByUser(userId);
    }
    public void UpLoadImgByShopId(String shopId){
        Shop shop=new Shop();
        shop.setShopId(shopId);
        shop.setHeadpic("/static/img/shop/"+shopId+".jpg");
        shopMapper.updateHeadImg(shop);
    }

    public void updateShop(ShopUserMsg shop){
        Shop s=new Shop();
        s.setHeadpic(shop.getHeadImg());
        s.setShopId(shop.getShopId());
        s.setPhone(shop.getPhone());
        s.setAddress(shop.getAddress());
        s.setIncome(shop.getIncome());
        s.setShopname(shop.getShopName());
        s.setUserId(shop.getUserId());
        s.setScore(shop.getScore());
        shopMapper.updateByPrimaryKey(s);
    }

    @Deprecated
    public Shop getShopByName(String name){
        return null;//shopMapper.getShopByName(name);
    }
}
