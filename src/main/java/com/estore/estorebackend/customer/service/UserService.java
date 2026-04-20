package com.estore.estorebackend.customer.service;

import com.estore.estorebackend.customer.dto.UserResponseDTO;
import java.util.List;

public interface UserService {
    UserResponseDTO getUserById(Long id);
    List<UserResponseDTO> getAllUsers();
    void deleteUser(Long id);
    boolean existsByEmail(String email);

}