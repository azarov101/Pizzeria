package com.example.order.gateways.async.producers;

import com.example.order.dto.OrderDto;
import com.example.order.gateways.async.producers.models.Delivery;
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
        Message<Delivery> deliveryMessage = MessageBuilder
                .withPayload(mapCreateOrderRequestToDelivery(orderDto))
                .setHeader(KafkaHeaders.MESSAGE_KEY, orderDto.getId())
                .build();
        streamBridge.send("orderCreated-out-0", deliveryMessage);
        log.info("Sending new order {}", orderDto.getId());
    }


    private Delivery mapCreateOrderRequestToDelivery(OrderDto order) {
        LocalDateTime orderTime = LocalDateTime.now();

        return Delivery.builder()
                .orderId(order.getId())
                .name(order.getName())
                .price(order.getTotalPrice())
                .status("Ordered")
                .createdTime(orderTime)
                .updatedTime(orderTime)
                .build();
    }
}