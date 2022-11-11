package com.Assesment_5.Movie_App.service;

import com.Assesment_5.Movie_App.model.Movie;
import com.Assesment_5.Movie_App.repository.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getMovies(){
        List<Movie> result = movieRepository.findAll();
        return result;
    }

    public Movie saveMovie(Movie movie){
        Movie savedMovie = movieRepository.save(movie);
        return savedMovie;
    }

    public String deleteMovies(ObjectId id) {
        movieRepository.deleteById(id);
        return "{\"message\":\"Successfully deleted.\"}";
    }

    public Movie getMovieById(ObjectId id) {
        return movieRepository.findById(id).get();
    }

    public Movie updateMovies(Movie movie) {
        return movieRepository.save(movie);
    }
}