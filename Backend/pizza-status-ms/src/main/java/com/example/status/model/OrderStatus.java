package com.example.status.model;

public enum OrderStatus {
    NEW_ORDER,

    IN_PROGRESS,

    READY_FOR_DELIVERY,

    DELIVERED;

    private static final OrderStatus[] vals = values();

    public OrderStatus next() {
        return vals[(this.ordinal() + 1) % vals.length];
    }
}
