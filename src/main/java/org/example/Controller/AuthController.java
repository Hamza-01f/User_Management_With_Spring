package org.example.Controller;


import jakarta.validation.Valid;
import org.example.DTO.Request.UserRequestDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class AuthController {

    @PostMapping("/register")
    public String register(@Valid  @RequestBody UserRequestDTO userRequestDTO){

    }
}
