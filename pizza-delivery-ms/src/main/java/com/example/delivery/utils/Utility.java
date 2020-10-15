package com.example.delivery.utils;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class Utility {
    public static <T> boolean isObjectContainNull(T obj) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            try {
                if (field.get(obj) == null) {
                    return true;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
