package com.wlliu.blog.gateway.oauthgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wlliu
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OauthGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(OauthGatewayApplication.class);
    }
}
