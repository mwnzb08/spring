package com.example.consulrongduan.inter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "server-1",contextId = "hello2")
public interface Hello2 {
    @GetMapping(path = "/hello2")
    String gethello2();
}
