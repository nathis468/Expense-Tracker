package com.example.expensetracker.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.expensetracker.model.Expense;

@Repository
public class ExpenseDAO {
    @Autowired
    MongoTemplate template;

    // yearly expense from all categories
    public List<Expense> findAnnualExpenseByUserName(String userName,LocalDateTime startDate,LocalDateTime endDate){
        Criteria criteria=Criteria.where("user_name").is(userName).andOperator(Criteria.where("date_time").gte(startDate).andOperator(Criteria.where("date_time").lte(endDate)));
        Query query=new Query(criteria);
        return template.find(query, Expense.class);
    }

    // yearly expense from all category - total
    public List<Expense> findAnnualExpenseByUserNameTotal(String userName,LocalDateTime startDate,LocalDateTime endDate){
        Criteria criteria=Criteria.where("user_name").is(userName).andOperator(Criteria.where("date_time").gte(startDate).andOperator(Criteria.where("date_time").lte(endDate)));
        Query query=new Query(criteria);
        return template.find(query, Expense.class);
    }

    // all expense from particular category
    public List<Expense> findExpenseFromParticularCategory(String userName,String category){
        Criteria criteria=Criteria.where("user_name").is(userName).andOperator(Criteria.where("category").is(category));
        Query query=new Query(criteria);
        return template.find(query, Expense.class);
    }

    // Annual Expense statement from particular category
    // Montly  Expense statement from particular category
    public List<Expense> findByCategoryByDateAndTime(String userName,String category,LocalDateTime startDate,LocalDateTime endDate){
        Criteria criteria=Criteria.where("user_name").is(userName).andOperator(Criteria.where("category").is(category).andOperator(Criteria.where("date_time").gte(startDate).andOperator(Criteria.where("date_time").lte(endDate))));
        Query query=new Query(criteria);
        return template.find(query, Expense.class);
    }

}
