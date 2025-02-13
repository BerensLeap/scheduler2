package com.example.scheduleApp.entity;

import com.example.scheduleApp.config.PasswordEncoder;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
    private String email;

    public String getPassword() {
        return password;
    }
}
