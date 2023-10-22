package com.example.expensesTracker.services;

import com.example.expensesTracker.model.Expenses;
import com.example.expensesTracker.model.User;
import com.example.expensesTracker.model.dtos.AuthInpDto;
import com.example.expensesTracker.model.dtos.ExpenseReportDto;
import com.example.expensesTracker.model.dtos.ExpensesInpDto;
import com.example.expensesTracker.repositories.IExpensesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExpensesServices {
    @Autowired
    private IExpensesRepo expensesRepo;
    @Autowired
    private UserServices userServices;

    public String addExpense(ExpensesInpDto expensesInpDto, LocalDateTime timeStamp) {
        User user=userServices.getUserFromAuthDto(expensesInpDto.getAuthInpDto());
        if(userServices.isValidCredentials(expensesInpDto.getAuthInpDto(),user)){
            Expenses expenses=new Expenses(expensesInpDto.getProductName(),expensesInpDto.getDescription(),expensesInpDto.getAmount(),expensesInpDto.getPaymentModes(),user,timeStamp);
            expensesRepo.save(expenses);
            return "expense saved sucessfully";
        }
        return "please login";
    }


    public List<Expenses> getAllExpenses(AuthInpDto authInpDto) {

        User user=userServices.getUserFromAuthDto(authInpDto);
        if(userServices.isValidCredentials(authInpDto,user)){
            return expensesRepo.findAllByUser(user);
        }
        return null;
    }

    public ExpenseReportDto getAllExpensesOnDate(AuthInpDto authInpDto, LocalDate date) {
        User user=userServices.getUserFromAuthDto(authInpDto);
        if(userServices.isValidCredentials(authInpDto,user)){
            if(null == date)date=LocalDate.now();
            return new ExpenseReportDto( date.getMonthValue(),date.getYear(),expensesRepo.findByUserAndCreationTimeStamp(user,date));
        }
        return null;
    }

    public ExpenseReportDto ExpenseReportForAMonth(AuthInpDto authInpDto, Integer moth, Integer year) {
        User user=userServices.getUserFromAuthDto(authInpDto);
        if(userServices.isValidCredentials(authInpDto,user)){
            return new ExpenseReportDto(moth,year,expensesRepo.findAllByUserAndCreationTimeStampYearAndCreationTimeStampMonth(user,year,moth));
        }
        return null;
    }
}
