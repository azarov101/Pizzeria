package com.example.delivery.gateways.async.consumers.implementations;

import com.example.delivery.gateways.async.consumers.listeners.OrderCreatedListener;
import com.example.delivery.gateways.async.consumers.sinks.IOrderCreatedSink;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderCreatedConsumerService implements OrderCreatedListener {

    protected static Logger logger = LoggerFactory.getLogger(OrderCreatedConsumerService.class);


    @Override
    public void execute(Message<String> message) {
        logger.debug("Starting process {}...", IOrderCreatedSink.CHANNEL_NAME);

        // TODO add try/catch handle for validation.
        // TODO create model to assign message.getPayload();
        // TODO start countdown for delivery status change
    }

    @Override
    public void validate(Message<String> message) {

    }
}
