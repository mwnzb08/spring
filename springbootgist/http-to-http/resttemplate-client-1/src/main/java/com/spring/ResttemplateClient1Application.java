package com.spring;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ResttemplateClient1Application {

//    public static void main(String[] args) {
//        SpringApplication.run(ResttemplateClient1Application.class, args);
//    }
    public static void main(String[] args){
      ; new SpringApplicationBuilder()
                .sources(ResttemplateClient1Application.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.NONE)
                .run(args);
    }

}
