package com.example.order.gateways.async.producers.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Delivery {
//    @Id
//    private String deliveryId;
    private Long orderId;
    private String name;
    private Double price;
    private String status;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
