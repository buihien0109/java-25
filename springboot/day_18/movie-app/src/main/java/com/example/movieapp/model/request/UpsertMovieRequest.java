package com.example.movieapp.model.request;

import com.example.movieapp.model.enums.MovieType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpsertMovieRequest {
    String name;
    String trailerUrl;
    String description;
    List<Integer> genreIds;
    List<Integer> actorIds;
    List<Integer> directorIds;
    Boolean status;
    MovieType type;
    Integer releaseYear;
    Integer countryId;
}
