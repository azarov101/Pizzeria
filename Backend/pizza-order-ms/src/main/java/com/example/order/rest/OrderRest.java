package com.example.order.rest;

import com.example.order.controller.OrderController;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.CreateOrderApi;
import org.openapitools.api.GetAllOrdersApi;
import org.openapitools.api.GetOrderDetailsApi;
import org.openapitools.model.CreateOrderRequest;
import org.openapitools.model.CreateOrderResponse;
import org.openapitools.model.GetAllOrdersResponse;
import org.openapitools.model.GetOrderDetailsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;


@CrossOrigin(origins = "${cross.origins}")
@RequestMapping("order")
@RequiredArgsConstructor
@RestController
public class OrderRest implements CreateOrderApi, GetOrderDetailsApi, GetAllOrdersApi {

    private final OrderController orderController;

    @Override
    @GetMapping(value = "/getOrderDetails/{orderId}", produces = {"application/json"})
    public ResponseEntity<GetOrderDetailsResponse> getOrder(@PathVariable String orderId) {
        return new ResponseEntity<>(orderController.getOrderDetails(orderId), HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "/getAllOrders", produces = {"application/json"})
    public ResponseEntity<GetAllOrdersResponse> getAllOrders() {
        return new ResponseEntity<>(orderController.getAllOrders(), HttpStatus.OK);
    }

    @Override
    @PostMapping(value = "/createOrder", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<CreateOrderResponse> createOrder(CreateOrderRequest orderRequest) {
        return new ResponseEntity<>(orderController.createOrder(orderRequest), HttpStatus.OK);
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return CreateOrderApi.super.getRequest();
    }
}
