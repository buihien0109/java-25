package com.example.movieapp.service;

import com.example.movieapp.entity.Movie;
import com.example.movieapp.entity.Review;
import com.example.movieapp.entity.User;
import com.example.movieapp.model.request.CreateReviewRequest;
import com.example.movieapp.model.request.UpdateReviewRequest;
import com.example.movieapp.repository.MovieRepository;
import com.example.movieapp.repository.ReviewRepository;
import com.example.movieapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public List<Review> getReviewsByMovieId(Integer movieId) {
        return reviewRepository.findByMovie_Id(movieId, Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public Review createReview(CreateReviewRequest request) {
        // TODO: Fix user. Sau này user sẽ là user đang đăng nhập
        Integer userId = 1;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + request.getMovieId()));

        // TODO: Bổ sung validate rating, content
        Review review = Review.builder()
                .content(request.getContent())
                .rating(request.getRating())
                .movie(movie)
                .user(user)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return reviewRepository.save(review);
    }

    public Review updateReview(Integer id, UpdateReviewRequest request) {
        // TODO: Fix user. Sau này user sẽ là user đang đăng nhập
        Integer userId = 1;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));

        if (!review.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not owner of this review");
        }

        // TODO: Bổ sung validate rating, content
        review.setContent(request.getContent());
        review.setRating(request.getRating());
        review.setUpdatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    public void deleteReview(Integer id) {
        // TODO: Fix user. Sau này user sẽ là user đang đăng nhập
        Integer userId = 1;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));

        if (!review.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not owner of this review");
        }
        reviewRepository.delete(review);
    }
}
