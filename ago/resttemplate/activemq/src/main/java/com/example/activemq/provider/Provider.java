package com.example.activemq.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

@Component
public class Provider {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;
    public void send(String msg){
        this.jmsMessagingTemplate.convertAndSend(this.queue,msg);
    }
}
