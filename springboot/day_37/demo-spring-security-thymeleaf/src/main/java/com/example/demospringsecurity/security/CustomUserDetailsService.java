package com.example.demospringsecurity.security;

import com.example.demospringsecurity.model.User;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomUserDetailsService implements UserDetailsService {
    final PasswordEncoder passwordEncoder;
    List<User> users = new ArrayList<>();

    public CustomUserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        users.add(new User(1, "user", "user@gmail.com", passwordEncoder.encode("123"), List.of("USER"), true));
        users.add(new User(2, "admin", "admin@gmail.com", passwordEncoder.encode("123"), List.of("USER", "ADMIN"), true));
        users.add(new User(3, "sale", "sale@gmail.com", passwordEncoder.encode("123"), List.of("USER"), false));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        return users.stream()
//                .filter(user -> user.getEmail().equals(email))
//                .findFirst()
//                .map(CustomUserDetails::new)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        User user = users.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new CustomUserDetails(user);
    }
}
