package com.example.menu.controller;

import com.example.menu.dto.DrinkDto;
import com.example.menu.dto.PizzaDto;
import com.example.menu.dto.ToppingDto;
import com.example.menu.mapper.DrinkMapper;
import com.example.menu.mapper.PizzaMapper;
import com.example.menu.mapper.ToppingMapper;
import com.example.menu.repository.DrinkRepository;
import com.example.menu.repository.PizzaRepository;
import com.example.menu.repository.ToppingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.ExtraItem;
import org.openapitools.model.GetMenuResponse;
import org.openapitools.model.PizzaItem;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

import static com.example.menu.utils.Constants.*;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MenuController {

    private final PizzaRepository pizzaRepository;
    private final ToppingRepository toppingRepository;
    private final DrinkRepository drinkRepository;
    private final PizzaMapper pizzaMapper;
    private final ToppingMapper toppingMapper;
    private final DrinkMapper drinkMapper;


    public GetMenuResponse getMenu() {
        GetMenuResponse response = new GetMenuResponse();

        response.setPizzaList(getPizzas());
        response.setToppingList(getToppings());
        response.setDrinkList(getDrinks());

        return response;
    }

    private List<PizzaItem> getPizzas() {
        List<PizzaDto> pizzaList;

        try {
            log.debug(TRYING_RETRIEVE_FROM_DB, PIZZAS);
            pizzaList = pizzaRepository.findAll();

            if (CollectionUtils.isEmpty(pizzaList)) {
                throw new NotFoundException(RETRIEVE_FROM_DB_FAILED);
            }
            log.debug(RETRIEVE_FROM_DB_SUCCEEDED, PIZZAS);

        } catch (Exception e) {
            log.error(RETRIEVE_FROM_DB_EXCEPTION, PIZZAS, e.getMessage());
            pizzaList = new ArrayList<>();
        }
        return pizzaMapper.sourceToDestination(pizzaList);
    }


    private List<ExtraItem> getToppings() {
        List<ToppingDto> toppingList;

        try {
            log.debug(TRYING_RETRIEVE_FROM_DB, TOPPINGS);
            toppingList = toppingRepository.findAll();

            if (CollectionUtils.isEmpty(toppingList)) {
                throw new NotFoundException(RETRIEVE_FROM_DB_FAILED);
            }
            log.debug(RETRIEVE_FROM_DB_SUCCEEDED, TOPPINGS);

        } catch (Exception e) {
            log.error(RETRIEVE_FROM_DB_EXCEPTION, TOPPINGS, e.getMessage());
            toppingList = new ArrayList<>();
        }
        return toppingMapper.sourceToDestination(toppingList);
    }

    private List<ExtraItem> getDrinks() {
        List<DrinkDto> drinkList;

        try {
            log.debug(TRYING_RETRIEVE_FROM_DB, DRINKS);
            drinkList = drinkRepository.findAll();

            if (CollectionUtils.isEmpty(drinkList)) {
                throw new NotFoundException(RETRIEVE_FROM_DB_FAILED);
            }
            log.debug(RETRIEVE_FROM_DB_SUCCEEDED, DRINKS);

        } catch (Exception e) {
            log.error(RETRIEVE_FROM_DB_EXCEPTION, DRINKS, e.getMessage());
            drinkList = new ArrayList<>();
        }
        return drinkMapper.sourceToDestination(drinkList);
    }
}
