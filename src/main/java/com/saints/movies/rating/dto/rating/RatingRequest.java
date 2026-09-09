package com.saints.movies.rating.dto.rating;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "Datos requeridos para registrar o actualizar el voto de una película")
public class RatingRequest {

    @NotNull(message = "El id de la película es requerido")
    @Schema(description = "Identificador único de la película a votar", example = "1")
    private Long movieId;

    @NotNull(message = "El score es requerido")
    @DecimalMin(value = "1.0", message = "El score mínimo es 1.0")
    @DecimalMax(value = "5.0", message = "El score máximo es 5.0")
    @Schema(description = "Puntuación asignada a la película en escala del 1.0 al 5.0",
            example = "4.5")
    private BigDecimal score;
}