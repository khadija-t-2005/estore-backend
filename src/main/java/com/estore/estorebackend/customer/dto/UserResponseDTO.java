package com.estore.estorebackend.customer.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UserResponseDTO {
    private Long id;
    private String email;
    private String role;
    private LocalDateTime createdAt;
    private ProfileDTO profile;
}
