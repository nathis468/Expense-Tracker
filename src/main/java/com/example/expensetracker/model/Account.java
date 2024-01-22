package com.example.expensetracker.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    private String id;

    @Field("account_number")
    private String accountNumber;

    @Field("account_holder_name")
    private String accountHolderName;

    @Field("account_type")
    private String accountType;

    @Field("bank_name")
    private String bankName;

    @Field("total_balance")
    private BigDecimal balance;

    @Field("transaction_type")
    private List<Transaction> transaction;
}
