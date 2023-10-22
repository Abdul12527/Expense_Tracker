package com.example.expensesTracker.repositories;

import com.example.expensesTracker.model.AuthToken;
import com.example.expensesTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthTokenRepo extends JpaRepository<AuthToken,Integer> {
    AuthToken findByValue(String value);
    AuthToken findByUser(User user);
}
