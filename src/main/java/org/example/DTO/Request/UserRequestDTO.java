package org.example.DTO.Request;

import jakarta.persistence.Column;
import org.example.Enums.UserRole;

import java.time.LocalDateTime;

public class UserRequestDTO {

    private Long id;
    @Column(nullable = false )
    private String name;
    private String email;
    private String password;
    private UserRole role;
    private Boolean isActive;
    private LocalDateTime createdAt;

}
