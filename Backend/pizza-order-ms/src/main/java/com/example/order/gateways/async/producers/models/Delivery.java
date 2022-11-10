package com.example.order.gateways.async.producers.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
//    @Id
//    private String id;
    private String orderId;
    private String name;
    private Double price;
    private String status;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
