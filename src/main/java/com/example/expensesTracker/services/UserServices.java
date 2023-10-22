package com.example.expensesTracker.services;

import com.example.expensesTracker.model.User;
import com.example.expensesTracker.model.dtos.AuthInpDto;
import com.example.expensesTracker.model.dtos.LogInDto;
import com.example.expensesTracker.model.dtos.UserRegisterDTO;
import com.example.expensesTracker.repositories.IUserRepo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    @Autowired
    IUserRepo userRepo;
    @Autowired
    private AuthTokenServices authTokenServices;
    @SneakyThrows
    public String addUser(UserRegisterDTO userRegisterDTO) {
        String enPass=PasswordEncryptor.encrypt(userRegisterDTO.getPassword());
        User user =new User(userRegisterDTO.getName(),userRegisterDTO.getEmail(),userRegisterDTO.getNumber(),enPass);
        userRepo.save(user);
        return "registered sucessfully";
    }
    @SneakyThrows
    public AuthInpDto loginUser(LogInDto logInDto) {
        User user=userRepo.findFirstByEmail(logInDto.getEmail());
        AuthInpDto authInpDto=new AuthInpDto();
        if(user==null){
            authInpDto.setMessage("wrong credentials");
            return authInpDto;
        }

        if(!PasswordEncryptor.encrypt(logInDto.getPassword()).equals(user.getPassword())){
            authInpDto.setMessage("wrong credentials");
            return authInpDto;
        }

        authInpDto.setMessage("loggedIn successfully");
        authInpDto.setEmail(user.getEmail());
        authInpDto.setTokenValue(authTokenServices.getAuthToken(user));
        return authInpDto;
    }

    public String logOut(AuthInpDto inpDto) {
        if( authTokenServices.deleteToken(inpDto))return "logged out sucessfully";
        return "your not authorized";
    }
    public User getUserFromAuthDto(AuthInpDto authInpDto){
        return  authTokenServices.getUserForValidToken(authInpDto);
    }
    public boolean isValidCredentials(AuthInpDto authInpDto){
        User user=getUserFromAuthDto(authInpDto);
        return user!=null&&user.getEmail().equals(authInpDto.getEmail());
    }

    public boolean isValidCredentials(AuthInpDto authInpDto,User user) {
        return user!=null&&user.getEmail().equals(authInpDto.getEmail());
    }
}
