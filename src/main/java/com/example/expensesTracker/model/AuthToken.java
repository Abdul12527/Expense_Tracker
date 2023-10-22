package com.example.expensesTracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AuthToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String value;
    private LocalDateTime creationDateTime;
    private LocalDateTime sessionEndDateTime;
    @OneToOne
    @JoinColumn(name = "fkUserId")
    private User user;
    public AuthToken(User user){
        this.value= UUID.randomUUID().toString();
        this.user=user;
        creationDateTime=LocalDateTime.now();
        sessionEndDateTime=creationDateTime.plusDays(2);
    }
}
