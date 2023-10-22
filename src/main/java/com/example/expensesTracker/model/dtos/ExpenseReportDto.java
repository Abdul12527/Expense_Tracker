package com.example.expensesTracker.model.dtos;

import com.example.expensesTracker.model.Expenses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseReportDto {
    private Integer totalAmount;
    private Integer numberOfPurchases;
    private Integer month;
    private Integer year;
    private List<Expenses> expenses;

    public ExpenseReportDto(Integer month, Integer year, List<Expenses> expenses){
        this.expenses=expenses;
        this.month=month;
        this.year=year;
        totalAmount=expenses.stream().map(Expenses::getAmount).reduce(0,Integer::sum);
        numberOfPurchases=expenses.size();
    }
}
