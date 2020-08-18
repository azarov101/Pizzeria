package com.example.delivery.gateways.async.producers.sources;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface IDeliveryStatusUpdateSource {
    String CHANNEL_NAME = "DELIVERY_STATUS_UPDATE_EVENT";

    @Output(IDeliveryStatusUpdateSource.CHANNEL_NAME)
    MessageChannel output();
}
