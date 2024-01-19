package com.example.expensetracker.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;

@Document(collection = "income")
@Data
@AllArgsConstructor
public class Income {
    @Id
    private String id;

    @Field("user_id")
    @DocumentReference(collection = "users")
    private User userId;

    @Field("date_time")
    private LocalDateTime dateAndTime;
    private String source;
    private BigDecimal amount;

    @Field("previous_balance")
    private BigDecimal previousBalance;

    @Field("current_balance")
    private BigDecimal currentBalance;

    public Income() {
        this.dateAndTime = LocalDateTime.now();
    }
}
