package org.example.Model;


import jakarta.persistence.*;
import org.example.Enums.UserRole;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String password;
    private UserRole role;
    private Boolean isActive;
    private LocalDateTime createdAt;

}
