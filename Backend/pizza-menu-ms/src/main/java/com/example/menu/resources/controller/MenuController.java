package com.example.menu.resources.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.menu.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;

import com.example.pizzamenu.model.external.ExtraItem;
import com.example.pizzamenu.model.external.GetMenuResponse;
import com.example.pizzamenu.model.external.PizzaItem;

@Slf4j
@Controller
public class MenuController {

	protected MongoTemplate mongoTemplate;
	protected String pizzasCollectionName;
	protected String toppingsCollectionName;
	protected String drinksCollectionName;

	protected static Logger logger = LoggerFactory.getLogger(MenuController.class);

	public MenuController(@Value("${spring.data.mongodb.pizzas-collection-name}")String pizzasCollectionName,
						  @Value("${spring.data.mongodb.toppings-collection-name}")String toppingsCollectionName,
						  @Value("${spring.data.mongodb.drinks-collection-name}")String drinksCollectionName,
						  MongoTemplate mongoTemplate) {
		this.pizzasCollectionName = pizzasCollectionName;
		this.toppingsCollectionName = toppingsCollectionName;
		this.drinksCollectionName = drinksCollectionName;
		this.mongoTemplate = mongoTemplate;
	}
	
	public GetMenuResponse getMenu() {
		GetMenuResponse response = new GetMenuResponse();

		response.setPizzaList(getPizzas());
		response.setToppingList(getToppings());
		response.setDrinkList(getDrinks());

		return response;
	}

	private List<ExtraItem> getToppings() {
		return getExtraItems(toppingsCollectionName, Constants.TOPPINGS);
	}

	private List<ExtraItem> getDrinks() {
		return getExtraItems(drinksCollectionName, Constants.DRINKS);
	}

	private List<PizzaItem> getPizzas() {
		List<PizzaItem> pizzaList;

		try {
			logger.debug(Constants.tryRetrieveFromDBMessage(Constants.PIZZAS));
			pizzaList = mongoTemplate.findAll(PizzaItem.class, pizzasCollectionName);

			if (pizzaList == null || pizzaList.isEmpty()) {
				throw new Exception(Constants.failRetrieveFromDBMessage(Constants.PIZZAS));
			}
			logger.debug(Constants.successRetrieveFromDBMessage(Constants.PIZZAS));

		} catch (Exception e) {
			logger.error(Constants.exceptionRetrieveFromDBMessage(Constants.PIZZAS), e.getMessage());
			pizzaList = new ArrayList<>();
		}
		return pizzaList;
	}

	private List<ExtraItem> getExtraItems(String collectionName, String item) {
		List<ExtraItem> extraList;

		try {
			logger.debug(Constants.tryRetrieveFromDBMessage(item));
			extraList = mongoTemplate.findAll(ExtraItem.class, collectionName);

			if (extraList == null || extraList.isEmpty()) {
				throw new Exception(Constants.failRetrieveFromDBMessage(item));
			}
			logger.debug(Constants.successRetrieveFromDBMessage(item));

		} catch (Exception e) {
			logger.error(Constants.exceptionRetrieveFromDBMessage(item), e.getMessage());
			extraList = new ArrayList<>();
		}
		return extraList;
	}
}
