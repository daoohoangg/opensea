package com.example.authservice.repository;

import com.example.authservice.entity.Role;
import com.example.authservice.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(Roles name);

    boolean existsByName(Roles roleType);

}
