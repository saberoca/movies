package com.saints.movies.movie.helper;

import com.saints.movies.movie.dto.MovieRequest;
import com.saints.movies.movie.dto.MovieResponse;
import com.saints.movies.movie.model.Movie;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    public Movie toUpdateData(Movie movie, MovieRequest request){
        return Movie.builder()
                .id(movie.getId())
                .title(isStringValid(request.getTitle()) ? request.getTitle() : movie.getTitle())
                .description(isStringValid(request.getDescription()) ? request.getDescription() : movie.getDescription())
                .genre(isStringValid(request.getGenre()) ? request.getGenre() : movie.getGenre())
                .releaseYear(request.getReleaseYear() != null ? request.getReleaseYear() : movie.getReleaseYear())
                .posterUrl(isStringValid(request.getPosterUrl()) ? request.getPosterUrl() : movie.getPosterUrl())
                .ratingSum(movie.getRatingSum())
                .ratingCount(movie.getRatingCount())
                .createdAt(movie.getCreatedAt())
                .updatedAt(movie.getUpdatedAt())
                .build();
    }

    private BigDecimal getAverage(BigDecimal averageSum, Integer count) {
        if (count == 0) return BigDecimal.ZERO;
        return averageSum.divide(new BigDecimal(count), 2, RoundingMode.HALF_UP);
    }

    private Boolean isStringValid(String value){
        return value != null && !value.trim().isEmpty();
    }

}
