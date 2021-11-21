package com.andile.houseofpizza.api.dto;

import lombok.Data;

import java.util.List;
@Data
public class CartDto {
    private List<CartItemDto> cartItems;
    private double totalPrice;
}
