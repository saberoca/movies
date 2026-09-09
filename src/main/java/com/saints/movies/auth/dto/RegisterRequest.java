package com.saints.movies.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterRequest {

    @NotBlank(message = "El nombre de usuario es requerido")
    @Size(min = 3, max = 50, message = "El nombre de usuario debe tener entre 3 y 50 caracteres")
    @Schema(description = "Nombre de usuario único", example = "santos_dev")
    private String userName;

    @NotBlank(message = "El email es requerido")
    @Email(message = "El email no tiene un formato válido")
    @Schema(example = "userName@mail.com")
    private String email;

    @NotBlank(message = "La contraseña es requerida")
    @Size(min = 8, message = "La contraseña debe tener mínimo 8 caracteres")
    @Schema(example = "Pwd152358")
    private String password;

    @NotBlank(message = "El nombre es requerido")
    @Size(max = 250, message = "El nombre no puede exceder 250 caracteres")
    @Schema(example = "Santos")
    private String name;

    @NotBlank(message = "El apellido es requerido")
    @Size(max = 250, message = "El apellido no puede exceder 250 caracteres")
    @Schema(example = "Apellido")
    private String lastName;

    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    @Schema(description = "Fecha de nacimiento en formato ISO 8601", example = "2001-01-01")
    private LocalDate birthDate;

    @Size(max = 20, message = "El género no puede exceder 20 caracteres")
    @Schema(description = "Género del usuario", example = "Male")
    private String gender;
}