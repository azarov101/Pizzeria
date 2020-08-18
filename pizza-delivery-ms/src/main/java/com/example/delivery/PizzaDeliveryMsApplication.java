package com.example.delivery;

import com.example.delivery.gateways.async.consumers.sinks.IOrderCreatedSink;
import com.example.delivery.gateways.async.producers.sources.IDeliveryStatusUpdateSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableBinding({IOrderCreatedSink.class, IDeliveryStatusUpdateSource.class})
@EnableDiscoveryClient
@EnableSwagger2
@SpringBootApplication
public class PizzaDeliveryMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzaDeliveryMsApplication.class, args);
	}

}
