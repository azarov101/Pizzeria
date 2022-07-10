package com.example.location.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "pizza_location")
public class LocationDto {
    @Id
    private String id;
    private String city;
}
