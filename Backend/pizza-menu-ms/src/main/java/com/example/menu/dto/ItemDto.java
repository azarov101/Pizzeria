package com.example.menu.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public abstract class ItemDto {
    @Id
    private String id;
    private String item;
    private Double price;
    private String image;
}
