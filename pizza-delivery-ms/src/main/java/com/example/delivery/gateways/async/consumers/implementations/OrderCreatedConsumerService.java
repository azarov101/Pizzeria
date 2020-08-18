package com.example.delivery.gateways.async.consumers.implementations;

import com.example.delivery.gateways.async.consumers.listeners.OrderCreatedListener;
import com.example.delivery.gateways.async.consumers.sinks.IOrderCreatedSink;
import com.example.delivery.persistence.dao.DeliveryDAO;
import com.example.delivery.persistence.dto.DeliveryDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;

@Slf4j
@Service
public class OrderCreatedConsumerService implements OrderCreatedListener {

    protected DeliveryDAO deliveryDAO;
    protected static Logger logger = LoggerFactory.getLogger(OrderCreatedConsumerService.class);

    public OrderCreatedConsumerService(DeliveryDAO deliveryDAO) {
        this.deliveryDAO = deliveryDAO;
    }

    @Override
    public void execute(Message<DeliveryDTO> message) {
        logger.debug("Starting process {}...", IOrderCreatedSink.CHANNEL_NAME);

        validate(message);

        deliveryDAO.saveDelivery(message.getPayload());

        // TODO start countdown for delivery status change
    }

    @Override
    public void validate(Message<DeliveryDTO> message) {
        if (message == null || message.getPayload() == null) {
            throw new NullPointerException("Kafka message is empty");
        }

        DeliveryDTO delivery = message.getPayload();

        if (delivery.getOrderId() == null || delivery.getName() == null || delivery.getPrice() == null ||
                delivery.getCreatedDate() == null) {
            throw new NullPointerException("Kafka message is missing parameter");
        }
    }
}
