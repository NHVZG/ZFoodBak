package com.nhvzg.entity;

public class CouponItem {
    private String couponItemId;

    private Integer state;

    private String userId;

    private String couponid;

    private String starttime;

    private String endtime;

    public String getCouponItemId() {
        return couponItemId;
    }

    public void setCouponItemId(String couponItemId) {
        this.couponItemId = couponItemId == null ? null : couponItemId.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getCouponid() {
        return couponid;
    }

    public void setCouponid(String couponid) {
        this.couponid = couponid == null ? null : couponid.trim();
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime == null ? null : starttime.trim();
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime == null ? null : endtime.trim();
    }
}