package org.example.Controller;


import org.example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

   private final UserService userService;

   @Autowired
   public UserController(UserService userService){
      this.userService = userService;
   }
    @GetMapping("/")
    public String displayMessage(){
        return userService.Talk();
    }
}
