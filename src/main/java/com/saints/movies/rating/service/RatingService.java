package com.saints.movies.rating.service;

import com.saints.movies.common.event.RankingUpdatedEvent;
import com.saints.movies.common.exception.ResourceNotFoundException;
import com.saints.movies.movie.model.Movie;
import com.saints.movies.movie.repository.MovieRepository;
import com.saints.movies.rating.dto.rating.RatingRequest;
import com.saints.movies.rating.dto.rating.RatingResponse;
import com.saints.movies.rating.helper.RatingHelper;
import com.saints.movies.rating.model.Rating;
import com.saints.movies.rating.repository.RatingHistoryRepository;
import com.saints.movies.rating.repository.RatingRepository;
import com.saints.movies.user.model.User;
import com.saints.movies.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final RatingHistoryRepository ratingHistoryRepository;
    private final RatingHelper ratingHelper;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public RatingResponse voteMovie(RatingRequest request, Long userId){

        Optional<Rating> existRating = ratingRepository.findByUserIdAndMovieId(userId, request.getMovieId());

        if (existRating.isPresent()){
            Rating rating = existRating.get();
            BigDecimal previousScore = rating.getScore();
            rating.getMovie().setRatingSum(rating.getMovie().getRatingSum().subtract(previousScore).add(request.getScore()));
            rating.setScore(request.getScore());
            Movie movie = movieRepository.save(rating.getMovie());
            Rating savedRating = ratingRepository.save(rating);
            ratingHistoryRepository.save(ratingHelper.toRatingHistory(rating, previousScore));
            eventPublisher.publishEvent(new RankingUpdatedEvent(
                    this,
                    movie.getId(),
                    movie.getTitle(),
                    ratingHelper.calculateAverage(movie.getRatingSum(), movie.getRatingCount()),
                    movie.getRatingCount()
            ));
            return ratingHelper.toResponse(savedRating);
        } else {
            User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrada con id: " + userId));
            Movie movie = movieRepository.findById(request.getMovieId()).orElseThrow(() -> new ResourceNotFoundException("Pelicula no encontrada con id: " + request.getMovieId()));
            movie.setRatingSum(movie.getRatingSum().add(request.getScore()));
            movie.setRatingCount(movie.getRatingCount() + 1);
            movieRepository.save(movie);
            Rating rating = Rating.builder()
                    .user(user)
                    .movie(movie)
                    .score(request.getScore())
                    .build();
            Rating savedRating = ratingRepository.save(rating);
            ratingHistoryRepository.save(ratingHelper.toRatingHistory(rating, null));
            eventPublisher.publishEvent(new RankingUpdatedEvent(
                    this,
                    movie.getId(),
                    movie.getTitle(),
                    ratingHelper.calculateAverage(movie.getRatingSum(), movie.getRatingCount()),
                    movie.getRatingCount()
            ));
            return ratingHelper.toResponse(savedRating);
        }
    }

}
