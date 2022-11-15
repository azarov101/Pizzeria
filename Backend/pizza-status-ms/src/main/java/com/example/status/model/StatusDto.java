package com.example.status.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "pizza_status")
public class StatusDto {
    @Id
    private String id;
    private String orderId;
    private String status;
}