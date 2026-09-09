package com.saints.movies.movie.service;

import com.saints.movies.common.exception.ResourceNotFoundException;
import com.saints.movies.movie.dto.MovieRequest;
import com.saints.movies.movie.dto.MovieResponse;
import com.saints.movies.movie.dto.RankingResponse;
import com.saints.movies.movie.helper.MovieHelper;
import com.saints.movies.movie.model.Movie;
import com.saints.movies.movie.repository.MovieRepository;
import com.saints.movies.movie.specification.MovieSpecification;
import com.saints.movies.rating.helper.RatingHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieHelper movieHelper;
    private final RatingHelper ratingHelper;

    public MovieResponse getMovieById(Long id) {

        return movieRepository.findById(id)
                .map(movieHelper::toSingleResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Pelicula no encontrada con id: " + id));
    }

    public MovieResponse createMovie(MovieRequest request) {
        return movieHelper.toSingleResponse(movieRepository.save(movieHelper.requestToMovie(request)));
    }

    public MovieResponse updateMovie(Long id, MovieRequest request) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pelicula no encontrada con id: " + id));
        Movie movieSaved = movieRepository.save(movieHelper.toUpdateData(movie, request));
        return movieHelper.toSingleResponse(movieSaved);
    }

    public void deleteMovie(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pelicula no encontrada con id: " + id));
        movieRepository.delete(movie);
    }

    public List<MovieResponse> getMovies(String genre, Integer year, String title){
        // conjunción() equivale a WHERE 1=1, siempre verdadero
        // es el punto de partida seguro para encadenar condiciones
        Specification<Movie> specification = ((Specification<Movie>) (root, query, cb) -> cb.conjunction())
                .and(MovieSpecification.hasGenre(genre))
                .and(MovieSpecification.hasTitle(title))
                .and(MovieSpecification.hasYear(year));
        return movieHelper.toListResponse(movieRepository.findAll(specification));
    }

    public List<RankingResponse> getRanking() {
        return movieRepository.findAllByOrderByRatingCountDesc()
                .stream()
                .map(movie -> {
                    BigDecimal averageRating = ratingHelper.calculateAverage(movie.getRatingSum(), movie.getRatingCount());
                    return RankingResponse.builder()
                        .movieId(movie.getId())
                        .title(movie.getTitle())
                        .averageRating(averageRating)
                        .ratingCount(movie.getRatingCount())
                        .message("La película " + movie.getTitle()
                                + " está valorada con "
                                + averageRating
                                + " en nuestro top")
                        .build();
                })
                .toList();
    }


}
