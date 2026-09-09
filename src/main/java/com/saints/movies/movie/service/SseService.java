package com.saints.movies.movie.service;

import com.saints.movies.common.event.RankingUpdatedEvent;
import com.saints.movies.movie.dto.RankingResponse;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SseService {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    public SseEmitter subscribe(){

        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);

        emitters.add(emitter);

        emitter.onCompletion( () -> emitters.remove(emitter));
        emitter.onTimeout( () -> emitters.remove(emitter));

        return emitter;
    }

    public void send(Object data){
        emitters.forEach(e -> {
            try {
                e.send(data);
            } catch (IOException ex) {
                emitters.remove(e);
            }
        });
    }

    @EventListener
    public void onRankingUpdated(RankingUpdatedEvent event) {
        RankingResponse response = RankingResponse.builder()
                .movieId(event.getMovieId())
                .title(event.getTitle())
                .averageRating(event.getAverageRating())
                .ratingCount(event.getRatingCount())
                .message(event.getMessage())
                .build();
        send(response);
    }

}
