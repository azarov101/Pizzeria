package com.example.order.dto;

import lombok.Data;
import org.openapitools.model.OrderDetailsExtra;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "pizza_order")
public class OrderDto {
    @Id
    private String id;
    private String name;
    private Double totalPrice;
    private String location;
    private String notes;
    private List<OrderDetailsExtra> items;
}
