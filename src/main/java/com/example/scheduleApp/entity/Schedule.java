package com.example.scheduleApp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column
    private String content;
}
