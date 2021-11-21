package com.andile.houseofpizza.persistence.repository;

import com.andile.houseofpizza.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {



    /**
     * find user by their email address
     * @param email
     * @return User
     */
    User findUserByEmail(String email);

}
