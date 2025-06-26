package com.example.authservice.entity;

import com.example.authservice.enums.Permissions;
import com.example.authservice.enums.SignInTypes;
import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.web3j.protocol.core.RpcErrors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Permission {
    @Id
    @GeneratedValue
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private Permissions name;

    String description;
}
