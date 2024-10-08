package com.example.movieapp.service;

import com.example.movieapp.entity.Episode;
import com.example.movieapp.repository.EpisodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EpisodeService {
    private final EpisodeRepository episodeRepository;

    public List<Episode> getEpisodesActiveByMovie(Integer movieId) {
        return episodeRepository.findByStatusAndMovie_IdOrderByDisplayOrderAsc(true, movieId);
    }

    public Episode getEpisodeByDisplayOrder(Integer movieId, String tap) {
        Integer covertTap = tap.equals("full") ? 1 : Integer.parseInt(tap);
        return episodeRepository
                .findByMovie_IdAndStatusAndDisplayOrder(movieId, true, covertTap)
                .orElse(null);
    }
}
