package com.example.delivery.gateways.async.consumers.sinks;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface IDeliveryStatusUpdateSink {

    String CHANNEL_NAME = "DELIVERY_STATUS_UPDATE_INPUT_EVENT";

    @Input(CHANNEL_NAME)
    SubscribableChannel subscribe();
}
