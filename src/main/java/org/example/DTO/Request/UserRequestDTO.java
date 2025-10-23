package org.example.DTO.Request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.Enums.UserRole;

import java.time.LocalDateTime;

public class UserRequestDTO {

    @NotBlank(message = " the username is required ")
    @Size(min = 4 , max = 10)
    private String name;
    @NotBlank(message = "email filed could not be empty")
    @Email(message = "email is not valid")
    private String email;
    @NotBlank(message = "you need to enter such password")
    @Size(min = 4 , max = 10 , message = "password must be at least 4 letters and not passing 10 chatacter")
    private String password;
    @NotBlank(message = "role is mondatory")
    private UserRole role;
    private Boolean isActive;
    private LocalDateTime createdAt;

    public UserRequestDTO() {
    }

    public UserRequestDTO(String name, String email, String password, UserRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isActive = true;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }



}
