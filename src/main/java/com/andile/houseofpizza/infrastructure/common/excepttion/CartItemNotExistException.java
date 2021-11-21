package com.andile.houseofpizza.infrastructure.common.excepttion;

public class CartItemNotExistException extends IllegalArgumentException {

    public CartItemNotExistException(String s) {
        super(s);
    }
}
