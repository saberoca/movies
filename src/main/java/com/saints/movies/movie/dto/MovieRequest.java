package com.saints.movies.movie.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(description = "Datos requeridos para crear o actualizar una película")
public class MovieRequest {

    @NotBlank(message = "El título es requerido")
    @Size(max = 100, message = "El título no puede exceder 100 caracteres")
    @Schema(example = "The Godfather")
    private String title;

    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    @Schema(description = "Sinopsis de la película",
            example = "The aging patriarch of an organized crime dynasty transfers control to his reluctant son.")
    private String description;

    @NotBlank(message = "El género es requerido")
    @Size(max = 50, message = "El género no puede exceder 50 caracteres")
    @Schema(example = "Crime")
    private String genre;

    @NotNull(message = "El año de lanzamiento es requerido")
    @Min(value = 1888, message = "El año de lanzamiento no puede ser anterior a 1888")
    @Max(value = 2100, message = "El año de lanzamiento no es válido")
    @Schema(description = "Año de lanzamiento de la película. Mínimo 1888 (primera película de la historia)",
            example = "1972")
    private Integer releaseYear;

    @Size(max = 500, message = "La URL del poster no puede exceder 500 caracteres")
    @Schema(description = "URL de la imagen del poster de la película",
            example = "https://example.com/godfather.jpg")
    private String posterUrl;
}