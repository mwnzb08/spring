package com.spring;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyStore;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Slf4j
public class ResttemplateClient1Application {
    @Value("${ssl.nice.store}")
    private KeyStore url;
    @Value("${ssl.pass}")
    private String pwd;
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

    @Bean
    public Jackson2HalModule jackson2HalModule(){
        return new Jackson2HalModule();
    }
    @Bean
    public HttpComponentsClientHttpRequestFactory requestFactory(){
        SSLContext sslContext = null;
        log.info("{}",pwd);
        try{
            sslContext = SSLContextBuilder.create()
//                    .loadKeyMaterial(new KeyStore("classpath:springbucks.p12",""),"spring")
                    .loadKeyMaterial(url,pwd.toCharArray())
                    .build();
        }catch (Exception e){
            log.error("error", e);
        }
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager
                = new PoolingHttpClientConnectionManager(1, TimeUnit.SECONDS);
        poolingHttpClientConnectionManager.setMaxTotal(200);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(20);
        CloseableHttpClient closeableHttpClient = HttpClients.custom()
                .setConnectionManager(poolingHttpClientConnectionManager)
                .evictIdleConnections(30,TimeUnit.SECONDS)
                .disableAutomaticRetries()
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setSSLContext(sslContext)
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory(closeableHttpClient);
        return requestFactory;
    }
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return  builder.setConnectTimeout(Duration.ofMillis(100))
                .setReadTimeout(Duration.ofMillis(100))
                .requestFactory(this::requestFactory)
                .build();
    }

}
