package com.yukawawebseite.models.order;

public enum OrderStatus {

    PROCESSING("Processing"),
    SHIPPED("Shipped"),
    DELIVERED("Delivered"),
    CANCELED("Canceled"),
    RETURNED("Returned");

    private final String statusName;

    OrderStatus(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return statusName;
    }
}
