package com.example.module1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

@Slf4j
public class ModuleApplicationRunner implements ApplicationRunner {
    private String name;
    public ModuleApplicationRunner(){
       this( "modules");
    }
    public ModuleApplicationRunner(String name){
        this.name=name;
        log.info("special:{}",name);

    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Hello sir welcome");
    }
}
