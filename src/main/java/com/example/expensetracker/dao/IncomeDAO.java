package com.example.expensetracker.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.expensetracker.model.Income;

@Repository
public class IncomeDAO {

    @Autowired
    MongoTemplate template;

    // yearly income from all sources
    public List<Income> findAnnualIncomeByUserName(String userName,LocalDateTime startDate,LocalDateTime endDate){
        Criteria criteria=Criteria.where("user_name").is(userName).andOperator(Criteria.where("date_time").gte(startDate).andOperator(Criteria.where("date_time").lte(endDate)));
        Query query=new Query(criteria);
        return template.find(query, Income.class);
    }

    // yearly income from all source - total
    public List<Income> findAnnualIncomeByUserNameTotal(String userName,LocalDateTime startDate,LocalDateTime endDate){
        Criteria criteria=Criteria.where("user_name").is(userName).andOperator(Criteria.where("date_time").gte(startDate).andOperator(Criteria.where("date_time").lte(endDate)));
        Query query=new Query(criteria);
        return template.find(query, Income.class);
    }

    // all income from particular source
    public List<Income> findIncomeFromParticularSource(String userName,String source){
        Criteria criteria=Criteria.where("user_name").is(userName).andOperator(Criteria.where("source").is(source));
        Query query=new Query(criteria);
        return template.find(query, Income.class);
    }

    // Annual Income statement from particular source
    // Montly  Income statement from particular source
    public List<Income> findBySourceByDateAndTime(String userName,String source,LocalDateTime startDate,LocalDateTime endDate){
        Criteria criteria=Criteria.where("user_name").is(userName).andOperator(Criteria.where("source").is(source).andOperator(Criteria.where("date_time").gte(startDate).andOperator(Criteria.where("date_time").lte(endDate))));
        Query query=new Query(criteria);
        return template.find(query, Income.class);
    }
}
