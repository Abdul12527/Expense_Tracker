package com.example.expensesTracker.controller;

import com.example.expensesTracker.model.dtos.AuthInpDto;
import com.example.expensesTracker.model.dtos.LogInDto;
import com.example.expensesTracker.model.dtos.UserRegisterDTO;
import com.example.expensesTracker.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserServices userServices;

    @PostMapping("/user/signup")
    public String addPatient(@RequestBody @Valid UserRegisterDTO userRegisterDTO){ return userServices.addUser(userRegisterDTO); }
    @PostMapping("/user/login")
    public AuthInpDto loginUser(@RequestBody @Valid LogInDto logInDto){
        return userServices.loginUser(logInDto);
    }
    @DeleteMapping("/user/logout")
    public String logOut(@RequestBody @Valid AuthInpDto inpDto){ return userServices.logOut(inpDto); }


}
