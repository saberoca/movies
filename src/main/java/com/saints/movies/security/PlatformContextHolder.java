package com.saints.movies.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class PlatformContextHolder {

    public String getPlatform() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getCredentials() == null) {
            throw new AccessDeniedException("No se encontró información de plataforma en el token");
        }

        return authentication.getCredentials().toString();
    }
}