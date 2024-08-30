package com.example.movieapp.repository;

import com.example.movieapp.entity.Movie;
import com.example.movieapp.model.enums.MovieType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findByName(String name);

    List<Movie> findByNameContainingIgnoreCase(String name);

    Movie findByNameAndSlug(String name, String slug);

    List<Movie> findByRatingBetween(Double min, Double max);

    List<Movie> findByReleaseYearGreaterThanEqual(Integer year);

    List<Movie> findByStatusOrderByReleaseYearDesc(Boolean status);

    long countByType(MovieType type);

    boolean existsByRatingGreaterThan(Double rating);

    List<Movie> findByStatus(Boolean status, Sort sort);

    Page<Movie> findByStatus(Boolean status, Pageable pageable);

    // Tim kiem cac bo phim theo loai va status sap xep theo thoi gian tao giam dan, rating tang dan va lay 5 ban ghi dau tien

    // Ưng dung movie
    Page<Movie> findByTypeAndStatus(MovieType type, Boolean status, Pageable pageable);
}
