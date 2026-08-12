package com.saints.movies.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {

    private Long id;
    private String title;
    private String description;
    private String genre;
    private Integer releaseYear;
    private String posterUrl;
    private BigDecimal averageRating;
    private Integer ratingCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static MovieResponse fromEntity(
            com.saints.movies.movie.model.Movie movie
    ) {
        BigDecimal average = movie.getRatingCount() == 0
                ? BigDecimal.ZERO
                : movie.getRatingSum()
                .divide(
                        new BigDecimal(movie.getRatingCount()),
                        2,
                        RoundingMode.HALF_UP
                );

        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .genre(movie.getGenre())
                .releaseYear(movie.getReleaseYear())
                .posterUrl(movie.getPosterUrl())
                .averageRating(average)
                .ratingCount(movie.getRatingCount())
                .createdAt(movie.getCreatedAt())
                .updatedAt(movie.getUpdatedAt())
                .build();
    }
}