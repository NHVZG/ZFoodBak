package com.nhvzg.dao;

import com.nhvzg.entity.AdminHot;

import java.util.List;

public interface AdminHotMapper {
    int deleteByPrimaryKey(String topicId);

    int insert(AdminHot record);

    int insertSelective(AdminHot record);

    AdminHot selectByPrimaryKey(String topicId);

    int updateByPrimaryKeySelective(AdminHot record);

    int updateByPrimaryKey(AdminHot record);

    List<AdminHot> getAll();
}