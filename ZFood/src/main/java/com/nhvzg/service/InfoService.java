package com.nhvzg.service;

import com.nhvzg.dao.AdminHotMapper;
import com.nhvzg.dao.AdminKindMapper;
import com.nhvzg.entity.AdminHot;
import com.nhvzg.entity.AdminKind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by NHVZG on 2018/2/27.
 */
@Service
public class InfoService {
    @Autowired
    private AdminKindMapper kindMapper;
    @Autowired
    private AdminHotMapper hotMapper;

    public List<AdminKind> getAllKind(){
        return kindMapper.getAll();
    }

    public List<AdminHot> getAllHot(){
        return hotMapper.getAll();
    }


}
