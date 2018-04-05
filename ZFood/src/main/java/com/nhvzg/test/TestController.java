package com.nhvzg.test;

import com.nhvzg.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by NHVZG on 2018/2/23.
 */
@RestController
@SessionAttributes("data")
public class TestController {
    @Autowired
    private TestLoginService service;
    @RequestMapping("/form")
    @CrossOrigin        //跨域请求允许
    public  @ResponseBody  ResponseEntity<User> getForm(String s){
        System.out.println(s);
        return ResponseEntity.ok().body(service.getUser("1"));
    }

    @PostMapping("/forms")
    @CrossOrigin
    public String getForms(String s){
        System.out.println(s);
        return "success";
    }

    @GetMapping("/getSession")
    public String getSession(@SessionAttribute("data")String data){//先访问/session再访问/getSession便可获取Session数据
        System.out.println(data);
        return data;
    }
}
