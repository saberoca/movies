package com.saints.movies.rating.repository;

import com.saints.movies.rating.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    boolean existsByUserIdAndMovieId(Long userId, Long movieId);

    Optional<Rating> findByUserIdAndMovieId(Long userId, Long movieId);

}
