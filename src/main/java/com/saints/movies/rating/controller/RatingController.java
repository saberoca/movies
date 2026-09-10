package com.saints.movies.rating.controller;

import com.saints.movies.common.ApiConstants;
import com.saints.movies.rating.dto.rating.RatingRequest;
import com.saints.movies.rating.dto.rating.RatingResponse;
import com.saints.movies.rating.service.RatingService;
import com.saints.movies.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("/api/v1/rating")
@RestController
@Tag(name = ApiConstants.RATING_TAG, description = ApiConstants.RATING_DESCRIPTION)
public class RatingController {

    private final RatingService ratingService;

    @PostMapping("/vote")
    @Operation(summary = "Votar por una pelicula", description = "Permite votar por una pelicula")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ApiConstants.SUCCESSFUL, description = ApiConstants.SUCCESSFUL_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.BAD_REQUEST, description = ApiConstants.BAD_REQUEST_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.UNAUTHORIZED, description = ApiConstants.UNAUTHORIZED_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.FORBIDDEN, description = ApiConstants.FORBIDDEN_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.RESOURCE_NOT_FOUND, description = ApiConstants.RESOURCE_NOT_FOUND_MESSAGE),
            @ApiResponse(responseCode = ApiConstants.INTERNAL_ERROR, description = ApiConstants.INTERNAL_ERROR_MESSAGE)
    })
    public ResponseEntity<RatingResponse> voteMovie(@Valid @RequestBody RatingRequest request,
                                                    @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(ratingService.voteMovie(request, userDetails.getId()));
    }
}
