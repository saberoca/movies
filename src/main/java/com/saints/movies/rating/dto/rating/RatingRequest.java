package com.saints.movies.rating.dto.rating;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RatingRequest {
    @NotNull(message = "El id de la película es requerido")
    private Long movieId;

    @NotNull(message = "El score es requerido")
    @DecimalMin(value = "1.0", message = "El score mínimo es 1.0")
    @DecimalMax(value = "5.0", message = "El score máximo es 5.0")
    private BigDecimal score;
}
