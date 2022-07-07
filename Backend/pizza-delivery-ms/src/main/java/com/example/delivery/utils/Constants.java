package com.example.delivery.utils;

public class Constants {

    private Constants(){}

    public static final String ORDER_ID = "orderId";

    public static String database_error(String obj) {
        return "Database error when trying to retrieve " + obj;
    }
}
