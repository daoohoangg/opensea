package com.example.authservice.repository;

import com.example.authservice.entity.SignInType;
import com.example.authservice.enums.SignInTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SignInTypeRepository extends JpaRepository<SignInType, Long> {
    Optional<SignInType> findByName(SignInTypes name);

    boolean existsByName(SignInTypes signInType);
}
