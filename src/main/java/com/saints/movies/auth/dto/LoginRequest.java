package com.saints.movies.auth.dto;

import com.saints.movies.common.util.Platform;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "El email es requerido")
    @Email(message = "El email no tiene un formato válido")
    @Schema(example = "userName@mail.com")
    private String email;

    @NotBlank(message = "La contraseña es requerida")
    @Schema(example = "Pwd152358")
    private String password;

    @NotNull(message = "La plataforma es requerida")
    @Schema(description = "Plataforma desde la que se autentica el usuario", example = "WEB", allowableValues = {"WEB", "MOBILE"})
    private Platform platform;
}