package com.saints.movies.auth.service;

import com.saints.movies.auth.dto.AuthResponse;
import com.saints.movies.auth.dto.LoginRequest;
import com.saints.movies.auth.dto.RegisterRequest;
import com.saints.movies.common.util.Platform;
import com.saints.movies.security.UserDetailsImpl;
import com.saints.movies.security.service.JwtService;
import com.saints.movies.user.model.User;
import com.saints.movies.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        if (userRepository.existsByUserName(request.getUserName())) {
            throw new IllegalArgumentException("El nombre de usuario ya está en uso");
        }

        User user = User.builder()
                .userName(request.getUserName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .lastName(request.getLastName())
                .birthDate(request.getBirthDate())
                .gender(request.getGender())
                .build();

        userRepository.save(user);

        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        String token = jwtService.generateToken(userDetails, Platform.WEB.name());

        return AuthResponse.builder()
                .token(token)
                .userName(user.getUserName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .platform(Platform.WEB.name())
                .build();
    }

    public AuthResponse login(LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String platform = request.getPlatform().name();
        String token = jwtService.generateToken(userDetails, platform);

        return AuthResponse.builder()
                .token(token)
                .userName(userDetails.getUserName())
                .email(userDetails.getUsername())
                .role(userDetails.getRole().name())
                .platform(platform)
                .build();
    }
}