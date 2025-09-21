package com.juandavyc.orders.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public enum OrderStatus {
    CREATED,
    PAID,
    CANCELED,
    SHIPPED,
    DELIVERED;
}
