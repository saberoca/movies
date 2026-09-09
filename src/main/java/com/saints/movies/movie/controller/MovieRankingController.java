package com.saints.movies.movie.controller;

import com.saints.movies.movie.dto.RankingResponse;
import com.saints.movies.movie.service.MovieService;
import com.saints.movies.movie.service.SseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/ranking")
@RestController
public class MovieRankingController {

    private final MovieService movieService;
    private final SseService sseService;

    @GetMapping
    public ResponseEntity<List<RankingResponse>> getRanking(){
        return ResponseEntity.ok(movieService.getRanking());
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamRanking(){
        return sseService.subscribe();
    }
}
