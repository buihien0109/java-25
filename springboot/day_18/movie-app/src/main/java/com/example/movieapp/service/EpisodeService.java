package com.example.movieapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.movieapp.entity.Episode;
import com.example.movieapp.entity.Movie;
import com.example.movieapp.model.request.CreateEpisodeRequest;
import com.example.movieapp.model.request.UpdateEpisodeRequest;
import com.example.movieapp.repository.EpisodeRepository;
import com.example.movieapp.repository.MovieRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EpisodeService {
    private final EpisodeRepository episodeRepository;
    private final MovieRepository movieRepository;

    public List<Episode> getEpisodesActiveByMovie(Integer movieId) {
        return episodeRepository.findByStatusAndMovie_IdOrderByDisplayOrderAsc(true, movieId);
    }

    public Episode getEpisodeByDisplayOrder(Integer movieId, String tap) {
        Integer covertTap = tap.equals("full") ? 1 : Integer.parseInt(tap);
        return episodeRepository
                .findByMovie_IdAndStatusAndDisplayOrder(movieId, true, covertTap)
                .orElse(null);
    }

    public List<Episode> getEpisodeListOfMovieByAdmin(Integer movieId) {
        return episodeRepository.findByMovie_IdOrderByDisplayOrderAsc(movieId);
    }

    public Episode createEpisode(CreateEpisodeRequest request) {
        // Kiểm tra xem request.getMovieId() có tồn tại không

        // Kiểm tra xem request.getDisplayOrder() có tồn tại không
        // Kiểm tra xem loại phim là gì?
        // Nếu loại phim bộ có thể tạo nhiều tập phim
        // Nếu loại phim lẻ, phim chiếu rạp chỉ tạo 1 tập phim
        return null;
    }

    public Episode updateEpisode(Integer id, UpdateEpisodeRequest request) {
        return null;
    }
}
