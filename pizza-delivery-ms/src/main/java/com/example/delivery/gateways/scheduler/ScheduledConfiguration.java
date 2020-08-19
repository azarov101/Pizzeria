package com.example.delivery.gateways.scheduler;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduledConfiguration {

    // TODO Create enum for statuses
    // TODO Start scheduler per delivery
    // TODO Call DB service to update status and updatedTime for delivery
    // TODO Send Kafka message for update
    // TODO End scheduler countdown when delivery completed
    // TODO Maybe create new MS just for Scheduler? what about DB?

    enum Status {
        PREPARING_DELIVERY, IN_DELIVERY, DELIVERED;
    }

    @Scheduled(cron = "0 * * * * *") // run every minute
    public void scheduleTaskChangeEventStatus() {

    }
}
