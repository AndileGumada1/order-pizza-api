package com.andile.houseofpizza.api.dto.user;

import lombok.Data;

@Data
public class UserUpdateDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
