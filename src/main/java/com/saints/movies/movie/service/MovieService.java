package com.saints.movies.movie.service;

import com.saints.movies.movie.dto.MovieRequest;
import com.saints.movies.movie.dto.MovieResponse;
import com.saints.movies.movie.helper.MovieHelper;
import com.saints.movies.movie.model.Movie;
import com.saints.movies.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieHelper movieHelper;

    public MovieService(MovieRepository movieRepository, MovieHelper movieHelper) {
        this.movieRepository = movieRepository;
        this.movieHelper = movieHelper;
    }

    public List<MovieResponse> getAllMovies(){
        return movieHelper.toListResponse(movieRepository.findAll());
    }

    public MovieResponse getMovieById(Long id){

        Optional<Movie> movie = movieRepository.findById(id);
        //cambiar null por excepción
        return movie.map(movieHelper::toSingleResponse).orElse(null);

    }

    public List<MovieResponse> getMoviesByGenre(String genre){
        return movieHelper.toListResponse(movieRepository.findByGenreIgnoreCase(genre));
    }

    public List<MovieResponse> getMoviesByYear(Integer year){
        return movieHelper.toListResponse(movieRepository.findByReleaseYear(year));
    }

    public List<MovieResponse> searchMovies(String title){
        return movieHelper.toListResponse(movieRepository.findByTitleContainingIgnoreCase(title));
    }

    public MovieResponse createMovie(MovieRequest request){
        return movieHelper.toSingleResponse(movieRepository.save(movieHelper.requestToMovie(request)));
    }

    public MovieResponse updateMovie(Long id, MovieRequest request){
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()){

        }


    }


}
