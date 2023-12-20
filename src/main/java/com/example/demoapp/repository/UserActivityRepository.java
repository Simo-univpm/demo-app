package com.example.demoapp.repository;

import com.example.demoapp.model.entities.UserActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;


// contiene query per operare sulla repository popolata dal listener
public interface UserActivityRepository extends JpaRepository<UserActivityEntity, Long> {

    // ritorna la lista di tuple comprese nelle date
    @Query("SELECT ua FROM UserActivityEntity ua WHERE ua.activity_date >= :startDate AND ua.activity_date <= :endDate AND ua.activity_key = :key")
    List<UserActivityEntity> findOperationsByDateRange(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate, @Param("key") String key);

    // ritorna il numero di operazioni "key" comprese nelle date
    @Query("SELECT COUNT(ua) FROM UserActivityEntity ua WHERE ua.activity_date >= :startDate AND ua.activity_date <= :endDate AND ua.activity_key = :key")
    long countOperationsByKey(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate, @Param("key") String key);

}
