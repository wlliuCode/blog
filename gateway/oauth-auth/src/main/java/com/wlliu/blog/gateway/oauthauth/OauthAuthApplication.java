package com.wlliu.blog.gateway.oauthauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OauthAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(OauthAuthApplication.class,args);
    }
}
