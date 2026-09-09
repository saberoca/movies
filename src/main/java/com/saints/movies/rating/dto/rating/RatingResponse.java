package com.saints.movies.rating.dto.rating;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Resultado del voto registrado o actualizado para una película")
public class RatingResponse {

    @Schema(description = "Identificador único del voto", example = "1")
    private Long id;

    @Schema(description = "Identificador único de la película votada", example = "1")
    private Long movieId;

    @Schema(description = "Título de la película votada", example = "The Godfather")
    private String movieTitle;

    @Schema(description = "Nombre de usuario que emitió el voto", example = "santos_dev")
    private String userName;

    @Schema(description = "Puntuación asignada en escala del 1.0 al 5.0", example = "4.5")
    private BigDecimal score;

    @Schema(description = "Fecha y hora en que se registró el voto",
            example = "2024-01-15T10:30:00")
    private LocalDateTime createAt;

    @Schema(description = "Fecha y hora de la última actualización del voto",
            example = "2024-01-15T12:00:00")
    private LocalDateTime updatedAt;
}