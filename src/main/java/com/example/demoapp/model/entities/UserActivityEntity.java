package com.example.demoapp.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "user_activity")
@Data
@RequiredArgsConstructor
public class UserActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name ="activity_partition")
    private String activity_partition;

    @Column(name ="activity_key")
    private String activity_key;

    @Column(name ="username")
    private String username;

    @Column(name ="activity_date")
    private java.sql.Timestamp activity_date;

}