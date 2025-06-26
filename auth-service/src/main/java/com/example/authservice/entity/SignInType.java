package com.example.authservice.entity;

import com.example.authservice.enums.SignInTypes;
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
public class SignInType {
    @Id
    @GeneratedValue
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private SignInTypes name;

    String description;

}
