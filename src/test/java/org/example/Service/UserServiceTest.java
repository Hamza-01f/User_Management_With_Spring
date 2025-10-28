//package org.example.Service;
//
//
//import org.example.DTO.Response.UserResponseDTO;
//import org.example.Model.User;
//import org.example.Repository.UserRespository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//
//@ExtendWith(MockitoExtension.class)
//public class UserServiceTest {
//
//    @Mock
//    private UserRespository userRespository;
//
//    @InjectMocks
//    private UserService userService;
//
//    @Test
//    public void UserService_CreateUer_ReturnUserResponseDTO(){
//
//        User user = User.builder().name("user").type("electric").build();
//
//        UserResponseDTO userResponseDTO = UserResponseDTO.builder().name("user").type("electric").builder();
//    }
//}
