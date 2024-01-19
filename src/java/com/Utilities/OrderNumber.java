package com.Utilities;

import com.database.Database;

public class OrderNumber {
    private String orderNumber;

    public OrderNumber() {
        Database database = new Database();
        int count = database.getOrdersCount();
        count++;
        orderNumber = "OR:" + String.format("%06d", count);
    }

    public String getOrderNumber() {
        return orderNumber;
    }
}
