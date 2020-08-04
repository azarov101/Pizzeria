package com.example.menu.utils;

public class Constants {

    private Constants(){}

    public static final String PIZZAS = "pizzas";
    public static final String TOPPINGS = "pizza toppings";
    public static final String DRINKS = "pizza drinks";

    public static String tryRetrieveFromDBMessage(String item) {
        return "Trying to retrieve " + item + " from Database";
    }

    public static String failRetrieveFromDBMessage(String item) {
        return "There are no " + item + " in Database";
    }

    public static String successRetrieveFromDBMessage(String item) {
        return "Successfully retrieved " + item + " from Database";
    }

    public static String exceptionRetrieveFromDBMessage(String item) {
        return "Database error when trying to retrieve " + item + ". Error Message: {}";
    }
}
