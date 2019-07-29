package com.example.configclient;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
@ConfigurationProperties(prefix = "menu")
@Data
public class MenuGetPriceFromApplication {
    private Integer price=100;
    private String name="apple";
}
