//package com.example.addmaven;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.env.EnvironmentPostProcessor;
//import org.springframework.boot.env.PropertiesPropertySourceLoader;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.ConfigurableEnvironment;
//import org.springframework.core.env.MutablePropertySources;
//import org.springframework.core.env.PropertySource;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//
//@Slf4j
//@Configuration
//public class AnotherConfig implements EnvironmentPostProcessor {
//    private PropertiesPropertySourceLoader loader = new PropertiesPropertySourceLoader();
//    @Override
//    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
//        MutablePropertySources propertySources = environment.getPropertySources();
//        Resource resource = new ClassPathResource("another.properties");
//        try {
//            PropertySource ps = loader.load("AddMavenApplication",resource).get(0);
//            propertySources.addFirst(ps);
//        }catch (Exception e){
//            log.error("",e);
//        }
//
//    }
//
//
//}
