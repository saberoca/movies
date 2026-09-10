package com.saints.movies.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("platformValidator")
@RequiredArgsConstructor
public class PlatformValidator {

    private final PlatformContextHolder platformContextHolder;

    public boolean hasAccess(String requiredPlatform) {
        try {
            String currentPlatform = platformContextHolder.getPlatform();
            return currentPlatform.equalsIgnoreCase(requiredPlatform);
        } catch (Exception e) {
            return false;
        }
    }
}