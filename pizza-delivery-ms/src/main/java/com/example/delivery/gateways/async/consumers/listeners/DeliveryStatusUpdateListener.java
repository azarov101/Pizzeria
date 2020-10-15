package com.example.delivery.gateways.async.consumers.listeners;

import com.example.delivery.gateways.async.consumers.sinks.IDeliveryStatusUpdateSink;
import com.example.delivery.persistence.dao.DeliveryDAO;
import com.example.delivery.persistence.dto.DeliveryDTO;
import com.example.delivery.utils.Utility;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class DeliveryStatusUpdateListener extends AbstractConsumeListener<DeliveryDTO>{

    protected DeliveryDAO deliveryDAO;

    public DeliveryStatusUpdateListener(DeliveryDAO deliveryDAO) {
        this.deliveryDAO = deliveryDAO;
    }

    @StreamListener(IDeliveryStatusUpdateSink.CHANNEL_NAME)
    public void handleEvent (final Message<DeliveryDTO> message) {
        logger.info("Consumed async message from channel: {}", IDeliveryStatusUpdateSink.CHANNEL_NAME);
        this.handle(message);
    }

    @Override
    public void validate(final Message<DeliveryDTO> message) {
        if (message == null || message.getPayload() == null) {
            throw new NullPointerException("Kafka message is empty");
        }

        if (Utility.isObjectContainNull(message.getPayload())) {
            throw new NullPointerException("Kafka message is missing parameters");
        }
    }

    @Override
    public void execute(final Message<DeliveryDTO> message) {
        deliveryDAO.updateDelivery(message.getPayload());
    }
}
