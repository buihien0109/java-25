package com.example.demospringsecurity;

import com.example.demospringsecurity.entity.Role;
import com.example.demospringsecurity.entity.User;
import com.example.demospringsecurity.repository.RoleRepository;
import com.example.demospringsecurity.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class DemoSpringSecurityApplicationTests {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void save_roles() {
        Role userRole = Role.builder().name("USER").build();
        Role adminRole = Role.builder().name("ADMIN").build();
        roleRepository.save(userRole);
        roleRepository.save(adminRole);
    }

    @Test
    void save_users() {
        Role userRole = roleRepository
                .findByName("USER").orElse(null);
        Role adminRole = roleRepository
                .findByName("ADMIN").orElse(null);

        User user = User.builder()
                .name("user")
                .email("user@gmail.com")
                .password(passwordEncoder.encode("123"))
                .roles(List.of(userRole))
                .enabled(true)
                .build();
        userRepository.save(user);

        User admin = User.builder()
                .name("admin")
                .email("admin@gmail.com")
                .password(passwordEncoder.encode("123"))
                .roles(List.of(userRole, adminRole))
                .enabled(true)
                .build();
        userRepository.save(admin);

        User sale = User.builder()
                .name("sale")
                .email("sale@gmail.com")
                .password(passwordEncoder.encode("123"))
                .roles(List.of(userRole))
                .enabled(false)
                .build();
        userRepository.save(sale);
    }
}
