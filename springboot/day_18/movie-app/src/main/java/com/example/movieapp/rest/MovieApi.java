package com.example.movieapp.rest;

import com.example.movieapp.entity.Movie;
import com.example.movieapp.model.request.UpsertMovieRequest;
import com.example.movieapp.model.response.FileResponse;
import com.example.movieapp.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/movies")
@RequiredArgsConstructor
public class MovieApi {
    private final MovieService movieService;

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable Integer id,
                                         @RequestBody UpsertMovieRequest request) {
        Movie movie = movieService.updateMovie(id, request);
        return ResponseEntity.ok(movie);
    }

    @PostMapping("/{id}/upload-poster")
    public ResponseEntity<?> uploadPoster(@PathVariable Integer id,
                                          @RequestParam MultipartFile file) {
        String path = movieService.uploadPoster(id, file);
        FileResponse fileResponse = FileResponse.builder()
                .url(path)
                .build();
        return ResponseEntity.ok(fileResponse);
    }
}
