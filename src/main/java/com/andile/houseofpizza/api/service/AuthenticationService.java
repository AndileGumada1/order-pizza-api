package com.andile.houseofpizza.api.service;

import com.andile.houseofpizza.infrastructure.common.excepttion.AuthenticationFailException;
import com.andile.houseofpizza.infrastructure.common.utils.Helper;
import com.andile.houseofpizza.infrastructure.config.MessageStrings;
import com.andile.houseofpizza.persistence.model.AuthenticationToken;
import com.andile.houseofpizza.persistence.model.User;
import com.andile.houseofpizza.persistence.repository.AuthenticationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {

    @Autowired
    AuthenticationTokenRepository authenticationTokenRepository;

    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        authenticationTokenRepository.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
        return authenticationTokenRepository.findTokenByUser(user);
    }

    public User getUser(String token) {

        AuthenticationToken authenticationToken = authenticationTokenRepository.findTokenByToken(token);
        if (Helper.notNull(authenticationToken)) {
            if (Helper.notNull(authenticationToken.getUser())) {
                return authenticationToken.getUser();
            }
        }
        return null;
    }
    public void authenticate(String token) throws AuthenticationFailException {

        if (!Helper.notNull(token)) {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOKEN_NOT_PRESENT);
        }
        if (!Helper.notNull(getUser(token))) {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOKEN_NOT_VALID);
        }
    }
}

