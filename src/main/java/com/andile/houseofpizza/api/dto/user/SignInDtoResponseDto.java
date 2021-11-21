package com.andile.houseofpizza.api.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInDtoResponseDto {
    private String status;
    private String token;
}
