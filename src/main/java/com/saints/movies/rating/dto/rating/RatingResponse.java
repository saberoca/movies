package com.saints.movies.rating.dto.rating;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatingResponse {
    private Long id;
    private Long movieId;
    private String movieTitle;
    private String userName;
    private BigDecimal score;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
}
