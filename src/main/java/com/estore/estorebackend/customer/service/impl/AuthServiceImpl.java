package com.estore.estorebackend.customer.service.impl;

import com.estore.estorebackend.customer.dto.UserLoginDTO;
import com.estore.estorebackend.customer.dto.UserRegisterDTO;
import com.estore.estorebackend.customer.dto.UserResponseDTO;
import com.estore.estorebackend.customer.entity.User;
import com.estore.estorebackend.customer.mapper.UserMapper;
import com.estore.estorebackend.customer.repository.UserRepository;
import com.estore.estorebackend.customer.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO register(UserRegisterDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email déjà utilisé !");
        }
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        User user = userMapper.toEntity(dto, encodedPassword);
        return userMapper.toResponseDTO(userRepository.save(user));
    }

    @Override
    public String login(UserLoginDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Email ou mot de passe incorrect"));
        
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Email ou mot de passe incorrect");
        }
        return "SUCCESS_TOKEN"; 
    }
}