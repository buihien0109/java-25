package com.example.movieapp.entity;

import com.example.movieapp.model.enums.TokenType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "token_confirm")
public class TokenConfirm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Integer id;

    String token; // chuỗi token

    @Enumerated(EnumType.STRING)
    TokenType type; // loại token

    LocalDateTime createdAt; // thời gian tạo
    LocalDateTime expiredAt; // thời gian hết hạn
    LocalDateTime confirmedAt; // thời gian xác thực

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user; // token này của user nào
}
