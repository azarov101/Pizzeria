package com.example.order.gateways.async.producers;

import com.example.order.gateways.async.producers.models.Delivery;
import com.example.order.gateways.async.producers.sources.IOrderCreatedSource;
import com.example.order.model.external.CreateOrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class OrderCreatedProducer {

    protected IOrderCreatedSource orderCreatedSource;
    protected static Logger logger = LoggerFactory.getLogger(OrderCreatedProducer.class);

    public OrderCreatedProducer(IOrderCreatedSource orderCreatedSource) {
        this.orderCreatedSource = orderCreatedSource;
    }

    public void produce(final Delivery delivery) {
        logger.info("Sending async message to channel: {}", IOrderCreatedSource.CHANNEL_NAME);

        Message<Delivery> message = MessageBuilder.withPayload(delivery).build();
        orderCreatedSource.output().send(message);

        logger.info("Message sent successfully");
    }

    public Delivery mapCreateOrderRequestToDelivery(CreateOrderRequest order, Long orderId) {
        Delivery delivery = new Delivery();
        LocalDateTime orderTime = LocalDateTime.now();

        delivery.setOrderId(orderId);
        delivery.setName(order.getName());
        delivery.setPrice(order.getTotalPrice());
        delivery.setStatus("Ordered");
        delivery.setCreatedTime(orderTime);
        delivery.setUpdatedTime(orderTime);

        return delivery;
    }
}