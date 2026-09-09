package com.saints.movies.movie.controller;

import com.saints.movies.common.ApiConstants;
import com.saints.movies.movie.dto.MovieRequest;
import com.saints.movies.movie.dto.MovieResponse;
import com.saints.movies.movie.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/movies")
@RestController
@Tag(name = ApiConstants.MOVIE_TAG, description = ApiConstants.MOVIE_DESCRIPTION)
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    @Operation(summary = "Busca peliculas con filtro opcional", description = "Busca todas las peliculas con filtro opcional filtro = {genero, titulo, año}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ApiConstants.SUCCESSFUL, description = ApiConstants.SUCCESSFUL_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.RESOURCE_NOT_FOUND, description = ApiConstants.RESOURCE_NOT_FOUND_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.INTERNAL_ERROR, description = ApiConstants.INTERNAL_ERROR_MESSAGE)
    })
    public ResponseEntity<List<MovieResponse>> getAllMovies(@RequestParam(required = false) String genre,
                                                            @RequestParam(required = false) String title,
                                                            @RequestParam(required = false) Integer year){
        return ResponseEntity.ok(movieService.getMovies(genre,year,title));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca pelicula por id", description = "Busca una pelicula por el id de la pelicula")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ApiConstants.SUCCESSFUL, description = ApiConstants.SUCCESSFUL_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.RESOURCE_NOT_FOUND, description = ApiConstants.RESOURCE_NOT_FOUND_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.INTERNAL_ERROR, description = ApiConstants.INTERNAL_ERROR_MESSAGE)
    })
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable Long id){
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(summary = "Crear pelicula", description = "Crear una nueva pelicula")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ApiConstants.CREATED, description = ApiConstants.CREATED_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.BAD_REQUEST, description = ApiConstants.BAD_REQUEST_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.UNAUTHORIZED, description = ApiConstants.UNAUTHORIZED_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.FORBIDDEN, description = ApiConstants.FORBIDDEN_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.RESOURCE_NOT_FOUND, description = ApiConstants.RESOURCE_NOT_FOUND_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.INTERNAL_ERROR, description = ApiConstants.INTERNAL_ERROR_MESSAGE)
    })
    public ResponseEntity<MovieResponse> createMovie(@Valid @RequestBody MovieRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.createMovie(request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar pelicula", description = "Actualizar una pelicula buscando por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ApiConstants.SUCCESSFUL, description = ApiConstants.SUCCESSFUL_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.BAD_REQUEST, description = ApiConstants.BAD_REQUEST_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.UNAUTHORIZED, description = ApiConstants.UNAUTHORIZED_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.FORBIDDEN, description = ApiConstants.FORBIDDEN_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.RESOURCE_NOT_FOUND, description = ApiConstants.RESOURCE_NOT_FOUND_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.INTERNAL_ERROR, description = ApiConstants.INTERNAL_ERROR_MESSAGE)
    })
    public ResponseEntity<MovieResponse> updateMovie(@Valid @RequestBody MovieRequest request, @PathVariable Long id){
        return ResponseEntity.ok(movieService.updateMovie(id, request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar pelicula", description = "Eliminar pelicula por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ApiConstants.NO_CONTENT, description = ApiConstants.NO_CONTENT_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.UNAUTHORIZED, description = ApiConstants.UNAUTHORIZED_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.FORBIDDEN, description = ApiConstants.FORBIDDEN_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.RESOURCE_NOT_FOUND, description = ApiConstants.RESOURCE_NOT_FOUND_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.INTERNAL_ERROR, description = ApiConstants.INTERNAL_ERROR_MESSAGE)
    })
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

}
