package com.andile.houseofpizza.api.dto.user;

import com.andile.houseofpizza.infrastructure.common.Role;
import lombok.Data;

@Data
public class UserCreateDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private Role role;
    private String address;

}
