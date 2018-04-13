package com.nhvzg.dao;

import com.nhvzg.entity.Courier;
import com.nhvzg.result.CourierUserMsg;

import java.util.List;

public interface CourierMapper {
    int deleteByPrimaryKey(String courierId);

    int insert(Courier record);

    int insertSelective(Courier record);

    Courier selectByPrimaryKey(String courierId);

    int updateByPrimaryKeySelective(Courier record);

    int updateByPrimaryKey(Courier record);

    List getAll();

    CourierUserMsg getCourierByUser(String userId);

    void updateCourierBasicMsg(CourierUserMsg msg);
}