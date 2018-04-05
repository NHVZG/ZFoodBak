package com.nhvzg.test;

import com.nhvzg.dao.UserMapper;
import com.nhvzg.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by NHVZG on 2018/2/23.
 */
@Service
public class TestLoginService {
    @Autowired
    private UserMapper mapper;
    public String checkPassword(String password){
        if(password.equals(mapper.getUserByName("e6879869Me55eM4d10Mada5M65eb7855dc46").getPassword()))
            return "true";
        return "false";
    }

    public User getUser(String password){
        return mapper.selectByPrimaryKey("e6879869Me55eM4d10Mada5M65eb7855dc46");
    }
}
