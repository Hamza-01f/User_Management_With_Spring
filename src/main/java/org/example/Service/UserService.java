package org.example.Service;

import jakarta.transaction.Transactional;
import org.example.DTO.Request.UserRequestDTO;
import org.example.DTO.Response.UserResponseDTO;
import org.example.Exception.DuplicateResourceException;
import org.example.Exception.ResourceNotFoundException;
import org.example.Mappers.UserMapper;
import org.example.Model.User;
import org.example.Repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRespository userRespository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRespository userRespository , UserMapper userMapper){
         this.userRespository = userRespository;
         this.userMapper = userMapper;
    }

    //---------------------------- create user --------------------------------
    public UserResponseDTO createUser(UserRequestDTO requestDTO){
           if(userRespository.existsByEmail(requestDTO.getEmail())){
               throw  new DuplicateResourceException("email already exists");
           }

           User user = userMapper.toEntity(requestDTO);
           User savedUser = (User) userRespository.save(user);

           return userMapper.toResponse(savedUser);
    }


    //----------------------- get user by id -----------------------------------
    public UserResponseDTO getUserById(Long id) throws Throwable {
          User user = (User) userRespository.findById(id).orElseThrow(() -> new ResourceNotFoundException("the user with this id is not found"));
          return  userMapper.toResponse(user);
    }


    //-------------------------- get all users ---------------------------------
    public Page<UserResponseDTO> getAllUsers(Pageable pageable) {
        return userRespository.findByActiveTrue(pageable)
                .map(userMapper::toResponse);
    }


    //----------------------- get all active users ------------------------------
    public List<UserResponseDTO> getAllActiveUsers(){
          return  userRespository.findByActiveTrue()
                                 .stream()
                                 .map(userMapper::toResponse)
                                 .collect(Collectors.toList());
    }


    //---------------------- update users ---------------------------------------
    public UserResponseDTO updateUser(Long id , UserRequestDTO requestDTO) throws Throwable {

         User existingUser =  (User) userRespository.findById(id)
                                                .orElseThrow(() -> new ResourceNotFoundException("the user not found"));

         if(requestDTO.getEmail() != null && !requestDTO.getEmail().equals(existingUser.getEmail())
                 && userRespository.existsByEmailAndIdNot(requestDTO.getEmail(), id)){
                 throw new DuplicateResourceException("You attempt to update an already existed email");
         }

         existingUser.setName(requestDTO.getName());
         existingUser.setEmail(requestDTO.getEmail());
         existingUser.setRole(requestDTO.getRole());
         existingUser.setPassword(requestDTO.getPassword());
         existingUser.setActive(requestDTO.getActive());

         userRespository.save(existingUser);

         return userMapper.toResponse(existingUser);
    }


    //-------------- delete user --------------------
    public String deleteUser(Long id) throws Throwable {
        Optional<User> existingUser = (Optional<User>) userRespository.findById(id)
                                                                      .orElseThrow(() -> new ResourceNotFoundException("the user that you wanna delete is not found"));
        userRespository.delete(existingUser);

        return "the user was deleted with success";
    }
}
