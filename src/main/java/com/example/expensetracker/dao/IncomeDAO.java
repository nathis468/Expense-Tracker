package com.example.expensetracker.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.expensetracker.model.Income;
import com.example.expensetracker.model.User;

@Repository
public class IncomeDAO {

    @Autowired
    MongoTemplate template;


    // yearly income from all sources
    public List<Income> findAnnualIncomeByUserId(User userId,LocalDateTime startDate,LocalDateTime endDate){
        Criteria criteria=Criteria.where("userId").is(userId).andOperator(Criteria.where("date_time").gte(startDate).andOperator(Criteria.where("date_time").lte(endDate)));
        Query query=new Query(criteria);
        return template.find(query, Income.class);
    }

    // yearly income from all source - total
    public List<Income> findAnnualIncomeByUserIdTotal(User userId,LocalDateTime startDate,LocalDateTime endDate){
        Criteria criteria=Criteria.where("userId").is(userId).andOperator(Criteria.where("date_time").gte(startDate).andOperator(Criteria.where("date_time").lte(endDate)));
        Query query=new Query(criteria);
        return template.find(query, Income.class);
    }


    // all income from particular source
    public List<Income> findIncomeFromParticularSource(User userId,String source){
        Criteria criteria=Criteria.where("userId").is(userId).andOperator(Criteria.where("source").is(source));
        Query query=new Query(criteria);
        return template.find(query, Income.class);
    }

    // Annual Income statement from particular source
    // Montly  Income statement from particular source
    public List<Income> findBySourceByDateAndTime(User userId,String source,LocalDateTime startDate,LocalDateTime endDate){
        Criteria criteria=Criteria.where("userId").is(userId).andOperator(Criteria.where("source").is(source).andOperator(Criteria.where("date_time").gte(startDate).andOperator(Criteria.where("date_time").lte(endDate))));
        Query query=new Query(criteria);
        return template.find(query, Income.class);
    }
}
