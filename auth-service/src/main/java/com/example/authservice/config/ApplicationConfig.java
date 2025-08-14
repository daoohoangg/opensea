package com.example.authservice.config;

import java.util.HashSet;
import java.util.Set;

import com.example.authservice.entity.Account;
import com.example.authservice.entity.Role;
import com.example.authservice.enums.Roles;
import com.example.authservice.repository.AccountRepository;
import com.example.authservice.repository.RoleRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/v1/auth/**") // hoặc "/*" nếu bạn dùng nhiều endpoint
                .allowedOrigins("http://localhost:5173") // port frontend
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;
    @Bean
    ApplicationRunner applicationRunner(AccountRepository accountRepository){
        return args -> {
            Role adminRole = (Role) roleRepository.findByName(Roles.ADMIN)
                    .orElseThrow(() -> new RuntimeException("ADMIN role not found"));
            if (accountRepository.findByUsername("admin").isEmpty()){
                Set<Role> roles = new HashSet<>();
                roles.add(adminRole);

                Account user = Account.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles(roles)
                        .build();

                accountRepository.save(user);
                log.warn("admin user has been created with default password: admin, please change it");
            }
        };
    }

}
