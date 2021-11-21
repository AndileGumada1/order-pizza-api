package com.andile.houseofpizza.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlaceOrderDto {
    private Integer id;
    @NotNull
    private Integer userId;
    @NotNull
    private Double totalPrice;
}
