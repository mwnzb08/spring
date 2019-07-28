package com.example.consulrongduan.inter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Fallbacks implements Hello {
    @Override
    public String gethello() {
        log.warn("call");
        return null;
    }
}
