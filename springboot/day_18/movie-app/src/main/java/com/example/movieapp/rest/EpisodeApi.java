package com.example.movieapp.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.movieapp.entity.Episode;
import com.example.movieapp.model.request.CreateEpisodeRequest;
import com.example.movieapp.model.request.UpdateEpisodeRequest;
import com.example.movieapp.service.EpisodeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/episodes")
@RequiredArgsConstructor
public class EpisodeApi {
    private final EpisodeService episodeService;

    @PostMapping
    public ResponseEntity<?> createEpisode(@Valid @RequestBody CreateEpisodeRequest request) {
        Episode episode = episodeService.createEpisode(request);
        return ResponseEntity.ok(episode);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEpisode(@Valid @RequestBody UpdateEpisodeRequest request,
            @PathVariable Integer id) {
        Episode episode = episodeService.updateEpisode(id, request);
        return ResponseEntity.ok(episode);
    }
}
