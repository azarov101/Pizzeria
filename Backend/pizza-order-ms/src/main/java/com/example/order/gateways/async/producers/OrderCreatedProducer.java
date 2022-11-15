package com.example.order.gateways.async.producers;

import com.example.order.dto.OrderDto;
import com.example.order.gateways.async.producers.models.OrderStatus;
import com.example.order.gateways.async.producers.models.OrderStatusMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderCreatedProducer {

    private final StreamBridge streamBridge;

    public void orderCreated(OrderDto orderDto) {
        Message<OrderStatusMessage> deliveryMessage = MessageBuilder
                .withPayload(mapCreateOrderRequestToDelivery(orderDto))
                .setHeader(KafkaHeaders.MESSAGE_KEY, orderDto.getId())
                .build();
        streamBridge.send("orderCreated-out-0", deliveryMessage);
        log.info("Sending new order {}", orderDto.getId());
    }


    private OrderStatusMessage mapCreateOrderRequestToDelivery(OrderDto order) {
        LocalDateTime orderTime = LocalDateTime.now();

        return OrderStatusMessage.builder()
                .orderId(order.getId())
                .name(order.getName())
                .price(order.getTotalPrice())
                .status(OrderStatus.NEW_ORDER)
                .createdTime(orderTime)
                .updatedTime(orderTime)
                .build();
    }
}