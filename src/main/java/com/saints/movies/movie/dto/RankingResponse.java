package com.saints.movies.movie.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Posición de una película en el ranking con su puntuación actual")
public class RankingResponse {

    @Schema(description = "Identificador único de la película", example = "1")
    private Long movieId;

    @Schema(example = "The Godfather")
    private String title;

    @Schema(description = "Promedio de puntuación en escala del 1.0 al 5.0", example = "4.50")
    private BigDecimal averageRating;

    @Schema(description = "Cantidad total de votos recibidos", example = "1250")
    private Integer ratingCount;

    @Schema(description = "Mensaje descriptivo de la posición en el ranking",
            example = "La película The Godfather está valorada con 4.50 en nuestro top")
    private String message;
}