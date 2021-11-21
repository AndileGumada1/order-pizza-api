package com.andile.houseofpizza.api.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AddToCartDto {
    private Integer id;
    private Integer pizzaId;
    private Integer quantity;
}
