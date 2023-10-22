package com.example.expensesTracker.model.dtos;

import com.example.expensesTracker.model.PaymentModes;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpensesInpDto {
    @NotBlank
    private String productName;
    @NotBlank
    private String description;
    @Min(1)
    private Integer amount;
    private PaymentModes paymentModes;
    private AuthInpDto authInpDto;

}
