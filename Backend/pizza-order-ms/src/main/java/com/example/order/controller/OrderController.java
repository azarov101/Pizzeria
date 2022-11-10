package com.example.order.controller;

import com.example.order.dto.OrderDto;
import com.example.order.gateways.async.producers.OrderCreatedProducer;
import com.example.order.mapper.OrderMapper;
import com.example.order.repository.OrderRepository;
import com.example.order.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.CreateOrderRequest;
import org.openapitools.model.CreateOrderResponse;
import org.openapitools.model.GetAllOrdersResponse;
import org.openapitools.model.GetOrderDetailsResponse;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Controller;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Controller
public class OrderController {
    private final OrderCreatedProducer orderCreatedProducer;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;


    public GetAllOrdersResponse getAllOrders() {
        log.debug("Trying to retrieve orders from Database");
        List<OrderDto> orderDtos = orderRepository.findAll();

        GetAllOrdersResponse response = new GetAllOrdersResponse();
        response.setOrderList(orderMapper.dtoToResponse(orderDtos));
        return response;
    }

    public GetOrderDetailsResponse getOrderDetails(String orderId) {
        OrderDto orderDto = orderRepository.findById(orderId)
                .orElseThrow(() -> new ExpressionException("Could not find order"));
        return orderMapper.dtoToResponse(orderDto);
    }

    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        OrderDto orderDto = orderMapper.requestToDto(request);

        log.debug("Trying to save order to Database");
        orderRepository.save(orderDto);

        // produce order to Delivery MS
        orderCreatedProducer.orderCreated(orderDto);

        return orderMapper.dtoToOrderResponse(orderDto, Constants.SUCCESS);
    }
}
