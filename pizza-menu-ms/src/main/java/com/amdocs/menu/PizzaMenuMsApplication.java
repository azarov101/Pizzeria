package com.amdocs.menu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@EnableSwagger2
@SpringBootApplication
public class PizzaMenuMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzaMenuMsApplication.class, args);
	}

}
