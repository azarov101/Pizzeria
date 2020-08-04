package com.example.order.resources.rest;

import com.example.order.resources.controller.OrderController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.order.api.external.CreateOrderApi;
import com.example.order.api.external.GetAllOrdersApi;
import com.example.order.api.external.GetOrderDetailsApi;
import com.example.order.model.external.CreateOrderRequest;
import com.example.order.model.external.CreateOrderResponse;
import com.example.order.model.external.GetAllOrdersResponse;
import com.example.order.model.external.GetOrderDetailsResponse;

@RestController
@CrossOrigin(origins = "${cros.origins}")
@RequestMapping("order")
public class OrderRest implements CreateOrderApi, GetOrderDetailsApi, GetAllOrdersApi {
	
	protected OrderController orderController;
	
	public OrderRest(OrderController orderController) {
		this.orderController = orderController;
	}

	@Override
	public ResponseEntity<GetOrderDetailsResponse> getOrderDetailsOrderIdGet(@PathVariable Long orderId) {
		return new ResponseEntity<>(orderController.getOrderDetails(orderId), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CreateOrderResponse> createOrder(CreateOrderRequest body) {
		return new ResponseEntity<>(orderController.createOrder(body), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<GetAllOrdersResponse> getAllOrdersGet() {
		return new ResponseEntity<>(orderController.getAllOrders(), HttpStatus.OK);
	}
}
