package com.example.resttemplatedomore.Config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import sun.net.www.http.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class RestTemplateConfig {

    @Bean
    public HttpComponentsClientHttpRequestFactory requestFactory(){
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager
                = new PoolingHttpClientConnectionManager(1, TimeUnit.DAYS);
        poolingHttpClientConnectionManager.setMaxTotal(200);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(20);
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(poolingHttpClientConnectionManager)
                .evictIdleConnections(30,TimeUnit.SECONDS)
                .disableAutomaticRetries()
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .build();
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return  builder
                .setConnectTimeout(Duration.ofMillis(30))
                .setReadTimeout(Duration.ofMillis(30))
                .build();
    }
}
