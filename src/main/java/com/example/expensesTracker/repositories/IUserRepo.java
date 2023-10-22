package com.example.expensesTracker.repositories;

import com.example.expensesTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User,Integer> {
    User findFirstByEmail(String email);
}
