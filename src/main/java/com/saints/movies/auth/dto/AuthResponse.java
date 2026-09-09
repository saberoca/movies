package com.saints.movies.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta de autenticación con token JWT y datos del usuario")
public class AuthResponse {

    @Schema(description = "Token JWT para autenticar requests posteriores",
            example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyQG1haWwuY29tIn0.abc123")
    private String token;

    @Schema(example = "santos_dev")
    private String userName;

    @Schema(example = "userName@mail.com")
    private String email;

    @Schema(description = "Rol del usuario en el sistema", example = "USER",
            allowableValues = {"USER", "ADMIN"})
    private String role;

    @Schema(description = "Plataforma desde la que se autenticó", example = "WEB",
            allowableValues = {"WEB", "MOBILE"})
    private String platform;
}