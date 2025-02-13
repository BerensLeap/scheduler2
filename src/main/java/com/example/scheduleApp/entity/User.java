package com.example.scheduleApp.entity;

import jakarta.persistence.*;
import lombok.Setter;

@Entity
@Table(name = "user")
@Setter
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String email;
}
