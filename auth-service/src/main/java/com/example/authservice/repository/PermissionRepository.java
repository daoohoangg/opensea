package com.example.authservice.repository;

import com.example.authservice.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {
    Optional<Permission> findByName(Permission name);

    boolean existsByName(Permission type);
}
