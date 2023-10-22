package com.example.expensesTracker.repositories;

import com.example.expensesTracker.model.Expenses;
import com.example.expensesTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface IExpensesRepo extends JpaRepository<Expenses,Integer> {

    @Query("SELECT e FROM Expenses e WHERE e.user = :user " +
            "AND DATE(e.creationTimeStamp) = :date")
    List<Expenses> findByUserAndCreationTimeStamp(User user, LocalDate date);

    @Query("SELECT e FROM Expenses e WHERE e.user = :user " +
            "AND FUNCTION('YEAR', e.creationTimeStamp) = :year " +
            "AND FUNCTION('MONTH', e.creationTimeStamp) = :month")
    List<Expenses> findAllByUserAndCreationTimeStampYearAndCreationTimeStampMonth(User user, Integer year, Integer month);
    List<Expenses> findAllByUser(User user);
}
