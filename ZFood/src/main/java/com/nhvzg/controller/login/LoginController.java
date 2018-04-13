package com.nhvzg.controller.login;

import com.nhvzg.entity.User;
import com.nhvzg.service.CourierService;
import com.nhvzg.service.ShopService;
import com.nhvzg.service.UserService;
import com.nhvzg.tools.JsonTools;
import com.nhvzg.tools.TokenTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by NHVZG on 2018/2/23.
 */
@RestController
@CrossOrigin
@SessionAttributes(value = {"us"})
public class LoginController {
    @Autowired
    private UserService service;
    @Autowired
    private ShopService shopService;
    @Autowired
    private CourierService courierService;
    @PostMapping("/login/in")
    //json提供name password
    public String login(HttpServletResponse httpServletResponse, @RequestBody String json, Model model, HttpSession session) throws IOException {
        User user= JsonTools.GetObject(json,User.class);
        Map<String,Object> map=new HashMap<String,Object>();
        Map<String,Object> responseHeader=new HashMap<String,Object>();
        User u=service.GetUserByName(user.getName());
        System.out.println(json);
        if(u!=null&&u.getPassword().equals(user.getPassword())){//有此用户且密码一样
            //token生成
            responseHeader.put("userId",u.getUserId());
            responseHeader.put("headImg",u.getHeadimg());
            responseHeader.put("state",u.getState());
            map.put("user",responseHeader);
            String token=TokenTools.generToken(map,1296000000);//十五天超时
            httpServletResponse.setHeader("token",token);

            //httpServletResponse.setHeader("maxAge","1296000000");
            if(u.getState().equals("1")) {//商家
                httpServletResponse.setCharacterEncoding("UTF-8");
                OutputStream out = httpServletResponse.getOutputStream();
                String jsonS=JsonTools.GetJson(shopService.getShopByUser(u.getUserId()));
                out.write(jsonS.getBytes("UTF-8"));
                out.flush();
                //String jsonS=JsonTools.GetJson(shopService.getShopByUser(u.getUserId()));
                //return jsonS;
            }else if(u.getState().equals("2")){//配送员
                httpServletResponse.setCharacterEncoding("UTF-8");
                OutputStream out = httpServletResponse.getOutputStream();
                String jsonS=JsonTools.GetJson(courierService.getCourierByUser(u.getUserId()));
                out.write(jsonS.getBytes("UTF-8"));
                out.flush();
            }
            model.addAttribute("us", u);
            return "success";
        }
        return "fail";
    }
    @PostMapping("/login/regist")
    @ResponseBody
    //json提供name password state  0 消费者 1 商家 2配送员 3管理员
    public String regist(HttpServletResponse httpServletResponse, @RequestBody String json,Model model) throws IOException {
        User user= JsonTools.GetObject(json,User.class);
        Map map=new HashMap();
        if(service.checkUserOnly(user.getName())){
            service.addUser(user);
            String token=TokenTools.generToken(map,1296000000);
            httpServletResponse.setHeader("token",token);
            model.addAttribute("us", user);
            return "success";
        }
        return "fail";
    }

}
