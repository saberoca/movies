package com.saints.movies.platform.service;

import com.saints.movies.security.PlatformContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlatformTestService {

    private final PlatformContextHolder platformContextHolder;

    public String webOnlyManual() {
        String platform = platformContextHolder.getPlatform();
        if (!platform.equals("WEB")) {
            throw new AccessDeniedException(
                    "Este endpoint solo es accesible desde la plataforma WEB"
            );
        }
        return "Acceso correcto desde plataforma WEB — validación manual";
    }

    public String mobileOnlyManual() {
        String platform = platformContextHolder.getPlatform();
        if (!platform.equals("MOBILE")) {
            throw new AccessDeniedException(
                    "Este endpoint solo es accesible desde la plataforma MOBILE"
            );
        }
        return "Acceso correcto desde plataforma MOBILE — validación manual";
    }
}