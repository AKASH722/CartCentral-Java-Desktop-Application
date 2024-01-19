package com.Records;

import java.sql.Date;

public record Orders(long orderId, long userId, long product , int quantity, String orderNumber, Date orderDate, String orderStatus, Double price) {
}
