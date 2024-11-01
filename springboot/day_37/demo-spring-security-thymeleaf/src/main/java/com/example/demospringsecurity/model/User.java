package com.example.demospringsecurity.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    Integer id;
    String name;
    String email;
    String password;
    List<String> roles; // USER, ADMIN
    Boolean enabled;
}
