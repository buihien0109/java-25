package com.example.movieapp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "blogs")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String title;
    String slug;

    @Column(columnDefinition = "TEXT")
    String description;

    @Column(columnDefinition = "MEDIUMTEXT")
    String content;

    String thumbnail;
    Boolean status;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime publishedAt;
}
