package com.andile.houseofpizza.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItem extends JpaRepository<OrderItem,Integer> {
}
