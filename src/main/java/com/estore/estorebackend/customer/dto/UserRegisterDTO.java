package com.estore.estorebackend.customer.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UserRegisterDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
}

