package com.example.expensetracker.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.expensetracker.model.Income;

public interface IncomeRepository extends MongoRepository<Income,String>{
    // List<Income> findByUserIdAndDateAndTimeBetween(User userId,LocalDateTime startDate,LocalDateTime endDate);]\
    // List<Income> findByUserIdAndDateAndTimeBetween(User userId,LocalDateTime startDate,LocalDateTime endDate);
    // List<Income> findByDateAndTime(LocalDateTime dateAndTime);

    // @Query("{'userId':?0,'dateAndTime': { $gte: ISODate(?1), $lte: ISODate(?2) } }")
    // @Query("{'user_id': ?0}")
    // List<Income> findByUserId(User userId);
    // List<Income> findByUserId(User userId);

    List<Income> findBySource(String source);

    @Query("'dateAndTime': {$gte : startDate,lte : endDate}")
    List<Income> findByDateAndTime(LocalDateTime startDate, LocalDateTime endDate);

    @Query("{'source': source}, 'dateAndTime': {$gte : startDate,lte : endDate}")
    List<Income> findBySourceByDateAndTime(String source,LocalDateTime startDate,LocalDateTime endDate);
}