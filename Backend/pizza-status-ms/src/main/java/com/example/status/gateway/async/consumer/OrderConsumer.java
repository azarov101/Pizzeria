package com.example.status.gateway.async.consumer;

import com.example.status.mapper.StatusMapper;
import com.example.status.model.OrderStatus;
import com.example.status.model.OrderStatusMessage;
import com.example.status.service.StatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderConsumer {

    private final StreamBridge streamBridge;
    private final StatusService statusService;
    private final StatusMapper statusMapper;

    @Bean
    public Consumer<Message<OrderStatusMessage>> processOrder() {
        return value -> {
            OrderStatusMessage updatedOrder = updateOrderStatusMessage(value.getPayload());
            statusService.updateOrderStatus(statusMapper.messageToDto(updatedOrder));

            if (!OrderStatus.DELIVERED.equals(updatedOrder.getStatus())) {
                Message<OrderStatusMessage> updatedOrderStatusMessage = MessageBuilder
                        .withPayload(updatedOrder)
                        .setHeader(KafkaHeaders.MESSAGE_KEY, value.getPayload().getOrderId())
                        .build();

                streamBridge.send("orderUpdated-out-0", updatedOrderStatusMessage);
                log.info("Sent updated order message with order-id {}", updatedOrder.getOrderId());
            }
        };
    }


    private OrderStatusMessage updateOrderStatusMessage(OrderStatusMessage orderStatusMessage) {
        orderStatusMessage.setStatus(orderStatusMessage.getStatus().next());
        orderStatusMessage.setUpdatedTime(LocalDateTime.now());
        return orderStatusMessage;
    }
}
