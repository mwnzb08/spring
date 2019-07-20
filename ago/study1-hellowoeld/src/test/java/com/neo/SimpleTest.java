package com.neo;

import com.neo.database.simple.SimpleImp;
import com.neo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleTest {
    @Autowired
    private SimpleImp simpleImp;
    @Test
    public void savemessage(){
        simpleImp.save(new User("mo","123456"));
    }
    @Test
    public void find(){
        User user  = simpleImp.selectById(1);
        System.out.println(user.toString());
    }
    @Test
    public void findtwo(){
        List<User> users = simpleImp.selectAll();
        for(User user:users){
            System.out.println(user.toString());
        }
    }
    @Test
    public void update(){
        simpleImp.update(new User("momo","12345678"));
    }
}
