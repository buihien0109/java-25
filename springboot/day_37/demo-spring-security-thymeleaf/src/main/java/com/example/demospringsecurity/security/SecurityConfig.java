package com.example.demospringsecurity.security;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true
)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SecurityConfig {
    final UserDetailsService userDetailsService;
    final PasswordEncoder passwordEncoder;

//    @Bean
//    public UserDetailsService users() {
//        // The builder will ensure the passwords are encoded before saving in memory
//        User.UserBuilder users = User.withDefaultPasswordEncoder();
//        UserDetails user = users
//                .username("user")
//                .password("123")
//                .roles("USER")
//                .build();
//        UserDetails admin = users
//                .username("admin")
//                .password("123")
//                .roles("USER", "ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String[] PUBLIC_PATH = {"/aa", "/bb", "/css/**", "/js/**", "/images/**"};
        http.authorizeHttpRequests(authorizeRequests -> {
//            authorizeRequests.requestMatchers("/", "/a", "/b").permitAll();
//            authorizeRequests.requestMatchers(PUBLIC_PATH).permitAll();
//            authorizeRequests.requestMatchers("/user").hasRole("USER");
//            authorizeRequests.requestMatchers("/admin").hasRole("ADMIN");
//            authorizeRequests.requestMatchers("/blogs", "/admin/users").hasAnyRole("USER", "ADMIN");
//            authorizeRequests.requestMatchers(HttpMethod.GET, "/movies", "/episodes").hasAnyRole("USER", "ADMIN");
//            authorizeRequests.requestMatchers("/actors").hasAuthority("ROLE_ADMIN");
//            authorizeRequests.anyRequest().authenticated();

            authorizeRequests.anyRequest().permitAll();
        });

        http.formLogin(formLogin -> {
            formLogin.defaultSuccessUrl("/", true);
            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/login-process");
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("credential");
        });

        http.logout(logout -> {
            logout.logoutSuccessUrl("/");
        });

        http.authenticationProvider(authenticationProvider());
        return http.build();
    }
}