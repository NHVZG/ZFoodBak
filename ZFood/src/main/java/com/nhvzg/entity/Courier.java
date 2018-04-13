package com.nhvzg.entity;

public class Courier {
    private String courierId;

    private String name;

    private String phone;

    private Double income;

    private Double source;

    private Integer state;

    private Integer ratecount;

    private String userId;

    public String getCourierId() {
        return courierId;
    }

    public void setCourierId(String courierId) {
        this.courierId = courierId == null ? null : courierId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getSource() {
        return source;
    }

    public void setSource(Double source) {
        this.source = source;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getRatecount() {
        return ratecount;
    }

    public void setRatecount(Integer ratecount) {
        this.ratecount = ratecount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}