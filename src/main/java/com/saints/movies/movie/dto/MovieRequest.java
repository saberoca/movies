package com.saints.movies.movie.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class MovieRequest {

    @NotBlank(message = "El título es requerido")
    @Size(max = 100, message = "El título no puede exceder 100 caracteres")
    private String title;

    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String description;

    @NotBlank(message = "El género es requerido")
    @Size(max = 50, message = "El género no puede exceder 50 caracteres")
    private String genre;

    @NotNull(message = "El año de lanzamiento es requerido")
    @Min(value = 1888, message = "El año de lanzamiento no puede ser anterior a 1888")
    @Max(value = 2100, message = "El año de lanzamiento no es válido")
    private Integer releaseYear;

    @Size(max = 500, message = "La URL del poster no puede exceder 500 caracteres")
    private String posterUrl;
}