package com.example.demospringsecurity.service;

import com.example.demospringsecurity.exception.BadRequestException;
import com.example.demospringsecurity.model.request.LoginRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthService {
    final AuthenticationManager authenticationManager;
    final HttpSession session;

    public void login(LoginRequest request) {
        // Tạo đối tượng xác thực
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        );

        try {
            // Tiến hành xác thực
            Authentication authentication = authenticationManager.authenticate(token);

            // Lưu đối tượng đã xác thực vào trong SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Lưu vào trong session
            session.setAttribute("MY_SESSION", authentication.getName()); // Lưu email -> session
        } catch (DisabledException e) {
            throw new BadRequestException("Tai khoan chua duoc kich hoat");
        } catch (AuthenticationException e) {
            throw new BadRequestException("Tai khoan hoac mat khau khong dung");
        }
    }
}
