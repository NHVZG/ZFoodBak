package com.nhvzg.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nhvzg.entity.OrderItem;

import java.util.List;

/**
 * Created by NHVZG on 2018/3/16.
 */
public class OrderMessage {
    //Order
    private String orderId;
    private String userId;
    private String shopId;
    private String foodName;
    private double price;
    private String orderTime;
    private String receiverName;
    private String address;
    private String phone;
    private String remark;
    private String courierId;
    private Integer score;
    private String state;
    private Double sendprice;
    private Double preferential;
    private Integer sendscore;
    //OrderItem
    private List<OrderItem>orderItems;
   /* private String orderItemId;
    private String foodId;
    private String foodname;
    private String unitprice;
    private String num;*/
    //Shop
    private String shopName;
    private String headPic;
    //Comment
    private String comment;
    //Courier
    private String courierName;

    public Integer getSendscore() {
        return sendscore;
    }

    public void setSendscore(Integer sendscore) {
        this.sendscore = sendscore;
    }

    public Double getSendprice() {
        return sendprice;
    }

    public void setSendprice(Double sendprice) {
        this.sendprice = sendprice;
    }

    public Double getPreferential() {
        return preferential;
    }

    public void setPreferential(Double preferential) {
        this.preferential = preferential;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCourierId() {
        return courierId;
    }

    public void setCourierId(String courierId) {
        this.courierId = courierId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getCourierName() {
        return courierName;
    }

    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }
}
