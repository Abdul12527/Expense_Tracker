package com.example.expensesTracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(unique = true)
    private String email;
    private String number;
    @JsonIgnore
    private String password;

    LocalDateTime creationTimeStamp;
    public User(String name,String email,String number,String password){
        this.name=name;
        this.email=email;
        this.number=number;
        this.password=password;
        creationTimeStamp=LocalDateTime.now();
    }
}
