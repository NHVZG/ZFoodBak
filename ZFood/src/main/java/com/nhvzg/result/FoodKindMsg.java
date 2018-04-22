package com.nhvzg.result;

import java.util.List;

/**
 * Created by NHVZG on 2018/4/19.
 */
public class FoodKindMsg{
    private String kind;
    private List<FoodsMsg> foodList;
    public static class FoodsMsg{//必须用静态内部类
       private String foodName;
       private String foodId;
       private String shopName;
       private String shopId;
       private Double price;
       private String pic;

        public String getFoodName() {
            return foodName;
        }

        public void setFoodName(String foodName) {
            this.foodName = foodName;
        }

        public String getFoodId() {
            return foodId;
        }

        public void setFoodId(String foodId) {
            this.foodId = foodId;
        }

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

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<FoodsMsg> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<FoodsMsg> foodList) {
        this.foodList = foodList;
    }
}
