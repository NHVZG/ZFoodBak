package com.nhvzg.controller.admin.info;

import com.nhvzg.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by NHVZG on 2018/2/27.
 */
@RestController
@CrossOrigin
public class InfoController {
    @Autowired
    private InfoService infoService;
    @GetMapping("/manage/info")
    public Map<String,List> GetInfo(){
        Map<String,List> map=new HashMap<String,List>();
        map.put("Hot",infoService.getAllHot());
        map.put("Kind",infoService.getAllKind());
        return map;
    }
    @PostMapping("/manage/info/edit")
    public void postInfo(){
    }
}
