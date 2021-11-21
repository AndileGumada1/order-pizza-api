package com.andile.houseofpizza.api.service;

import com.andile.houseofpizza.api.dto.AddToCartDto;
import com.andile.houseofpizza.infrastructure.common.excepttion.CartItemNotExistException;
import com.andile.houseofpizza.persistence.model.Cart;
import com.andile.houseofpizza.persistence.model.Pizza;
import com.andile.houseofpizza.persistence.model.User;
import com.andile.houseofpizza.persistence.repository.CartRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class CartService {

    private CartRepository cartRepository;

    public void addToCart(AddToCartDto addToCartDto, Pizza pizza, User user) {
        Cart cart = new Cart(pizza, addToCartDto.getQuantity(),user);
        cartRepository.save(cart);
    }
    public void deleteCartItems(int id,int userId) throws CartItemNotExistException {
        if (!cartRepository.existsById(id))
         throw  new CartItemNotExistException("Cart Id is invalid"+ id);
        cartRepository.deleteById(id);
    }
    public void deleteCartItems(int userId) {
        cartRepository.deleteAll();
    }
    public void deleteUserCartItems(User user) {
        cartRepository.deleteByUser(user);
    }
}
