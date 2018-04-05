package com.nhvzg.result;

/**
 * Created by NHVZG on 2018/3/18.
 */
public class FavShopMsg {
    private String favShopId;
    private String userId;
    private String shopId;
    private String favShopName;
    private String shopPic;

    public String getShopPic() {
        return shopPic;
    }

    public void setShopPic(String shopPic) {
        this.shopPic = shopPic;
    }

    public String getFavShopId() {
        return favShopId;
    }

    public void setFavShopId(String favShopId) {
        this.favShopId = favShopId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getFavShopName() {
        return favShopName;
    }

    public void setFavShopName(String favShopName) {
        this.favShopName = favShopName;
    }
}
