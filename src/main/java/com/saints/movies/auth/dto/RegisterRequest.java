package com.saints.movies.auth.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterRequest {

    @NotBlank(message = "El nombre de usuario es requerido")
    @Size(min = 3, max = 50, message = "El nombre de usuario debe tener entre 3 y 50 caracteres")
    private String userName;

    @NotBlank(message = "El email es requerido")
    @Email(message = "El email no tiene un formato válido")
    private String email;

    @NotBlank(message = "La contraseña es requerida")
    @Size(min = 8, message = "La contraseña debe tener mínimo 8 caracteres")
    private String password;

    @NotBlank(message = "El nombre es requerido")
    @Size(max = 250, message = "El nombre no puede exceder 250 caracteres")
    private String name;

    @NotBlank(message = "El apellido es requerido")
    @Size(max = 250, message = "El apellido no puede exceder 250 caracteres")
    private String lastName;

    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    private LocalDate birthDate;

    @Size(max = 20, message = "El género no puede exceder 20 caracteres")
    private String gender;
}