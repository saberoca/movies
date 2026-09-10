package com.saints.movies.auth.controller;

import com.saints.movies.auth.dto.AuthResponse;
import com.saints.movies.auth.dto.LoginRequest;
import com.saints.movies.auth.dto.RegisterRequest;
import com.saints.movies.auth.service.AuthService;
import com.saints.movies.common.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = ApiConstants.AUTH_TAG, description = ApiConstants.AUTH_DESCRIPTION)
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "Registro de usuario", description = "Endpoint para registrar un nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ApiConstants.SUCCESSFUL, description = ApiConstants.SUCCESSFUL_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.BAD_REQUEST, description = ApiConstants.BAD_REQUEST_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.UNAUTHORIZED, description = ApiConstants.UNAUTHORIZED_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.FORBIDDEN, description = ApiConstants.FORBIDDEN_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.RESOURCE_NOT_FOUND, description = ApiConstants.RESOURCE_NOT_FOUND_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.INTERNAL_ERROR, description = ApiConstants.INTERNAL_ERROR_MESSAGE)
    })
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    @Operation(summary = "Login de usuario", description = "Endpoint para realizar login al sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ApiConstants.CREATED, description = ApiConstants.CREATED_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.BAD_REQUEST, description = ApiConstants.BAD_REQUEST_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.UNAUTHORIZED, description = ApiConstants.UNAUTHORIZED_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.FORBIDDEN, description = ApiConstants.FORBIDDEN_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.RESOURCE_NOT_FOUND, description = ApiConstants.RESOURCE_NOT_FOUND_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.INTERNAL_ERROR, description = ApiConstants.INTERNAL_ERROR_MESSAGE)
    })
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}