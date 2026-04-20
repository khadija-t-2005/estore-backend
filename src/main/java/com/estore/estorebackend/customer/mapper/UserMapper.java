package com.estore.estorebackend.customer.mapper;

import com.estore.estorebackend.customer.dto.*;
import com.estore.estorebackend.customer.entity.*;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDTO toResponseDTO(User user) {
        ProfileDTO profileDTO = null;
        if (user.getProfile() != null) {
            Profile p = user.getProfile();
            profileDTO = ProfileDTO.builder()
                    .firstName(p.getFirstName())
                    .lastName(p.getLastName())
                    .phone(p.getPhone())
                    .address(p.getAddress())
                    .build();
        }
        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .profile(profileDTO)
                .build();
    }

    public User toEntity(UserRegisterDTO dto, String encodedPassword) {
        User user = User.builder()
                .email(dto.getEmail())
                .password(encodedPassword)
                .role("CUSTOMER")
                .build();

        Profile profile = Profile.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .user(user)
                .build();

        user.setProfile(profile);
        return user;
    }
}
