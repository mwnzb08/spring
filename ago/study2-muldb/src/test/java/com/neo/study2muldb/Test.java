package com.neo.study2muldb;

import com.neo.study2muldb.model.User;
import com.neo.study2muldb.resp.UserImp;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
    @Autowired
    private UserImp userImp;
    @Autowired
    private JdbcTemplate primaryTemplate;
    @Autowired
    private JdbcTemplate secondaryTemplate;
    @org.junit.Test
    public void saves(){
        userImp.save(new User("momomo","12345678"),primaryTemplate);
        userImp.save(new User("momomo","12345678"),secondaryTemplate);
    }
    @org.junit.Test
    public void bitsave(){
        userImp.batchInsert(primaryTemplate);
    }
    @org.junit.Test
    public void select(){
        System.out.println(userImp.selectAll(primaryTemplate));
    }
}
