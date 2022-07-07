package com.example.order.gateways.async.producers.sources;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface IOrderCreatedSource {
    String CHANNEL_NAME = "ORDER_CREATED_OUTPUT_EVENT";

    @Output(IOrderCreatedSource.CHANNEL_NAME)
    MessageChannel output();
}
