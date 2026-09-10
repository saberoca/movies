package com.saints.movies.platform.controller;

import com.saints.movies.common.util.Platform;
import com.saints.movies.platform.service.PlatformTestService;
import com.saints.movies.security.PlatformRequired;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/platform")
@RequiredArgsConstructor
@Tag(name = "Platform", description = "Endpoints de prueba para validación de plataforma")
public class PlatformTestController {

    private final PlatformTestService platformTestService;

    @GetMapping("/manual/web")
    @Operation(summary = "Solo WEB - validación manual",
            description = "Endpoint accesible solo desde plataforma WEB usando validación manual en el service")
    public ResponseEntity<String> manualWeb() {
        return ResponseEntity.ok(platformTestService.webOnlyManual());
    }

    @GetMapping("/manual/mobile")
    @Operation(summary = "Solo MOBILE - validación manual",
            description = "Endpoint accesible solo desde plataforma MOBILE usando validación manual en el service")
    public ResponseEntity<String> manualMobile() {
        return ResponseEntity.ok(platformTestService.mobileOnlyManual());
    }

    @GetMapping("/spel/web")
    @PreAuthorize("@platformValidator.hasAccess('WEB')")
    @Operation(summary = "Solo WEB - validación SpEL",
            description = "Endpoint accesible solo desde plataforma WEB usando SpEL en @PreAuthorize")
    public ResponseEntity<String> spelWeb() {
        return ResponseEntity.ok("Acceso correcto desde plataforma WEB — validación SpEL");
    }

    @GetMapping("/spel/mobile")
    @PreAuthorize("@platformValidator.hasAccess('MOBILE')")
    @Operation(summary = "Solo MOBILE - validación SpEL",
            description = "Endpoint accesible solo desde plataforma MOBILE usando SpEL en @PreAuthorize")
    public ResponseEntity<String> spelMobile() {
        return ResponseEntity.ok("Acceso correcto desde plataforma MOBILE — validación SpEL");
    }

    @GetMapping("/annotation/web")
    @PlatformRequired(Platform.WEB)
    @Operation(summary = "Solo WEB - anotación personalizada",
            description = "Endpoint accesible solo desde plataforma WEB usando anotación personalizada con AOP")
    public ResponseEntity<String> annotationWeb() {
        return ResponseEntity.ok("Acceso correcto desde plataforma WEB — anotación personalizada");
    }

    @GetMapping("/annotation/mobile")
    @PlatformRequired(Platform.MOBILE)
    @Operation(summary = "Solo MOBILE - anotación personalizada",
            description = "Endpoint accesible solo desde plataforma MOBILE usando anotación personalizada con AOP")
    public ResponseEntity<String> annotationMobile() {
        return ResponseEntity.ok("Acceso correcto desde plataforma MOBILE — anotación personalizada");
    }

}