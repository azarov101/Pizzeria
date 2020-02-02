package com.example.pizzaeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class PizzaEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzaEurekaServerApplication.class, args);
	}

}
