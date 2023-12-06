package com.rarekickz.rk_inventory_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class RkInventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RkInventoryServiceApplication.class, args);
    }

}
