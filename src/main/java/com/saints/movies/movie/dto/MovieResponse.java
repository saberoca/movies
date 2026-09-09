package com.saints.movies.movie.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Datos completos de una película incluyendo su puntuación actual")
public class MovieResponse {

    @Schema(description = "Identificador único de la película", example = "1")
    private Long id;

    @Schema(example = "The Godfather")
    private String title;

    @Schema(description = "Sinopsis de la película",
            example = "The aging patriarch of an organized crime dynasty transfers control to his reluctant son.")
    private String description;

    @Schema(example = "Crime")
    private String genre;

    @Schema(description = "Año de lanzamiento de la película", example = "1972")
    private Integer releaseYear;

    @Schema(description = "URL de la imagen del poster de la película",
            example = "https://example.com/godfather.jpg")
    private String posterUrl;

    @Schema(description = "Promedio de puntuación calculado en escala del 1.0 al 5.0",
            example = "4.50")
    private BigDecimal averageRating;

    @Schema(description = "Cantidad total de votos recibidos", example = "1250")
    private Integer ratingCount;

    @Schema(description = "Fecha y hora de creación del registro",
            example = "2024-01-15T10:30:00")
    private LocalDateTime createdAt;

    @Schema(description = "Fecha y hora de la última actualización",
            example = "2024-01-15T10:30:00")
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