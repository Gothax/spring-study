package com.gothaxcity.userservice;

import com.gothaxcity.userservice.error.FeignErrorDecoder;
import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }


    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public FeignErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }

//    @Bean
//    @LoadBalanced
//    public RestTemplate getRestTemplate() {
//        return new RestTemplate();
//    }

}
