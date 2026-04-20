package com.estore.estorebackend.customer.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profiles")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
