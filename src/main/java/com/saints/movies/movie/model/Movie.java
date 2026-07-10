package com.saints.movies.movie.model;

import jakarta.persistence.*;
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
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, length = 50)
    private String genre;

    @Column(name = "release_year", nullable = false, columnDefinition = "YEAR")
    private Integer releaseYear;

    @Column(name = "poster_url", length = 500)
    private String posterUrl;

    @Column(name = "rating_sum", nullable = false)
    private BigDecimal ratingSum;

    @Column(name = "rating_count", nullable = false)
    private Integer ratingCount;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.ratingSum = BigDecimal.ZERO;
        this.ratingCount = 0;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}