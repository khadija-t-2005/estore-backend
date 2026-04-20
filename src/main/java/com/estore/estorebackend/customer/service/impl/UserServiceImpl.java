package com.estore.estorebackend.customer.service.impl;

import com.estore.estorebackend.customer.dto.UserResponseDTO;
import com.estore.estorebackend.customer.repository.UserRepository;
import com.estore.estorebackend.customer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDTO getUserById(Long id) {
        // هنا غاديري الـ Logic باش تجيبي اليوزر بالـ id
        return null;
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        // هنا غاديري الـ Logic باش تجيبي كاع اليوزرز
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}