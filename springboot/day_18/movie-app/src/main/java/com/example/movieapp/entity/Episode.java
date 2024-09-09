package com.example.movieapp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;
    Integer duration;
    Integer displayOrder;
    String videoUrl;
    Boolean status;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    Movie movie;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime publishedAt;
}
