package com.example.authservice.entity;


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
public class Permission {
    @Id
    String name;

//    @Enumerated(EnumType.STRING)
//    @Column(unique = true)
//    private Permissions name;

    String description;
}
