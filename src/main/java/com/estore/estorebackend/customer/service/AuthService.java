package com.estore.estorebackend.customer.service;

import com.estore.estorebackend.customer.dto.UserLoginDTO;
import com.estore.estorebackend.customer.dto.UserRegisterDTO;
import com.estore.estorebackend.customer.dto.UserResponseDTO;

public interface AuthService {
    UserResponseDTO register(UserRegisterDTO dto);
    String login(UserLoginDTO dto); // retourne un JWT token
}
