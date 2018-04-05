package com.nhvzg.entity;

public class FavFood {
    private String favFoodId;

    private String userId;

    private String foodId;

    public String getFavFoodId() {
        return favFoodId;
    }

    public void setFavFoodId(String favFoodId) {
        this.favFoodId = favFoodId == null ? null : favFoodId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId == null ? null : foodId.trim();
    }
}