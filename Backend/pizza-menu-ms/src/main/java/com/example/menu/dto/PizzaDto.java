package com.example.menu.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "pizza_menu_pizzas")
public class PizzaDto extends ItemDto {
    private Double discountedPrice;
}
