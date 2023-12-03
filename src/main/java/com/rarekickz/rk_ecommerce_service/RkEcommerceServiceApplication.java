package com.rarekickz.rk_ecommerce_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class RkEcommerceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RkEcommerceServiceApplication.class, args);
    }

}
