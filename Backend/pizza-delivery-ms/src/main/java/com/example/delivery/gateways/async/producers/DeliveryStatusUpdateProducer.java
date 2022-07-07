package com.example.delivery.gateways.async.producers;

import com.example.delivery.gateways.async.producers.sources.IDeliveryStatusUpdateSource;
import com.example.delivery.persistence.dto.DeliveryDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeliveryStatusUpdateProducer {

    protected IDeliveryStatusUpdateSource deliveryStatusUpdateSource;

    protected static Logger logger = LoggerFactory.getLogger(DeliveryStatusUpdateProducer.class);

    public DeliveryStatusUpdateProducer(IDeliveryStatusUpdateSource deliveryStatusUpdateSource) {
        this.deliveryStatusUpdateSource = deliveryStatusUpdateSource;
    }

    public void produce(final DeliveryDTO delivery) {
        logger.info("Sending async message to channel: {}", IDeliveryStatusUpdateSource.CHANNEL_NAME);

        Message<DeliveryDTO> message = MessageBuilder.withPayload(delivery).build();
        deliveryStatusUpdateSource.output().send(message);

        logger.info("Message sent successfully");
    }
}
