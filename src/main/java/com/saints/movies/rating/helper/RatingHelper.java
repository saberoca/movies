package com.saints.movies.rating.helper;

import com.saints.movies.rating.dto.rating.RatingResponse;
import com.saints.movies.rating.model.Rating;
import com.saints.movies.rating.model.RatingHistory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RatingHelper {

    public RatingResponse toResponse(Rating rating){
        return RatingResponse.builder()
                .id(rating.getId())
                .movieId(rating.getMovie().getId())
                .movieTitle(rating.getMovie().getTitle())
                .userName(rating.getUser().getUserName())
                .score(rating.getScore())
                .createAt(rating.getCreatedAt())
                .updatedAt(rating.getUpdatedAt())
                .build();
    }

    public RatingHistory toRatingHistory(Rating rating, BigDecimal previousScore){
        return RatingHistory.builder()
                .user(rating.getUser())
                .movie(rating.getMovie())
                .previousScore(previousScore)
                .newScore(rating.getScore())
                .build();
    }

}
