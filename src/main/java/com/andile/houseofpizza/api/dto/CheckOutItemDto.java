package com.andile.houseofpizza.api.dto;

import lombok.Data;

@Data
public class CheckOutItemDto {
    private String pizzaName;
    private Integer id;
    private int quantity;
    private double pizzaId;
    private double price;
}
