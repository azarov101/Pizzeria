package com.example.menu.dto;

import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "pizza_menu_toppings")
public class ToppingDto extends ItemDto {
}
