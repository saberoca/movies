package com.saints.movies.auth.dto;

import com.saints.movies.common.util.Platform;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "El email es requerido")
    @Email(message = "El email no tiene un formato válido")
    private String email;

    @NotBlank(message = "La contraseña es requerida")
    private String password;

    @NotNull(message = "La plataforma es requerida")
    private Platform platform;
}