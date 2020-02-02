package com.amdocs.order.resources.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.order.resources.services.interfaces.IOrderService;
import com.example.order.api.external.CreateOrderApi;
import com.example.order.api.external.GetAllOrdersApi;
import com.example.order.api.external.GetOrderDetailsApi;
import com.example.order.model.external.CreateOrderRequest;
import com.example.order.model.external.CreateOrderResponse;
import com.example.order.model.external.GetAllOrdersResponse;
import com.example.order.model.external.GetOrderDetailsResponse;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("order")
public class OrderRest implements CreateOrderApi, GetOrderDetailsApi, GetAllOrdersApi {
	
	protected IOrderService orderService;
	
	public OrderRest(IOrderService orderService) {
		super();
		this.orderService = orderService;
	}

	@Override
	@GetMapping("/getOrderDetails/{orderId}")
	public ResponseEntity<GetOrderDetailsResponse> getOrderDetailsOrderIdGet(@PathVariable Long orderId) {
		return new ResponseEntity<>(orderService.getOrderDetails(orderId), HttpStatus.OK);
	}

	@Override
	@PostMapping(path = "/createOrder", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CreateOrderResponse> createOrder(CreateOrderRequest body) {
		return new ResponseEntity<>(orderService.createOrder(body), HttpStatus.OK);
	}

	@Override
	@GetMapping("/getAllOrders")
	public ResponseEntity<GetAllOrdersResponse> getAllOrdersGet() {
		return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
	}
}
