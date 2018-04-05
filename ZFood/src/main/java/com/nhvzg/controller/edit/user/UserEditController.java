package com.nhvzg.controller.edit.user;

import com.nhvzg.entity.User;
import com.nhvzg.service.UserService;
import com.nhvzg.tools.JsonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by NHVZG on 2018/2/26.
 */
@RestController
@CrossOrigin
public class UserEditController {
    @Autowired
    private UserService service;
    @PostMapping("/user/common/edit")
    @ResponseBody
    //name headimg phone address
    public void UserEdit(@RequestBody String json,HttpServletRequest request) throws IOException {
        String userId= (String) request.getAttribute("userId");
        User user=JsonTools.GetObject(json,User.class);
        user.setUserId(userId);
        service.updateUser(user);
    }
    @PostMapping("/user/headImg")
    //@нд╪Ч
    public void FileUpload(MultipartFile file, HttpServletRequest request) throws IOException {
        String userId= (String) request.getAttribute("userId");
        File filepath = new File("E:\\Code\\Work\\ZFoodFront\\ZFoodFront\\static\\img\\user",userId+".jpg");
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        file.transferTo(filepath);
        service.updateImg(userId);
        /*if(filepath.exists()){
            filepath.delete();
        }*/
    }
    @PostMapping("/user/message")
    public User UserMessage(HttpServletRequest request){
        String userId= (String) request.getAttribute("userId");
        return service.GetUserById(userId);
    }
}
