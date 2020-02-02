package com.amdocs.menu.resources.services;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.amdocs.menu.resources.services.interfaces.IMenuService;
import com.example.pizzamenu.model.external.ExtraItem;
import com.example.pizzamenu.model.external.GetMenuResponse;
import com.example.pizzamenu.model.external.PizzaItem;

@Service
public class MenuService implements IMenuService {

	protected MongoTemplate mongoTemplate;
	
	public MenuService(MongoTemplate mongoTemplate) {
		super();
		this.mongoTemplate = mongoTemplate;
	}
	
	public GetMenuResponse getMenu() {
		GetMenuResponse response = new GetMenuResponse();

		List<PizzaItem> pizzaList = mongoTemplate.findAll(PizzaItem.class, "pizza_menu_pizzas");
		response.setPizzaList(pizzaList);

		List<ExtraItem> toppingList = mongoTemplate.findAll(ExtraItem.class, "pizza_menu_toppings");
		response.setToppingList(toppingList);

		List<ExtraItem> drinkList = mongoTemplate.findAll(ExtraItem.class, "pizza_menu_drinks");
		response.setDrinkList(drinkList);
		
		return response;
	}
}
