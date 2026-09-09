package com.saints.movies.common.event;

import org.springframework.context.ApplicationEvent;

import java.math.BigDecimal;

public class RankingUpdatedEvent extends ApplicationEvent {

    private final Long movieId;
    private final String title;
    private final BigDecimal averageRating;
    private final Integer ratingCount;
    private final String message;

    public RankingUpdatedEvent(
            Object source,
            Long movieId,
            String title,
            BigDecimal averageRating,
            Integer ratingCount
    ) {
        super(source);
        this.movieId = movieId;
        this.title = title;
        this.averageRating = averageRating;
        this.ratingCount = ratingCount;
        this.message = "La película " + title
                + " está valorada con " + averageRating
                + " en nuestro top";
    }

    public Long getMovieId() { return movieId; }
    public String getTitle() { return title; }
    public BigDecimal getAverageRating() { return averageRating; }
    public Integer getRatingCount() { return ratingCount; }
    public String getMessage() { return message; }
}