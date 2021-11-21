package com.andile.houseofpizza.persistence.repository;


import com.andile.houseofpizza.persistence.model.Cart;
import com.andile.houseofpizza.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    /**
     * @param user
     * @return
     */
    List<Cart> deleteByUser(User user);
}
