package com.example.activemq.lister;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Lister {


    @JmsListener(destination = "provider")
    public void get(String msg){
        log.info("msg:{}",msg);
    }

}
