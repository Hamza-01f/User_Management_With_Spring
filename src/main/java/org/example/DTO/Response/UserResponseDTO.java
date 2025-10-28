package org.example.DTO.Response;
import org.example.Enums.UserRole;

import java.time.LocalDateTime;

public class UserResponseDTO {

    private Long id;

    private String name;

    private String email;

    private UserRole role;

    private Boolean isActive;

    private  LocalDateTime createdAt;

    public UserResponseDTO() {
    }

    public UserResponseDTO(String email, String name, UserRole role, Boolean isActive, Long id , LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
