package com.example.consulrongduan.inter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "server-1",contextId = "hello",fallback = Fallbacks.class)
public interface Hello {

    @GetMapping(path = "/hello")
    String gethello();
}
