package org.example.Controller;


import jakarta.validation.Valid;
import org.example.DTO.Request.UserRequestDTO;
import org.example.DTO.Response.UserResponseDTO;
import org.example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

   private final UserService userService;

   @Autowired
   public UserController(UserService userService){
      this.userService = userService;
   }

   @PostMapping
   public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody  UserRequestDTO userRequestDTO){
       UserResponseDTO createdUser = userService.createUser(userRequestDTO);
       return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
   }


   @GetMapping("/{id}")
   public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) throws Throwable {
         UserResponseDTO user = userService.getUserById(id);
         return ResponseEntity.ok(user);
   }

   @GetMapping
   public ResponseEntity<Page<UserResponseDTO>> getAllUsers(
           @RequestParam(defaultValue = "0") int page ,
           @RequestParam(defaultValue = "10") int size,
           @RequestParam(defaultValue = "id") String sortBy,
           @RequestParam(defaultValue = "asc") String sortDirection
   ){

       Sort sort = sortDirection.equalsIgnoreCase("desc")
                          ? Sort.by(sortBy).descending()
                          : Sort.by(sortBy).ascending();

       Pageable pageable = (Pageable) PageRequest.of(page , size , sort);

       Page<UserResponseDTO> users = (Page<UserResponseDTO>) userService.getAllUsers(pageable);

       return ResponseEntity.ok(users);

   }

   @GetMapping("/all")
   public ResponseEntity<List<UserResponseDTO>> getAllActiveUsers(){
       List<UserResponseDTO> users = userService.getAllActiveUsers();
       return  ResponseEntity.ok(users);
   }

   @PutMapping("/{id}")
   public ResponseEntity<UserResponseDTO> updateUser( @PathVariable Long id , @Valid @RequestBody UserRequestDTO userRequestDTO) throws Throwable {
             UserResponseDTO userResponseDTO = userService.updateUser(id , userRequestDTO);
             return  ResponseEntity.ok(userResponseDTO);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<String> deleteUser(@PathVariable Long id) throws Throwable {
       return ResponseEntity.ok(userService.deleteUser(id));
   }


}
