package com.andile.houseofpizza.api.dto.user;

import lombok.Data;

@Data
public class SignInDto {
    private String email;
    private String password;
}
