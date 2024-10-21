package com.example.movieapp.service;

import com.example.movieapp.entity.TokenConfirm;
import com.example.movieapp.entity.User;
import com.example.movieapp.exception.BadRequestException;
import com.example.movieapp.exception.NotFoundException;
import com.example.movieapp.model.enums.TokenType;
import com.example.movieapp.model.enums.UserRole;
import com.example.movieapp.model.request.LoginRequest;
import com.example.movieapp.model.request.RegisterRequest;
import com.example.movieapp.model.response.TokenConfirmMessageResponse;
import com.example.movieapp.repository.TokenConfirmRepository;
import com.example.movieapp.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final HttpSession session;
    private final TokenConfirmRepository tokenConfirmRepository;
    private final MailService mailService;

    public void login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid password");
        }

        if (!user.getIsActive()) {
            throw new BadRequestException("User is not active");
        }

        // Co the luu trong cookie, redis, database, ...
        session.setAttribute("currentUser", user);
    }

    public void logout() {
        session.removeAttribute("currentUser");
    }

    public void register(RegisterRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if (userOptional.isPresent()) {
            throw new BadRequestException("Email is already taken");
        }

        // Tao user
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .isActive(false)
                .role(UserRole.USER)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        userRepository.save(user);

        // Sinh token
        TokenConfirm tokenConfirm = TokenConfirm.builder()
                .token(UUID.randomUUID().toString())
                .type(TokenType.CONFIRM_REGISTRATION)
                .user(user)
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusHours(1))
                .build();
        tokenConfirmRepository.save(tokenConfirm);

        // Gui email xac nhan
        String link = "http://localhost:8081/xac-thuc-tai-khoan?token=" + tokenConfirm.getToken();
        System.out.println("Link xac thuc: " + link);

        mailService.sendMailRegistration(
                user.getEmail(),
                "Xác thực tài khoản",
                "Nhấn vào link sau để xác thực tài khoản: " + link
        );
    }

    public TokenConfirmMessageResponse verifyAccount(String token) {
        // Kiem tra xem token co ton tai khong
        Optional<TokenConfirm> tokenConfirmOptional = tokenConfirmRepository
                .findByTokenAndType(token, TokenType.CONFIRM_REGISTRATION);
        if (tokenConfirmOptional.isEmpty()) {
            return TokenConfirmMessageResponse.builder()
                    .success(false)
                    .message("Token không tồn tại")
                    .build();
        }

        TokenConfirm tokenConfirm = tokenConfirmOptional.get();
        // Kiem tra xem token da duoc xac thuc chua
        if (tokenConfirm.getConfirmedAt() != null) {
            return TokenConfirmMessageResponse.builder()
                    .success(false)
                    .message("Token đã được xác thực")
                    .build();
        }

        // Kiem tra xem token da het han chua
        if (tokenConfirm.getExpiredAt().isBefore(LocalDateTime.now())) {
            return TokenConfirmMessageResponse.builder()
                    .success(false)
                    .message("Token đã hết hạn")
                    .build();
        }

        // Xac thuc tai khoan
        User user = tokenConfirm.getUser();
        user.setIsActive(true);
        userRepository.save(user);

        // Cap nhat thoi gian xac thuc
        tokenConfirm.setConfirmedAt(LocalDateTime.now());
        tokenConfirmRepository.save(tokenConfirm);

        return TokenConfirmMessageResponse.builder()
                .success(true)
                .message("Xác thực tài khoản thành công")
                .build();
    }
}
