package com.example.menu.utils;

public class Constants {

    private Constants(){}

    public static final String PIZZAS = "pizzas";
    public static final String TOPPINGS = "pizza toppings";
    public static final String DRINKS = "pizza drinks";


    /********** Messages **********/
    public static final String TRYING_RETRIEVE_FROM_DB = "Trying to retrieve {} from Database";
    public static final String RETRIEVE_FROM_DB_FAILED = "There are no records in Database";
    public static final String RETRIEVE_FROM_DB_SUCCEEDED = "Successfully retrieved {} from Database";
    public static final String RETRIEVE_FROM_DB_EXCEPTION = "Database error when trying to retrieve {}. Error Message: {}";
}
