package com.saints.movies.rating.controller;

import com.saints.movies.rating.dto.rating.RatingRequest;
import com.saints.movies.rating.dto.rating.RatingResponse;
import com.saints.movies.rating.service.RatingService;
import com.saints.movies.security.UserDetailsImpl;
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
public class RatingController {

    private final RatingService ratingService;

    @PostMapping("/vote")
    public ResponseEntity<RatingResponse> voteMovie(@Valid @RequestBody RatingRequest request,
                                                    @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(ratingService.voteMovie(request, userDetails.getId()));
    }
}
