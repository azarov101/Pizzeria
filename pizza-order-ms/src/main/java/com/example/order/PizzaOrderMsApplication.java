package com.example.order;

import com.example.order.gateways.async.producers.sources.IOrderCreatedSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@EnableBinding({IOrderCreatedSource.class})
@EnableSwagger2
@SpringBootApplication
public class PizzaOrderMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzaOrderMsApplication.class, args);
	}

}
