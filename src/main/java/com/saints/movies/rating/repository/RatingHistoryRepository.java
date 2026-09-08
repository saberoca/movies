package com.saints.movies.rating.repository;

import com.saints.movies.rating.model.RatingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingHistoryRepository extends JpaRepository<RatingHistory, Long> {
}
