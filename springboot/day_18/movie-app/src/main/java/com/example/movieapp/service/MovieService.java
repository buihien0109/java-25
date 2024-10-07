package com.example.movieapp.service;

import com.example.movieapp.entity.*;
import com.example.movieapp.model.enums.MovieType;
import com.example.movieapp.model.request.UpsertMovieRequest;
import com.example.movieapp.repository.*;
import com.github.slugify.Slugify;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final CountryRepository countryRepository;
    private final GenreRepository genreRepository;
    private final ActorRepository actorRepository;
    private final DirectorRepository directorRepository;
    private final CloudinaryService cloudinaryService;

    public Page<Movie> getMoviesByType(MovieType type, Boolean status, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("createdAt").descending());
        return movieRepository.findByTypeAndStatus(type, status, pageable);
    }

    public Movie getMovieDetails(Integer id, String slug) {
        return movieRepository.findByIdAndSlugAndStatus(id, slug, true)
                .orElse(null);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll(Sort.by("createdAt").descending());
    }

    public Movie getMovieById(Integer id) {
        return movieRepository.findById(id)
                .orElse(null);
    }

    public Movie updateMovie(Integer id, UpsertMovieRequest request) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new RuntimeException("Country not found"));

        List<Genre> genres = genreRepository.findAllById(request.getGenreIds());
        List<Actor> actors = actorRepository.findAllById(request.getActorIds());
        List<Director> directors = directorRepository.findAllById(request.getDirectorIds());

        Slugify slugify = Slugify.builder().build();
        movie.setName(request.getName());
        movie.setSlug(slugify.slugify(request.getName()));
        movie.setTrailerUrl(request.getTrailerUrl());
        movie.setDescription(request.getDescription());
        movie.setGenres(genres);
        movie.setActors(actors);
        movie.setDirectors(directors);
        movie.setStatus(request.getStatus());
        movie.setType(request.getType());
        movie.setReleaseYear(request.getReleaseYear());
        movie.setCountry(country);
        movie.setUpdatedAt(LocalDateTime.now());
        movie.setPublishedAt(request.getStatus() ? LocalDateTime.now() : null);
        return movieRepository.save(movie);
    }

    public String uploadPoster(Integer id, MultipartFile file) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        try {
            Map map = cloudinaryService.uploadFile(file, "java-25-movie");
            System.out.println(map);
            String path = map.get("url").toString();
            movie.setPoster(path);
            movieRepository.save(movie);
            return path;
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload poster");
        }
    }
}
