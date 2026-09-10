package com.saints.movies.security;

import com.saints.movies.common.util.Platform;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class PlatformAspect {

    private final PlatformContextHolder platformContextHolder;

    @Before("@annotation(platformRequired)")
    public void checkPlatform(PlatformRequired platformRequired) {
        String currentPlatform = platformContextHolder.getPlatform();
        Platform required = platformRequired.value();

        if (!currentPlatform.equalsIgnoreCase(required.name())) {
            throw new AccessDeniedException(
                    "Este endpoint solo es accesible desde la plataforma " + required.name()
            );
        }
    }
}