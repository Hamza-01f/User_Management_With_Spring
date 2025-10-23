package org.example.Mappers;

import org.example.DTO.Request.UserRequestDTO;
import org.example.DTO.Response.UserResponseDTO;
import org.example.Model.User;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {

    public User toEntity(UserRequestDTO userRequestDTO){
            if(userRequestDTO == null){
                return  null;
            }

            User user = new User();

            user.setName(userRequestDTO.getName());
            user.setEmail(userRequestDTO.getEmail());
            user.setPassword(userRequestDTO.getPassword());
            user.setRole(userRequestDTO.getRole());
            user.setActive(userRequestDTO.getActive() != null ? userRequestDTO.getActive() : true);

            return  user;
    }

    public UserResponseDTO toResponse(User user){

         if(user == null){
             return null;
         }

         UserResponseDTO userResponseDTO = new UserResponseDTO();
         userResponseDTO.setId(user.getId());
         userResponseDTO.setName(user.getName());
         userResponseDTO.setEmail(user.getEmail());
         userResponseDTO.setRole(user.getRole());
         userResponseDTO.setActive(user.getActive());
         userResponseDTO.setCreatedAt(user.getCreatedAt());

         return userResponseDTO;
    }

}
