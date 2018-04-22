package com.nhvzg.result;

/**
 * Created by NHVZG on 2018/3/26.
 */
public class  FoodShortMsg {
    private String shopId;
    private String foodId;
    private String name;
    private Double price;
    private Double sendprice;
    private Double packprice;
    private String pic;
    private String favFoodId;
    private String shopName;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getFavFoodId() {
        return favFoodId;
    }

    public void setFavFoodId(String favFoodId) {
        this.favFoodId = favFoodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSendprice() {
        return sendprice;
    }

    public void setSendprice(Double sendprice) {
        this.sendprice = sendprice;
    }

    public Double getPackprice() {
        return packprice;
    }

    public void setPackprice(Double packprice) {
        this.packprice = packprice;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
