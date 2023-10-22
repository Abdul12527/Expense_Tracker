package com.example.expensesTracker.controller;

import com.example.expensesTracker.model.Expenses;
import com.example.expensesTracker.model.dtos.AuthInpDto;
import com.example.expensesTracker.model.dtos.ExpenseReportDto;
import com.example.expensesTracker.model.dtos.ExpensesInpDto;
import com.example.expensesTracker.services.ExpensesServices;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ExpensesController {
    @Autowired
    private ExpensesServices expensesServices;

    @PostMapping("/expense/user")
    public String addExpense(@RequestBody @Valid ExpensesInpDto expensesInpDto, @RequestParam(required = false) LocalDateTime timeStamp){
        return expensesServices.addExpense(expensesInpDto,timeStamp);
    }
    @GetMapping("/expenses")
    private List<Expenses> getAllExpenses(@RequestBody AuthInpDto authInpDto){
        return expensesServices.getAllExpenses(authInpDto);
    }
    @GetMapping("/expense/user/date")
    public ExpenseReportDto getAllExpensesOnDate(@RequestBody AuthInpDto authInpDto, @RequestParam(required = false) LocalDate timeStamp){
        return expensesServices.getAllExpensesOnDate(authInpDto,timeStamp);
    }
    @GetMapping("/expense/user/moth")
    public ExpenseReportDto ExpenseReportForAMonth(@RequestBody AuthInpDto authInpDto, @RequestParam @Range(min = 1,max = 12) Integer moth,@RequestParam @Min(2023) Integer year){
        return expensesServices.ExpenseReportForAMonth(authInpDto,moth,year);
    }

}
