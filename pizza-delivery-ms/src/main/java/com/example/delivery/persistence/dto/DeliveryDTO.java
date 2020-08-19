package com.example.delivery.persistence.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "${spring.data.mongodb.collection-name}")
public class DeliveryDTO {
//    @Id
//    private String deliveryId;
    private Long orderId;
    private String name;
    private Double price;
    private String status;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
