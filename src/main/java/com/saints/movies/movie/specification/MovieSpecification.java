package com.saints.movies.movie.specification;

import com.saints.movies.movie.model.Movie;
import org.springframework.data.jpa.domain.Specification;

public class MovieSpecification {

    // Condición 1: filtrar por género exacto (ignorando mayúsculas)
    public static Specification<Movie> hasGenre(String genre) {
        return (root, query, cb) -> {
            if (genre == null || genre.isBlank()) {
                return null; // ← este filtro se ignora
            }
            return cb.equal(root.get("genre"), genre.toLowerCase());
        };
    }

    // Condición 2: filtrar por año exacto
    public static Specification<Movie> hasYear(Integer year) {
        return (root, query, cb) -> {
            if (year == null) {
                return null; // ← este filtro se ignora
            }
            return cb.equal(root.get("releaseYear"), year);
        };
    }

    // Condición 3: buscar por título parcial (ignorando mayúsculas)
    public static Specification<Movie> hasTitle(String title) {
        return (root, query, cb) -> {
            if (title == null || title.isBlank()) {
                return null; // ← este filtro se ignora
            }
            return cb.like(root.get("title"), title);
        };
    }
}