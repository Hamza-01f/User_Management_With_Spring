package org.example.Service;

import jakarta.transaction.Transactional;
import org.example.DTO.Request.UserRequestDTO;
import org.example.DTO.Response.UserResponseDTO;
import org.example.Exception.DuplicateResourceException;
import org.example.Exception.ResourceNotFoundException;
import org.example.Mappers.UserMapper;
import org.example.Model.User;
import org.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponseDTO createUser(UserRequestDTO requestDTO) {
        if (userRepository.existsByEmail(requestDTO.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        User user = userMapper.toEntity(requestDTO);
        user.setIsActive(true);
        user.setCreatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return userMapper.toResponse(user);
    }

    public Page<UserResponseDTO> getAllUsers(Pageable pageable) {
        return userRepository.findByIsActiveTrue(pageable)
                .map(userMapper::toResponse);
    }

    public List<UserResponseDTO> getAllActiveUsers() {
        return userRepository.findByIsActiveTrue()
                .stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO requestDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        if (requestDTO.getEmail() != null &&
                !requestDTO.getEmail().equals(existingUser.getEmail()) &&
                userRepository.existsByEmailAndIdNot(requestDTO.getEmail(), id)) {
            throw new DuplicateResourceException("Email already exists");
        }

        if (requestDTO.getName() != null) {
            existingUser.setName(requestDTO.getName());
        }
        if (requestDTO.getEmail() != null) {
            existingUser.setEmail(requestDTO.getEmail());
        }
        if (requestDTO.getPassword() != null) {
            existingUser.setPassword(requestDTO.getPassword());
        }
        if (requestDTO.getRole() != null) {
            existingUser.setRole(requestDTO.getRole());
        }

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toResponse(updatedUser);
    }

    public String deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        user.setIsActive(false);
        userRepository.save(user);

        return "User deleted successfully";
    }
}