package com.andile.houseofpizza.api.service;

import com.andile.houseofpizza.api.dto.ResponseDto;
import com.andile.houseofpizza.api.dto.user.SignInDto;
import com.andile.houseofpizza.api.dto.user.SignInDtoResponseDto;
import com.andile.houseofpizza.api.dto.user.SignUpDto;
import com.andile.houseofpizza.api.dto.user.UserCreateDto;
import com.andile.houseofpizza.infrastructure.common.ResponseStatus;
import com.andile.houseofpizza.infrastructure.common.Role;
import com.andile.houseofpizza.infrastructure.common.excepttion.AuthenticationFailException;
import com.andile.houseofpizza.infrastructure.common.excepttion.CustomException;
import com.andile.houseofpizza.infrastructure.common.utils.Helper;
import com.andile.houseofpizza.infrastructure.config.MessageStrings;
import com.andile.houseofpizza.persistence.model.AuthenticationToken;
import com.andile.houseofpizza.persistence.model.User;
import com.andile.houseofpizza.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.andile.houseofpizza.infrastructure.config.MessageStrings.USER_CREATED;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService service;

    public SignInDtoResponseDto signIn(SignInDto signInDto) throws CustomException {
        //we first find the User by email
        User user = userRepository.findUserByEmail(signInDto.getEmail());

        if (Helper.notNull(user)) {
            throw new AuthenticationFailException("User not present");
        }
        try {
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))){
                //password doesn't match
                throw new AuthenticationFailException(MessageStrings.WRONG_PASSWORD);
            }
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            throw new CustomException(e.getMessage());
        }
        AuthenticationToken token = service.getToken(user);
        if (!Helper.notNull(token)){
            throw new CustomException("token to present");
        }
        return new SignInDtoResponseDto("success",token.getToken());
    }
    public ResponseDto signUp(SignUpDto signUpDto) throws CustomException {

        //We to check to see if the current email address has already been registered
        if (Helper.notNull(userRepository.findUserByEmail(signUpDto.getEmail()))){
            throw new CustomException("User already exists");
        }
        //we must first encrypt the password
        String encryptPassword = signUpDto.getPassword();
        try {
            encryptPassword = hashPassword(signUpDto.getPassword());
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        User user = new User(signUpDto.getFirstName(), signUpDto.getLastName() ,signUpDto.getEmail(),Role.USERS,encryptPassword,signUpDto.getAddress(),signUpDto.getPhoneNumber());

        User createdUser;
        try {
            //save the user in the database
            createdUser = userRepository.save(user);

            //generate token for the user
            final AuthenticationToken authenticationToken = new AuthenticationToken(createdUser);
            //save the token to the database
            service.saveConfirmationToken(authenticationToken);
            //success in the creating
            return new ResponseDto(ResponseStatus.SUCCESS.toString(), USER_CREATED);
        }catch (Exception e){
            //handle the signUp exception
            throw new CustomException(e.getMessage());
        }
    }
    /**
     * Method for creating a user
     * @param token represents the token to be created for the user
     * @param userCreateDto presents the data transfer object to be created
     * @return ResponseDto object
     * @throws CustomException
     * */
    public ResponseDto createUser(String token, UserCreateDto userCreateDto) throws CustomException {

        User creatingUser = service.getUser(token);
        if (!canCrudUser(creatingUser.getRole())){
            throw new AuthenticationFailException(MessageStrings.USER_NOT_PERMITTED);
        }
        //we must first encrypt the password
        String encryptPassword = creatingUser.getPassword();
        try {
            encryptPassword = hashPassword(userCreateDto.getPassword());
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        User user = new User(userCreateDto.getFirstName(), userCreateDto.getLastName() ,userCreateDto.getEmail(),Role.USERS,encryptPassword,userCreateDto.getAddress(),userCreateDto.getPhoneNumber());

        User createdUser;
        try {
            //save the user in the database
            createdUser = userRepository.save(user);

            //generate token for the user
            final AuthenticationToken authenticationToken = new AuthenticationToken(createdUser);
            //save the token to the database
            service.saveConfirmationToken(authenticationToken);
            //success in the creating
            return new ResponseDto(ResponseStatus.SUCCESS.toString(), USER_CREATED);
        }catch (Exception e){
            //handle the signUp exception
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * Method is used to for create a user based on the admin role
     * @param role represents the role enum
     * @return boolean
     **/
    private boolean canCrudUser(Role role) {
        if (role == Role.ADMIN || role == Role.MANAGER){
            return true;
        }
        return false;
    }

    /**
     * Method that handles the hashing of password
     * @param password represents the password entered by user
     * @return String object
     * @throws NoSuchAlgorithmException
     **/
    private String hashPassword(String password) throws NoSuchAlgorithmException{
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(password.getBytes());
        byte[] digest = messageDigest.digest();
        String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();

        return myHash;

    }

}
