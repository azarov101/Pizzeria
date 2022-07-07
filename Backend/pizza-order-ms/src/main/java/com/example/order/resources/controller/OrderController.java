package com.example.order.resources.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.order.gateways.async.producers.OrderCreatedProducer;
import com.example.order.resources.mapper.OrderResponseMapper;
import com.example.order.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;

import com.example.order.model.external.CreateOrderRequest;
import com.example.order.model.external.CreateOrderResponse;
import com.example.order.model.external.GetAllOrdersResponse;
import com.example.order.model.external.GetOrderDetailsResponse;

@Slf4j
@Controller
public class OrderController {

	protected String collectionName;
	protected MongoTemplate mongoTemplate;
	protected OrderResponseMapper orderResponseMapper;
	protected OrderCreatedProducer orderCreatedProducer;

	protected static final int SLEEP_TIME = 1000;
	protected static Logger logger = LoggerFactory.getLogger(OrderController.class);

	public OrderController(@Value("${spring.data.mongodb.collection-name}")String collectionName, MongoTemplate mongoTemplate,
						   OrderResponseMapper orderResponseMapper, OrderCreatedProducer orderCreatedProducer) {
		this.collectionName = collectionName;
		this.mongoTemplate = mongoTemplate;
		this.orderResponseMapper = orderResponseMapper;
		this.orderCreatedProducer = orderCreatedProducer;
	}
	
	public GetAllOrdersResponse getAllOrders() {
		List<GetOrderDetailsResponse> orderList;

		try {
			logger.debug("Trying to retrieve orders from Database");
			orderList = mongoTemplate.findAll(GetOrderDetailsResponse.class, collectionName);

			if (orderList == null || orderList.isEmpty()) {
				throw new Exception("There are no orders in Database");
			}
			logger.debug("Successfully retrieved {} orders", orderList.size());

		} catch (Exception e) {
			logger.error("Database error when trying to retrieve orders. Error Message: {}", e.getMessage());
			orderList = new ArrayList<>();
		}

		GetAllOrdersResponse response = new GetAllOrdersResponse();
		response.setOrderList(orderList);

		return response;
	}
	
	public GetOrderDetailsResponse getOrderDetails(Long orderId) {
		Query query = new Query();
		query.addCriteria(Criteria.where(Constants.ORDER_ID).is(orderId));
		GetOrderDetailsResponse response;

		try {
			logger.debug("Trying to retrieve order details for {} from Database", orderId.toString());
			response = mongoTemplate.findOne(query, GetOrderDetailsResponse.class, collectionName);

			if (response == null) {
				throw new Exception("There are no details for order:" + orderId.toString() + " in Database");
			}
			logger.debug("Successfully retrieved details for order {}", orderId.toString());

		} catch (Exception e) {
			logger.error("Database error when trying to retrieve order details. Error Message: {}", e.getMessage());
			response = new GetOrderDetailsResponse();
		}

		return response;
	}
	
	public CreateOrderResponse createOrder(CreateOrderRequest request) {
		GetOrderDetailsResponse newOrder = orderResponseMapper.mapOrder(request);
		int counter = 0;
		
		do {
			try {
				logger.debug("Trying to save order to Database");
				mongoTemplate.save(newOrder, collectionName); // save to database
				break;

			} catch(Exception e) {
				logger.error("Failed to save order to Database. Error Message: {}", e.getMessage());
				sleep();

				if (++counter == 3) {
					logger.error("Failed to save order to Database");
					return orderResponseMapper.createOrderResponse(0L, Constants.FAILURE);
				}
				logger.debug("Retry #{}", counter);
			}
		} while (counter < 3);

		// produce order to Delivery MS
		orderCreatedProducer.produce(
				orderCreatedProducer.mapCreateOrderRequestToDelivery(request, newOrder.getOrderId())
		);

		return orderResponseMapper.createOrderResponse(newOrder.getOrderId(), Constants.SUCCESS);
	}

	private void sleep() {
		try {
			Thread.sleep(SLEEP_TIME);
		} catch (InterruptedException ie) {
			logger.error("InterruptedException - Thread exception");
			Thread.currentThread().interrupt();
		}
	}
}
