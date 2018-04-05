package com.nhvzg.result;

/**
 * Created by NHVZG on 2018/3/18.
 */
public class FavFoodMsg {
    private String favFoodId;
    private String foodId;
    private String foodName;
    private String shopId;
    private String shopName;
    private String foodPic;
    private String shopPic;

    public String getFoodPic() {
        return foodPic;
    }

    public void setFoodPic(String foodPic) {
        this.foodPic = foodPic;
    }

    public String getShopPic() {
        return shopPic;
    }

    public void setShopPic(String shopPic) {
        this.shopPic = shopPic;
    }

    public String getFavFoodId() {
        return favFoodId;
    }

    public void setFavFoodId(String favFoodId) {
        this.favFoodId = favFoodId;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
