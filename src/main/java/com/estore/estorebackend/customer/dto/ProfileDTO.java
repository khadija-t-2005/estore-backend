package com.estore.estorebackend.customer.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProfileDTO {
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
}
