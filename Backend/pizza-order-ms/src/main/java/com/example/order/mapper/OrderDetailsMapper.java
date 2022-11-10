package com.example.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface OrderDetailsMapper {
    OrderDetails detailsExtraToDetails(OrderDetailsExtra orderDetailsExtra);

    List<PizzaToppings> toppingsExtraToToppings(List<PizzaToppingsExtra> toppings);

    @Mapping(target = "toppings", expression = "java(getToppings(topping.getToppings()))")
    PizzaToppings toppingsExtraToToppings(PizzaToppingsExtra topping);

    List<PizzaDrinks> drinksExtraToDrinks(List<PizzaDrinksExtra> drinks);

    PizzaDrinks drinksExtraToDrinks(PizzaDrinksExtra drink);


    default List<String> getToppings(List<PizzaExtra> toppings) {
        return toppings.stream().map(PizzaExtra::getItem).collect(Collectors.toList());
    }
}
