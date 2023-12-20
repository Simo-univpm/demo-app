package com.example.demoapp.repository;

import com.example.demoapp.model.entities.LastAccessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface LastAccessRepository extends JpaRepository<LastAccessEntity, Long> {

    ArrayList<LastAccessEntity> findByUserId(long userId);

}
