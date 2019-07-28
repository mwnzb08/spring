package com.example.sessionredis;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@SpringBootApplication
@EnableRedisHttpSession
public class SessionRedisApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SessionRedisApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
    @RequestMapping("/hello")
    public String printSession(HttpSession session, String name) {
        String storedName = (String) session.getAttribute("name");
        if (storedName == null) {
            session.setAttribute("name", name);
            storedName = name;
        }
        return "hello " + storedName;
    }
}
