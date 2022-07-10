package com.example.location;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PizzaLocationMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzaLocationMsApplication.class, args);
    }

}
