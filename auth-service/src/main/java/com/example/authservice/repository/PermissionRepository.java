package com.example.authservice.repository;

import com.example.authservice.entity.Permission;
import com.example.authservice.enums.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByName(Permissions name);

    boolean existsByName(Permissions type);
}
