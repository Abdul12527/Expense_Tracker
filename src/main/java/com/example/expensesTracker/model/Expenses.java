package com.example.expensesTracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String productName;
    @NotBlank
    private String description;
    @Min(1)
    private Integer amount;
    @Enumerated(value = EnumType.STRING)
    private PaymentModes paymentModes;
    private LocalDateTime creationTimeStamp;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fkUserId")
    private User user;

    public Expenses(String productName, String description, Integer amount, PaymentModes paymentModes, User user, LocalDateTime timeStamp){
        this.paymentModes=paymentModes;
        this.amount=amount;
        this.productName=productName;
        this.description=description;
        this.creationTimeStamp=timeStamp==null?LocalDateTime.now():timeStamp;
        this.user=user;
    }
}
