package com.nhvzg.test;

import com.nhvzg.entity.User;
import com.nhvzg.tools.JsonTools;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by NHVZG on 2018/2/28.
 */
@RestController
@CrossOrigin
public class MutiMessageController {
    @PostMapping("/multifile")
    public void Upload(@RequestParam("files") MultipartFile[] files ,@RequestParam("txt")String json) throws IOException {
        System.out.println("~~~@@");
        System.out.println(json);
        Map<String,String> map=JsonTools.GetObject(json,Map.class);
        System.out.println(map.get("a")+" "+map.get("b"));
        String jsonUser=map.get("a");
        User user=JsonTools.GetObject(jsonUser,User.class);
        System.out.println(user.getPassword());

        File filepath1 = new File("E:/img/","1.jpg");
        File filepath2 = new File("E:/img/","2.jpg");
        if (!filepath1.getParentFile().exists()) {
            filepath1.getParentFile().mkdirs();
        }
        if (!filepath2.getParentFile().exists()) {
            filepath2.getParentFile().mkdirs();
        }
        files[0].transferTo(new File("E:/img/" + File.separator + "1.jpg"));
        files[1].transferTo(new File("E:/img/" + File.separator + "2.jpg"));
    }
}
