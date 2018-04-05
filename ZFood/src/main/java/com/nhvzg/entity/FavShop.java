package com.nhvzg.entity;

public class FavShop {
    private String favShopId;

    private String userId;

    private String shopId;

    public String getFavShopId() {
        return favShopId;
    }

    public void setFavShopId(String favShopId) {
        this.favShopId = favShopId == null ? null : favShopId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }
}