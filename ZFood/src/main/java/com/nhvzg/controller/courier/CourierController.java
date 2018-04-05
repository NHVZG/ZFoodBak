package com.nhvzg.controller.courier;

import com.nhvzg.entity.Courier;
import com.nhvzg.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by NHVZG on 2018/4/2.
 */
@RestController
@Scope("prototype")
public class CourierController {
    @Autowired
    private CourierService service;
    @PostMapping("/couriers")
    public List<Courier> getCouriers(){
        return service.getAllCouriers();
    }
}
