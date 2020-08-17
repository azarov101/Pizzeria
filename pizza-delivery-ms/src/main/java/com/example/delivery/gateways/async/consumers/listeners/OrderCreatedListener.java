package com.example.delivery.gateways.async.consumers.listeners;

import com.example.delivery.gateways.async.consumers.sinks.IOrderCreatedSink;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public interface OrderCreatedListener {

    @StreamListener(IOrderCreatedSink.CHANNEL_NAME)
    void execute(final Message<String> message);

    void validate(final Message<String> message);
}
