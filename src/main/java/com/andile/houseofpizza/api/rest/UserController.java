package com.andile.houseofpizza.api.rest;

import com.andile.houseofpizza.api.dto.ResponseDto;
import com.andile.houseofpizza.api.dto.user.SignInDto;
import com.andile.houseofpizza.api.dto.user.SignInDtoResponseDto;
import com.andile.houseofpizza.api.dto.user.SignUpDto;
import com.andile.houseofpizza.api.service.AuthenticationService;
import com.andile.houseofpizza.api.service.UserService;
import com.andile.houseofpizza.infrastructure.common.excepttion.CustomException;
import com.andile.houseofpizza.persistence.model.User;
import com.andile.houseofpizza.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationService service;

    @Autowired
    UserService userService;

    @GetMapping("all")
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }
    @PostMapping("/signUp")
    public ResponseDto signUp(@RequestBody SignUpDto signUpDto) throws CustomException {
        return userService.signUp(signUpDto);
    }
    @PostMapping("/signIn")
    public SignInDtoResponseDto signIn(@RequestBody SignInDto signInDto) throws CustomException {
        return userService.signIn(signInDto);
    }
}
