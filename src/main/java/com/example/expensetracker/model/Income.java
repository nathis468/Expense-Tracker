package com.example.expensetracker.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
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
    private String userId;

    @Field("date_time")
    private LocalDateTime dateAndTime;
    private String source;
    private BigDecimal amount;

    public Income() {
        this.dateAndTime = LocalDateTime.now();
    }
}
