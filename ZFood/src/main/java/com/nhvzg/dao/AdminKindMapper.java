package com.nhvzg.dao;

import com.nhvzg.entity.AdminKind;

import java.util.List;

public interface AdminKindMapper {
    int deleteByPrimaryKey(String kindId);

    int insert(AdminKind record);

    int insertSelective(AdminKind record);

    AdminKind selectByPrimaryKey(String kindId);

    int updateByPrimaryKeySelective(AdminKind record);

    int updateByPrimaryKey(AdminKind record);

    List<AdminKind> getAll();
}