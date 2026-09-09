package com.saints.movies.rating.helper;

import com.saints.movies.rating.dto.rating.RatingResponse;
import com.saints.movies.rating.model.Rating;
import com.saints.movies.rating.model.RatingHistory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

    public BigDecimal calculateAverage(BigDecimal ratingSum, Integer ratingCount) {
        if (ratingCount == 0) return BigDecimal.ZERO;
        return ratingSum.divide(
                new BigDecimal(ratingCount), 2, RoundingMode.HALF_UP
        );
    }

}
