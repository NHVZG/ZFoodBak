package com.nhvzg.entity;

public class AdminHot {
    private String topicId;

    private String topicpic;

    private String foodId;

    private String shopId;

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId == null ? null : topicId.trim();
    }

    public String getTopicpic() {
        return topicpic;
    }

    public void setTopicpic(String topicpic) {
        this.topicpic = topicpic == null ? null : topicpic.trim();
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId == null ? null : foodId.trim();
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }
}