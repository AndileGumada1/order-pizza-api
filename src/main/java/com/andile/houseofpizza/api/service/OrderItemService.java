package com.andile.houseofpizza.api.service;

import com.andile.houseofpizza.persistence.model.OrderItem;
import com.andile.houseofpizza.persistence.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

//    public void addOrderedPizzas(OrderItem orderItem){
//        orderItemRepository.save(orderItem);
//    }
}
