package com.example.order.resources.mapper;

import com.example.order.model.external.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class OrderResponseMapper {

    protected Random rand; // generate random order id

    public OrderResponseMapper() {
        this.rand = new Random();
    }

    public CreateOrderResponse createOrderResponse(Long id, String status) {
        return new CreateOrderResponse() {{
            setOrderId(id);
            setRequestStatus(status);
        }};
    }

    /**
     * This method map the request order before inserting it to database.
     * @param request - the request order.
     * @return the new mapped order.
     */
    public GetOrderDetailsResponse mapOrder(CreateOrderRequest request) {
        GetOrderDetailsResponse order = new GetOrderDetailsResponse(); // create new list

        order.setName(request.getName());
        order.setTotalPrice(request.getTotalPrice());
        order.setLocation(request.getLocation());
        order.setNotes(request.getNotes());

        List<OrderDetails> orderDetailsList = new ArrayList<>();

        for (OrderDetailsExtra requestItem: request.getItems()) {
            OrderDetails orderDetails = new OrderDetails(); // create new orderDetails

            orderDetails.setPizzaDescription(requestItem.getPizzaDescription());
            orderDetails.setNumberOfPizzas(requestItem.getNumberOfPizzas());
            orderDetails.setOrderPrice(requestItem.getOrderPrice());
            orderDetails.setToppings(mapToppings(requestItem.getToppings()));
            orderDetails.setDrinks(mapDrinks(requestItem.getDrinks()));

            orderDetailsList.add(orderDetails); // add object to list
        }

        order.setItems(orderDetailsList);

        Long orderId = GenerateRandomNumber();
        order.setOrderId(orderId);

        return order;
    }

    /**
     * This method map the extended toppings list to simplified toppings list (remove extra data before saving to DB).
     * PizzaToppingsExtra object may contain: int pizzaNumber, PizzaExtra toppings.
     * @param otherList - toppings list.
     * @return PizzaToppings list of toppings.
     */
    private List<PizzaToppings> mapToppings(List<PizzaToppingsExtra> otherList) {
        List<PizzaToppings> toppingsList = new ArrayList<>(); // create new list

        for (PizzaToppingsExtra other: otherList) {
            PizzaToppings toppings = new PizzaToppings();

            toppings.setPizzaNumber(other.getPizzaNumber());
            toppings.setToppings(mapExtra(other.getToppings()));

            toppingsList.add(toppings); // add object to list
        }

        return toppingsList;
    }

    /**
     * This method map toppings from List of PizzaExtra to List of Strings.
     * PizzaExtra Object may contain: String item, Double price, Double discountedPrice, String image.
     * @param extraList - toppings list.
     * @return toppings list of strings.
     */
    private List<String> mapExtra(List<PizzaExtra> extraList) {
        List<String> list = new ArrayList<>();

        for (PizzaExtra extra: extraList) {
            list.add(extra.getItem());
        }
        return list;
    }

    /**
     * This method map drinks from List of PizzaDrinksExtra to List of PizzaDrinks.
     * PizzaDrinksExtra Object may contain: String item, Double price, Double discountedPrice, String image, int amount.
     * @param otherList - drinks list.
     * @return drink list of PizzaDrinks object. Each object contains: String item, int amount.
     */
    private List<PizzaDrinks> mapDrinks(List<PizzaDrinksExtra> otherList) {
        List<PizzaDrinks> list = new ArrayList<>();

        for (PizzaDrinksExtra other: otherList) {
            PizzaDrinks drink = new PizzaDrinks();

            drink.setItem(other.getItem());
            drink.setAmount(other.getAmount());

            list.add(drink);
        }
        return list;
    }

    private Long GenerateRandomNumber() {
        int random = rand.nextInt(Integer.MAX_VALUE);
        return Long.valueOf(random);
    }
}
