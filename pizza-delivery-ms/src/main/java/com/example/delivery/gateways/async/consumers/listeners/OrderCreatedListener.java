package com.example.delivery.gateways.async.consumers.listeners;

import com.example.delivery.gateways.async.consumers.sinks.IOrderCreatedSink;
import com.example.delivery.gateways.scheduler.DeliveryUpdaterTask;
import com.example.delivery.persistence.dao.DeliveryDAO;
import com.example.delivery.persistence.dto.DeliveryDTO;
import com.example.delivery.utils.Utility;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.inject.Provider;
import java.util.Timer;

@Component
public class OrderCreatedListener extends AbstractConsumeListener<DeliveryDTO> {

    protected final long delayBeforeExecute;
    protected final long periodBetweenExecute;
    protected DeliveryDAO deliveryDAO;
    protected Provider<DeliveryUpdaterTask> deliveryUpdaterTaskProvider;

    public OrderCreatedListener(@Value("${pizza-delivery-ms.scheduler.delay}") long delayBeforeExecute,
                                @Value("${pizza-delivery-ms.scheduler.period}") long periodBetweenExecute,
                                DeliveryDAO deliveryDAO, Provider<DeliveryUpdaterTask> deliveryUpdaterTaskProvider) {
        this.delayBeforeExecute = delayBeforeExecute;
        this.periodBetweenExecute = periodBetweenExecute;
        this.deliveryDAO = deliveryDAO;
        this.deliveryUpdaterTaskProvider = deliveryUpdaterTaskProvider;
    }

    @StreamListener(IOrderCreatedSink.CHANNEL_NAME)
    public void handleEvent (final Message<DeliveryDTO> message) {
        logger.info("Consumed async message from channel: {}", IOrderCreatedSink.CHANNEL_NAME);
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
        DeliveryDTO delivery = message.getPayload();
        deliveryDAO.saveDelivery(delivery);

        DeliveryUpdaterTask deliveryUpdaterTask = deliveryUpdaterTaskProvider.get();
        deliveryUpdaterTask.setDelivery(delivery);

        Timer timer = new Timer();
        timer.schedule(deliveryUpdaterTask, delayBeforeExecute, periodBetweenExecute);
    }
}
