package com.springsecurity.config;

import com.springsecurity.entity.Role;
import com.springsecurity.entity.User;
import com.springsecurity.repository.RoleRepository;
import com.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.LinkedHashSet;

@Configuration
@RequiredArgsConstructor
public class InitiationConfiguration {

    private final PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            HashSet<Role> roles = new HashSet<>();
            roles.add(Role.builder()
                    .roleName(com.springsecurity.enums.Role.ADMIN.name())
                    .build());

            User adminUser = User.builder()
                    .userName("admin")
                    .passWord(passwordEncoder.encode("admin"))
                    .roles(roles)
                    .build();

            if (!roleRepository.existsByRoleName("ADMIN")) {
                userRepository.save(adminUser);
            }
        };
    }
}
