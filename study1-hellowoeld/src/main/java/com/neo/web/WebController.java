package com.neo.web;

import com.neo.database.simple.SimpleImp;
import com.neo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    @Autowired
    private SimpleImp simpleImp;
    @RequestMapping("hello")
    public String hello(){
        simpleImp.save(new User("mowe1","333444"));
        return "hello";
    }
}
