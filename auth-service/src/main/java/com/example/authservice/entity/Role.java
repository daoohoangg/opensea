package com.example.authservice.entity;

import java.util.Set;

import com.example.authservice.enums.Roles;
import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Role {
    @Id
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    Roles name;

    String description;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<Permission> permissions;

}
