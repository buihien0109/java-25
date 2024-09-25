package com.example.movieapp.repository;

import com.example.movieapp.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EpisodeRepository extends JpaRepository<Episode, Integer> {
    List<Episode> findByStatusAndMovie_IdOrderByDisplayOrderAsc(boolean status, Integer movieId);

    Optional<Episode> findByMovie_IdAndStatusAndDisplayOrder(Integer movieId, boolean status, Integer displayOrder);
}