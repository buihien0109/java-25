package com.example.movieapp;

import com.example.movieapp.entity.Blog;
import com.example.movieapp.entity.Movie;
import com.example.movieapp.model.enums.MovieType;
import com.example.movieapp.repository.BlogRepository;
import com.example.movieapp.repository.MovieRepository;
import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@SpringBootTest
class MovieAppApplicationTests {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Test
    void save_movies() {
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();
        Random rd = new Random();

        for (int i = 0; i < 150; i++) {
            String name = faker.name().fullName();
            Boolean status = faker.bool().bool();
            Movie movie = Movie.builder()
                    .name(name)
                    .slug(slugify.slugify(name))
                    .description(faker.lorem().paragraph())
                    .poster(generateLinkImage(name))
                    .releaseYear(faker.number().numberBetween(2020, 2024))
                    .rating(faker.number().randomDouble(1, 6, 10))
                    .trailerUrl("https://www.youtube.com/embed/gCUg6Td5fgQ?si=OCtNkpFF03gq03ny")
                    .type(MovieType.values()[rd.nextInt(MovieType.values().length)])
                    .status(status)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .publishedAt(status ? LocalDateTime.now() : null)
                    .build();
            movieRepository.save(movie);
        }
    }

    @Test
    void save_blogs() {
        Faker faker = new Faker();
        Random rd = new Random();
        Slugify slugify = Slugify.builder().build();

        for (int i = 0; i < 100; i++) {
            String title = faker.book().title();
            boolean status = rd.nextInt(2) == 0;
            Blog blog = Blog.builder()
                    .title(title)
                    .slug(slugify.slugify(title))
                    .description(faker.lorem().paragraph())
                    .content(faker.lorem().paragraph(100))
                    .status(status)
                    .thumbnail(generateLinkImage(title))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .publishedAt(status ? LocalDateTime.now() : null)
                    .build();

            blogRepository.save(blog);
        }
    }

    private String generateLinkImage(String name) {
        return "https://placehold.co/200x200?text=" + name.substring(0, 1).toUpperCase();
    }

    @Test
    void test_methods() {
        List<Movie> movies = movieRepository.findAll();
        System.out.println("Movies size: " + movies.size());

        Movie movie1 = movieRepository.findById(1).orElse(null);
        System.out.println("Movie 1: " + movie1);

        List<Movie> moviesByIds = movieRepository.findAllById(List.of(1, 2, 3));
        System.out.println("moviesByIds: " + moviesByIds);

        // Update
        movie1.setName("Chua te nhung chiec nhan");
        movieRepository.save(movie1);

        // Delete
        movieRepository.delete(movie1); // delete from movies where id=?
//        movieRepository.deleteById(2);
//        movieRepository.deleteAllById(List.of(3, 4, 5));
//        movieRepository.deleteAll();
    }

    @Test
    public void testMethodQuery() {
//        long countPhimBo = movieRepository.countByType(MovieType.PHIM_BO);
//        System.out.println("So luong Phim bo: " + countPhimBo);
//
//        List<Movie> movies = movieRepository.findByStatus(true, Sort.by("name", "releaseYear").descending());
//        movies.forEach(System.out::println);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Movie> pageData = movieRepository.findByStatus(true, pageable);
        System.out.println("Total pages: " + pageData.getTotalPages());
        System.out.println("Total elements: " + pageData.getTotalElements());
        System.out.println("Is first: " + pageData.isFirst());
        pageData.getContent().forEach(System.out::println);
    }
}
