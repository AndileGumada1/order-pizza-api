package com.andile.houseofpizza.persistence.repository;

import com.andile.houseofpizza.persistence.model.AuthenticationToken;
import com.andile.houseofpizza.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken, Integer> {
    /**
     * @param user
     * @return AuthenticationToken
     */
    AuthenticationToken findTokenByUser(User user);

    /**
     * @param token
     * @return AuthenticationToken
     */
    AuthenticationToken findTokenByToken(String token);
}
