package com.example.expensesTracker.services;

import com.example.expensesTracker.model.AuthToken;
import com.example.expensesTracker.model.User;
import com.example.expensesTracker.model.dtos.AuthInpDto;
import com.example.expensesTracker.repositories.IAuthTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenServices {
    @Autowired
    IAuthTokenRepo authTokenRepo;
    public String getAuthToken(User user) {
        AuthToken authToken=authTokenRepo.findByUser(user);
        if(authToken==null) {
            authToken = new AuthToken(user);
            authTokenRepo.save(authToken);
        }
        return authToken.getValue();
    }
    private AuthToken getTokenFromValue(String value){
        return authTokenRepo.findByValue(value);
    }
    boolean verifyRoleAndEmailUserInpDto(User user, AuthInpDto inpDto){
        return user.getEmail().equals(inpDto.getEmail());
    }
    public boolean deleteToken(AuthInpDto inpDto) {
        AuthToken authToken=getTokenFromValue(inpDto.getTokenValue());
        if(verifyRoleAndEmailUserInpDto(authToken.getUser(),inpDto)){
            authTokenRepo.delete(authToken);
            return true;
        }
        return false;
    }

    public User getUserForValidToken(AuthInpDto inpDto) {
       AuthToken  authToken= getTokenFromValue(inpDto.getTokenValue());
        return  authToken.getUser();
    }
}
