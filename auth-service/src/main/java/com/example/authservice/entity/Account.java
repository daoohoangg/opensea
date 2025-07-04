package com.example.authservice.entity;


import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Set;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String username;
    String password;
    String walletAddress;
    String email;
    @ManyToMany(fetch = FetchType.EAGER)
    Set<Role> roles;
    @ManyToMany(fetch = FetchType.EAGER)
    Set<SignInType> signInTypes;
}


