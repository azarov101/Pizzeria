package com.amdocs.order.resources.services.interfaces;

import com.example.order.model.external.CreateOrderRequest;
import com.example.order.model.external.CreateOrderResponse;
import com.example.order.model.external.GetAllOrdersResponse;
import com.example.order.model.external.GetOrderDetailsResponse;

public interface IOrderService {
	public GetAllOrdersResponse getAllOrders();
	public GetOrderDetailsResponse getOrderDetails(Long orderId);
	public CreateOrderResponse createOrder(CreateOrderRequest request);
}
