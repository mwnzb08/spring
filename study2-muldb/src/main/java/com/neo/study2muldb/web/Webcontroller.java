package com.neo.study2muldb.web;

import com.neo.study2muldb.model.User;
import com.neo.study2muldb.resp.UserImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Webcontroller {
    @Autowired
    private UserImp userImp;
    @Autowired
    private JdbcTemplate primaryTemplate;
    @Autowired
    private JdbcTemplate secondaryTemplate;
    @RequestMapping("/hello")
    public String save() {
        userImp.save(new User("momomo", "12345678"), primaryTemplate);
        userImp.save(new User("momomo", "12345678"), secondaryTemplate);
        return "ok";
    }
}
