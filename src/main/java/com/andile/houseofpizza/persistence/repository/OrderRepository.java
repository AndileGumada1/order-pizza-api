package com.andile.houseofpizza.persistence.repository;


import com.andile.houseofpizza.persistence.model.Order;
import com.andile.houseofpizza.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderRepository interface
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * @param user
     * @return List of users in the database by order
     */
    List<Order> findAllUserByOrderCreatedDateDesc(User user);
}
