package com.example.eurekaclientfeign.interfaced;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FeignClient(name = "client-1",contextId = "example")
public interface GetHello {
    @GetMapping(path = "/hello")
    String getHeloo();
}
