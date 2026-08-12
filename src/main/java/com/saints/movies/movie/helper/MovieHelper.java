package com.saints.movies.movie.helper;

import com.saints.movies.movie.dto.MovieRequest;
import com.saints.movies.movie.dto.MovieResponse;
import com.saints.movies.movie.model.Movie;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class MovieHelper {

    public List<MovieResponse> toListResponse(List<Movie> movie) {
        return movie.stream()
                .map(movies -> {
                    return MovieResponse.builder()
                            .id(movies.getId())
                            .title(movies.getTitle())
                            .description(movies.getDescription())
                            .genre(movies.getGenre())
                            .releaseYear(movies.getReleaseYear())
                            .posterUrl(movies.getPosterUrl())
                            .averageRating(getAverage(movies.getRatingSum(), movies.getRatingCount()))
                            .ratingCount(movies.getRatingCount())
                            .createdAt(movies.getCreatedAt())
                            .updatedAt(movies.getUpdatedAt())
                            .build();
                })
                .toList();
    }

    public MovieResponse toSingleResponse(Movie movie) {
        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .genre(movie.getGenre())
                .releaseYear(movie.getReleaseYear())
                .posterUrl(movie.getPosterUrl())
                .averageRating(getAverage(movie.getRatingSum(), movie.getRatingCount()))
                .ratingCount(movie.getRatingCount())
                .createdAt(movie.getCreatedAt())
                .updatedAt(movie.getUpdatedAt())
                .build();

    }

    public Movie requestToMovie(MovieRequest request){
        return Movie.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .genre(request.getGenre())
                .releaseYear(request.getReleaseYear())
                .posterUrl(request.getPosterUrl())
                .build();
    }

    private BigDecimal getAverage(BigDecimal averageSum, Integer count) {

        return averageSum.divide(new BigDecimal(count), 2, RoundingMode.HALF_UP);

    }

}
