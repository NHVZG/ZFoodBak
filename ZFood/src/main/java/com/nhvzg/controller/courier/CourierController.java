package com.nhvzg.controller.courier;

import com.nhvzg.entity.Courier;
import com.nhvzg.result.CourierUserMsg;
import com.nhvzg.service.CourierService;
import com.nhvzg.tools.JsonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by NHVZG on 2018/4/2.
 */
@RestController
@Scope("prototype")
public class CourierController {
    @Autowired
    private CourierService service;


    @PostMapping("/courier/msg")
    public CourierUserMsg getCourierMsgByUser(HttpServletRequest request){
        return service.getCourierByUser((String) request.getAttribute("userId"));
    }
    @PostMapping("/courier/msg/edit")
    public void editCourierMsg(@RequestBody String json) throws IOException {
        CourierUserMsg userMsg= JsonTools.GetObject(json,CourierUserMsg.class);
        service.updateCourierBasicMsg(userMsg);
    }


    @Deprecated
    @PostMapping("/couriers")
    public List<Courier> getCouriers(){
        return service.getAllCouriers();
    }
}
