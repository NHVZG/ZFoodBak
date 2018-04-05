package com.nhvzg.dao;

import com.nhvzg.entity.Courier;

import java.util.List;

public interface CourierMapper {
    int deleteByPrimaryKey(String courierId);

    int insert(Courier record);

    int insertSelective(Courier record);

    Courier selectByPrimaryKey(String courierId);

    int updateByPrimaryKeySelective(Courier record);

    int updateByPrimaryKey(Courier record);

    List<Courier> getAll();
}