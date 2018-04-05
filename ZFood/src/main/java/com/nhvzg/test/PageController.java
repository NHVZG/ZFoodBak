package com.nhvzg.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by NHVZG on 2018/2/24.
 */
@RestController
@SessionAttributes(value = "data")//session存储数据全局 可以在其他controller(TestController)获取
public class PageController {

    @GetMapping("/session")
    public ModelAndView redirect(Map<String,Object> map){
        map.put("data","kkk");
        return new ModelAndView("redirect:/anotherMethod");
    }
    @GetMapping("/anotherMethod")
    public String anotherMethod(Map<String,Object> map){
        System.out.println(map.toString());
        return "redirect:/pages/final.html";
    }
}
