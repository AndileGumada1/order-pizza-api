package com.andile.houseofpizza.api.dto;

import com.andile.houseofpizza.persistence.model.Category;
import com.andile.houseofpizza.persistence.model.Pizza;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
public class PizzaDto {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private Double price;
    @NotNull
    private String imageUrl;
    @NotNull
    private Category categoryId;

    public PizzaDto(Pizza pizza) {
        this.setId(pizza.getId());
        this.setName(pizza.getName());
        this.setDescription(pizza.getDescription());
        this.setPrice(pizza.getPrice());
        this.setCategoryId(pizza.getCategory());

    }
}
