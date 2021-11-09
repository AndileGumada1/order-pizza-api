package com.andile.houseofpizza.persistence.repository;

import com.andile.houseofpizza.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    /**
     * Method used to find all users
     * @return List
     */
    List<User> findAll();

    /**
     * find user based on their
     * @param email
     * @return
     */
    User findByEmail(String email);

    /**
     * find user by their email address
     * @param email
     * @return User
     */
    User findUserByEmail(String email);
}
