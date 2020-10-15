package com.example.delivery.gateways.async.consumers.listeners;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;

@Slf4j
public abstract class AbstractConsumeListener<T> {

    protected static Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class);

    public void handle (final Message<T> message) {
        logger.debug("Message content: {}", message);

        try {
            this.validate(message);
            this.execute(message);

        } catch (Exception e) {
            logger.error("{}", e.getMessage());
            throw e; // TODO maybe remove
        }
    }

    public abstract void execute(final Message<T> message);
    public abstract void validate(final Message<T> message);
}
