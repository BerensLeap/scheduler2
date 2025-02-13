package com.example.scheduleApp.entity;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Range(max = 10)
    private User user;

    @Column(nullable = false)
    @Range(max = 10)
    private String title;

    @Column
    private String content;
}
