package com.example.authservice.components;

import com.example.authservice.entity.Role;
import com.example.authservice.entity.SignInType;
import com.example.authservice.enums.Roles;
import com.example.authservice.enums.SignInTypes;
import com.example.authservice.repository.PermissionRepository;
import com.example.authservice.repository.RoleRepository;
import com.example.authservice.repository.SignInTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
@RequiredArgsConstructor
public class SeederEnums implements CommandLineRunner {
    @Autowired
    SignInTypeRepository signInTypeRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        for(SignInTypes signInType : SignInTypes.values()){
            if(!signInTypeRepository.existsByName(signInType)){
                SignInType signIn = new SignInType();
                signIn.setName(signInType);
                signIn.setDescription(signIn.getDescription());
                signInTypeRepository.save(signIn);
            }
        }

        for (Roles roleType : Roles.values()) {
            if (!roleRepository.existsByName(roleType)) {
                Role role = new Role();
                role.setName(roleType);
                role.setDescription("Role: " + roleType.name());

                // Gán permission cho role nếu muốn

//                if (roleType == Roles.ADMIN) {
//                    role.setPermissions(new HashSet<>(permissionRepository.findAll()));
//                } else if (roleType == Roles.USER) {
//                    role.setPermissions(Set.of(
//                            permissionRepository.findByName(Permissions.READ_USER).orElseThrow()
//                    ));
//                }

                roleRepository.save(role);
            }
        }
    }
}
