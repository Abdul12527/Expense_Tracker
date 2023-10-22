package com.example.expensesTracker.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthInpDto {
    private String email;
    private String tokenValue;
    private String message;
}
