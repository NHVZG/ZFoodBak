package com.nhvzg.service;

import com.nhvzg.dao.CourierMapper;
import com.nhvzg.entity.Courier;
import com.nhvzg.result.CourierUserMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by NHVZG on 2018/4/2.
 */
@Service
public class CourierService {
    @Autowired
    private CourierMapper mapper;

    public List<Courier> getAllCouriers(){
        return mapper.getAll();
    }

    public CourierUserMsg getCourierByUser(String userId){
        return mapper.getCourierByUser(userId);
    }

    public void updateCourierBasicMsg(CourierUserMsg msg){
        mapper.updateCourierBasicMsg(msg);
    }
}
