package com.andile.houseofpizza.persistence.repository;

import com.andile.houseofpizza.persistence.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza,Long> {

}
