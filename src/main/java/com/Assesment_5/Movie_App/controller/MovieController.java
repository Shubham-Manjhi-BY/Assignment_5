package com.Assesment_5.Movie_App.controller;

import com.Assesment_5.Movie_App.exceptionHandling.MovieException;
import com.Assesment_5.Movie_App.model.Movie;
import com.Assesment_5.Movie_App.service.MovieService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    // ############################################# Save Movie Method #################################################
    @PostMapping(value = "/saveMovie" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public Movie savingAMovie(@RequestBody Movie movie) throws MovieException {
        if(movie.getName()==null)
            throw new MovieException("Missing Movie name.");
        if(movie.getReleaseDate()==null)
            throw new MovieException("Missing Release Date.");
        return movieService.saveMovie(movie);
    }
    // #################################################################################################################


    // ################################################ Get All Movies #################################################
    @GetMapping("/getMovies")
    public List<Movie> GatMovies() {
        return  movieService.getMovies();
    }
    // #################################################################################################################


    // ############################################## Delete Movie With ID #############################################
    @DeleteMapping("/deleteMovie/{id}")
    public String deleteMovie(@PathVariable("id") ObjectId id) throws MovieException {
        try
        {
            movieService.getMovieById(id);
            return  movieService.deleteMovies(id);
        }
        catch (Exception e) {
            throw new MovieException("MOVIE DOESN't EXIST WITH THIS ID");
        }
    }
    // #################################################################################################################


    // ################################################ Get Movie With ID ##############################################
    @GetMapping("/getMovie/{id}")
    public Movie getMovie(@PathVariable("id") ObjectId id) throws MovieException {
        Movie movie = null;
        try
        {
            movie = movieService.getMovieById(id);
            return movie;
        }
        catch (Exception e)
        {
            if (movie == null) throw new MovieException("MOVIE DOESN't EXIST WITH THIS ID");
            else throw new MovieException("Unknown Error.");
        }
    }
    // #################################################################################################################


    // ################################################# Update Movie ##################################################
    @PatchMapping(value = "/updateMovie" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public Movie updateMovie(@RequestBody Movie movie) throws MovieException {
        Movie movie_ = null;
        try
        {
            movie_ = movieService.getMovieById(movie.getId()); // throw exception in case error.
            return movieService.updateMovies(movie);
        }
        catch (Exception e){
            if(movie==null) throw new MovieException("MOVIE DOESN't EXIST WITH THIS ID");
            throw new MovieException(e.getMessage());
        }
    }
    // #################################################################################################################

}