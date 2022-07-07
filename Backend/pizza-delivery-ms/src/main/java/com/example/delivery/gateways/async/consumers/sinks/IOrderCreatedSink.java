package com.example.delivery.gateways.async.consumers.sinks;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface IOrderCreatedSink {
    String CHANNEL_NAME = "ORDER_CREATED_INPUT_EVENT";

    @Input(CHANNEL_NAME)
    SubscribableChannel subscribe();
}
