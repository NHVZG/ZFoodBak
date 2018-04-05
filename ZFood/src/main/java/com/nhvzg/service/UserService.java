package com.nhvzg.service;

import com.github.pagehelper.PageHelper;
import com.nhvzg.dao.UserMapper;
import com.nhvzg.entity.User;
import com.nhvzg.tools.UUIDTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by NHVZG on 2018/2/24.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper mapper;

    public User GetUserByName(String name){
        User user=mapper.getUserByName(name);
        return user;
    }
    public User GetUserById(String userId){
        return mapper.selectByPrimaryKey(userId);
    }

    public boolean checkUserOnly(String name){//检查名字唯一性
        List<User> list=mapper.getAll();
        for(int i=0,j=list.size();i<j;i++){
            User u=list.get(i);
            if(u.getName().equals(name))
                return false;
        }
        return true;
    }

    public void addUser(User user){
        user.setUserId(UUIDTools.getPrimaryKey());
        mapper.insert(user);
    }

    public void updateUser(User user){
        User u=mapper.selectByPrimaryKey(user.getUserId());
        u.setName(user.getName());
        u.setPhone(user.getPhone());
        u.setAddress(user.getAddress());
        mapper.updateByPrimaryKey(u);
    }

    public void updateImg(String userId){
        User user=new User();
        user.setUserId(userId);
        user.setHeadimg("/static/img/user/"+userId+".jpg");
        mapper.updateHeadImg(user);
    }

    @Deprecated
    public List<User> getAll(){
        PageHelper.startPage(1,2);
        return mapper.getAll();
    }

}
